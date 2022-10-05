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
        Review review = new Review("This film film sucks and is the worst film ever, not amazing", 1);
        Review review1 = new Review("What an amazing amazing amazing film", 5);
        List<Review> testReviews = new ArrayList<>();
        testReviews.add(review);
        testReviews.add(review1);
        for (int i = 0; i < testReviews.size(); i++){// for loop to split review into individual words
            String [] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            for (int j = 0; j < temp.length; j++){
                a.fd.addWordEntry(temp[j], testReviews.get(i).score);
                WordEntry wordEntry = new WordEntry(temp[j], testReviews.get(i).score);
                for(int k = 0; k < temp.length; k++){// loop to check for repeat words
                    if(temp[j].equals(temp[k]) && k != j){
                        wordEntry.addNewAppearance(testReviews.get(i).score);
                    }
                }
                /*if(wordEntry.equals("film")){
                    assertTrue(wordEntry.numAppearances == 4);
                    assertTrue(wordEntry.totalScore == 8);
                }
                if(wordEntry.equals("What")){
                    assertTrue(wordEntry.numAppearances == 1);
                    assertFalse((wordEntry.totalScore == 3));
                }
                if(wordEntry.equals("amazing")){
                    assertTrue(wordEntry.numAppearances == 4);
                    assertTrue(wordEntry.totalScore != 16);
                }*/
            }

        }
        /*for (int i = 0; i < testReviews.size(); i++) {
            String[] temp = testReviews.get(i).getText().split("\s");
            for (int j = 0; j < temp.length; j++) {
                a.fd.addWordEntry(temp[j], testReviews.get(i).score);

            }
        }*/
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
                System.out.println(temp[j]);
                System.out.println("Average: " + a.fd.getAverageScore(temp[j]));
                sum += a.fd.getAverageScore(temp[j]);// add up averages for each word within a review
            }
            predictedScore = sum / temp.length;
            if(i == 0){
                assertTrue(predictedScore == .66);
                assertFalse(predictedScore == 0);
            }
            if(i == 0){
                assertFalse(predictedScore == 0);
            }
            System.out.println("Score: " + predictedScore);
            System.out.println();
        }
    }
}