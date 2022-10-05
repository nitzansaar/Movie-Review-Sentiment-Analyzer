import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class FreqDistTest{


    @Test
    void testAddWordEntry() {
        FreqDist freqDist = new FreqDist();
        String word = "test";
        String word1 = "hello";
        int score = 2;
        int score1 = 1;
        freqDist.addWordEntry(word, score);
        assertTrue(freqDist.wordTable.containsKey(word));
        assertFalse(!freqDist.wordTable.containsKey(word));
        freqDist.addWordEntry(word1, score1);
        assertTrue(freqDist.wordTable.containsKey(word1));
        freqDist.wordTable.remove(word1);
        freqDist.addWordEntry(word, 7);
        freqDist.addWordEntry(word, 4);
        assertTrue(!freqDist.wordTable.containsKey(word1));
        System.out.println(freqDist.wordTable.get(word).totalScore);
        System.out.println(freqDist.wordTable.get(word).numAppearances);

    }

    @Test
    void testGetAverageScore() {
        FreqDist fd = new FreqDist();
        String word = "howdy";
        String word1 = "hello";
        double average;
        double average1;
        fd.addWordEntry(word, 1);
        fd.addWordEntry(word, 5);
        average = fd.getAverageScore(word);
        assertTrue(average == (1 + 5) / 2);
        fd.addWordEntry(word1, 9);
        fd.addWordEntry(word1, 0);
        average1 = fd.getAverageScore(word1);
        assertTrue(average1 == 9.0 / 2);


    }
}