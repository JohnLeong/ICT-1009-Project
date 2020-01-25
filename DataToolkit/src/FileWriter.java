import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.StandardOpenOption;



public class FileWriter{
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
