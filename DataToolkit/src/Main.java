
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScrapeUtility scrapper = new ScrapeUtility("https://www.instagram.com/explore/tags/apples/");
		scrapper.browseToUrl();
		ArrayList<String> subUrls = scrapper.getHashTagPostsSubUrls();
		
		for (String url : subUrls) {
			System.out.println(url);
		}
	}

}
