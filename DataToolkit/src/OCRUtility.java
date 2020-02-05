import java.io.BufferedWriter;
import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRUtility {
	private final static String TESS_DATA_PATH = System.getProperty("user.dir") + "\\tessdata\\";
	
	public static void evaluateImage2(String imagePath) {
		System.out.println(TESS_DATA_PATH);
		File imageFile = new File("C:\\Users\\User\\Desktop\\JSON Exports\\test.jpg");
//	    Tesseract instance = Tesseract.getInstance();
	     Tesseract instance = new Tesseract();

	    try {
	    	
	        String result = instance.doOCR(imageFile);
	        System.out.println("inside");
	        System.out.println(result);

	    } catch (TesseractException ep) {
	        System.err.println(ep.getMessage());
	    }
	    
	}
	
	public static void evaluateImage(String imagePath) {
		File imageFile = new File(imagePath);
		ITesseract instance = new Tesseract();
		instance.setDatapath(TESS_DATA_PATH);
		
		try {
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			System.err.println(e.getMessage());
		}
	}
}
