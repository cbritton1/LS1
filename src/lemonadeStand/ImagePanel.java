package lemonadeStand;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;

@SuppressWarnings({ "serial", "unused" })
public class ImagePanel extends JPanel {
	private boolean loser = false;
	private boolean winner = false;
	private boolean storm = false;
	private boolean bully = false;
	private String[] kids = { "bobs1", "Rick", "Sonic", "Morty", "homerRight", "trollface", };
	private int kidIndex;
	private String[] bullys = { "Bully3", "Bully1", "BullyWedgie", "Bully2", "Bully" };
	private static int bullyIndex;

	public static void setBullyIndex(int index) {
		bullyIndex = index;
	}

	public ImagePanel() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (storm) {
			createLemonadeStand(g);
			createStormBackground(g);
		} else if (bully) {
			normalDay(g);
			createBully(g);
		} else if (loser) {
			loserImage(g);
		} else if (winner) {
			winnerImage(g);
		} else {
			normalDay(g);
			createKidLeft(g);
		}

	}

	private void normalDay(Graphics g) {
		createClouds(g);
		createLemonadeStand(g);
	}

	public void changeKid() {
		kidIndex = ++kidIndex % kids.length;
		repaint();
	}

	public boolean isStorm() {
		return storm;
	}

	public void setStorm(boolean storm) {
		this.storm = storm;
		repaint();
	}

	public boolean isBully() {
		return bully;
	}

	public void setBully(boolean bully) {
		this.bully = bully;
		repaint();
	}

	public void setLoser(boolean loser) {
		this.loser = loser;
		repaint();
		Thread thread3 = new Thread() {
			public void run() {
				Music.youLose();
			}
		};
		thread3.start();
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
		repaint();
		Thread thread3 = new Thread() {
			public void run() {
				Music.playWinner();
			}
		};
		thread3.start();
	}

	private void createBully(Graphics g) {
		ImageIcon loser = new ImageIcon(
				ImagePanel.class.getResource("/lemonadeStand/Images/" + bullys[bullyIndex] + ".png"));
		loser.paintIcon(this, g, 80, 250);
	}

	private void createStormBackground(Graphics g) {
		setBackground(new Color(51, 51, 51));
		ImageIcon background = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/stormLeft.png"));
		background.paintIcon(this, g, 100, 20);
		ImageIcon background2 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/stormRight.png"));
		background2.paintIcon(this, g, 600, 20);
		ImageIcon background3 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/puddle.png"));
		background3.paintIcon(this, g, 20, 350);
		ImageIcon background4 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/puddle1.png"));
		background4.paintIcon(this, g, 100, 400);
		ImageIcon background5 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/puddle2.png"));
		background5.paintIcon(this, g, 600, 400);
		ImageIcon rain = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/rain.gif"));
		rain.paintIcon(this, g, 0, 0);
		ImageIcon rain1 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/rain.gif"));
		rain1.paintIcon(this, g, 0, 300);
		ImageIcon rain2 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/rain.gif"));
		rain2.paintIcon(this, g, 400, 300);
		ImageIcon rain3 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/rain.gif"));
		rain3.paintIcon(this, g, 400, 0);
		ImageIcon rain4 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/rain.gif"));
		rain4.paintIcon(this, g, 800, 300);
		ImageIcon rain5 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/rain.gif"));
		rain5.paintIcon(this, g, 800, 0);
		// repaint();
	}

	private void createLemonadeStand(Graphics g) {
		ImageIcon background = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/backdrop.png"));
		background.paintIcon(this, g, 0, 0);
		ImageIcon sidewalk = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/Sidewalk.png"));
		sidewalk.paintIcon(this, g, 0, 500);
		ImageIcon lemonadeStand = new ImageIcon(
				ImagePanel.class.getResource("/lemonadeStand/Images/LemonadeStand.png"));
		lemonadeStand.paintIcon(this, g, 300, 20);
	}

	private void createClouds(Graphics g) {
		ImageIcon sun = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/Sun.gif"));
		sun.paintIcon(this, g, 540, 0);
		ImageIcon cloud1 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/Cloud1.png"));
		cloud1.paintIcon(this, g, 350, 0);
		ImageIcon cloud2 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/Cloud2.png"));
		cloud2.paintIcon(this, g, 700, 0);
		ImageIcon cloud3 = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/Cloud3.png"));
		cloud3.paintIcon(this, g, 0, 0);
	}

	private void createKidLeft(Graphics g) {
		ImageIcon background = new ImageIcon(
				ImagePanel.class.getResource("/lemonadeStand/Images/" + kids[kidIndex] + ".gif"));
		background.paintIcon(this, g, 50, 210);
	}

	private void loserImage(Graphics g) {
		ImageIcon loser = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/Loser.jpg"));
		loser.paintIcon(this, g, 0, 0);
	}

	private void winnerImage(Graphics g) {
		setBackground(new Color(255, 255, 255));
		ImageIcon loser = new ImageIcon(ImagePanel.class.getResource("/lemonadeStand/Images/Winner.gif"));
		loser.paintIcon(this, g, 30, 0);
	}

}
