import java.io.UnsupportedEncodingException;

public class StringConverter {

	public static String convertUnicodeToUTF8(String input) {
		try {
			byte[] bytes = input.getBytes("UTF-8");
			return new String(bytes).replace("\\u0026", "&");			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "Error";
		}
	}
}
