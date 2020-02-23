package com.ict1009.scrapper;
import java.util.ArrayList;
//import java.io.FileWriter;
//import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ict1009.userinterface.FrameDashboard;
import com.ict1009.utilities.DataCleansing;
import com.ict1009.utilities.StringConverter;
import com.ict1009.utilities.TimeStampConverter;

import java.util.List; 
import java.util.LinkedHashSet;

import org.json.*;



/**
 * @author PatrickNigga
 */
public class InstagramScraper extends ScrapeUtilityWebDriver implements InstagramDryScraping {
	private final int TIMEOUT_PAGE_DURA = 20;
	private final int TIMEOUT_ELEMENT_DURA = 10;
	private final int TIMEOUT_VIEW_MORE = 1;  

	private final int TIMEOUT_PAGE_LOAD = 3;
	/**
	 * All constants named with CSS_SLT are used for selection of WebElements
	 */	
	private final String CSS_PAGE_DETAILS 			= "span[class='g47SY ']";  

	private final String CSS_HASHTAG_POSTS_SUB_URL 	= ".KC1QD a[href]"; // Article to link

	private final String CSS_PROFILE_POSTS_SUB_URL 	= ".ySN3v a[href]"; // Article to link
	private final String CSS_PROFILE_DESCRIPTION	= "div[class='-vDIg']";

	private final String CSS_LOGIN_ID 				= ".HmktE input[name='username']";
	private final String CSS_LOGIN_PASSWORD 		= ".HmktE input[name='password']";
	private final String CSS_LOGIN_SUBMIT 			= ".HmktE button[type='submit']";

	private final String CSS_POST_DATETIME			= "time[class='_1o9PC Nzb55']";
	private final String CSS_POST_LOCATION			= "a.O4GlU";
	private final String CSS_POST_COMMENTS			= "div.C4VMK";
	private final String CSS_POST_VIEW_MORE			= "button[class='dCJp8 afkep']";
	private final String CSS_POST_VIDEO_VIEWS		= "span[class='vcOH2'] span"; 
	private final String CSS_POST_NUM_OF_LIKES		= "button[class='sqdOP yWX7d     _8A5w5    '] span";
	private final String CSS_POST_POSTED_BY			= "div.e1e1d a"; 

	private final String CSS_VALID_PAGE_DIV			= "div[id='react-root']";
	private final String CSS_PRIVATE				= ".rkEop";
	private final String LOGIN_URL 					= "https://www.instagram.com/accounts/login/";

	private final String DELIM_PROFILES_NAME		= "\\s+";
	
	
	public InstagramScraper(String defaultURL) {
		super(defaultURL);
	}
	
	/**
	 * Checks if page has element which will be present in a valid HashTag page
	 * @param hashTagUrl	URL of hashtag page
	 * @return				HashTag page is Valid and available
	 */
	private boolean hashTagPageIsAvailable(String hashTagUrl) {
		if (!hashTagUrl.equals(super.driver.getCurrentUrl())) { super.browseToUrl(hashTagUrl); }
		WebElement e = super.safeGetWebElement(CSS_VALID_PAGE_DIV, TIMEOUT_PAGE_LOAD);
		return e != null;
	}

	/**
	 * Checks if the page has element which is present in ProfileURL. If page doesnt have that element,
	 * its an indicator that the profile is either invalid or private
	 * @param profileUrl	URL of user profile
	 * @return				Profile is valid and available
	 */
	private boolean profileIsAvailable(String profileUrl) {
		if (!profileUrl.equals(super.driver.getCurrentUrl())) { super.browseToUrl(profileUrl); }
		//		if (super.driver.getCurrentUrl() != profileUrl) { super.browseToUrl(profileUrl); }
		WebElement e = super.safeGetWebElement(CSS_VALID_PAGE_DIV, TIMEOUT_PAGE_LOAD);
		return e != null;
	}

	/**
	 * Checks if the profile page contains an element which determines that the
	 * user profile page is private
	 * @param profileUrl	URL of user profile
	 * @return				Whether user profile is private
	 */
	private boolean profileIsPrivate(String profileUrl) {
		System.out.println("profileUrl: " + profileUrl);
		System.out.println("CurrentUrl: " + super.driver.getCurrentUrl());
		//		if (super.driver.getCurrentUrl() != profileUrl) { super.browseToUrl(profileUrl); }
		return (profileIsAvailable(profileUrl) && cssSelectorExists(CSS_PRIVATE));
	}


