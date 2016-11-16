package codegame.utils.compile;

import java.net.URI;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

/**
 * @author : jialin
 * @group : THS_JAVA_PLATFORM
 * @Date : 2014-10-25 涓婂�?10:57:54
 * @Comments : 鐢ㄤ簬浼犻�掓簮绋嬪�?
 * @Version : 1.0.0
 */
public class CharSequenceJavaFileObject extends SimpleJavaFileObject {

	private CharSequence content;

	public CharSequenceJavaFileObject(String className, CharSequence content) {
		super(URI.create("string:///" + className.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension),
				JavaFileObject.Kind.SOURCE);
		this.content = content;
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return content;
	}
}