# Movie Review Sentiment Analyzer

This project is focused on building a sentiment analysis tool that predicts the sentiment of movie reviews. The tool analyzes the words in a review and predicts whether the review is positive or negative based on the words it contains.

## Project Structure

The project consists of several Java classes that work together to perform sentiment analysis:

- **Analyzer**: This class is responsible for training the sentiment analysis model, testing reviews, and implementing a five-fold cross-validation approach. It uses a FreqDist object to keep track of word frequencies and scores.

- **FreqDist**: This class represents a frequency distribution of words. It uses a HashMap to store WordEntry objects that contain information about word appearances and scores.

- **WordEntry**: This class represents a word entry in the frequency distribution. It stores the word itself, the number of appearances, and the total score associated with that word.

- **Review**: This class encapsulates a movie review, storing both the review text and its corresponding score.

- **ReviewLoader**: This class is responsible for loading movie reviews from a file ("movieReviews.txt").

- **AnalyzerTest**: This JUnit test class contains test methods for testing the functionality of the Analyzer class.

- **FreqDistTest**: This JUnit test class contains test methods for testing the functionality of the FreqDist class.

- **ReviewLoaderTest**: This JUnit test class contains a test method for testing the loading of movie reviews using the ReviewLoader class.

## Getting Started

To run the project and perform sentiment analysis:

1. Clone or download the project files.
2. Ensure you have Java installed on your system.
3. Open the project in your preferred Java development environment (e.g., IntelliJ IDEA, Eclipse).
4. Run the Main class to execute the sentiment analysis process.

## Usage

1. The `Analyzer` class performs sentiment analysis using a simple averaging strategy.
2. The `FreqDist` class stores word frequencies and scores for sentiment analysis.
3. The `Review` class represents individual movie reviews with text and scores.
4. The `ReviewLoader` class loads movie reviews from the "movieReviews.txt" file.

## Testing

The project includes JUnit tests for various classes to ensure the correctness of their functionality.

1. Run the JUnit tests from the test classes (`AnalyzerTest`, `FreqDistTest`, `ReviewLoaderTest`) to verify the implementation.

## What I Learned

Through this project, I gained experience in:

- Implementing a sentiment analysis tool using supervised machine learning.
- Using Java programming to process and analyze textual data.
- Developing classes and methods for data storage and manipulation.
- Writing and conducting unit tests to validate the functionality of different components.
- Applying cross-validation techniques to assess the performance of the sentiment analysis model.