	private double getNumberOfLikesInPost() {
		WebElement e = super.safeGetWebElement(CSS_POST_NUM_OF_LIKES, TIMEOUT_ELEMENT_DURA);
		return e != null ? Double.parseDouble(e.getText().replace(",", "")) : -1;		
	}

	private double getNumberOfViewsInPost() {
		WebElement e = super.safeGetWebElement(CSS_POST_VIDEO_VIEWS, TIMEOUT_ELEMENT_DURA);
		return e != null ? Double.parseDouble(e.getText().replace(",", "")) : -1;
	}

	private String getDateTimeOfPost() {
		WebElement e = super.safeGetWebElement(CSS_POST_DATETIME, TIMEOUT_ELEMENT_DURA);
		return e != null ? e.getAttribute("datetime") : "None";
	}

	private String getLocationOfPost() {
		WebElement e = super.safeGetWebElement(CSS_POST_LOCATION, TIMEOUT_ELEMENT_DURA);
		return e != null ? e.getText().replace(",",  "") : "None";
	}

	private long getNumOfPostsInPage() {
		WebElement element = super.driver.findElement(By.cssSelector(CSS_PAGE_DETAILS));
		return Long.parseLong(element.getText().replace(",", "")); 
	}

	private long getNumOfPostsInProfilePage() {
		List<WebElement> details = super.safeGetWebElements(CSS_PAGE_DETAILS, TIMEOUT_ELEMENT_DURA);
		System.out.println("Details size" + details.size());
		return details != null ? Long.parseLong(details.get(0).getText().replace(",", "")) : -1;
	}

	private long getNumOfFollowersInProfilePage() {
		List<WebElement> details = super.safeGetWebElements(CSS_PAGE_DETAILS, TIMEOUT_ELEMENT_DURA);
		return details != null ? Long.parseLong(details.get(1).getAttribute("title").replace(",", "")) : -1;
	}

	private long getNumOfFollowingInProfilePage() {
		List<WebElement> details = super.safeGetWebElements(CSS_PAGE_DETAILS, TIMEOUT_ELEMENT_DURA);
		return details != null ? Long.parseLong(details.get(2).getText().replace(",", "")) : -1;
	}

	private String getProfileDescription() {
		WebElement e = super.safeGetWebElement(CSS_PROFILE_DESCRIPTION, TIMEOUT_ELEMENT_DURA);
		return e != null ? e.getText() : "None";
	}

	/**
	 * To get current number of comments that is loaded in post page. 
	 * Used in loadAllComments logic to prevent infinite loop incase element fails to disspear. 
	 * @return		Count of visible comments
	 */
	private int getCurrentCommentsCountInPostPage() {
		List<WebElement> elements = super.safeGetWebElements(CSS_POST_COMMENTS, 2);
		return elements != null ? elements.size() : -1;
	}

	/**
	 * Dry scraping location field from instagram returns info as a String JSON.
	 * This function is to extract the location name from it.
	 * @param jsonLocation	JSON String extracted from InstagramDryScraping 	
	 * @return				Location name of post
	 */
	private String getLocationNameFromJson(String jsonLocation) {
		try {
			JSONObject obj = new JSONObject(jsonLocation);
			return obj.getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
			return "None";
		}
		
	}
	
	/**
	 * Extracts all posts sub URL current page by scrolling down the page and
	 * appendign the suburls retrieved into a hashmap to ensure that they are unique
	 * and converted to ArrayList before returning. 
	 * @param threshold		Count of posts to scrape 
	 * @return				ArrayList of SubUrls
	 */
	private ArrayList<String> getPostsSubUrl(final long threshold, final String cssQuery) {
		ArrayList<String> subUrls = new ArrayList<String>();
		long hashTagPostCount = this.getNumOfPostsInPage();
		long stopNumber = threshold < hashTagPostCount ? threshold : hashTagPostCount;

		Elements postsDiv; String htmlSource = super.driver.getPageSource();
		LinkedHashSet<String> uniqueSubUrls = new LinkedHashSet<String>();;

		if (htmlSource != null && !htmlSource.isEmpty()) {
			do {
				postsDiv = Jsoup.parse(super.driver
						.getPageSource())
						.select(cssQuery);
				for (Element e : postsDiv) {
					System.out.println(e.attr("href"));
					uniqueSubUrls.add(e.attr("href"));
				}
				super.scrollToPageBottom();				
			} while (uniqueSubUrls.size() < stopNumber);
		} else {
			return null;
		}

		subUrls = new ArrayList<String>(uniqueSubUrls);
		for (; subUrls.size() > stopNumber; subUrls.remove(subUrls.size()-1));
		return subUrls;
	}


