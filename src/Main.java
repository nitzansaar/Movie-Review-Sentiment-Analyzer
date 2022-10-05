import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
        Analyzer analyzer = new Analyzer();
        ArrayList<Review> reviews = analyzer.loadReviews();
        //analyzer.test(reviews);
        //analyzer.train(reviews);
        analyzer.learn();

    }
}
