package lemonadeStand;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is the lottery logic.
 * 
 * @author Cory Britton
 *
 */
@SuppressWarnings({ "unused", "serial" })
public class Lottery extends DemoLemonadeStand {
	private static int matchCheck;
	private static int percent;
	private static int finalAccountBal;
	static int[] random;

	public static String getRandom() {
		StringBuilder sb = new StringBuilder();
		for (int el : random) {
			sb.append(el + " ");
		}
		return sb.toString();
	}

	private static int moneyWon;

	public static int getMoneyWon() {
		return moneyWon;
	}

	public static int getMatchCheck() {
		return matchCheck;
	}

	public static int getPercent() {
		return percent;
	}

	public static int getFinalAccountBal() {
		return finalAccountBal;
	}

	public static int lottery(int currentMoney, int[] userNumbers, int wageredMoney) {

		random = randomNumbers();

		finalAccountBal = currentMoney;

		// Calculates how much money you have after wagering
		currentMoney = currentMoney - wageredMoney;
		matchCheck = compareNumbers(random, userNumbers);
		percent = howMuchMoneyWon(matchCheck);
		moneyWon = (wageredMoney * percent);
		finalAccountBal = (wageredMoney * percent) + currentMoney;

		return finalAccountBal;
	}

	/**
	 * Picks random number between 1 - 20.
	 * 
	 * @return
	 */
	public static int[] randomNumbers() {
		List<Integer> shuffeling = new ArrayList<Integer>();
		for (int i = 1; i < 21; i++) {
			shuffeling.add(i);
		}

		Collections.shuffle(shuffeling);
		int[] lottoArray = new int[5];
		for (int j = 0; j < 5; j++) {
			lottoArray[j] = shuffeling.get(j);
		}
		return lottoArray;
	}

	/**
	 * compare user numbers to the random numbers in the lottery.
	 * 
	 * @param lottoArray
	 * @param userArray
	 * @return
	 */
	public static int compareNumbers(int[] lottoArray, int[] userArray) {
		int matchCheck = 0;
		for (int i = 0; i < lottoArray.length; i++) {
			for (int j = 0; j < lottoArray.length; j++) {
				if (lottoArray[i] == userArray[j]) {
					matchCheck++;
				}
			}
		}
		if (matchCheck > 5) {
			matchCheck = 5;
		}
		return matchCheck;
	}

	/**
	 * Pays out depending on how many matches you got.
	 * 
	 * @param numMatches
	 * @return
	 */
	public static int howMuchMoneyWon(int numMatches) {
		int percent = 0;
		switch (numMatches) {
		case 0:
			Thread thread3 = new Thread() {
				public void run() {
					Music.crowdBoo();
				}
			};
			thread3.start();
			break;
		case 1:
			cheering();
			percent = 1;
			break;
		case 2:
			cheering();
			percent = 2;
			break;
		case 3:
			cheering();
			percent = 3;
			break;
		case 4:
			cheering();
			percent = 5;
			break;
		case 5:
			cheering();
			percent = 10;
			break;
		}
		return percent;
	}

	private static void cheering() {
		Thread thread4 = new Thread() {
			public void run() {
				Music.Cheering();
			}
		};
		thread4.start();
	}

}