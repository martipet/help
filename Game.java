package hangman;

import java.util.Random;
import java.util.Scanner;

// ve4e pi6a mnogo dobre na java, napisah vsi4ko za 2 4asa i trugna ot raz, yahuuaoaoaoaaauuuuu
public class Game {
	private static final String[] wordForGuessing = { "computer", "programmer",
			"software", "debugger", "compiler", "developer", "algorithm",
			"array", "method", "variable" };

	private String guessWord;
	
	
	
	
	private StringBuffer dashedWord;
	private FileReadWriter filerw;

	public Game(boolean autoStart) {
		guessWord = getRandWord();
		dashedWord = getW(guessWord);
		filerw = new FileReadWriter();
		if(autoStart) {
			displayMenu();
		}
	}


	private String getRandWord() {
		Random rand = new Random();
		String randWord = wordForGuessing[rand.nextInt(10)];
		//uppdate number if words are added to the list
		return randWord;
	}
	public void displayMenu() {
		System.out
				.println("Welcome to �Hangman� game. Please, try to guess my secret word.\n"
						+ "Use 'TOP' to view the top scoreboard, 'RESTART' to start a new game,"
						+ "'HELP' to cheat and 'EXIT' to quit the game.");

		findLetterAndPrintIt();
	}
	private void findLetterAndPrintIt() {
		boolean isHelpUsed = false;
		String letter = "";
		StringBuffer dashBuff = new StringBuffer(dashedWord);
		int mistakes = 0;

		do {
			System.out.println("The secret word is: " + printDashes(dashBuff));
			System.out.println("DEBUG " + guessWord);
			
				System.out.println("Enter your gues(1 letter alowed): ");
				Scanner input = new Scanner(System.in);
				letter = input.next();

				if (letter.equals(Command.HELP.toString())) {
					isHelpUsed = true;
					int i = 0, j = 0;
					while (j < 1) {
						if (dashBuff.charAt(i) == '_') {
							dashBuff.setCharAt(i, guessWord.charAt(i));
							++j;
						}
						++i;
					}
					System.out.println("The secret word is: "
							+ printDashes(dashBuff));
				}// end if
				menu(letter);

			

			int counter = 0;
			for (int i = 0; i < guessWord.length(); i++) {
				String currentLetter = Character.toString(guessWord.charAt(i));
				if (letter.equals(currentLetter)) {
					
					{
					
					++counter;
					}
					dashBuff.setCharAt(i, letter.charAt(0));
				}
			}

			if (counter == 0) {
				++mistakes;
				{
					System.out.printf(
							"Sorry! There are no unrevealed letters \'%s\'. \n",
							letter);
				}
			} else {
				System.out.printf("Good job! You revealed %d letter(s).\n",
						counter);
			}

		} while (!dashBuff.toString().equals(guessWord));

		if (isHelpUsed == false) { // if the player did not use HELP
			System.out.println("You won with " + mistakes + " mistake(s).");
			System.out.println("The secret word is: " + printDashes(dashBuff));

			System.out
					.println("Please enter your name for the top scoreboard:");
			Scanner input = new Scanner(System.in);
			String playerName = input.next();

			{
				
				filerw.openFileToWite();
				filerw.addRecords(mistakes, playerName);
				filerw.closeFileFromWriting();
				filerw.openFiletoRead();
				filerw.readRecords();
				filerw.tryCloseFileFromReading()();
				filerw.printAndSortScoreBoard();
			}

			
		} else { //if player used HELP
			System.out
					.println("You won with "
							+ mistakes
							+ " mistake(s). but you have cheated. You are not allowed to enter into the scoreboard.");
			System.out.println("The secret word is: " + printDashes(dashBuff));
		}
		
		// restart the game
		new Game(true);
		
	}// end method findLetterAndPrintIt
	private void menu(String letter) {
		if (letter.equals(Command.RESTART.toString())) {
			new Game(true);
		} else if (letter.equals(Command.TOP.toString())) {
				filerw.openFiletoRead();
				filerw.readRecords();
				filerw.tryCloseFileFromReading()();
				filerw.printAndSortScoreBoard();
				new Game(true);
			} else if (letter.equals(Command.EXIT.toString())) {
					System.exit(1);
				
			
		}
	}//end method

	private StringBuffer getW(String word) {
		StringBuffer dashes = new StringBuffer("");
		for (int i = 0; i < word.length(); i++) {
			dashes.append("_");
			
			
			
		}
		return dashes;
	}//end getW
	private String printDashes(StringBuffer word) {
		String toDashes = "";
		
		
		for (int i = 0; i < word.length(); i++) {
			
			
			
			toDashes += (" " + word.charAt(i));
		}
		return toDashes;

	} //end printDashes
}//end game
