import java.util.ArrayList;
//import java.io.FileWriter;
//import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List; 
import java.util.LinkedHashSet;

import org.json.*;

interface PostDetailRetrieving {
	
}
/**
 * @author PatrickNigga
 * @Version 1.0
 */
public class InstagramScraper extends ScrapeUtilityWebDriver implements PostDetailRetrieving {
	private final int TIMEOUT_PAGE_DURA = 20;
	private final int TIMEOUT_ELEMENT_DURA = 10;
	private final int TIMEOUT_VIEW_MORE = 2;  
	/**
	 * All constants named with CSS_SLT are used for selection of WebElements
	 */	
	private final String CSS_SLT_HASHTAG_NO_OF_POSTS 	= "span[class='g47SY ']";
	private final String CSS_SLT_POSTS_SUB_URL 			= ".KC1QD a[href]";

	private final String CSS_SLT_LOGIN_ID 				= ".HmktE input[name='username']";
	private final String CSS_SLT_LOGIN_PASSWORD 		= ".HmktE input[name='password']";
	private final String CSS_SLT_LOGIN_SUBMIT 			= ".HmktE button[type='submit']";
	
	private final String CSS_SLT_DATETIME				= "time[class='_1o9PC Nzb55']";
	private final String CSS_SLT_LOCATION				= "a.O4GlU";
	private final String CSS_SLT_POST_COMMENTS			= "div.C4VMK";
	private final String CSS_SLT_POST_VIEW_MORE			= "button[class='dCJp8 afkep']";
	private final String CSS_SLT_POST_VIDEO_VIEWS		= "span[class='vcOH2'] span"; 
	private final String CSS_SLT_NUM_OF_LIKES			= "button[class='sqdOP yWX7d     _8A5w5    '] span";
	private final String CSS_SLT_POST_BY				= "div.e1e1d a"; 
	
	private final String LOGIN_URL 				= "https://www.instagram.com/accounts/login/";
	
	/**
	 * For storing all sub Urls in given hashtag page.
	 */
	private ArrayList<String> subUrls;

	public InstagramScraper(String defaultURL) {
		super(defaultURL);
	}
	
	/**
	 * Waits for the element which contain the number of likes to appear
	 * @return		Count of likes else -1 if element is not found after waiting.
	 */
	private double getNumberOfLikesInPost() {
		try {
			waitUntilSelectorLoads(CSS_SLT_NUM_OF_LIKES, TIMEOUT_ELEMENT_DURA);
			return Double.parseDouble(super.driver
					.findElement(By.cssSelector(CSS_SLT_NUM_OF_LIKES))
					.getText()
					.replace(",", ""));
		} catch (Exception e) {
			return -1;
		}		
	}
	
	/**
	 * Waits for the element which contain the number of video views
	 * @return 		Count of video views else -1 if the element is not found after waiting.
	 */
	private double getNumberOfViewsInPost() {
		try {
			waitUntilSelectorLoads(CSS_SLT_POST_VIDEO_VIEWS, TIMEOUT_ELEMENT_DURA);
			return Double.parseDouble(super.driver
					.findElement(By.cssSelector(CSS_SLT_POST_VIDEO_VIEWS))
					.getText()
					.replace(",", ""));
		} catch (Exception e) {
			return -1;
		}
	}
	
	private String getDateTimeOfPost() {
		try {
			waitUntilSelectorLoads(CSS_SLT_DATETIME, TIMEOUT_ELEMENT_DURA);
			return super.driver
					.findElement(By.cssSelector(CSS_SLT_DATETIME))
					.getAttribute("datetime");
		} catch (Exception e) {
			return "None";
		}
	}
	
	private String getLocationOfPost() {
		try {
			waitUntilSelectorLoads(CSS_SLT_LOCATION, TIMEOUT_ELEMENT_DURA);
			return super.driver
					.findElement(By.cssSelector(CSS_SLT_LOCATION))
					.getText()
					.replace(",", "");
		} catch (Exception e) {
			return "None";
		}
	}
	
	/**
	 * Waits for the number of posts in hashtag page to load.
	 * Return value be 0 cause else the post wont be in the hashtag page.
	 * @return 		Count of posts for current hashtag page. 
	 */
	private long getNumOfPostsInHashTagPage() {
		WebElement numberOfPosts = super.driver.findElement(By.cssSelector(CSS_SLT_HASHTAG_NO_OF_POSTS));
		return Long.parseLong(numberOfPosts.getText().replace(",", "")); 
	}
	
	
	/**
	 * To get current number of comments that is loaded in post page. 
	 * Used in loadAllComments logic to prevent infinite loop incase element fails to disspear. 
	 * @return		Count of visible comments
	 */
	private int getCurrentCommentsCountInPostPage() {
		try {
			waitUntilSelectorLoads(CSS_SLT_POST_COMMENTS, TIMEOUT_ELEMENT_DURA);
			return super.driver.findElements(By.cssSelector(CSS_SLT_POST_COMMENTS)).size();
		} catch (Exception e) {
			return -1;
		}		
	}
	