	private ArrayList<String> getHashTagPostsSubUrl(final long threshold) {
		return getPostsSubUrl(threshold, CSS_HASHTAG_POSTS_SUB_URL);
	}

	private ArrayList<String> getProfilePostSubUrl(final long threshold) {
		return getPostsSubUrl(threshold, CSS_PROFILE_POSTS_SUB_URL);
	}


	/**
	 * Continuously clicks on view more post element, until the element disappears.
	 * Once element is not found, it means that all comments in the post has been loaded.
	 */
	private void showAllCommentsInPost() {
		WebElement viewMoreButton;
		int previousCount, repeatCount = 0;
		while (true) {
			System.out.println("While loop.");
			if (repeatCount == 10) { 
				System.out.println("--showAllComments()-- Safety Break after " 
						+ repeatCount + " repeated tries of clicking view more.");
				return;
			}
			try {
				System.out.println("Waiting for view more");
				super.waitUntilSelectorLoads(CSS_POST_VIEW_MORE, TIMEOUT_VIEW_MORE); 
				viewMoreButton = super.driver.findElement(By.cssSelector(CSS_POST_VIEW_MORE));
				viewMoreButton.click();
				super.forceWaitInMiliseconds(200);				
			} catch (Exception e) {
				break;
			}
			previousCount = getCurrentCommentsCountInPostPage();
			repeatCount += (previousCount == getCurrentCommentsCountInPostPage()) ? 1 : 0; 
		}
		System.out.println("--showAllComments()-- Broke from loop after " + TIMEOUT_VIEW_MORE + " seconds.");
	}

	/**
	 * Scrapes Post Details.
	 * @param url	PostUrl
	 * @return		JSONObject containing post details
	 */
	private JSONObject scrapePostDetails(String url) {

		do {
			super.browseToUrl(url);
		} while (!super.driver.getCurrentUrl().equals(url));

		JSONObject post = new JSONObject();
		JSONArray comments = new JSONArray();

		double likes = this.getNumberOfLikesInPost();	
		
		post.put("display_image_url", InstagramDryScraping.super.getDisplayImageUrl(url));
		post.put("posted_by", super.driver.findElement(By.cssSelector(CSS_POST_POSTED_BY))
				.getAttribute("title"));
		post.put("no_of_comments", InstagramDryScraping.super.getNumberOfComments(url));
		post.put("location", getLocationOfPost());
		post.put("date_time", getDateTimeOfPost());
		
		System.out.println("Putting number of likes");
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

		/* Will go to exception if dont have any comments of caption */
		try {
			//Need to handle case for videos as it wont have likes but views.
			System.out.println("Wait until comment selector to load");
			waitUntilSelectorLoads(CSS_POST_COMMENTS, TIMEOUT_ELEMENT_DURA);
			System.out.println("Finished waiting for post comments to load.");
			showAllCommentsInPost();		
			

			/* Will have comment elements as waited for it to load else will return */
			List<WebElement> commentElements = super.driver
					.findElements(By.cssSelector(CSS_POST_COMMENTS));

			post.put("caption",commentElements.size() > 0 ? 
					DataCleansing
					.dataCleanse(commentElements
							.get(0)
							.findElement(By.tagName("span"))
							.getText()
							) :
					"None"); 

			for (int i = 1; i < commentElements.size(); ++i) {
				JSONObject comment = new JSONObject();
				/* Update below if Instagram changes how their elements are displayed */
				comment.put("user", commentElements.get(i).findElement(By.tagName("a")).getAttribute("title")); 
				comment.put("desc", DataCleansing.dataCleanse(StringConverter
						.convertUnicodeToUTF8(commentElements.get(i)
								.findElement(By.tagName("span")).getText())));
				comments.put(comment);
			}		
			post.put("comments", comments);

		} catch (Exception e) {
			post.put("comments", "None");
			System.out.println("--scrapePostDetails-- Unable to load any caption/comment elements");
			return null;
		}
		return post;
	}
	
