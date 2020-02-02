import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		
		FrameDashboard.launchGui(args);
		
		InstagramSentimentAnalyzer instagramAnalysis = new InstagramSentimentAnalyzer();
		HashMap<String, Integer> results = 
				instagramAnalysis.getInstagramSentimentResults("C:\\Users\\User\\Desktop\\EXPORT2.JSON");
		
		System.out.println(results);
	}

}
