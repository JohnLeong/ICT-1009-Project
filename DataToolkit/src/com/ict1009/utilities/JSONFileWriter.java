package com.ict1009.utilities;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONObject;

public interface JSONFileWriter {
	
	/**
	 * Exports the specified JSONObject to the specified folder
	 * 
	 * @param obj			The JSONObject to export
	 * @param folderPath	The folder to export to
	 * @return				Returns true if the export was successful
	 */
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
	
	/**
	 * Exports the specified JSONObject to the specified file path
	 * 
	 * @param obj			The JSONObject to export
	 * @param folderPath	The filepath to export to
	 * @return				Returns true if the export was successful
	 */
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