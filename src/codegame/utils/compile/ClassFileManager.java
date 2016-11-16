package codegame.utils.compile;

import java.io.IOException;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
 
/**
 * @author     : jialin
 * @group      : THS_JAVA_PLATFORM
 * @Date       : 2014-10-25 上午10:59:34
 * @Comments   : 类文件管理器
 * @Version    : 1.0.0
 */
public class ClassFileManager extends
        ForwardingJavaFileManager {
    public JavaClassObject getJavaClassObject() {
        return jclassObject;
    }
 
    private JavaClassObject jclassObject;
 
 
    public ClassFileManager(StandardJavaFileManager
        standardManager) {
        super(standardManager);
    }
 
 
    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
        String className, JavaFileObject.Kind kind, FileObject sibling)
            throws IOException {
            jclassObject = new JavaClassObject(className, kind);
        return jclassObject;
    }
}