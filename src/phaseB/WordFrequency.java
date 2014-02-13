package phaseB;

// A class that holds a word and its corresponding frequency
public class WordFrequency {
	private String word;		//the word
	private double frequency;	//the word's frequency
	
	// Pass in the word, the amount of times the word appears in the file, and
	// the total amount of words in the file
	public WordFrequency(String word, int totalWords, int wordCount) {
		this.word = word;
		frequency = 1.0 * wordCount / totalWords;
	}
	
	// Return the word
	public String getWord() {
		return word;
	}
	
	// Return the word's frequency
	public double getFreq() {
		return frequency;
	}
}