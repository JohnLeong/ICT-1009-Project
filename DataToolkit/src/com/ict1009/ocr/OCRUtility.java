package com.ict1009.ocr;
import java.io.File;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ict1009.utilities.DataCleansing;
import com.ict1009.utilities.FileHelper;
import com.ict1009.utilities.JSONUtility;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class OCRUtility {

	private final static String TEMP_JPG_DLD_AS = System.getProperty("user.dir") + "\\temp\\temp.jpg";
	private final static String TEMP_PNG_SAVE_AS = System.getProperty("user.dir") + "\\temp\\temp.png";
	
	
	/**
	 * Conducts OCR on image passed in.
	 * 
	 * @param imagePath				Filepath of where the PNG to be OCRed
	 * @return						OCRed output
	 * @throws TesseractException	Error analyzing image
	 */
	public static String getOCRText(String imagePath) throws TesseractException {
		File imageFile = new File(imagePath);
		Tesseract instance = new Tesseract();
		return instance.doOCR(imageFile);
	}
	
	/**
	 * Download image from url first, converts image from JPG to PNG then
	 * conduct OCR using Tesseract and finally deleting the files. 
	 * 
	 * @param imageUrl 		URl of Instagram Post Image
	 * @return				Image Text of Post Image
	 */
	public static String getOCRTextFromURL(String imageUrl) {
		
		//Use converter to 
		//Use Tesseract to get Text from PNG
		//Finally delete the jpg and png and return text.
		String ocrText = "default_none";
		try {
//			FileHelper.deleteFile(TEMP_DL_IMG_FlDR_PATH);
//			FileHelper.deleteFile(TEMP_IMG_SAVE_PATH);
			FileHelper.downloadFromURL(imageUrl, TEMP_JPG_DLD_AS);
			FileHelper.convertJpgToPng(TEMP_JPG_DLD_AS, TEMP_PNG_SAVE_AS);
			ocrText = getOCRText(TEMP_PNG_SAVE_AS);
		} catch (IOException | TesseractException e) {
			ocrText = "None";
			e.printStackTrace();
		} finally {
			FileHelper.deleteFile(TEMP_JPG_DLD_AS);
			FileHelper.deleteFile(TEMP_PNG_SAVE_AS);
		}
		return ocrText;
	}
	
	/**
	 * Parses JSON file generated by Instagram Scraper for HashTag and Profile modes, 
	 * Downloads every image from imageurl specified in post and conducts OCR and appends 
	 * img_ocr_text field to the post node. 
	 * 
	 * @param jsonFilePath		The file path of the JSON file scraped by InstagramScraper
	 * @throws IOException 		Unable to read bytes from JSON File or get JSONString output
	 * @throws JSONException 	Unable to parse JSON Object
	 */
	public long parseJsonAndAppendImageText(String jsonFilePath) throws JSONException, IOException {
		long numberOfImagesText = 0;
		JSONObject obj = new JSONObject(JSONUtility.parseJSONToString(jsonFilePath));
		
		JSONArray allDetails = obj.getJSONArray("details");
		for (int i = 0; i < allDetails.length(); ++i) {
			JSONArray posts = allDetails.getJSONObject(i).getJSONArray("extracted_posts");
			if (posts.length() == 0) {
				continue;
			} else {				
				for (int j = 0; j < posts.length(); ++j) {
					JSONObject post = posts.getJSONObject(j);
					System.out.println("img_url: " + post.getString("display_image_url"));
					String ocrText = DataCleansing
							.dataCleanse(getOCRTextFromURL(post.getString("display_image_url")));
					post.put("img_ocr_text", ocrText);
					numberOfImagesText += 1;
					System.out.println("Successfully OCRed: " + ocrText);					
				}
			}
		}
		JSONUtility jsonUtility = new JSONUtility();
		jsonUtility.exportJsonObjToFile(obj, jsonFilePath); //Replace the current one.
		
		return numberOfImagesText;
	}
}