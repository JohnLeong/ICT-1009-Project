
public class ScrapeUtility implements JSONFileWriter {
	protected String defaultUrl;	
	protected final String DELIM_HASHTAGS		= "\\s+";
	
	public ScrapeUtility(String defaultURL) {
		this.defaultUrl = defaultURL; 	
	}
	
	/**
	 * Launches the scraping procedure. Looks for posts with the given hashtag and retrieves their data.
	 * @param loginId			Login credentials
	 * @param loginPassword		Login credentials
	 * @param joinedHashTags	A string of appended hashtags seperated by a space
	 * @param numberOfPosts 	Max number of posts details to scrape
	 * @param savePath			Export JSON file path
	 * @return					Returns 0 is the procedure is successful
	 */
	public ReturnCode scrapeByHashTags(final String loginId, final String loginPassword,
			final String joinedHashTags, final long numberOfPosts, final String savePath) {
		return ReturnCode.SUCCESS;
	}
	
	/**
	 * Launches the scraping procedure. Looks for posts with the given hashtag and retrieves their data.
	 * @param loginId			Login credentials
	 * @param loginPassword		Login credentials
	 * @param joinedProfileNames	A string of appended profile names seperated by a space
	 * @param numberOfPosts 	Max number of posts details to scrape
	 * @param savePath			Export JSON file path
	 * @return					Returns 0 is the procedure is successful
	 */
	public ReturnCode scrapeByProfiles(final String loginId, final String loginPassword,
			final String joinedProfileNames, final long numberOfPosts, final String savePath) {
		return ReturnCode.SUCCESS;
	}
	
	/**
	 * 
	 * @param obj		The json object containing the data to write
	 * @param savePath	The file path to save the new file to
	 * @return			Returns true if successful
	 */
//	protected boolean exportJsonObjToFile(JSONObject obj, String savePath) {
//		
//		FileWriter file;
//		try {
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");  
//			file = new FileWriter(savePath + "\\" + dtf.format(LocalDateTime.now()) + ".txt");
//			file.write(obj.toString());
//			file.flush();
//			file.close();
//			System.out.println("File successfully saved at" + savePath);
//		} catch (IOException e) {
//			System.out.println("Error, file failed to save at" + savePath);
//			e.printStackTrace();
//			return false;
//		}
//		return true;		
//	}
}
