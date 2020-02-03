
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class JSONUtility {
	private static final int INDENT_SPACING = 4;
	
	
	public static String parseJSONToString(String jsonFilePath) {
		try {
			return new String(Files.readAllBytes(Paths.get(jsonFilePath)));
		} catch (IOException e) {
			return "Error";
		}
	}
	
	public static String prettifyJSON(JSONObject jsonObject) {
		return jsonObject.toString(INDENT_SPACING);
	}
	
	public static String prettifyJSON(String jsonString) {
		return (new JSONObject(jsonString)).toString(INDENT_SPACING);
	}
	
	public static String unPrettifyJSON(JSONObject jsonObject) {
		return jsonObject.toString(0);
	}
	
	public static String unPrettifyJSON(String jsonString) {
		return (new JSONObject(jsonString)).toString(0);
	}
}