	/**
	 * Extracts all posts sub URL in HashTag main page into private variable.
	 * @param threshold		Count of posts to scrape 
	 * @return				True if successfully stored all posts sub URL into variable
	 */
	private boolean extractSubUrlsFromHashTagPage(final long threshold) {
		long hashTagPostCount = this.getNumOfPostsInHashTagPage();
		long stopNumber = threshold < hashTagPostCount ?
				threshold : hashTagPostCount;

		Elements postsDiv;
		String htmlSource = super.driver.getPageSource();
		LinkedHashSet<String> uniqueSubUrls = new LinkedHashSet<String>();;

		if (htmlSource != null && !htmlSource.isEmpty()) {
			do {
				postsDiv = Jsoup.parse(super.driver
						.getPageSource())
						.select(CSS_SLT_POSTS_SUB_URL);
				for (Element e : postsDiv) {
					System.out.println(e.attr("href"));
					uniqueSubUrls.add(e.attr("href"));
				}
				super.scrollToPageBottom();				
			} while (uniqueSubUrls.size() < stopNumber);
		} else {
			return false;
		}

		this.subUrls = new ArrayList<String>(uniqueSubUrls);
		for (; this.subUrls.size() > stopNumber; this.subUrls.remove(this.subUrls.size()-1));
		return true;
	}

	/**
	 * Continuously clicking view more posts button, until the element disappears. 
	 * Maybe should implement a safety check algorithm to escape in case element cant disappear...
	 * Implemented repeated count and logic inside to prevent continuous looping
	 */
	private void showAllCommentsInPost() {
		WebElement viewMoreButton;
		int previousCount, repeatCount = 0;
		while (true) {
			if (repeatCount == 10) { 
				System.out.println("--showAllComments()-- Safety Break after " + repeatCount + " repeated tries of clicking view more.");
				return;
			}
			try {				
				super.waitUntilSelectorLoads(CSS_SLT_POST_VIEW_MORE, TIMEOUT_VIEW_MORE); 
				viewMoreButton = super.driver.findElement(By.cssSelector(CSS_SLT_POST_VIEW_MORE));
				viewMoreButton.click();
				super.forceWaitInMiliseconds(200);				
			} catch (Exception e) {
				break;
			}
			previousCount = getCurrentCommentsCountInPostPage();
			repeatCount = (previousCount == getCurrentCommentsCountInPostPage()) ? 0 : ++repeatCount; 
		}
		System.out.println("--showAllComments()-- Broke from loop after " + TIMEOUT_VIEW_MORE + " seconds.");
	}

	private JSONObject scrapePostDetails(String url) {
		
		do {
			super.browseToUrl(url);			
		} while (!super.driver.getCurrentUrl().equals(url));
			
		JSONObject post = new JSONObject();
		JSONArray comments = new JSONArray();
		
		double likes = this.getNumberOfLikesInPost();	
		
		post.put("posted_by", super.driver.findElement(By.cssSelector(CSS_SLT_POST_BY)).getAttribute("title"));
		post.put("location", getLocationOfPost());
		post.put("date_time", getDateTimeOfPost());
		if (likes > -1) {
			post.put("no_of_likes", likes);
			post.put("no_of_views", 0);
			post.put("type", "image");
		}
		else {
			double numberOfViews = this.getNumberOfViewsInPost();
			post.put("no_of_likes", 0);
			post.put("no_of_views", numberOfViews);
			post.put("type", "video");
		}
		
		List<WebElement> commentElements = null;
		try {
			//Need to handle case for videos as it wont have likes but views.
			waitUntilSelectorLoads(CSS_SLT_POST_COMMENTS, TIMEOUT_ELEMENT_DURA);
			showAllCommentsInPost();		

			/* Will have comment elements as waited for it to load else will return */
			commentElements = super.driver.findElements(By.cssSelector(CSS_SLT_POST_COMMENTS));
			post.put("caption",commentElements.size() > 0 ? 
					commentElements.get(0).findElement(By.tagName("span")).getText() :
					"None"); 

			for (int i = 1; i < commentElements.size(); ++i) {
				JSONObject comment = new JSONObject();
				/* Update below if Instagram changes how their elements are displayed */
				comment.put("user", commentElements.get(i).findElement(By.tagName("a")).getAttribute("title")); 
				comment.put("desc", commentElements.get(i).findElement(By.tagName("span")).getText());
				comments.put(comment);
			}		
			
		} catch (Exception e) {
			System.out.println("--scrapePostDetails-- Unable to load any caption/comment elements");
			return null;
		}
				
		post.put("comments", comments);

		return post;
	}
	
