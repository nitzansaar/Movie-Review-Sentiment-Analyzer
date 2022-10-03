import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Analyzer extends ReviewLoader{
    protected FreqDist fd;

    public Analyzer() {
        super();
        this.fd = new FreqDist();
    }

    public void train(List<Review> testReviews) {
        for (int i = 0; i < testReviews.size(); i++){// for loop to split review into individual words
            String [] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            for (int j = 0; j < temp.length; j++){
                fd.addWordEntry(temp[j], testReviews.get(i).score);
                WordEntry wordEntry = new WordEntry(temp[j], testReviews.get(i).score);
                for(int k = 0; k < temp.length; k++){// loop to check for repeat words
                    if(temp[j].equals(temp[k]) && k != j){
                        wordEntry.addNewAppearance(testReviews.get(i).score);
                    }
                }
            }

        }
    }


    public void test(List<Review> testReviews) {
        Analyzer a = new Analyzer();
        a.train(testReviews);
        for (int i = 0; i < testReviews.size(); i++) {//loop to split review into individual words
            String[] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            for(int j = 0; j < temp.length; j++){
               a.fd.getAverageScore(temp[j]);
            }
        }
    }

    public void learn() throws FileNotFoundException {
        ArrayList<Review> reviews= new ArrayList<>();
        reviews = loadReviews();
        for (int i = 0; i < 5; i++){
        }

    }

}
