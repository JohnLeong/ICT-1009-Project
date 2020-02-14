import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataCleansing {

	public static String dataCleanse(String input) {
		boolean spaceReached = false; 
		//remove unnecessary character
		StringBuilder output= new StringBuilder(input);
		Pattern p = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]");
		Matcher m = p.matcher(output);
		output = new StringBuilder(m.replaceAll(" "));
		//remove multiple spaces
		for (int j = output.length() -1; j >-1; --j) {
			if (output.charAt(j) == ' ') {
				if (spaceReached == true) {
					output.deleteCharAt(j);
				}	
				else {
					spaceReached = true;
				}
			}
			else {
				spaceReached = false;
			}
		}
		return output.toString();	
	}
	
	//Old
//	
//	public static JSONObject dataCleanse(JSONObject scrapeData) {
//		JSONArray extractDetails =  scrapeData.getJSONArray("details");
//		
//		for (int k = 0; k < extractDetails.length(); k++) {
//			JSONArray extractData =  extractDetails.getJSONObject(k).getJSONArray("extracted_posts");
//
//			for (int i = 0; i < extractData.length(); i++ ) {
//				StringBuilder postCaption= new StringBuilder(extractData.getJSONObject(i).getString("caption"));
//				
//				boolean spaceReached = false; 
//				//remove unnecessary character
//				Pattern p = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]");
//				Matcher m = p.matcher(postCaption);
//				postCaption = new StringBuilder(m.replaceAll(" "));
//				//remove multiple spaces
//				for (int j = postCaption.length() -1; j >-1; j-- ) {
//					if (postCaption.charAt(j) == ' ') {
//						if (spaceReached == true) {
//							postCaption.deleteCharAt(j);
//						}	
//						else {
//							spaceReached = true;
//						}
//					}
//					else {
//						spaceReached = false;
//					}
//				}
//				//		remove unnecessary character (test)
//				//	    Pattern p = Pattern.compile("[^a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\|,.<>\\/?]");
//				//      Matcher m = p.matcher(postCaption);
//				//      System.out.println(m.replaceAll(" "));
//
//				extractData.getJSONObject(i).put("caption", postCaption.toString());
//			}	
//		}
//		return scrapeData;
//	}
}
