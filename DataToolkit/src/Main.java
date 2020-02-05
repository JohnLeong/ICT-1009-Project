import java.io.IOException;

import org.json.JSONException;

public class Main {

	
	public static void main(String[] args) {
		

		//GUI
//		FrameDashboard.launchGui(args);
		
		//Usage of OCR here
		OCRUtility ocr = new OCRUtility();
		try {
			ocr.parseJsonAndAppendImageText("C:\\Users\\User\\Desktop\\JSON Exports\\06-02-2020_03-53-27.txt");
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}

}
