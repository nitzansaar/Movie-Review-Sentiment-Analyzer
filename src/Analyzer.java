import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Analyzer extends ReviewLoader{
    protected FreqDist fd;

    public Analyzer() {
        this.fd = new FreqDist();
    }

    public void train(List<Review> testReviews) {
        /*for (int i = 0; i < testReviews.size(); i++){// for loop to split review into individual words
            String [] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            for (int j = 0; j < temp.length; j++){
                fd.addWordEntry(temp[j], testReviews.get(i).score);
                WordEntry wordEntry = new WordEntry(temp[j], testReviews.get(i).score);
                for(int k = 0; k < temp.length; k++){// loop to check for repeat words
                    if(temp[j].equals(temp[k]) && k != j){
                        wordEntry.addNewAppearance(testReviews.get(i).score);
                    }
                }
                System.out.println(temp[j] + " appeared: " + fd.wordTable.get(temp[j]).numAppearances + " times. Total score: "
                + fd.wordTable.get(temp[j]).totalScore);
                //System.out.println(wordEntry.getWord() + " " + wordEntry.numAppearances + " " + wordEntry.totalScore);
            }
        }*/

        for (int i = 0; i < testReviews.size(); i++) {
            String[] temp = testReviews.get(i).getText().split("\s");
            for (int j = 0; j < temp.length; j++) {
                fd.addWordEntry(temp[j], testReviews.get(i).score);
                //System.out.println(temp[j] + " appeared " + a.fd.wordTable.get(temp[j]).numAppearances + " times. " +
                        //"Total score: " + a.fd.wordTable.get(temp[j]).totalScore);
            }
        }
    }


    public void test(List<Review> testReviews) throws FileNotFoundException {
        float distanceBtw;
        double predictedScore[] = new double[testReviews.size()];
        train(testReviews);
        for (int i = 0; i < testReviews.size(); i++) {// for loop to split review into individual words
            String[] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            double sum = 0;
            for(int j = 0; j < temp.length; j++){
                sum += fd.getAverageScore(temp[j]);// add up averages for each word within a review
            }
            predictedScore[i] = sum / temp.length;
            distanceBtw = (float) (predictedScore[i] - testReviews.get(i).score);
            System.out.println(predictedScore[i] + " " + testReviews.get(i).score);
            //System.out.println("Distance between predicted and actual: " + distanceBtw);
        }
    }



    public void learn() throws FileNotFoundException {//list containing sublists
        ArrayList<Review> reviews = loadReviews();
        ArrayList<List<Review>> sublistList = new ArrayList<>();
        int fifth = reviews.size() / 5;
        List<Review> sublist1 = reviews.subList(0, fifth);
        List<Review> sublist2 = reviews.subList(fifth, (fifth * 2));
        List<Review> sublist3 = reviews.subList(fifth * 2, (fifth * 3));
        List<Review> sublist4 = reviews.subList(fifth * 3, (fifth * 4));
        List<Review> sublist5 = reviews.subList(fifth * 4, (fifth * 5));
        sublistList.add(sublist1);
        sublistList.add(sublist2);
        sublistList.add(sublist3);
        sublistList.add(sublist4);
        sublistList.add(sublist5);
        for(int i = 0; i < 5; i++){
            if(i == 0){
                train(sublist1);
                train(sublist2);
                train(sublist3);
                train(sublist4);
                test(sublist5);
            }if(i == 1){
                train(sublist5);
                train(sublist2);
                train(sublist3);
                train(sublist4);
                test(sublist1);
            }if(i == 2){
                train(sublist5);
                train(sublist1);
                train(sublist3);
                train(sublist4);
                test(sublist2);
            }if(i == 3){
                train(sublist5);
                train(sublist1);
                train(sublist2);
                train(sublist4);
                test(sublist3);
            }if(i == 4){
                train(sublist5);
                train(sublist1);
                train(sublist2);
                train(sublist3);
                test(sublist4);
            }
        }

    }
}
