import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Reader {
	/**
	 * This method reads sentences from the input file, creates a Sentence object
	 * for each, and returns a Set of the Sentences.
	 * 
	 * @param filename Name of the input file to be read
	 * @return Set containing one Sentence object per sentence in the input file
	 * @throws IllegalArgumentException if filename is null
	 */
	public static Set<Sentence> readFile(String filename) {
		if (filename == null) {
			throw new IllegalArgumentException("Filename cannot be null");
		}

		Set<Sentence> sentences = new HashSet<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\\s+", 2); // Split into score and text
				if (parts.length != 2) {
					continue; // Ignore lines that don't have both score and text
				}

				try {
					int score = Integer.parseInt(parts[0]);
					if (score < -2 || score > 2) {
						continue; // Ignore lines with scores outside [-2, 2]
					}
					String text = parts[1];
					sentences.add(new Sentence(score, text));
				} catch (NumberFormatException e) {
					// Ignore lines where the score is not a valid integer
				}
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not open file for reading: " + filename, e);
		}
		return sentences;
	}
}
