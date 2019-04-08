package lemonadeStand;

import java.awt.Color;
import java.util.Random;

/**
 * runs the storm, bully, or lotto.
 * 
 * @author Cory Britton
 *
 */
@SuppressWarnings({ "serial", "unused" })
public class StormBullyLotto extends DemoLemonadeStand {

	/**
	 * decides if you get to experience the storm, bully, or lotto.
	 * 
	 * @param money
	 * @param chance
	 * @return
	 */
	public static int stormOrBully(int money, int chance) {
		Random rand = new Random();
		int num = rand.nextInt(chance) + 1;

		if (num <= chanceOfStorm * chance) {
			incrementDay();
			imagePanel.setStorm(true);
			Thread thread3 = new Thread() {
				public void run() {
					Music.storm();
				}
			};
			thread3.start();
			money = storm(money);
			if (money == 0) {
				DemoLemonadeStand.txtrOutputTextPanel.setText("You Lost all of Your Money in the Storm!!");
				imagePanel.setLoser(true);
				Music.youLose();
			}

	    }else if ((num >= (minChanceOfLotto * chance)) && (num <= (maxChanceOfLotto * chance))){
	    	imagePanel.setLottery(true);
	    	Music.tada();
			parentPanel.removeAll();
			parentPanel.add(likeToPlayLottoPanel);
			parentPanel.repaint();
			parentPanel.revalidate();
//	       money = Lottery.lottery(money);
//	         
		} else if ((num >= (minChanceOfBully * chance)) && (num <= (maxChanceOfBully * chance))) {
			incrementDay();
			money = Bully.bully(money);
			imagePanel.setBully(true);
			Thread thread3 = new Thread() {
				public void run() {
					Music.bully();
				}
			};
			thread3.start();
		}
		return money;

	}

	/**
	 * Increments the day.
	 */
	private static void incrementDay() {
		int dayCount = getDay();
		setDay(++dayCount);
		dayInt.setText(String.valueOf(dayCount));
		cupsBeingMade.setText("");
	}

	/**
	 * Returns how much money you lost in the storm.
	 * 
	 * @param money
	 * @return
	 */
	public static int storm(int money) {
		Random rand = new Random();
		int keep = rand.nextInt(3) + 7;
		int newMoney = (int) (money * (keep * 0.1));
		int loss = money - newMoney;
		DemoLemonadeStand.txtrOutputTextPanel.setText("You Lost " + loss + " dollars due to the storm today.");
		return newMoney;
	}
}
