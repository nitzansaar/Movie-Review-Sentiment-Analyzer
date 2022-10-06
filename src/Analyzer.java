import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isLetter;

public class Analyzer extends ReviewLoader{
    protected FreqDist fd;
    public static final String[] stopwords = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "you're",
            "you've", "you'll", "you'd", "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself", "she",
            "she's", "her", "hers", "herself", "it", "it's", "its", "itself", "they", "them", "their", "theirs",
            "themselves", "what", "which", "who", "whom", "this", "that", "that'll", "these", "those", "am", "is", "are",
            "was", "were", "be", "been", "being", "have", "has", "had", "having", "do", "does", "did", "doing",
            "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while", "of", "at", "by", "for",
            "with", "about", "against", "between", "into", "through", "during", "before", "after", "above", "below", "to",
            "from", "up", "down", "in", "out", "on", "off", "over", "under", "again", "further", "then", "once", "here",
            "there", "when", "where", "why", "how", "all", "any", "both", "each", "few", "more", "most", "other", "some",
            "such", "no", "nor", "not", "only", "own", "same", "so", "than", "too", "very", "can", "will", "just", "don't",
            "should", "should've", "now", "aren't", "couldn't", "didn't", "doesn't",  "hasn't", "haven't", "isn't", "shouldn't",
            "wasn't", "weren't", "won't", "wouldn't"};


    public Analyzer() {
        this.fd = new FreqDist();
    }
    public boolean isJunk(String word) {
        for (int i = 0; i < word.length(); i++){
            if(!isLetter(word.charAt(i))){
                return true;
            }
        }
        return false;
    }
    public boolean isStopword(String word) {
        for (int i = 0; i < stopwords.length; i++){
            if(word.equals(stopwords[i])){
                return true;
            }
        }
        return false;
    }

    public void train(List<Review> testReviews) {

        for (int i = 0; i < testReviews.size(); i++) {
            String[] temp = testReviews.get(i).getText().split("\s");
            for (int j = 0; j < temp.length; j++) {
                if(!isStopword(temp[j]) && !isJunk(temp[j])) {
                    fd.addWordEntry(temp[j], testReviews.get(i).score);
                }
            }
        }
    }

    public void test(List<Review> testReviews) throws FileNotFoundException {
        int distanceBtw;
        int predictedScore[] = new int[testReviews.size()];
        double totalDistance = 0;
        float averageDistance;
        train(testReviews);
        for (int i = 0; i < testReviews.size(); i++) {// for loop to split review into individual words
            String[] temp = testReviews.get(i).getText().split("\s");// separates based on whitespace
            double sum = 0;
            for(int j = 0; j < temp.length; j++){
                sum += fd.getAverageScore(temp[j]);// add up averages for each word within a review
            }
            predictedScore[i] = (int) (sum / temp.length);
            distanceBtw = Math.abs(predictedScore[i] - testReviews.get(i).score);
            totalDistance += distanceBtw;
        }
        averageDistance = (float) (totalDistance / testReviews.size());
        System.out.println("Average distance: " + averageDistance);
    }

    public void learn() throws FileNotFoundException {
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
