import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateSentenceScoreTest {

    @Test
    void testCalculateSentenceScore_NormalCase() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 1.0);
        wordScores.put("are", 2.0);
        wordScores.put("cute", 3.0);

        String sentence = "dogs are cute";

        double result = Analyzer.calculateSentenceScore(wordScores, sentence);

        assertEquals(2.0, result); // Average of (1.0 + 2.0 + 3.0) / 3 = 2.0
    }

    @Test
    void testCalculateSentenceScore_WordNotInMap() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 1.0);
        wordScores.put("are", 2.0);

        String sentence = "dogs are funny";

        double result = Analyzer.calculateSentenceScore(wordScores, sentence);

        assertEquals(1.0, result); // Average of (1.0 + 2.0 + 0.0) / 3 = 1.0
    }

    @Test
    void testCalculateSentenceScore_EmptySentence() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 1.0);
        wordScores.put("are", 2.0);

        String sentence = "";

        double result = Analyzer.calculateSentenceScore(wordScores, sentence);

        assertEquals(0.0, result); // Empty sentence should return 0.0
    }

    @Test
    void testCalculateSentenceScore_NullInputs() {
        double result = Analyzer.calculateSentenceScore(null, null);

        assertEquals(0.0, result); // Null inputs should return 0.0
    }

    @Test
    void testCalculateSentenceScore_NullWordScores() {
        Map<String, Double> wordScores = new HashMap<>();
        wordScores.put("dogs", 1.0);
        wordScores.put("are", 2.0);

        String sentence = "dogs are cute";

        double result = Analyzer.calculateSentenceScore(null, sentence);

        assertEquals(0.0, result); // Null wordScores should return 0.0
    }
}
