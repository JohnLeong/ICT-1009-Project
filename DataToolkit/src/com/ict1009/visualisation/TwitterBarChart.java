package com.ict1009.visualisation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ict1009.utilities.JSONUtility;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class TwitterBarChart extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	static HashMap<String, String> tweets = new HashMap<String, String>();
	static int mon1, tue2, wed3, thur4, fri5, sat6, sun7;
	static int noOfPost = tweets.size();

	public TwitterBarChart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		// post = this.tweet.size();
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Twitter", "", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 500));
		setContentPane(chartPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Creates a dataset of post dates used to populate a chart
	 * 
	 * @return		The populated dataset
	 */
	public CategoryDataset createDataset() {

		final String mon = "Monday";
		final String tue = "Tuesday";
		final String wed = "Wednesday";
		final String thur = "Thursday";
		final String fri = "Friday";
		final String sat = "Saturday";
		final String sun = "Sunday";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (mon1 != 0) {
			dataset.addValue(mon1, mon, "Monday");
		}
		if (tue2 != 0) {
			dataset.addValue(tue2, tue, "Tuesday");
		}
		if (wed3 != 0) {
			dataset.addValue(wed3, wed, "Wednesday");
		}
		if (thur4 != 0) {
			dataset.addValue(thur4, thur, "Thursday");
		}
		if (fri5 != 0) {
			dataset.addValue(fri5, fri, "Friday");
		}
		if (sat6 != 0) {
			dataset.addValue(sat6, sat, "Saturday");
		}
		if (sat6 != 0) {
			dataset.addValue(sun7, sun, "Sunday");
		}

		return dataset;
	}

	/**
	 * Reads a JSON string for a scraped dataset and checks the date of each post
	 * 
	 * @param jFile		The JSON string to read
	 */
	public void ReadingJson(String jFile) {

		String jsonFile = jFile;
		try {
			JSONObject jsonObject = new JSONObject(JSONUtility.parseJSONToString(jsonFile));
			JSONArray records = jsonObject.getJSONArray("details");
			for (int x = 0; x < records.length(); x++) {
				JSONArray extractData = records.getJSONObject(x).getJSONArray("extracted_posts");

				for (int i = 0; i < extractData.length(); i++) {
					String captions = new String(extractData.getJSONObject(i).getString("caption"));
					String postDateTime = new String(extractData.getJSONObject(i).getString("date_time"));
					tweets.put(captions, postDateTime);
					char ch = postDateTime.charAt(0);
					char ch2 = postDateTime.charAt(1);
					if (ch == 'M') {

						mon1 = mon1 + 1;
					} else if (ch == 'T' && ch2 == 'u') {

						tue2 = tue2 + 1;
					} else if (ch == 'W') {

						wed3 = wed3 + 1;
					} else if (ch == 'T' && ch2 == 'h') {

						thur4 = thur4 + 1;
					} else if (ch == 'F') {

						fri5 = fri5 + 1;
					} else if (ch == 'S' && ch2 == 'a') {

						sat6 = sat6 + 1;
					} else {

						sun7 = sun7 + 1;
					}

				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
