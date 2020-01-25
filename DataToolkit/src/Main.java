
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		InstagramScraper scrapper = new InstagramScraper("https://www.instagram.com/accounts/login/");

		scrapper.launchScrapeProcedure("hehebongesh", "Password12345");
		System.out.println("Done");
		
	}

}