	private boolean loginProcess(final String userId, final String userPassword) {
		try {
			super.browseToUrl(this.LOGIN_URL);
			super.waitUntilSelectorLoads(CSS_SLT_LOGIN_ID, TIMEOUT_ELEMENT_DURA);
			super.waitUntilSelectorLoads(CSS_SLT_LOGIN_PASSWORD, TIMEOUT_ELEMENT_DURA);
			WebElement userName = super.driver.findElement(By.cssSelector(CSS_SLT_LOGIN_ID));
			WebElement password = super.driver.findElement(By.cssSelector(CSS_SLT_LOGIN_PASSWORD));
			WebElement loginButton = super.driver.findElement(By.cssSelector(CSS_SLT_LOGIN_SUBMIT));
			userName.sendKeys(userId);
			password.sendKeys(userPassword);
			loginButton.click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://www.instagram.com/"));
			return true;
		} catch (Exception e) {
			System.out.println("--loginProcess-- Unable to load login page normally.");
			return false;
		}	
		
	}
	
	private boolean redirectAfterLogin(final String redirectUrl) {
		try {
//			super.browseToUrl("https://www.instagram.com/explore/tags/"+hashTag+"/");
			super.browseToUrl(redirectUrl);
			super.waitUntilSelectorLoads(CSS_SLT_POSTS_SUB_URL, TIMEOUT_PAGE_DURA);
			return true;
		} catch (Exception e) {
			System.out.println("--launchScrapeProcedure-- Hashtag page not loading");
			super.driver.quit();
			return false;
//			return ReturnCode.PAGE_TIMEOUT;
		}
		
	}
	/**
	 * Main scraping procedure from Login-> scraping post URLS -> scraping each post details
	 * @param loginId		Login credentials
	 * @param loginPassword	Login credentials
	 * @param hashTag		HashTag keyword 
	 * @param numberOfPosts Max number of posts details to scrape
	 * @param savePath		Export JSON file path
	 * @return 				Look at enum above for better understanding
	 * 	 				
	 */
	
	@Override
	public ReturnCode launchScrapeProcedure(final String loginId, final String loginPassword,
			final String hashTag, final long numberOfPosts, final String savePath) {
		if (!loginProcess(loginId, loginPassword)) { 
			super.driver.quit();
			return ReturnCode.LOGIN_FAIL; 
		} 	
		
		/*  Wait until go back to home page then redirect to hashtag page.	*/ 	
		if (!redirectAfterLogin("https://www.instagram.com/explore/tags/"+hashTag+"/")) {
			super.driver.quit();
			return ReturnCode.PAGE_TIMEOUT;
		}

		/**
		 * Try wait for some element in hashtag page to appear to know that page has loaded.
		 */
		if (extractSubUrlsFromHashTagPage(numberOfPosts)) {
			System.out.println("Size of this.subUrls: " + this.subUrls.size());
		}

		/**
		 * Process all posts and append into JSONObject
		 * Will be used for getting all post url details 
		 */
		JSONObject hashTagPageInfo = new JSONObject();
		JSONArray allPosts = new JSONArray();

		hashTagPageInfo.put("hash_tag", hashTag);
		hashTagPageInfo.put("total_posts", this.getNumOfPostsInHashTagPage());

		for (String s : this.subUrls) {
			allPosts.put(scrapePostDetails("https://www.instagram.com"+ s));
		}
		
		hashTagPageInfo.put("extracted_posts", allPosts);
		System.out.println(hashTagPageInfo);
		
		super.exportJsonObjToFile(hashTagPageInfo, savePath);
		super.driver.quit();
		return ReturnCode.SUCCESS;
	}

	
	@Override
	public String toString() {
		return "InstagramScraper [TIMEOUT_PAGE_DURA=" + TIMEOUT_PAGE_DURA + ", TIMEOUT_ELEMENT_DURA="
				+ TIMEOUT_ELEMENT_DURA + ", TIMEOUT_VIEW_MORE=" + TIMEOUT_VIEW_MORE + ", CSS_SLT_HASHTAG_NO_OF_POSTS="
				+ CSS_SLT_HASHTAG_NO_OF_POSTS + ", CSS_SLT_POSTS_SUB_URL=" + CSS_SLT_POSTS_SUB_URL
				+ ", CSS_SLT_LOGIN_ID=" + CSS_SLT_LOGIN_ID + ", CSS_SLT_LOGIN_PASSWORD=" + CSS_SLT_LOGIN_PASSWORD
				+ ", CSS_SLT_LOGIN_SUBMIT=" + CSS_SLT_LOGIN_SUBMIT + ", CSS_SLT_POST_COMMENTS=" + CSS_SLT_POST_COMMENTS
				+ ", CSS_SLT_POST_VIEW_MORE=" + CSS_SLT_POST_VIEW_MORE + ", CSS_SLT_POST_VIDEO_VIEWS="
				+ CSS_SLT_POST_VIDEO_VIEWS + ", CSS_SLT_NUM_OF_LIKES=" + CSS_SLT_NUM_OF_LIKES + ", CSS_SLT_POST_BY="
				+ CSS_SLT_POST_BY + ", LOGIN_URL=" + LOGIN_URL + ", subUrls=" + subUrls + ", driver=" + driver
				+ ", defaultUrl=" + defaultUrl + "]";
	}



}
