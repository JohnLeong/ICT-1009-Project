
import java.io.FileNotFoundException;
import java.io.PrintWriter;




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
