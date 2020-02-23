package com.ict1009.scrapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.Cookie;

public class JsoupUtility {


	final static int TIMEOUT_DURATION = 12000; 
	
	final static String STRIP_JSON_OUTPUT 	= "<script type=\"text/javascript\">window._sharedData = ";
	final static String STRIP_END_TAG	= ";</script>";
	final static String SOUP_USER_AGENT		= "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";

	private final static String URL_HOME_INSTAGRAM = "https://www.instagram.com"; 
	private final static String STRIP_ADD_JSON1 = "<script type=\"text/javascript\">window.__additionalDataLoaded('";
	
	private static String previousUrl = "";
	private static String previousJson = "";

	private static String previousAddUrl = "";
	private static String previousAddJson = "";
	private static Map<String,String> browserCookies;

	/**
	 * Refreshes cookies
	 */
	public JsoupUtility() {
		browserCookies = new HashMap<String,String>();
	}

	public void insertCookies(Set<Cookie> cookies) {
		for (Cookie c : cookies) {
			browserCookies.put(c.getName(), c.getValue());
		}
	}

	/**
	 * Used for retrieving _windowSharedData field from Script tag in page.
	 * @param postUrl		URL of Instagram post
	 * @return				JSON string with details of the posts
	 */
	public static String getWindowSharedDataJson(String postUrl) {
		Document doc;
		int maxTries = 5, count = 0;
		while (true) {
			if (previousUrl.equals(postUrl) && !previousJson.contentEquals("")) {
				return previousJson;
			}
			try {
				previousUrl = postUrl;
				doc = Jsoup.connect(postUrl)
						.userAgent(SOUP_USER_AGENT)
						.cookies(browserCookies)
						.timeout(TIMEOUT_DURATION) 
						.followRedirects(true)
						.get();
				for (Element e : doc.select("script")) {
					if (e.toString().contains(STRIP_JSON_OUTPUT)) {
						previousJson = e.toString()
								.replace(STRIP_JSON_OUTPUT, "")
								.replace(STRIP_END_TAG, "");
						System.out.println(previousJson);
						return previousJson;
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				if (++count == maxTries) return "";
			}
		}
	}

	public static String getAditonalWindowSharedDataJson(String postUrl) {
		Document doc;
		int maxTries = 5, count = 0;
		while (true) {
			if (previousAddUrl.equals(postUrl) && !previousAddJson.contentEquals("")) {
				return previousAddJson;
			}
			try {
				String subUrl = postUrl.replace(URL_HOME_INSTAGRAM, "");
				String toTrim = STRIP_ADD_JSON1 + subUrl  + "',";
				previousAddUrl = postUrl;
				doc = Jsoup.connect(postUrl)
						.userAgent(SOUP_USER_AGENT)
						.cookies(browserCookies)
						.timeout(TIMEOUT_DURATION) 
						.followRedirects(true)
						.get();

				for (Element e : doc.select("script")) {
					if (e.toString().contains(toTrim)) {
						previousAddJson = e.toString()
								.replace(toTrim, "")
								.replace(STRIP_END_TAG, "");
						previousAddJson = previousAddJson.substring(0, previousAddJson.length()-1);
						System.out.println(previousAddJson);
						return previousAddJson;
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
				if (++count == maxTries) return "";
			}
		}
	
	}

}
