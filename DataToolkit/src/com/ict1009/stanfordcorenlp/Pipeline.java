package com.ict1009.stanfordcorenlp;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.Properties;

public class Pipeline {
	
	private static Properties properties;
	private static String propertiesName = "tokenize";
	private static StanfordCoreNLP stanfordCoreNLP;
	
	
	public static StanfordCoreNLP getPipeline() {
		properties = new Properties();
		properties.setProperty("annotators",  propertiesName);
		if (stanfordCoreNLP == null) {			
			stanfordCoreNLP = new StanfordCoreNLP(properties);
		}
		return stanfordCoreNLP;		
	}
	
	public static StanfordCoreNLP getPipeline(String modes) {
		propertiesName = modes;
		return getPipeline();
		
	}
	
}
