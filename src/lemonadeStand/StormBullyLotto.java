package lemonadeStand;

import java.awt.Color;
import java.util.Random;

@SuppressWarnings({ "serial", "unused" })
public class StormBullyLotto extends DemoLemonadeStand {
	public static int stormOrBully(int money, int chance) {
		Random rand = new Random();
		int num = rand.nextInt(chance) + 1;

		if (num <= chanceOfStorm * chance) {
			int dayCount = getDay();
			setDay(++dayCount);
			dayInt.setText(String.valueOf(dayCount));
			cupsBeingMade.setText("");
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
				Music.youLose();
			}

//	    }else if ((num >= (0.10 * chance)) && (num <= (0.30 * chance))){
//	       money = Lottery.lottery(money);
//	         
		} else if ((num >= (minChanceOfBully * chance)) && (num <= (maxChanceOfBully * chance))) {
			int dayCount = getDay();
			setDay(++dayCount);
			dayInt.setText(String.valueOf(dayCount));
			cupsBeingMade.setText("");
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

	public static int storm(int money) {
		Random rand = new Random();
		int keep = rand.nextInt(3) + 7;
		int newMoney = (int) (money * (keep * 0.1));
		int loss = money - newMoney;
		DemoLemonadeStand.txtrOutputTextPanel.setText("You Lost " + loss + " dollars due to the storm today.");
		return newMoney;
	}
}
