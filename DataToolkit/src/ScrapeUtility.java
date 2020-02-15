
public class ScrapeUtility implements JSONFileWriter, ReturnCodes {
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
	public ScrapeCode scrapeByHashTags(final String loginId, final String loginPassword,
			final String joinedHashTags, final long numberOfPosts, final String savePath) {
		return ScrapeCode.SUCCESS;
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
	public ScrapeCode scrapeByProfiles(final String loginId, final String loginPassword,
			final String joinedProfileNames, final long numberOfPosts, final String savePath) {
		return ScrapeCode.SUCCESS;
	}
}
