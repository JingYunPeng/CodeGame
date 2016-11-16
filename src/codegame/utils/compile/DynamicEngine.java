package codegame.utils.compile;
 
import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;

public class DynamicEngine {
    private static DynamicEngine ourInstance = new DynamicEngine();
 
    public static DynamicEngine getInstance() {
        return ourInstance;
    }
    private URLClassLoader parentClassLoader;
    private String classpath;
    private DynamicEngine() {
        this.parentClassLoader = (URLClassLoader) this.getClass().getClassLoader();
        
        this.buildClassPath();
    }
    
   
    private void buildClassPath() {
        this.classpath = null;
        StringBuilder sb = new StringBuilder();
        for (URL url : this.parentClassLoader.getURLs()) {
            String p = url.getFile();
            sb.append(p).append(File.pathSeparator);
        }
        this.classpath = sb.toString();
    }
    
    public Object javaCodeToObject(String fullClassName, String javaCode, StringWriter sw) throws IllegalAccessException, InstantiationException {
        long start = System.currentTimeMillis(); 
        Object instance = null;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); 
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        
        ClassFileManager fileManager = new ClassFileManager(compiler.getStandardFileManager(diagnostics, null, null));
 
        List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
        jfiles.add(new CharSequenceJavaFileObject(fullClassName, javaCode));
 
        List<String> options = new ArrayList<String>();
        options.add("-encoding");
        options.add("UTF-8");
        options.add("-classpath");
        options.add(this.classpath);
 
        //StringWriter sw = new StringWriter();
        JavaCompiler.CompilationTask task = compiler.getTask(sw, fileManager, diagnostics, options, null, jfiles);
        boolean success = task.call();
 
        if (success) {
            JavaClassObject jco = fileManager.getJavaClassObject();
            DynamicClassLoader dynamicClassLoader = new DynamicClassLoader(this.parentClassLoader);
            Class clazz = dynamicClassLoader.loadClass(fullClassName,jco);
            instance = clazz.newInstance();
        } else {
            String error = "";
            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                error = error + compilePrint(diagnostic);
            }
        }
        long end = System.currentTimeMillis();
        return instance;
    }
 
    private String compilePrint(Diagnostic diagnostic) {
        System.out.println("Code:" + diagnostic.getCode());
        System.out.println("Kind:" + diagnostic.getKind());
        System.out.println("Position:" + diagnostic.getPosition());
        System.out.println("Start Position:" + diagnostic.getStartPosition());
        System.out.println("End Position:" + diagnostic.getEndPosition());
        System.out.println("Source:" + diagnostic.getSource());
        System.out.println("Message:" + diagnostic.getMessage(null));
        System.out.println("LineNumber:" + diagnostic.getLineNumber());
        System.out.println("ColumnNumber:" + diagnostic.getColumnNumber());
        StringBuffer res = new StringBuffer();
        res.append("Code:[" + diagnostic.getCode() + "]\n");
        res.append("Kind:[" + diagnostic.getKind() + "]\n");
        res.append("Position:[" + diagnostic.getPosition() + "]\n");
        res.append("Start Position:[" + diagnostic.getStartPosition() + "]\n");
        res.append("End Position:[" + diagnostic.getEndPosition() + "]\n");
        res.append("Source:[" + diagnostic.getSource() + "]\n");
        res.append("Message:[" + diagnostic.getMessage(null) + "]\n");
        res.append("LineNumber:[" + diagnostic.getLineNumber() + "]\n");
        res.append("ColumnNumber:[" + diagnostic.getColumnNumber() + "]\n");
        return res.toString();
    }
}