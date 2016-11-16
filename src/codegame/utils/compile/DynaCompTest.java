package codegame.utils.compile;

import java.io.StringWriter;

public class DynaCompTest
{
    public static void main(String[] args) throws Exception {
        String fullName = "DynaClass";
        StringBuilder src = new StringBuilder();
        src.append("public class DynaClass {\n");
        src.append("    public String toString() {\n");
        src.append("    System.out.println(\"ni niu bi a\");");
        src.append("        return \"Hello, I am \" + ");
        src.append("this.getClass().getSimpleName();\n");
        src.append("    }\n");
        src.append("}\n");
 
        System.out.println(src);
        DynamicEngine de = DynamicEngine.getInstance();
        StringWriter sw = new StringWriter();
        Object instance =  de.javaCodeToObject(fullName,src.toString(),sw);
        sw.close();
        System.out.println(sw);
        System.out.println(instance);
    }
}