	/**
	 * Pilots selenium to do the login process
	 * @param userId		User Login ID
	 * @param userPassword	User Password
	 * @return				Login successfully 
	 */
	private boolean loginProcess(final String userId, final String userPassword) {
		try {
			super.browseToUrl(this.LOGIN_URL);
			super.waitUntilSelectorLoads(CSS_LOGIN_ID, TIMEOUT_ELEMENT_DURA);
			super.waitUntilSelectorLoads(CSS_LOGIN_PASSWORD, TIMEOUT_ELEMENT_DURA);
			WebElement userName = super.driver.findElement(By.cssSelector(CSS_LOGIN_ID));
			WebElement password = super.driver.findElement(By.cssSelector(CSS_LOGIN_PASSWORD));
			WebElement loginButton = super.driver.findElement(By.cssSelector(CSS_LOGIN_SUBMIT));
			userName.sendKeys(userId);
			password.sendKeys(userPassword);
			loginButton.click();
			new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://www.instagram.com/"));
			return true;
		} catch (NoSuchElementException e) {
			System.out.println("--loginProcess-- Unable to find some login elements.");
			return false;
		} catch (Exception e) {
			System.out.println("--loginProcess-- Unable to load login page normally.");
			return false;
		}	

	}
	
	/**
	 * Checks if Selenium has been redirected to the specific url
	 * @param redirectUrl	Redirected URL
	 * @return				Successful or not
	 */
	private boolean redirectAfterLogin(final String redirectUrl) {
		try {
			super.browseToUrl(redirectUrl);
			super.waitUntilSelectorLoads(CSS_VALID_PAGE_DIV, TIMEOUT_PAGE_DURA);
			return true;
		} catch (Exception e) {
			System.out.println("--launchScrapeProcedure-- Redirect Error.");
			super.driver.quit();
			return false;
		}

	}

	
	@Override
	public ScrapeCode scrapeByHashTags(final String loginId, final String loginPassword,
			final String joinedHashTags, final long numberOfPosts, final String savePath) {

		if (!loginProcess(loginId, loginPassword)) { 
			super.driver.quit();
			return ScrapeCode.LOGIN_FAIL; 
		} 	

		/* Redirect to homepage first to ensure that success is definitely successful. */
		if (!redirectAfterLogin("https://www.instagram.com/")) {
			super.driver.quit();
			return ScrapeCode.PAGE_TIMEOUT;
		}
		
		JsoupUtility extractor = new JsoupUtility();
		extractor.insertCookies(super.driver.manage().getCookies());
		

		String[] hashTags = joinedHashTags.split(DELIM_HASHTAGS);
		JSONArray allHashTagsDetails = new JSONArray();

		for (int i = 0; i < hashTags.length; ++i) {
			FrameDashboard.appendInstagramConsole("*Processing " + hashTags[i]+ "\n");
			JSONObject hashTagPageDetails = new JSONObject();
			String hashTagUrl = "https://www.instagram.com/explore/tags/"+hashTags[i]+"/";
			FrameDashboard.appendInstagramConsole("*Redirecting to " + hashTagUrl + "\n");
			//Redirect to each hashtag page then scrape all Suburls there and parse accordingly
			super.browseToUrl(hashTagUrl);
			if (!redirectAfterLogin(hashTagUrl)) {
				super.driver.quit();
				return ScrapeCode.PAGE_TIMEOUT;
			}

			if (!hashTagPageIsAvailable(hashTagUrl)) {
				hashTagPageDetails.put("hash_tag_is_valid", "invalid");
				continue;
			} else {
				hashTagPageDetails.put("hash_tag_is_valid", "valid");
			}

			hashTagPageDetails.put("hash_tag", hashTags[i]);
			hashTagPageDetails.put("total_posts", this.getNumOfPostsInPage());

			ArrayList<String> hashTagPostsSubUrl = getHashTagPostsSubUrl(numberOfPosts);
			JSONArray posts = new JSONArray();

			for (int j = 0; j < hashTagPostsSubUrl.size(); ++j) {
				String postUrl = "https://www.instagram.com" + hashTagPostsSubUrl.get(j);
				System.out.println("*Scraping posts in:" + postUrl);
				posts.put(scrapePostDetails(postUrl));
			}			

			hashTagPageDetails.put("extracted_posts", posts);
			allHashTagsDetails.put(hashTagPageDetails);			
		}

		JSONObject results = new JSONObject();
		results.put("platform", "instagram");
		results.put("scrape_mode", "hashtags");
		results.put("details", allHashTagsDetails);

		super.exportJsonObjToFolder(results, savePath);
		super.driver.quit();

		return ScrapeCode.SUCCESS;
	}

	
	@Override
	public ScrapeCode scrapeByProfiles(final String loginId, final String loginPassword,
			final String joinedProfileNames, final long numberOfPosts, final String savePath) {
		if (!loginProcess(loginId, loginPassword)) { 
			super.driver.quit();
			return ScrapeCode.LOGIN_FAIL; 
		} 	

		/* Redirect to homepage first to ensure that success is definitely successful. */
		if (!redirectAfterLogin("https://www.instagram.com/")) {
			super.driver.quit();
			return ScrapeCode.PAGE_TIMEOUT;
		}		
		
		JsoupUtility extractor = new JsoupUtility();
		extractor.insertCookies(super.driver.manage().getCookies());
		
		
		/* Iterate all the list of profiles and append into JSON. */
		JSONArray profiles = new JSONArray(); 

		String[] profileNames = joinedProfileNames.split(DELIM_PROFILES_NAME);
		String postLink;
		List<String> profilePostsSubUrl;
		
		for (int i = 0; i < profileNames.length; ++i) {
			JSONArray posts = new JSONArray();
			JSONObject profile = new JSONObject();
			String profileUrl = "https://www.instagram.com/" + profileNames[i] + "/";

			super.browseToUrl(profileUrl);			
			if (!profileIsAvailable(profileUrl)) {
				System.out.println("profile unavailable");
				profile.put("profile_is_viewable", "unavailable");
				continue;
			} else if (profileIsPrivate(profileUrl)){
				System.out.println("profile_is_private");
				profile.put("profile_is_viewable", "private");
				continue;
			} else {
				System.out.println("profile viewable");
				profile.put("profile_is_viewable", "viewable");
			}

			profile.put("profile_name", profileNames[i]);
			profile.put("profile_description", DataCleansing.dataCleanse(StringConverter.
					convertUnicodeToUTF8(getProfileDescription())));
			profile.put("no_of_posts", getNumOfPostsInProfilePage());
			profile.put("no_of_followers", getNumOfFollowersInProfilePage());
			profile.put("no_of_following", getNumOfFollowingInProfilePage());

			System.out.println("BeforeGetSuburls");
			profilePostsSubUrl = getProfilePostSubUrl(numberOfPosts);

			for (String subUrl : profilePostsSubUrl) {
				JSONObject post  = new JSONObject();
				postLink = "https://www.instagram.com" + subUrl;
				post.put("post_url", postLink);
				post.put("display_image_url", 
						StringConverter.convertUnicodeToUTF8(
								InstagramDryScraping.super.getDisplayImageUrl(postLink))
						);
				
				post.put("caption", InstagramDryScraping.super.getPostCaption(postLink));
				post.put("no_of_likes", InstagramDryScraping.super.getNumberOfLikes(postLink));
				post.put("no_of_comments", InstagramDryScraping.super.getNumberOfComments(postLink));
				post.put("is_video", InstagramDryScraping.super.getIsVideo(postLink));
				post.put("no_of_video_views", InstagramDryScraping.super.getNumberOfVideoViews(postLink));
				post.put("date_time", TimeStampConverter.timeStampToDate(InstagramDryScraping.super.getPostTimeStamp(postLink)));
				post.put("location", this.getLocationNameFromJson(InstagramDryScraping.super.getPostLocation(postLink)));
				posts.put(post);
			}
			profile.put("extracted_posts", posts);
			profiles.put(profile);
		}

		JSONObject results = new JSONObject();
		results.put("platform", "instagram");
		results.put("scrape_mode", "profiles");
		results.put("details", profiles);

		super.exportJsonObjToFolder(results, savePath);
		super.driver.quit();

		System.out.print("Successfully finish");		
		return ScrapeCode.SUCCESS;
	}


}
