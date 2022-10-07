import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewLoader {
    protected String filename;

    public ReviewLoader() {
        this.filename = filename;
    }

    /* you write this method. */
    public ArrayList<Review> loadReviews() throws FileNotFoundException {
        File reviewFile = new File("movieReviews.txt");
        ArrayList<Review> reviews = new ArrayList<Review>();
        Scanner scanner = new Scanner(reviewFile);
        while(scanner.hasNextLine()){
            int score = scanner.nextInt();
            String review = scanner.nextLine();
            Review review1 = new Review(review, score);
            reviews.add(review1);
        }
        return reviews;
    }


}
