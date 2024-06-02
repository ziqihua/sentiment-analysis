import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Analyzer {
	

	/**
	 * This method calculates the weighted average for each word in all the Sentences.
	 * This method is case-insensitive and all words should be stored in the Map using
	 * only lowercase letters.
	 * 
	 * @param sentences Set containing Sentence objects with words to score
	 * @return Map of each word to its weighted average; or an empty Map if the Set of
	 * Sentences is empty or null.
	 */
	public static Map<String, Double> calculateWordScores(Set<Sentence> sentences) {
		if (sentences == null || sentences.isEmpty()) {
			return new HashMap<>();  // Return an empty map
		}

		Map<String, Integer> wordCounts = new HashMap<>();
		Map<String, Integer> wordScores = new HashMap<>();

		for (Sentence sentence : sentences) {
			if (sentence.getText() == null) continue;

			int score = sentence.getScore();
			StringTokenizer tokenizer = new StringTokenizer(sentence.getText().toLowerCase(), " ");

			while (tokenizer.hasMoreTokens()) {
				String word = tokenizer.nextToken();
				if (!Character.isLetter(word.charAt(0))) continue;  // Ignore tokens that do not start with a letter

				wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
				wordScores.put(word, wordScores.getOrDefault(word, 0) + score);
			}
		}

		Map<String, Double> wordAverages = new HashMap<>();
		for (String word : wordCounts.keySet()) {
			double average = (double) wordScores.get(word) / wordCounts.get(word);
			wordAverages.put(word, average);
		}

		return wordAverages;
	}
	
	/**
	 * This method determines the sentiment of the input sentence using the average of the
	 * scores of the individual words, as stored in the Map.
	 * This method is case-insensitive and all words in the input sentence should be
	 * converted to lowercase before searching for them in the Map.
	 * 
	 * @param wordScores Map of words to their weighted averages
	 * @param sentence Text for which the method calculates the sentiment
	 * @return Weighted average scores of all words in input sentence; or 0 if any error occurs
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
		if (wordScores == null || sentence == null || sentence.isEmpty()) {
			return 0.0;
		}

		StringTokenizer tokenizer = new StringTokenizer(sentence.toLowerCase(), " ");
		double totalScore = 0.0;
		int wordCount = 0;

		while (tokenizer.hasMoreTokens()) {
			String word = tokenizer.nextToken();
			if (!Character.isLetter(word.charAt(0))) continue;  // Ignore tokens that do not start with a letter

			Double wordScore = wordScores.getOrDefault(word, 0.0);
			totalScore += wordScore;
			wordCount++;
		}

		return wordCount > 0 ? totalScore / wordCount : 0.0;
	}
}
