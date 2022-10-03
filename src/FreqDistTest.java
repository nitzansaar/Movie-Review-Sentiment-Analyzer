import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FreqDistTest extends FreqDist{


    @Test
    void testAddWordEntry() {
        String word = "test";
        String word1 = "hello";
        int score = 0;
        int score1 = 1;
        addWordEntry(word, score);
        assertTrue(wordTable.containsKey(word));
        assertFalse(!wordTable.containsKey(word));
        addWordEntry(word1, score1);
        assertTrue(wordTable.containsKey(word1));
        wordTable.remove(word1);
        assertTrue(!wordTable.containsKey(word1));

    }

    @Test
    void testGetAverageScore() {
        String word = "howdy";
        String word1 = "hello";
        double average;
        double average1;
        addWordEntry(word, 1);
        addWordEntry(word, 5);
        average = getAverageScore(word);
        assertTrue(average == (1 + 5) / 2);
        addWordEntry(word1, 9);
        addWordEntry(word1, 0);
        average1 = getAverageScore(word1);
        assertTrue(average1 == 9.0 / 2);


    }
}