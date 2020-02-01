import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SentimentAnalyzer {

	protected static StanfordCoreNLP stanfordCoreNLP;
	
	public SentimentAnalyzer(String modes) {
		stanfordCoreNLP = Pipeline.getPipeline(modes);
	}
	public SentimentAnalyzer() {
		this("tokenize, ssplit, pos, lemma, ner, parse, sentiment");
	}
	
	/**
	 * Uses StanfordCoreNLP models to conduct sentiment analysis to 
	 * determine user's feelings/reaction from his comment of a particular post.
	 * 
	 * @param comment		Comment to analyze
	 * @return				Category of user's feelings (Neutral/Postiive/Negative/Very Negative)
	 */
	public static String commentCategory(String comment) {
		CoreDocument coreDocument = new CoreDocument(comment);
		stanfordCoreNLP.annotate(coreDocument);
		
		HashMap<String, Integer> reaction  = new HashMap<String, Integer>();				
		List<CoreSentence> sentences = coreDocument.sentences();		
		int count; String sentimentCategory;
		
		for (CoreSentence sentence : sentences) {
			sentimentCategory = sentence.sentiment();
			count = reaction.containsKey(sentimentCategory) ? reaction.get(sentimentCategory) : 0;
			reaction.put(sentimentCategory, count + 1);
		}
		
//		System.out.println("for comment: " + comment + " " +  reaction + " " + mostCategory);
		return Collections.max(reaction.entrySet(), Comparator.comparingInt(HashMap.Entry::getValue)).getKey();
	}

	/**
	 * Usage example below
	 */
//	public static void main(String[] args) {
//		
//		StanfordCoreNLP pipeline = Pipeline.getPipeline("tokenize, ssplit, pos, lemma, ner, parse, sentiment");
//		String text = "Hello world. Im love this place. I hate my nigga.";
//		
//		CoreDocument coreDocument = new CoreDocument(text);
//		pipeline.annotate(coreDocument);
//		List<CoreSentence> sentences = coreDocument.sentences();
//		
//		for (CoreSentence sentence : sentences) {
//			String sentiment = sentence.sentiment();
//			System.out.println(sentiment + "\t" + sentence);			
//			
//		}
//		
//		System.out.println("end");
//	}
	
}

