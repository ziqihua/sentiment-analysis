/**
 * @author Chris Murphy
 *
 * This class represents a single sentence from the input file.
 * 
 */


public class Sentence {
	
	/**
	 * The sentiment score for the sentence. Should be in the range [-2, 2]
	 */
	private int score;
	
	/**
	 * The text contained in the sentence. 
	 */
	private String text;
	
	public Sentence(int score, String text) {
		this.score = score;
		this.text = text;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getText() {
		return text;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Sentence sentence = (Sentence) o;

		if (score != sentence.score) return false;
		return text != null ? text.equals(sentence.text) : sentence.text == null;
	}

	@Override
	public int hashCode() {
		int result = score;
		// use a prime number (31) helps to distribute the hash values more evenly
		result = 31 * result + (text != null ? text.hashCode() : 0);
		return result;
	}
	
}
