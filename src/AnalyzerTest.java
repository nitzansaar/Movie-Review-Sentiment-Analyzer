import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AnalyzerTest {

    Analyzer a;
    @BeforeEach
    void setUp() {
        a = new Analyzer();
    }

    /* put your tests here */

    @Test
    void testTrain(){
        FreqDist fd = new FreqDist();
        Review review = new Review("This film film sucks and is the worst film ever, not amazing", 0);
        Review review1 = new Review("What an amazing amazing amazing film", 5);
        List<Review> testReviews = new ArrayList<>();
        testReviews.add(review);
        testReviews.add(review1);
        for (int i = 0; i < testReviews.size(); i++) {
            String[] temp = testReviews.get(i).getText().split("\s");
            for (int j = 0; j < temp.length; j++) {
                fd.addWordEntry(temp[j], testReviews.get(i).score);
            }
        }
        assertTrue(fd.wordTable.get("film").numAppearances == 4);
        assertTrue(fd.wordTable.get("amazing").numAppearances == 4);
        assertTrue(fd.wordTable.get("amazing").totalScore == 15);
        assertFalse(fd.wordTable.get("What").numAppearances == 2);
        assertTrue(fd.wordTable.get("What").totalScore == 5);
    }

    @Test
    void testTest() {
        double predictedScore;
        Review review = new Review("This film film sucks and is the worst film ever", 0);
        Review review1 = new Review("What an amazing amazing amazing amazing film", 5);
        Review review2 = new Review("Holy moly what an amazing film", 6);
        Review review3 = new Review("I enjoyed it", 2);
        List<Review> testReviews = new ArrayList<>();
        testReviews.add(review);
        testReviews.add(review1);
        testReviews.add(review2);
        testReviews.add(review3);
        a.train(testReviews);
        for (int i = 0; i < testReviews.size(); i++) {// for loop to split review into individual words
            double sum = 0;
            String[] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            for(int j = 0; j < temp.length; j++){
                sum += a.fd.getAverageScore(temp[j]);// add up averages for each word within a review
            }
            predictedScore = sum / temp.length;
            if(i == 0){
                assertTrue(predictedScore == .66);
                assertFalse(predictedScore == 0);
            }
            if(i == 1){
                assertFalse(predictedScore == 0);
            }
            if(i == 1){
                assertTrue(predictedScore != 10);
            }
            if(i == 3){
                assertTrue(predictedScore == 1.3333333333333333);
            }
        }
    }
}
