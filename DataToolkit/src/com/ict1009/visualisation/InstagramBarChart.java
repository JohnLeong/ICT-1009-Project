package com.ict1009.visualisation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

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

public class InstagramBarChart extends ApplicationFrame {

	static Dictionary<String, String> insta = new Hashtable();
	static int jan1, feb2, march3, april4, may5, jun6, july7, aug8, sept9, oct10, nov11, dec12;

	public InstagramBarChart(String applicationTitle, String chartTitle) {
		super(applicationTitle);
		// System.out.println(insta.size());
		JFreeChart barChart = ChartFactory.createBarChart(chartTitle, "Instagram", "", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(560, 500));
		setContentPane(chartPanel);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public CategoryDataset createDataset() {

		final String jan = "JAN";
		final String feb = "FEB";
		final String march = "MARCH";
		final String april = "APRIL";
		final String may = "MAY";
		final String jun = "JUN";
		final String july = "JULY";
		final String aug = "AUG";
		final String sept = "SEPT";
		final String oct = "OCT";
		final String nov = "NOV";
		final String dec = "DEC";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		if (jan1 != 0) {
			dataset.addValue(jan1, jan, "JAN");
		}
		if (feb2 != 0) {
			dataset.addValue(feb2, feb, "FEB");
		}
		if (march3 != 0) {
			dataset.addValue(march3, march, "MARCH");
		}
		if (april4 != 0) {
			dataset.addValue(april4, april, "APRIL");
		}
		if (may5 != 0) {
			dataset.addValue(may5, may, "MAY");
		}
		if (jun6 != 0) {
			dataset.addValue(jun6, jun, "JUN");
		}
		if (july7 != 0) {
			dataset.addValue(july7, july, "JULY");
		}
		if (aug8 != 0) {
			dataset.addValue(aug8, aug, "AUG");
		}
		if (sept9 != 0) {
			dataset.addValue(sept9, sept, "SEPT");
		}
		if (oct10 != 0) {
			dataset.addValue(oct10, oct, "OCT");
		}
		if (nov11 != 0) {
			dataset.addValue(nov11, nov, "NOV");
		}
		if (dec12 != 0) {
			dataset.addValue(dec12, dec, "DEC");
		}

		return dataset;
	}

	public static char getCharFromIndex(String str, int index) {
		return str.charAt(index);
	}

	public void ReadingJson(String jFile) {

		String jsonFile = jFile;
		try {
			JSONObject jsonObject = new JSONObject(JSONUtility.parseJSONToString(jsonFile));
			JSONArray records = jsonObject.getJSONArray("details");

			for (int x = 0; x < records.length(); x++) {
				// details of json object and get json array from extractedposts
				JSONArray extractData = records.getJSONObject(x).getJSONArray("extracted_posts");

				for (int y = 0; y < extractData.length(); y++) {
					JSONObject extracted = extractData.getJSONObject(y);
					String dateTime = extracted.getString("date_time");
					String posted = extracted.getString("posted_by");
					insta.put(posted, dateTime);
					// System.out.println(posted + dateTime);
					
					char numeric1 = getCharFromIndex(dateTime, 5);
					char numeric2 = getCharFromIndex(dateTime, 6);
					if (numeric1 == '0') {
						if (numeric2 == '1') {
							jan1 += 1;
						} else if (numeric2 == '2') {
							feb2 += 1;
						} else if (numeric2 == '3') {
							march3 += 1;
						} else if (numeric2 == '4') {
							april4 += 1;
						} else if (numeric2 == '5') {
							may5 += 1;
						} else if (numeric2 == '6') {
							jun6 += 1;
						} else if (numeric2 == '7') {
							july7 += 1;
						} else if (numeric2 == '8') {
							aug8 += 1;
						} else if (numeric2 == '9') {
							sept9 += 1;
						}

					} else {
						if (numeric2 == '0') {
							oct10 += 1;
						} else if (numeric2 == '1') {
							nov11 += 1;
						} else {
							dec12 += 1;
						}

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

	public static void main(String[] args) {

		/*
		 * Instagram_BarChart rJson = new Instagram_BarChart(" "," ");
		 * rJson.ReadingJson("jfile"); System.out.println(feb2); Instagram_BarChart
		 * chart = new Instagram_BarChart("Instagram Monthly Base HashTag Statistics",
		 * "Instagram HashTag Statistics"); chart.pack();
		 * RefineryUtilities.centerFrameOnScreen( chart ); chart.setVisible( true );
		 */
	}
}