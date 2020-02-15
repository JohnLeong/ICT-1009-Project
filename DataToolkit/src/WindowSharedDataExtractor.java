import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WindowSharedDataExtractor {
	final static String STRIP_JSON_OUTPUT = "<script type=\"text/javascript\">window._sharedData = ";
	final static String STRIP_JSON_OUTPUT2	= ";</script>";
	final static String SOUP_USER_AGENT			= "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
	
	private static String previousUrl = "";
	private static String previousJson = "";
	
	
	static String getWindowSharedDataJson(String postUrl) {
		Document doc;
		if (previousUrl.equals(postUrl) && previousJson != "") {
			return previousJson;
		}
		try {
			previousUrl = postUrl;
			doc = Jsoup.connect(postUrl)
					.userAgent(SOUP_USER_AGENT)
					.timeout(12000) 
					.followRedirects(true)
					.get();
			for (Element e : doc.select("script")) {
				if (e.toString().contains(STRIP_JSON_OUTPUT)) {
					previousJson = e.toString()
							.replace(STRIP_JSON_OUTPUT, "")
							.replace(STRIP_JSON_OUTPUT2, "");
					return previousJson;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return "";	
	}
}
