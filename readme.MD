#### Homework 3: Sentiment Analysis

##### Due: Friday, Oct 7, start of class.


This homework will be graded using specifications grading. There are a sequence of tasks to complete. Grades will be assigned as follows:

- To receive a C, you should complete tasks 1,2,3,4
- To receive a B, complete all the C-level tasks, plus tasks 5, 6, and 7.
- To receive an A, complete the B-level tasks, plus task 8.

In this homework, you'll build a tool to perform sentiment analysis.
This is a simplified version of a program developed for a Kaggle machine learning competition (https://www.kaggle.com/c/sentiment-analysis-on-movie-reviews)- please feel free to take a look at that for ideas and ways to extend your work.

#### The Problem.

In this problem, we're interested in developing a tool that can determine whether a movie review is good or bad depending on the words it contains.

For example, a review that says "The film is a breath of fresh air" is positive, while a review that says "this movie made me want to poke my eyes out" is negative.

We're going to use supervised machine learning to solve this problem.

#### The Data

The file movieReviews.txt contains 8529 reviews scraped from RottenTomatoes. Each review has a score from 0 (worst) to 4 (best), along with the review text. The text has had some pre-processing done to separate punctuation from text.

##### The approach.

We're going to use a simple averaging strategy here to predict a review's score based on the word in it.
To do this, we'll start with a set of labeled reviews. For each review, we'll do the following:
- get all of the words in the review.
- Build a FreqDist that lets us keep track of the average score associated with each word.
- Estimate the score for a new review by averaging the estimated scores for each word in the review.

#### The classes

Task 1. To begin, complete the Review class. This class will hold the review text and the score associated with the review. Add a constructor that takes two arguments (text and score), along with setters and getters.

Task 2. I've provided two helper classes for you.
- The first is a WordEntry class. This class will keep track of each word, its total review score, and the number of occcurrences of the word.
- The second is a FreqDist. Like in homework 2, this is a wrapper for a HashMap. In this case, we're mapping Strings to WordEntry objects.
These classes should work as-is. Create a FreqDistTest class (using JUnit) that creates a FreqDist, adds sample words and scores, and then confirms that getAverageScore works as advertised.

Task 3. Now we need a way to read in the reviews from the file. I've provided a class called ReviewLoader. You should implement the loadReviews method so that it returns an  ArrayList<Review> of reviews. Also create a ReviewLoaderTest (using JUnit) to test out loadReviews; it can just print each review out.

Task 4. Now we need a method that will take our reviews and add up the scores for each word.
I've provided a class called Analyzer as a starting point. Notice that the Analyzer has a FreqDist as a member variable.
To start, implement train(). Train should take as input an ArrayList of Reviews and:
- For each review, get the text and separate it into separate words.
- For each word in the review, add it to the FreqDist, along with the score for that review.
  At the end of this, the FredDist should have a set of words and WordEntry objects - each WordEntry object contains that word (just to be safe) plus the total score of reviews containing that word, and the number of times that word occurred.
- Create AnalyzerTest using JUnit and use it to test the train method. You can set up a few hardcoded reviews in a list, pass them into train, and then confirm that the counts are as expected.

Task 5. Testing. We need to be able to use our Analyzer to classify new reviews. This will work very similarly, using the test() method. Test should also take as input an ArrayList of reviews.
- For each review, get the text and separate it into separate words.
- For each word, look in the FreqDist to get its average score.
- Add up all these averages, and divide by the number of words to get the predicted score for the review.
  Extend AnalyzerTest to test out the test() method. Again, create a couple of hardcoded reviews, pass them in, and confirm that the predicted scores are being calculated correctly.

Task 6: Learning. We're almost there - we just need to put it all together!
In order to test our algorithm appropriately, we want to train on one subset of the data and test on another subset.
- Add a method called learn() to the Analyzer. It should:
    - Read in all the reviews using the ReviewLoader.
    - Divide the Reviews into five groups. (note: you don't need to actually create five separate ArrayLists - you can just keep track of the indices of one list.) We'll use a technique called five-fold cross-validation to measure performance. It works like this:
    - for i = 1 to 5:
        - train on four of the groups, and test on the fifth.
    - Each time through the loop, we test on a different group - this way we always train on 80% and test on 20%.
- Modify test() to keep track of:
    - The average distance between the predicted value and the actual value for all test cases. (this will be a floating-point number).
    - The average distance between the predicted classification and the actual classification for all test cases. (This will require rounding the prediction to the nearest whole number - if we're predicting a score of 2.7, we would call that a review of 3.)

Task 7: Documenting. Create a class diagram for each of the classes using your favorite drawing software. Indicate the isA and hasA relationships, and the public interfaces for each class.

Task 8: Evaluation. Now that you have a working ML system, try it out!
- What is the average performance of your system across all five folds?
- What are the most positive words? (that is, the words with the highest average score)
- What are the most negative words? (that is, the words with the lowest average score)
- What are the most common words?
- What are the least common words?
- This tool does OK, but it would be better. We have some code from homework 2 that might help. Add in removal of stop words and junk. Does this help? What is your accuracy with this approach?
- Please add a short document to your repo answering these questions.

