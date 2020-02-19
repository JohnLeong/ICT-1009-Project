package com.ict1009.utilities;
import java.io.UnsupportedEncodingException;

public class StringConverter {
	
	/**
	 * Converts a Unicode string to UTF-8
	 * @param input		Unconverted UNICODE String
	 * @return			Converted UTF-8 String
	 */
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
