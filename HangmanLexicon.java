import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

public class HangmanLexicon {
	private ArrayList<String> list = new ArrayList<String>();
	private String line = "";

	public HangmanLexicon() {
		//reads the lexicon
		try {
			BufferedReader bf = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			line = bf.readLine();
			while (line != null) {
				list.add(line);
				line = bf.readLine();
			}
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		return list.size();
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		return list.get(index);
	}
}

