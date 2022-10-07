/**
 * Nitzan Saar Sentiment Analysis
 */
public class Review {
    protected String text;
    protected int score;

   /* you add constructors, getters and setters */

    public Review(String text, int score) {
        this.text = text;
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
