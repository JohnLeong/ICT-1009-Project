
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
}
