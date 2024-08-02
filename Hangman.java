
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private RandomGenerator random;
	private int triesNum = 8;
	private HangmanCanvas canvas;

	// counts mistakes
	private int mistake = 0;

	// counts the numbber of characters that have been guessed
	private int rightsCounter = 0;

	// wrong characters
	private String characters = "";
	private int wrongsCounter = 0;
	private char guess = '0';
	private String newWord = "";
	private String word = "";
	private int gamesCount = 0;

	//adds canvas
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	public void run() {
		while (true) {
			//if it is the first try game begins without asking any question
			if (gamesCount == 0) {
				gameIntro();
				game();
				
			}
			//every other round begings with question, wether or not user wants to continue
			//game continues if the answer is "yes"
			else {
				String answer = readLine("Do you want to continue? ");
				if (answer.toLowerCase().equals("yes")) {
					gameIntro();
					game();
				} else {
					println("GAME OVER");
					break; 
				}
			}
		}
	}

	//game begins here
	private void game() {
		
		// adds labels
		canvas.reset();
		canvas.displayWord(newWord);

		//game continues until user makes 8 mistakes or guesses the word
		while (triesNum > 0) {
			gamesCount++;
			addText(newWord, triesNum);
			wrongsCounter = 0;
			// user enteres a character
			String line = readLine("Your Guess: ");

			// entered character
			guess = line.charAt(0);

			// if user enteres symbol that has already been entered, game
			// continues
			if (newWord.contains(line.toUpperCase()) || !isLetter(guess)) {
				println("enter another character");
				continue;
			}

			// checks if entered character is in a chosen word
			checkGuess();

			// entered character does not match any character in the word
			if (wrongsCounter == word.length()) {
				println("there are no " + guess + "'s in the word");
				mistake += 1;
				triesNum--;
				characters += guess + ", ";
				canvas.noteIncorrectGuess(mistake, characters);

				// entered character matches at least one character
			} else {
				println("That guess is correct");
				changeCanvas(newWord);
			}

			//if every letter is guessed cycle ends
			if (rightsCounter == word.length()) {
				break;
			}
		}
		
		// user loses if there are no tries left
		if (triesNum == 0) {
			println("the word was: " + word);
			println("YOU LOSE :( ");
		}
		
		// user wins if they guess the word
		else{
			println("You guessed the word: " + word);
			println("YOU WIN :) ");
		}

	}

	private void gameIntro() {
		chooseWord();
		
		//adds welcome texts 
		println("Welcome To Hangman");
		newWord = changeSymbols("", word);
		
		//in the beginning every game has variables with these values
		mistake = 0;
		triesNum = 8;
		characters = "";
		rightsCounter = 0;
	}
	
	//chooses word from the lexicon
	private void chooseWord(){
		HangmanLexicon lexicon = new HangmanLexicon();
		random = RandomGenerator.getInstance();
		int m = random.nextInt(0, lexicon.getWordCount());
		word = lexicon.getWord(m);
	}
	
	//checks if entered letter is in the word
	private void checkGuess() {
		for (int j = 0; j < word.length(); j++) {
			// guess matches character in "word"
			if (guess == word.charAt(j)) {
				newWord = newWord.substring(0, j) + guess + newWord.substring(j + 1);
				rightsCounter += 1;
			} else if ((char) ('A' + guess - 'a') == word.charAt(j)) {
				newWord = newWord.substring(0, j) + (char) ('A' + guess - 'a') + newWord.substring(j + 1);
				rightsCounter += 1;

				// guess does not match a character, wrongsCounter increases
			} else {
				wrongsCounter += 1;
			}
		}
	}

	// changes every symbol in "word" with '-'
	private String changeSymbols(String newWord, String word) {
		for (int i = 0; i < word.length(); i++) {
			newWord += "-";
		}
		return (newWord);
	}

	// adds text in the beggining of every try
	private void addText(String newWord, int triesNum) {
		println("the word now looks like this: " + newWord);
		println("you have " + triesNum + " guesses left.");
	}

	// changer labels on canvas
	private void changeCanvas(String newWord) {
		canvas.remove(canvas.getElementAt(canvas.getWidth() / 2 - 144, canvas.getHeight() - 45));
		canvas.displayWord(newWord);
	}
	
	//returns true if the symbol is a letter
	private boolean isLetter(char c) {
		if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
			return true;
		}
		return false;
	}
}