package com.ict1009.utilities;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCleansing {
	
	/**
	 * For sanitizing string to provide accurate Sentiment Analysis results
	 * @param input		String to be sanitized
	 * @return			Sanitized string
	 */
	public static String dataCleanse(String input) {
		boolean spaceReached = false; 
		//remove unnecessary character
		StringBuilder output= new StringBuilder(input);
		Pattern p = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]");
		Matcher m = p.matcher(output);
		output = new StringBuilder(m.replaceAll(" "));
		//remove multiple spaces
		for (int j = output.length() -1; j >-1; --j) {
			if (output.charAt(j) == ' ') {
				if (spaceReached == true) {
					output.deleteCharAt(j);
				}	
				else {
					spaceReached = true;
				}
			}
			else {
				spaceReached = false;
			}
		}
		return output.toString();	
	}
}
