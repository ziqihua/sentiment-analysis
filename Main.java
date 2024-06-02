import java.util.Scanner;
import java.util.Set;
import java.util.Map;

/**
 *
 * Main class for the sentiment analysis program.
 *
 */
public class Main {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("no input file");
			return;
		}

		String filename = args[0];
		Set<Sentence> sentences;
		try {
			sentences = Reader.readFile(filename);
		} catch (IllegalArgumentException e) {
			System.out.println("bad input file");
			return;
		}

		Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter a sentence (or 'quit' to exit):");
			String inputSentence = scanner.nextLine();

			if ("quit".equals(inputSentence)) {
				break;
			}

			double sentenceScore = Analyzer.calculateSentenceScore(wordScores, inputSentence);
			System.out.println("The sentiment score for the sentence is: " + sentenceScore);
		}

		scanner.close();
	}
}

