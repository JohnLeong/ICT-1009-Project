package com.ict1009.visualisation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ict1009.utilities.JSONUtility;

public class InstagramLineGraph extends JFrame {

	private static final long serialVersionUID = 1L;
	static HashMap<String, Integer> data = new HashMap();
	
	static double positive = 100;
	static double negative = 100;
	static double neutral = 0;
	static int numberOfReplies = 0;

	public InstagramLineGraph(String title) {
		super(title);
		// Create dataset
		DefaultCategoryDataset dataset = createDataset();
		// Create chart
		JFreeChart chart = ChartFactory.createLineChart("Reaction Of Replies", // Chart title
				"Date", // X-Axis Label
				"Percentages", // Y-Axis Label
				dataset);
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private DefaultCategoryDataset createDataset() {

		String happy2 = "Positive";
		String sad2 = "Negative";
		String avgr = "Neutral";
		String date;
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String key : data.keySet()) { // To iterate through the dictionary and push in the value and location to
											// the piechart.
			date = key;
			dataset.addValue(positive, happy2, date);
			dataset.addValue(negative, sad2, date);
			dataset.addValue(neutral, avgr, date);

		}

		return dataset;
	}

	public static char getCharFromString(String str, int index) {
		return str.charAt(index);
	}

	static boolean checkedCharacters(String x, String y) {
		int n = x.length();
		for (int i = 0; i < n; i++)
			if (x.charAt(i) != y.charAt(i))
				return false;
		return true;
	}

	
	public void ReadingJson(String jsonFilePath) {
		String jsonFile = jsonFilePath;
		String[] mood1 = { "sad", "down", "tired", "upset", "cried", "hard", "fake" };
		String[] mood2 = { "happy", "like", "jy", "love", "cheers", "success", "yes", "good", "wow", "funny", "fun",
				"easy", "great", "thank", "cool" };
		try {
			JSONObject jsonObject = new JSONObject(JSONUtility.parseJSONToString(jsonFile));
			JSONArray records = jsonObject.getJSONArray("details");
			String keyToBeChecked = null;
			boolean isKeyPresent;
			int value = 0, count = 0;
			for (int x = 0; x < records.length(); x++) {// details of json object and get json array from extractedposts
				JSONArray extractData = records.getJSONObject(x).getJSONArray("extracted_posts");
				for (int y = 0; y < extractData.length(); y++) {
					JSONObject extracted = extractData.getJSONObject(y);
					String dateTime = extracted.getString("date_time");
					
					JSONArray comment = extractData.getJSONObject(y).getJSONArray("comments");
					for (int i = 0; i < comment.length(); i++) {
						JSONObject commentArray = comment.getJSONObject(i);
						String descript = commentArray.getString("desc");
						for (String m1 : mood1) {
							if (checkedCharacters(m1, descript) == true) {
								this.negative -= 0.1;

							} else {
								this.neutral += 0.002;
							}
							for (String m2 : mood2) {
								if (checkedCharacters(m2, descript) == true) {
									this.positive -= 0.1;
								} else {

									this.neutral += 0.002;
								}
							}
						}
						this.numberOfReplies += 1;
						System.out.println(negative + "\t" + positive + "\t" + neutral);
						
						count = data.containsKey(dateTime) ? data.get(dateTime) : 0;
						data.put(dateTime, count + 1);
						
						
//						if (data.isEmpty() == true) { // This is to check if the dictionary is empty.
//							data.put(dateTime, value + 1);// insert key and value into the dictionary to count number
//															// of times it appears on the same location.
//						} else {// Check if there is same key present on the dictionary.
//							keyToBeChecked = dateTime; // Used this variable to checked for key in the dictionary.
//							isKeyPresent = data.containsKey(keyToBeChecked);
//							if (isKeyPresent == false) { // Insert key and value if there is no same in the dictionary.
//								data.put(keyToBeChecked, value + 1);
//							} else if (isKeyPresent == true) {// If there is existing key in the dictionary store
//																// pervious value in OldValue and replace new value to
//																// increment the count.
//								int oldValue = data.get(keyToBeChecked);
//								data.put(keyToBeChecked, 1 + oldValue);
//							}
//						}

					}

				}

			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		/*
		 * Instagram_LineChart rJson = new Instagram_LineChart("");
		 * rJson.ReadingJson(""); SwingUtilities.invokeLater(() -> { Instagram_LineChart
		 * example = new Instagram_LineChart("Line Chart");
		 * example.setAlwaysOnTop(true); example.pack(); example.setSize(600, 400);
		 * example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 * example.setVisible(true); });
		 */
	}
}