import java.io.FileWriter;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.io.IOException;

import org.json.JSONObject;

public class ScrapeUtility {
	protected String defaultUrl;	
	
	
	public ScrapeUtility(String defaultURL) {
		this.defaultUrl = defaultURL; 	
	}
	
	/**
	 * Launches the scraping procedure. Looks for posts with the given hashtag and retrieves their data.
	 * @param loginId			Login credentials
	 * @param loginPassword		Login credentials
	 * @param hashTag			Hashtag keyword 
	 * @param numberOfPosts 	Max number of posts details to scrape
	 * @param savePath			Export JSON file path
	 * @return					Returns 0 is the procedure is successful
	 */
	public ReturnCode launchScrapeProcedure(final String loginId, final String loginPassword,
			final String hashTag, final long numberOfPosts, final String savePath) {
		return ReturnCode.SUCCESS;
	}
	
	/**
	 * Launches the scraping procedure. Looks for posts with the given hashtag and related hashtags,
	 *  and retrieves their data.
	 * @param loginId			Login credentials
	 * @param loginPassword		Login credentials
	 * @param hashTag			Hashtag keyword 
	 * @param numberOfPosts 	Max number of posts details to scrape
	 * @param savePath			Export JSON file path
	 * @return					Returns 0 is the procedure is successful
	 */
	public ReturnCode launchScrapeProcedure(final String loginId, final String loginPassword,
			final String hashTag, final long numberOfPosts, final long numberOfRelatedHashtags,final String savePath) {
		return ReturnCode.SUCCESS;
	}
	
	/**
	 * 
	 * @param obj		The json object containing the data to write
	 * @param savePath	The file path to save the new file to
	 * @return			Returns true if successful
	 */
	protected boolean exportJsonObjToFile(JSONObject obj, String savePath) {
		
		FileWriter file;
		try {   
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
			//file = new FileWriter(savePath + "\\apple.txt");
			file = new FileWriter(savePath + "\\" + dtf.format(LocalDateTime.now()) + ".txt");
			file.write(obj.toString());
			file.flush();
			file.close();
			System.out.println("File successfully saved at" + savePath);
		} catch (IOException e) {
			System.out.println("Error, file failed to save at" + savePath);
			e.printStackTrace();
			return false;
		}
		return true;		
	}
}
