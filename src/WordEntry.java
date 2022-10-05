/* this should work as-is */

public class WordEntry {

    protected String word;
    protected int numAppearances;
    protected int totalScore;

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public WordEntry() {
        word = "";
        numAppearances = 0;
        totalScore = 0;
    }
    public WordEntry(String w) {
        word = w;
        numAppearances = 0;
        totalScore = 0;
    }

    public WordEntry(String w, int initialScore) {
        word = w;
        numAppearances = 1;
        totalScore = initialScore;
    }

    public void addNewAppearance(int score) {
        totalScore += score;
        numAppearances++;
    }

    public String getWord() {
        return word;
    }

    public double getAverage() {
        return ((double)totalScore) / numAppearances;
    }

}
