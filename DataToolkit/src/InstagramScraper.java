import java.io.IOException;
import java.util.ArrayList;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List; 
import java.util.LinkedHashSet;

//import org.json.*;

public class InstagramScraper extends ScrapeUtility{
	private final String  	CSS_SLT_HASHTAG_NO_OF_POSTS 	= "span[class='g47SY ']";
	private final String 	CSS_SLT_POSTS_SUB_URL 			= ".KC1QD a[href]";
	
	private final String 	CSS_SLT_LOGIN_ID 				= ".HmktE input[name='username']";
	private final String 	CSS_SLT_LOGIN_PASSWORD 			= ".HmktE input[name='password']";
	private final String 	CSS_SLT_LOGIN_SUBMIT 			= ".HmktE button[type='submit']";
	
	private final String 	LOGIN_URL = "https://www.instagram.com/accounts/login/"; 
	
	private final String 	STRIP_JSON_OUTPUT 		= "<script type=\"text/javascript\">window._sharedData = ";
	private final String 	STRIP_JSON_OUTPUT2	 	= ";</script>";
	
	private ArrayList<String> subUrls;
	
	public InstagramScraper(String url) {
		super(url);
	}
	
	private long getNumberOfPosts() {
		WebElement numberOfPosts = super.driver.findElement(By.cssSelector(CSS_SLT_HASHTAG_NO_OF_POSTS));
		return Long.parseLong(numberOfPosts.getText().replace(",", "")); 
	}
	
	private ArrayList<String> getPostsSubUrlFromPage(long threshold) {
		
		String htmlSource = super.driver.getPageSource();
		long stopNumber = threshold < this.getNumberOfPosts() ? threshold : this.getNumberOfPosts();
		
		if (htmlSource != null && !htmlSource.isEmpty()) {
			LinkedHashSet<String> subUrls = new LinkedHashSet<String>();
			Elements eles;
			do {
				eles = Jsoup.parse(super.driver.getPageSource()).select(CSS_SLT_POSTS_SUB_URL);
				for (Element e : eles) {
					System.out.println(e.attr("href"));
					subUrls.add(e.attr("href"));
				}
				super.scrollToPageBottom();				
			} while (subUrls.size() < stopNumber);
		}
		
		ArrayList<String> retList = new ArrayList<String>(subUrls);
		for (; retList.size() != stopNumber; retList.remove(retList.size()-1));
		return retList;
		
	}
	
	private void extractAllPosts(LinkedHashSet<String> postSubUrls) throws IOException {
		Document doc;
		for (String subUrl : postSubUrls) {
			doc = Jsoup.connect("https://www.instagram.com" + subUrl).get();
		}
	}
	
	public String getJsonStringFromPage() {
		Document doc;
		try {
			doc = Jsoup.connect("https://www.instagram.com/p/B7uB4-9A7O-/").get();
			for (Element e : doc.select("script")) {
				if (e.toString().contains(STRIP_JSON_OUTPUT)) {					
					return e.toString()
							.replace(STRIP_JSON_OUTPUT, "")
							.replace(STRIP_JSON_OUTPUT2, "");
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			
		}
		return "";		
	}
	
	
	public void launchScrapeProcedure(String loginId, String loginPassword) {
		super.browseToUrl(this.LOGIN_URL);
	
//		System.out.println(getJsonStringFromPage());
		
//		JSONObject obj = new JSONObject(getJsonStringFromPage());
		System.out.println(getJsonStringFromPage());
		
		
		//Wait until the user id text box label to load first before getting page source
//		if (super.waitUntilSelectorLoads("._9nyy2") == null) {
//			System.out.println("TimeoUt element not found");
//		}
//		
//		WebElement userName = super.driver.findElement(By.cssSelector(CSS_SLT_LOGIN_ID));
//		WebElement password = super.driver.findElement(By.cssSelector(CSS_SLT_LOGIN_PASSWORD));
//		WebElement loginButton = super.driver.findElement(By.cssSelector(CSS_SLT_LOGIN_SUBMIT));
//		userName.sendKeys(loginId);
//		password.sendKeys(loginPassword);
//		loginButton.click();
//		
//		//Wait until go back to home page
//		new WebDriverWait(driver, 20).until(ExpectedConditions.urlToBe("https://www.instagram.com/"));
	
		
//		super.browseToUrl("https://www.instagram.com/explore/tags/apples/");
//		
//		
//		ArrayList<String> allSubUrls =  getPostsSubUrlFromPage(100);
//		
//		System.out.println("Size of allSubUrls: " + allSubUrls.size());
		
		
		
		/////
//		System.out.println("Number of posts: " + getNumberOfPosts());
		
		
//		super.waitInSeconds(5);
		
		
//		for (int i = 0; i < 10; ++i) {
//			super.scrollToPageBottom();
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		ArrayList<String> allSubUrls = getHashTagPostsSubUrls();
//		System.out.println("SubUrls Count: " + allSubUrls.size());
		
		
		
//		List<WebElement> webElements = super.driver.findElements(By.cssSelector(".KC1QD a[href]"));
//		for (WebElement ele : webElements) { 
//			System.out.println(ele.getAttribute("href"));
//			System.out.println("-----");
//		}
//		
//		System.out.println("Webelements size: " + webElements.size());
//		driver.find
//		Without scroll default is 21 Sub urls detectable.
		//B7v7GfnneNA last
		
		
	}
	
	
	
	
	
	
	
	
}
