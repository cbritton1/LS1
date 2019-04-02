package lemonadeStand;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;

/**
 * Creates the game Lemonade Stand.
 * 
 * @author Cory Britton
 *
 */
@SuppressWarnings({ "serial", "unused" })
public class DemoLemonadeStand extends JFrame {
	private CardLayout cardLayout = new CardLayout();
	private JPanel contentPane;
	public static JTextField cupsBeingMade;

	// set amounts
	private int amountToWin = 100;
	int chanceToHit = 100;

	/*
	 * for testing purposes only. While running leave at chanceOfStorm .05,
	 * minChanceOfBully = .051, maxChanceOfBully = .10, minChanceOfLotto = .101,
	 * maxChanceOfLotto = .30
	 */
	public static double chanceOfStorm = .05; // 0% - 0.99%
	public static double minChanceOfBully = .051; // 0% - 0.99%
	public static double maxChanceOfBully = .10; // 0% - 0.99%
	public static double minChanceOfLotto = .0; // 0% - 0.99%
	public static double maxChanceOfLotto = .0; // 0% - 0.99%

	private int money = 10;
	private static int day = 1;
	private int people = 11;
	private int randomPeople;
	private int cupsMade;
	private int cupsSold;
	private JLabel peopleCounter;
	protected static JLabel dayInt;

	public static JTextPane txtrOutputTextPanel;
	private JLabel moneyInt;
	private JLabel lblTitle;
	private static JButton btnNewButton;
	public static ImagePanel imagePanel;

	/**
	 * Returns the day it is.
	 * 
	 * @return
	 */
	public static int getDay() {
		return day;
	}

	/**
	 * Sets what day it is.
	 * 
	 * @param d
	 */
	public static void setDay(int d) {
		day = d;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		play();
	}

	/**
	 * Starts the game.
	 */
	static void play() {
		Thread thread2 = new Thread() {
			public void run() {
				Music.playBackgroundMusic();
			}
		};
		thread2.start();

		Thread thread1 = new Thread() {
			public void run() {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							DemoLemonadeStand frame = new DemoLemonadeStand();
							frame.setVisible(true);
							frame.getRootPane().setDefaultButton(btnNewButton);
							Instructions.main(null);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		};
		thread1.start();
	};

	/**
	 * Create the main frame.
	 */
	public DemoLemonadeStand() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Lemonade Stand 2.0");
		setBounds(100, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 6, true));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblTitle = createLblTitle();
		contentPane.add(lblTitle, BorderLayout.NORTH);

		JPanel panel = createOutputTextPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		JPanel panel_1 = createInfoLabel();
		contentPane.add(panel_1, BorderLayout.WEST);

		JPanel parentPanel = new JPanel();
		contentPane.add(parentPanel, BorderLayout.CENTER);

		imagePanel = new ImagePanel();
		parentPanel.setLayout(new CardLayout(0, 0));
		parentPanel.add(imagePanel, "name_30405707861963");
		imagePanel.setBackground(new Color(0, 191, 255));
	}

	/**
	 * Creates the game information panel.
	 * 
	 * @return
	 */
	private JPanel createInfoLabel() {
		JPanel gameInformationPanel = new JPanel();
		gameInformationPanel.setBackground(new Color(255, 255, 153));

		gameInformationPanel.setLayout(new GridLayout(7, 1, 0, 0));

		JLabel lblGameInformation = new JLabel("Game Information");
		lblGameInformation.setBorder(null);
		lblGameInformation.setOpaque(true);
		lblGameInformation.setBackground(new Color(255, 255, 153));
		lblGameInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameInformation.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 15));
		gameInformationPanel.add(lblGameInformation);

		JPanel moneyPanel = new JPanel();
		moneyPanel.setBackground(new Color(255, 255, 153));
		moneyPanel.setBorder(new EmptyBorder(10, 8, 0, 8));
		gameInformationPanel.add(moneyPanel);

		JLabel lblMoney = new JLabel("Money: $");
		lblMoney.setFont(new Font("Noteworthy", Font.BOLD, 14));
		moneyPanel.add(lblMoney);
		lblMoney.setHorizontalAlignment(SwingConstants.LEFT);

		moneyInt = new JLabel(String.valueOf(money));
		moneyInt.setFont(new Font("Noteworthy", Font.BOLD, 14));
		moneyPanel.add(moneyInt);
		moneyInt.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel dayPanel = new JPanel();
		dayPanel.setBackground(new Color(255, 255, 153));
		dayPanel.setBorder(new EmptyBorder(10, 8, 0, 8));
		gameInformationPanel.add(dayPanel);

		JLabel lblDay = new JLabel("Day: ");
		lblDay.setFont(new Font("Noteworthy", Font.BOLD, 14));
		dayPanel.add(lblDay);

		dayInt = new JLabel(String.valueOf(day));
		dayInt.setFont(new Font("Noteworthy", Font.BOLD, 14));
		dayPanel.add(dayInt);

		JPanel peopleCountPanel = new JPanel();
		peopleCountPanel.setBackground(new Color(0, 0, 0));
		peopleCountPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		gameInformationPanel.add(peopleCountPanel);
		peopleCountPanel.setLayout(new GridLayout(3, 0, 0, 0));

		JLabel lblYouCouldHave = new JLabel("You could have UP to");
		lblYouCouldHave.setFont(new Font("Noteworthy", Font.PLAIN, 15));
		lblYouCouldHave.setForeground(new Color(255, 255, 255));
		lblYouCouldHave.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouCouldHave.setBorder(new EmptyBorder(4, 4, 0, 4));
		peopleCountPanel.add(lblYouCouldHave);

		peopleCounter = new JLabel(String.valueOf(people));
		peopleCounter.setForeground(new Color(255, 255, 0));
		peopleCounter.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		peopleCounter.setHorizontalAlignment(SwingConstants.CENTER);
		peopleCountPanel.add(peopleCounter);

		JLabel lblPeopleShowUp = new JLabel("people show up");
		lblPeopleShowUp.setFont(new Font("Noteworthy", Font.PLAIN, 15));
		lblPeopleShowUp.setForeground(new Color(255, 255, 255));
		lblPeopleShowUp.setHorizontalAlignment(SwingConstants.CENTER);
		peopleCountPanel.add(lblPeopleShowUp);

		JLabel label = new JLabel("                                ");
		gameInformationPanel.add(label);
		return gameInformationPanel;
	}

	/**
	 * Creates the input panel.
	 * 
	 * @return
	 */
	private JPanel createOutputTextPanel() {
		JPanel mainInputPanel = new JPanel();
		mainInputPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainInputPanel.setBackground(Color.LIGHT_GRAY);

		mainInputPanel.setLayout(new BorderLayout(0, 0));

		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new LineBorder(new Color(255, 250, 205), 2));
		inputPanel.setBackground(new Color(255, 255, 204));
		mainInputPanel.add(inputPanel, BorderLayout.EAST);
		inputPanel.setLayout(new GridLayout(3, 1, 0, 0));

		JTextArea txtrHowMuch = new JTextArea();
		txtrHowMuch.setFont(new Font("Noteworthy", Font.PLAIN, 14));
		txtrHowMuch.setBackground(new Color(255, 250, 205));
		txtrHowMuch.setBorder(new EmptyBorder(4, 4, 8, 4));
		txtrHowMuch.setText("How many cups would \n" + "you like to make?");
		inputPanel.add(txtrHowMuch);

		// CUPS MADE INPUT
		cupsBeingMade = new JTextField();
		cupsBeingMade.setHorizontalAlignment(SwingConstants.CENTER);
		cupsBeingMade.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		inputPanel.add(cupsBeingMade);
		cupsBeingMade.setColumns(10);

