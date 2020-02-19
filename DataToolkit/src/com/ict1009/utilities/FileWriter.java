package com.ict1009.utilities;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileWriter{
	
	/**
	 * Writes the string output to the file at the specified filepath
	 * 
	 * @param fullPath		The path of the file to write to
	 * @param output		The content of the file to write
	 */
	public static void writeStringToPath(String fullPath, String output)  {
		
		PrintWriter out;
		try {
			out = new PrintWriter(fullPath);
			out.println(output);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
