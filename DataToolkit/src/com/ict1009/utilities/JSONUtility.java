package com.ict1009.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;


public class JSONUtility implements JSONFileWriter {
	private static final int INDENT_SPACING = 4;
	
	public JSONUtility() {
		
	}
	
	/**
	 * Converts a JSON file at the specified filepath into a string
	 * 
	 * @param jsonFilePath		The filepath of the JSON file
	 * @return
	 * @throws IOException
	 */
	public static String parseJSONToString(String jsonFilePath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(jsonFilePath)));
	}
	
	/**
	 * Formats the JSONObject by indenting each property
	 * 
	 * @param jsonObject	The JSONObject to format
	 * @return				The formated string of the JSONObject
	 */
	public static String prettifyJSON(JSONObject jsonObject) {
		return jsonObject.toString(INDENT_SPACING);
	}
	
	/**
	 * Formats the JSON string by indenting each property
	 * 
	 * @param jsonString	The string to format
	 * @return				The formated string
	 */
	public static String prettifyJSON(String jsonString) {
		return (new JSONObject(jsonString)).toString(INDENT_SPACING);
	}
	
	/**
	 * Converts a JSONObject into a string
	 * 
	 * @param jsonObject	The JSONObject to convert
	 * @return				The converted string
	 */
	public static String unPrettifyJSON(JSONObject jsonObject) {
		return jsonObject.toString(0);
	}
	
	/**
	 * Unprettifies a JSON string by removing all indentation
	 * 
	 * @param jsonString	The JSON string to unprettify
	 * @return				The unprettified string
	 */
	public static String unPrettifyJSON(String jsonString) {
		return (new JSONObject(jsonString)).toString(0);
	}
}
