import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/* you build this part */

class ReviewLoaderTest{

    @Test
    void testLoadReviews() throws FileNotFoundException {
        File reviewFile = new File("movieReviews.txt");
        ArrayList<Review> reviews = new ArrayList<Review>();
        Scanner scanner = new Scanner(reviewFile);
        while(scanner.hasNextLine()){
            int score = scanner.nextInt();
            String review = scanner.nextLine();
            Review temp = new Review(review, score);
            reviews.add(temp);
            System.out.println(score + " " + review);
        }
    }
}