//NEXT DAY ACTION BUTTON	
		btnNewButton = new JButton("Next Day");
		btnNewButton.setFont(new Font("Noteworthy", Font.PLAIN, 13));
		btnNewButton.setOpaque(true);
		btnNewButton.setBackground(new Color(204, 255, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// reset storm to false
				imagePanel.setStorm(false);
				imagePanel.setBully(false);
				imagePanel.setBackground(new Color(0, 191, 255));

				if (!LemonadeStandLogic.canAffordRequestedCups(Integer.parseInt(cupsBeingMade.getText()), money)) {
					if (money > 0) {
						txtrOutputTextPanel.setText("You do not have enough money to make that many cups.");
						cupsBeingMade.setText("");
						Music.notEnoughMoney();
					}
				} else {
					money = StormBullyLotto.stormOrBully(money, chanceToHit);
					moneyInt.setText(String.valueOf(money));
					if (!(imagePanel.isStorm()) && !(imagePanel.isBully())) {
						Music.playPourSound();
						imagePanel.changeKid();
						people = day + 11;
						day++;
						peopleCounter.setText(String.valueOf(people));
						dayInt.setText(String.valueOf(day));

						cupsMade = Integer.parseInt(cupsBeingMade.getText());

						randomPeople = LemonadeStandLogic.randomPeople(day);
						cupsSold = LemonadeStandLogic.cupsSold(cupsMade, randomPeople);

						money = money - cupsMade;
						money = LemonadeStandLogic.currentMoney(cupsSold, money);
						money = LemonadeStandLogic.cantSupply(randomPeople, cupsMade, money);

						moneyInt.setText(String.valueOf(money));
						cupsBeingMade.setText("");

						if (money <= 0) {
							txtrOutputTextPanel.setText("You made " + cupsMade + " cups.\n" + randomPeople
									+ " people showed up for your delicious beverage.\n" + "You sold " + cupsSold
									+ " cups.\n\nYou are now a homeless bum. Thanks for playing.");
//							playAgain.main(null);
							imagePanel.setLoser(true);
						} else if (money >= amountToWin) {
							txtrOutputTextPanel.setText("You win! Go treat yo self!");
							imagePanel.setWinner(true);
//							playAgain.main(null);
						} else {
							txtrOutputTextPanel.setText("You made " + cupsMade + " cups.\n" + randomPeople
									+ " people showed up for your delicious beverage.\n" + "You sold " + cupsSold
									+ " cups.");
						}
					}
				}
			}
		});
		inputPanel.add(btnNewButton);

		txtrOutputTextPanel = new JTextPane();
		txtrOutputTextPanel.setFont(new Font("Noteworthy", Font.PLAIN, 15));
		txtrOutputTextPanel.setBorder(new EmptyBorder(8, 8, 0, 0));
		txtrOutputTextPanel.setEditable(false);
		txtrOutputTextPanel.setText(
				"To start, enter how many cups you would like to make in the field provided and press \"Next Day\".\n");
		mainInputPanel.add(txtrOutputTextPanel, BorderLayout.CENTER);
		return mainInputPanel;
	}

	/**
	 * Creates the title bar.
	 * 
	 * @return
	 */
	private JLabel createLblTitle() {
		lblTitle = new JLabel("Lemonade Stand");
		lblTitle.setBorder(new EmptyBorder(2, 0, 0, 0));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(255, 255, 204));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Noteworthy", Font.BOLD, 22));
		return lblTitle;
	}

}
