package com.ict1009.utilities;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

interface FilePrintWriter {
	public static void writeStringToPath(String fullPath, String output) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(fullPath);
		out.println(output);
		out.close();
	}
}

interface FileConverter {
	static void convertJpgToPng(String inputFile, String outputFile) throws IOException {
		BufferedImage bufferedImage = ImageIO.read(new File(inputFile));	
		File file = new File(outputFile);
		ImageIO.write(bufferedImage, "png", file);
	}
}
interface FileDownloader {
	public static void downloadFromURL(String url, String saveAsPath) 
			throws MalformedURLException, IOException {

		BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
		FileOutputStream fileOutputStream = new FileOutputStream(saveAsPath);
		byte dataBuffer[] = new byte[1024];
		int bytesRead;
		while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
			fileOutputStream.write(dataBuffer, 0, bytesRead);
		}
		fileOutputStream.close();
	}
}

public class FileHelper implements FilePrintWriter, FileDownloader, FileConverter {

	public static void writeStringToPath(String path, String unPrettifyJSON) throws FileNotFoundException {
		FilePrintWriter.writeStringToPath(path, unPrettifyJSON);
	}

	/**
	 * 
	 * @param url						URL to get image from
	 * @param saveAsPath				FilePath to save the image file as
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void downloadFromURL(String url, String saveAsPath) 
			throws MalformedURLException, IOException {
		FileDownloader.downloadFromURL(url, saveAsPath);
	}

	/**
	 * 
	 * @param jpgPath			
	 * @param pngSavePath
	 * @throws IOException
	 */
	public static void convertJpgToPng(String jpgPath, String pngSavePath) throws IOException {
		FileConverter.convertJpgToPng(jpgPath, pngSavePath);
	}

	public static boolean deleteFile(String filePath) {
		try {         
			File f= new File(filePath);          
			return f.delete();  
		} catch(Exception e) {  
			e.printStackTrace();
			return false; 
		}		
	}	
}
