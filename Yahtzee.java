/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		scoreBoard = new int [17][nPlayers];
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		gameEnd = true;
		while (gameEnd != false) {
			for(int i = 1; i <= nPlayers; i++) {
				displayDice(i);
				diceSelected();
				diceSelected();
				checkCategory(i);
			}	
		}
		for(int b = 1; b <= nPlayers; b++) {
			checkTotals(b);
		}
	}



	public void displayDice(int player) {
		display.waitForPlayerToClickRoll(player);
		dice = new int[N_DICE];
		for (int i=0; i < N_DICE; i++) {
			dice[i] = rgen.nextInt(1,6);
		}
		display.displayDice(dice);
	}


	public void diceSelected() {
		display.waitForPlayerToSelectDice();
		for( int i=0; i < N_DICE; i++) {
			if(display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1,6);
			}
		}
		display.displayDice(dice);
	}

	public void checkCategory(int player) {
		int category = display.waitForPlayerToSelectCategory();
		if (category < 7) {
			oneThroughSix(player, category);
		} else if (category == 9 || category == 10){
			ofAKinds(player, category);
		}else if (category == 15) {
			chance(player, category);
		}else if (category == 14) {
			yahtzee(player, category);
		}else if (category == 11) {
			fullHouse(player, category);
		}else if (category == 12) {
			smallStraight(player, category);
		}else if (category == 13) {
			largeStraight(player, category);
		}

	}

	public void oneThroughSix(int player, int category) {
		int score;
		int counter = 0;
		for (int i=0; i < N_DICE; i++) {
			if (dice[i] == category) {
				counter = counter + 1;
			}

		}
		score = counter * category;
		scoreBoard [category][player-1] = score;
		display.updateScorecard(category, player, score);
		counter = 0;
	}

	public void ofAKinds(int player, int category) {
		int counter = 0;
		int score = 0;
		boolean ofAKind = false; 

		for (int g=1; g <= 6; g++) {
			for (int i=0; i < N_DICE; i++) {
				if(dice[i] == g) {
					counter = counter + 1;
				}
				if (counter == category - 6) {
					ofAKind = true; 
				}
			}
			counter = 0;
		}
		if(ofAKind) {
			for (int b = 0; b < N_DICE; b++) {
				score = dice[b] + score;
			}
		}

		scoreBoard [category][player-1] = score;
		display.updateScorecard(category, player, score);
	}

	public void chance(int player, int category) {
		int scores = 0;
		int counter = 0;
		if (category == CHANCE) {
			for (int c = 0; c < N_DICE; c++) {
				scores = dice[c] + scores;
			}
		}
		scoreBoard [category][player-1] = scores;
		display.updateScorecard(category, player, scores);
		counter = 0;
	}

	public void yahtzee (int player, int category) {
		int scores = 0;
		int counter = 0;
		for (int t=1; t <= 6; t++) {
			for (int i=0; i < N_DICE; i++) {
				if(dice[i] == t) {
					counter = counter + 1;
				}
				if (counter == 5) {
					scores = 50;
				}
			}
		}
		scoreBoard [category][player-1] = scores;
		display.updateScorecard(category, player, scores);
	}

	public void fullHouse(int player, int category) {
		int scores = 0;
		int counter = 0;
		int counters = 0;
		for (int y=1; y <= 6; y++) {
			for (int t=1; t <= 6; t++) {
				for (int i=0; i < N_DICE; i++) {
					if(dice[i] == t) {
						counter = counter + 1;
					}
					if (dice [i] == y && y != t) {
						counters = counters + 1;
					}
					if ((counter == 3 && counters == 2) || (counter == 2 && counters == 3)) {
						scores = 25;
					}
				}
				counter = 0;
				counters = 0;
			}
			counter = 0;
			counters = 0;

		}
		scoreBoard [category][player-1] = scores;
		display.updateScorecard(category, player, scores);
	}
	public void arrayMaker(int player, int category) {
		int counter1 = 0;
		int counter2 = 0;
		int counter3 = 0;
		int counter4 = 0;
		int counter5 = 0;
		int counter6 = 0;
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 1) {
				counter1++;
			}
		}
		newDiceArray[0] = counter1;	
		
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 2) {
				counter2++;
			}
		}
		newDiceArray[1] = counter2;
		
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 3) {
				counter3++;
			}
		}
		newDiceArray[2] = counter3;
		
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 4) {
				counter4++;
			}
		}
		newDiceArray[3] = counter4;
		
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 5) {
				counter5++;
			}
		}
		newDiceArray[4] = counter5;
		
		for (int i = 0; i < N_DICE; i++) {
			if (dice[i] == 6) {
				counter6++;
			}
		}
		newDiceArray[5] = counter6;
	}
	
	public void smallStraight(int player, int category) {
		arrayMaker(player, category);
		int scores = 0;
		int counter = 0;
		for(int i = 0; i < N_DICE; i++) {
			if(newDiceArray[i] >= 1) {
				counter++;
			}
		}
		if (counter >= 4) {
			scores = 30;
		}
		scoreBoard [category][player-1] = scores;
		display.updateScorecard(category, player, scores);
	}
	
	public void largeStraight(int player, int category) {
		arrayMaker(player, category);
		int scores = 0;
		int counter = 0;
		for(int i = 0; i < N_DICE+1; i++) {
			if(newDiceArray[i] >= 1) {
				counter++;
			}
		}
		if (counter >= 5) {
			scores = 40;
		}
		scoreBoard [category][player-1] = scores;
		display.updateScorecard(category, player, scores);
	}
	
	public void gameOver() {
		gameEnd = true;
		int counter = 0;
		for (int t = 1; t <= nPlayers; t++) {
			for (int i = 1; i < scoreBoard.length-1; i++) {
				if (scoreBoard[i][t] >= 1) {
					counter++;
				}
			}
		}
		if (counter >= 17*nPlayers) {
			gameEnd = false;
		}
	}
	
	public void checkTotals(int player) {
		int major = 0;
		int scores = 0;
		int counter = 0;
		for (int i = 1; i < 7; i++) {
			if (scoreBoard[i][player] >= 1) {
				counter++;
			}
			major = scoreBoard[i][player];
			scores = scores + major;
		}
		if (counter >= 5) {
			scoreBoard [7][player-1] = scores;
			display.updateScorecard(7, player, scores);
		}
		if (scores >= 63) {
			scoreBoard [8][player-1] = 35;
			display.updateScorecard(8, player, scores);
		}
	}

	
	/* Private instance variables */
	int[] dice;
	int[] newDiceArray = new int [6];
	boolean gameEnd;
	private int nPlayers;
	int[][] scoreBoard = new int [N_CATEGORIES][nPlayers];
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
