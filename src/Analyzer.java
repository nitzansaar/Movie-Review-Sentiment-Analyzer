import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Analyzer extends ReviewLoader{
    protected FreqDist fd;

    protected double[] predictedScore;

    public Analyzer() {
        this.fd = new FreqDist();
    }

    public void train(List<Review> testReviews) {
        for (int i = 0; i < testReviews.size(); i++){// for loop to split review into individual words
            String [] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            for (int j = 0; j < temp.length; j++){
                fd.addWordEntry(temp[j], testReviews.get(i).score);
                WordEntry wordEntry = new WordEntry(temp[j], testReviews.get(i).score);
                System.out.println(testReviews.get(i).score);
                for(int k = 0; k < temp.length; k++){// loop to check for repeat words
                    if(temp[j].equals(temp[k]) && k != j){
                        wordEntry.addNewAppearance(testReviews.get(i).score);
                    }
                }
            }

        }
    }


    public void test(List<Review> testReviews) throws FileNotFoundException {
        float distanceBtw;
        double predictedScore[] = new double[testReviews.size()];
        Analyzer a = new Analyzer();
        a.train(testReviews);
        for (int i = 0; i < testReviews.size(); i++) {// for loop to split review into individual words
            String[] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            double sum = 0;
            for(int j = 0; j < temp.length; j++){
                sum += a.fd.getAverageScore(temp[j]);// add up averages for each word within a review
            }
            predictedScore[i] = sum / temp.length;
            distanceBtw = (float) (predictedScore[i] - testReviews.get(i).score);
            System.out.println("Distance between predicted and actual: " + distanceBtw);
        }
    }



    public void learn() throws FileNotFoundException {
        Analyzer analyzer = new Analyzer();
        ArrayList<Review> reviews = loadReviews();
        int fifth = reviews.size() / 5;
        for(int i = 0; i < fifth; i++){
            test(reviews);
        }

    }
}
