import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataCleansing {
	public static JSONObject dataCleanse(JSONObject scrapeData) {

		JSONArray extractTwitterData =  scrapeData.getJSONArray("extracted_posts");

		for (int i = 0; i < extractTwitterData.length(); i++ ) {
			StringBuilder postCaption= new StringBuilder(extractTwitterData.getJSONObject(i).getString("caption")) ;
			boolean spaceReached = false; 
			//remove unnecessary character
			Pattern p = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]");
			Matcher m = p.matcher(postCaption);
			m.replaceAll(" ");
			//remove multiple spaces
			for (int j = postCaption.length() -1; j >-1; j-- ) {
				if (postCaption.charAt(j) == ' ') {
					if (spaceReached == true) {
						postCaption.deleteCharAt(j);
					}	
					else {
						spaceReached = false;
					}
				}
				else {
					spaceReached = false;
				}

			}
			//		remove unnecessary character (test)
			//	    Pattern p = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]");
			//      Matcher m = p.matcher(postCaption);
			//      System.out.println(m.replaceAll(" "));

			extractTwitterData.getJSONObject(i).put("caption", postCaption.toString());
		}

		JSONArray extractInstagramData =  scrapeData.getJSONArray("posts");

		for (int i = 0; i < extractInstagramData.length(); i++ ) {
			StringBuilder postCaption= new StringBuilder(extractInstagramData.getJSONObject(i).getString("caption")) ;
			boolean spaceReached = false; 
			//remove unnecessary character
			Pattern p = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]");
			Matcher m = p.matcher(postCaption);
			m.replaceAll(" ");
			for (int j = postCaption.length() -1; j >-1; j-- ) {
				if (postCaption.charAt(j) == ' ') {
					if (spaceReached == true) {
						postCaption.deleteCharAt(j);
					}	
					else {
						spaceReached = false;
					}
				}
				else {
					spaceReached = false;
				}

			}

			extractInstagramData.getJSONObject(i).put("caption", postCaption.toString());

			
		}
		return scrapeData;
	}
}
