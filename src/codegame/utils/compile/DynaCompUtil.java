package codegame.utils.compile;

public class DynaCompUtil {
	public static String getAnswer(String fullName,String source) throws Exception{
		String result = null;
		try{
			DynamicEngine de = DynamicEngine.getInstance();
	        Object instance =  de.javaCodeToObject(fullName,source,null);
	        result = (String) instance.getClass().getMethod("main").invoke(instance);
		}catch (Exception e) {
			return "err";
		}
		//System.err.println(sw.toString());
        return result;
	}

}
