
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ScrapeUtility scrapper = new ScrapeUtility("https://www.instagram.com/explore/tags/apples/");
//		
//		scrapper.browseToUrl();
//		ArrayList<String> subUrls = scrapper.getHashTagPost\sSubUrls();
//		
//		for (String url : subUrls) {
//			System.out.println(url);
//		}
		InstagramScraper scrapper = new InstagramScraper("https://www.instagram.com/accounts/login/");
//		scrapper.browseToUrl
		scrapper.launchScrapeProcedure("https://www.instagram.com/accounts/login/");
		System.out.println("Done");
		
	}

}
