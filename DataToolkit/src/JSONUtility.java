
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

interface JSONFileWriter {
	default boolean exportJsonObjToFolder(JSONObject obj, String folderPath) {
		FileWriter file;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
			file = new FileWriter(folderPath + "\\" + dtf.format(LocalDateTime.now()) + ".txt");
			file.write(obj.toString());
			file.flush();
			file.close();
			System.out.println("File successfully saved at" + folderPath);
		} catch (IOException e) {
			System.out.println("Error, file failed to save at" + folderPath);
			e.printStackTrace();
			return false;
		}
		return true;		
	}
	
	default boolean exportJsonObjToFile(JSONObject obj, String fileSavePath) {
		try {  
			FileWriter file = new FileWriter(fileSavePath);
			file.write(obj.toString());
			file.flush();
			file.close();
			System.out.println("File successfully saved at" + fileSavePath);
		} catch (IOException e) {
			System.out.println("Error, file failed to save at" + fileSavePath);
			e.printStackTrace();
			return false;
		}
		return true;		
	}
}

public class JSONUtility implements JSONFileWriter {
	private static final int INDENT_SPACING = 4;
	
	public JSONUtility() {
		
	}
	
	public static String parseJSONToString(String jsonFilePath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(jsonFilePath)));
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
