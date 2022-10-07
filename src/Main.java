/**
 * Nitzan Saar Sentiment Analysis
 */
import java.io.FileNotFoundException;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        Analyzer analyzer = new Analyzer();
        analyzer.learn();

    }
}
