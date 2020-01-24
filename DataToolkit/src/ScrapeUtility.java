//import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.ArrayList;

public class ScrapeUtility {
	
	WebDriver driver;
	protected String defaultUrl;	
	final private static String GECKO_DRIVER_PATH = System.getProperty("user.dir") + "\\geckodriver.exe";

	public ScrapeUtility(String url) {
		System.setProperty("webdriver.gecko.driver",GECKO_DRIVER_PATH);
		this.defaultUrl = url; 	
		this.driver= new FirefoxDriver();
	}
		
//	Move to instagram scraper class ltr
	protected ArrayList<String> getHashTagPostsSubUrls() {
		ArrayList<String> subUrls = new ArrayList<String>();	
		String doc = this.driver.getPageSource();
		if (doc != null && !doc.isEmpty()) {
			Elements postsLink = Jsoup.parse(doc).select(".KC1QD").select("a[href]");
			for (Element ele : postsLink) {
				
				System.out.println(ele.attr("href"));
			}
		} 
		return subUrls;
	}
	
	public void browseToUrl(String url) {
		this.driver.get(url);
	}
	public void browseToUrl() {
		this.driver.get(this.defaultUrl);
	}


		
}
	
	
	
	
	
	
	

