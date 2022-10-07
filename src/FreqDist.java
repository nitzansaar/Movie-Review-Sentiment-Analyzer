import java.util.HashMap;

/* this should work as-is */

/**
 * Nitzan Saar Sentiment Analysis
 */
public class FreqDist {
    protected HashMap<String, WordEntry> wordTable;
    
    public FreqDist() {
       wordTable = new HashMap<String, WordEntry>();
    }
    
    
    public void addWordEntry(String word, int score) {
        if (wordTable.containsKey(word)) {
            WordEntry temp = wordTable.get(word);
            temp.addNewAppearance(score);
        } else {
            wordTable.put(word, new WordEntry(word, score));
        }
    }

    public double getAverageScore(String word) {
        if (wordTable.containsKey(word)) {
            return (wordTable.get(word).getAverage());
        } else {
            return 0.0;
        }
    }
}
