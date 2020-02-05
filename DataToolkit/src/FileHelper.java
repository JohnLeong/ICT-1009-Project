
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

interface FilePrintWriter {
	public static void writeStringToPath(String fullPath, String output) {
		PrintWriter out;
		try {
			out = new PrintWriter(fullPath);
			out.println(output);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}


interface FileDownloader {
	public static void downloadFromURL(String url, String saveAsPath) {
		try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(saveAsPath)) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			System.out.println(e);
			// handle exception
		}
		//		InputStream in = new URL(FILE_URL).openStream();
		//		Files.copy(in, Paths.get(FILE_NAME), StandardCopyOption.REPLACE_EXISTING);

	}
}
public class FileHelper implements FilePrintWriter, FileDownloader{

	public static void writeStringToPath(String path, String unPrettifyJSON) {
		FilePrintWriter.writeStringToPath(path, unPrettifyJSON);
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
	
	

	//	public static void writeStringToPath(String fullPath, String output)  {
	//		
	//		PrintWriter out;
	//		try {
	//			out = new PrintWriter(fullPath);
	//			out.println(output);
	//		} catch (FileNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//	}
}
