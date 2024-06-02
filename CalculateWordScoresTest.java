import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class CalculateWordScoresTest {

    @Test
    public void testCalculateWordScores_NormalCase() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(2, "I like cake and could eat cake all day ."));
        sentences.add(new Sentence(1, "I hope the dog does not eat my cake ."));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertEquals(1.6666666666666667, wordScores.get("cake"));
        assertEquals(1.5, wordScores.get("eat"));
        assertEquals(1.0, wordScores.get("dog"));
        //System.out.println("Word scores map: " + wordScores); // Add this line to print the word scores map

        assertEquals(14, wordScores.size()); // Check the correct number of keys
    }

    @Test
    public void testCalculateWordScores_EmptySet() {
        Set<Sentence> sentences = new HashSet<>();

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertTrue(wordScores.isEmpty());
    }

    @Test
    public void testCalculateWordScores_NullSet() {
        Set<Sentence> sentences = null;

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertTrue(wordScores.isEmpty());
    }

    @Test
    public void testCalculateWordScores_CaseInsensitivity() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(1, "Dog"));
        sentences.add(new Sentence(2, "dog"));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertEquals(1.5, wordScores.get("dog"));
        assertEquals(1, wordScores.size()); // Ensure only one key "dog" exists
    }

    @Test
    public void testCalculateWordScores_IgnoreInvalidTokens() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(1, "I like cake ."));
        sentences.add(new Sentence(2, "Eat! Eat! Eat!"));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);
        //System.out.println("Word scores map: " + wordScores);

        assertEquals(2.0, wordScores.get("eat!"));
        assertNull(wordScores.get("eat")) ;
        assertEquals(1.0, wordScores.get("cake"));
        assertEquals(4, wordScores.size());
    }

    @Test
    public void testCalculateWordScores_IgnoreInvalidSentences() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(1, null)); // Invalid sentence with null text
        sentences.add(new Sentence(2, "Valid sentence here"));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);

        assertEquals(2.0, wordScores.get("valid"));
        assertEquals(3, wordScores.size());
    }

    @Test
    public void testCalculateWordScores_MixedValidAndInvalidSentences() {
        Set<Sentence> sentences = new HashSet<>();
        sentences.add(new Sentence(1, null)); // Invalid sentence with null text
        sentences.add(new Sentence(2, "Valid sentence here"));
        sentences.add(new Sentence(1, "Invalid token here @#$%"));

        Map<String, Double> wordScores = Analyzer.calculateWordScores(sentences);
        System.out.println("Word scores map: " + wordScores);

        assertEquals(2.0, wordScores.get("valid"));
        assertEquals(1.0, wordScores.get("invalid"));
        assertEquals(1.5, wordScores.get("here"));
        assertEquals(5, wordScores.size()); // Ensure only valid tokens are counted
    }
}
