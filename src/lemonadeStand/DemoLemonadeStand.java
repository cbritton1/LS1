package lemonadeStand;

import java.awt.BorderLayout;
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

@SuppressWarnings({ "serial", "unused" })
public class DemoLemonadeStand extends JFrame {
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
	public static double chanceOfStorm = .10; // 0% - 0.99%
	public static double minChanceOfBully = .101; // 0% - 0.99%
	public static double maxChanceOfBully = .20; // 0% - 0.99%
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

	// getters and setters
	public static int getDay() {
		return day;
	}

	public static void setDay(int d) {
		day = d;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		play();
	}

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
	 * Create the frame.
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

		imagePanel = new ImagePanel();
		imagePanel.setBackground(new Color(0, 191, 255));
		contentPane.add(imagePanel, BorderLayout.CENTER);

	}

	private JPanel createInfoLabel() {
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 153));

		panel_1.setLayout(new GridLayout(7, 1, 0, 0));

		JLabel lblGameInformation = new JLabel("Game Information");
		lblGameInformation.setBorder(null);
		lblGameInformation.setOpaque(true);
		lblGameInformation.setBackground(new Color(255, 255, 153));
		lblGameInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameInformation.setFont(new Font("Noteworthy", Font.BOLD | Font.ITALIC, 15));
		panel_1.add(lblGameInformation);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 153));
		panel_2.setBorder(new EmptyBorder(10, 8, 0, 8));
		panel_1.add(panel_2);

		JLabel lblMoney = new JLabel("Money: $");
		lblMoney.setFont(new Font("Noteworthy", Font.BOLD, 14));
		panel_2.add(lblMoney);
		lblMoney.setHorizontalAlignment(SwingConstants.LEFT);

		moneyInt = new JLabel(String.valueOf(money));
		moneyInt.setFont(new Font("Noteworthy", Font.BOLD, 14));
		panel_2.add(moneyInt);
		moneyInt.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 153));
		panel_3.setBorder(new EmptyBorder(10, 8, 0, 8));
		panel_1.add(panel_3);

		JLabel lblDay = new JLabel("Day: ");
		lblDay.setFont(new Font("Noteworthy", Font.BOLD, 14));
		panel_3.add(lblDay);

		dayInt = new JLabel(String.valueOf(day));
		dayInt.setFont(new Font("Noteworthy", Font.BOLD, 14));
		panel_3.add(dayInt);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.add(panel);
		panel.setLayout(new GridLayout(3, 0, 0, 0));

		JLabel lblYouCouldHave = new JLabel("You could have UP to");
		lblYouCouldHave.setFont(new Font("Noteworthy", Font.PLAIN, 15));
		lblYouCouldHave.setForeground(new Color(255, 255, 255));
		lblYouCouldHave.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouCouldHave.setBorder(new EmptyBorder(4, 4, 0, 4));
		panel.add(lblYouCouldHave);

		peopleCounter = new JLabel(String.valueOf(people));
		peopleCounter.setForeground(new Color(255, 255, 0));
		peopleCounter.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		peopleCounter.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(peopleCounter);

		JLabel lblPeopleShowUp = new JLabel("people show up");
		lblPeopleShowUp.setFont(new Font("Noteworthy", Font.PLAIN, 15));
		lblPeopleShowUp.setForeground(new Color(255, 255, 255));
		lblPeopleShowUp.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPeopleShowUp);

		JLabel label = new JLabel("                                ");
		panel_1.add(label);
		return panel_1;
	}

	private JPanel createOutputTextPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.LIGHT_GRAY);

		panel.setLayout(new BorderLayout(0, 0));

		JPanel controlPanelArea = new JPanel();
		controlPanelArea.setBorder(new LineBorder(new Color(255, 250, 205), 2));
		controlPanelArea.setBackground(new Color(255, 255, 204));
		panel.add(controlPanelArea, BorderLayout.EAST);
		controlPanelArea.setLayout(new GridLayout(3, 1, 0, 0));

		JTextArea txtrHowMuch = new JTextArea();
		txtrHowMuch.setFont(new Font("Noteworthy", Font.PLAIN, 14));
		txtrHowMuch.setBackground(new Color(255, 250, 205));
		txtrHowMuch.setBorder(new EmptyBorder(4, 4, 8, 4));
		txtrHowMuch.setText("How many cups would \n" + "you like to make?");
		controlPanelArea.add(txtrHowMuch);

		// CUPS MADE INPUT
		cupsBeingMade = new JTextField();
		cupsBeingMade.setHorizontalAlignment(SwingConstants.CENTER);
		cupsBeingMade.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		controlPanelArea.add(cupsBeingMade);
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
		controlPanelArea.add(btnNewButton);

		txtrOutputTextPanel = new JTextPane();
		txtrOutputTextPanel.setFont(new Font("Noteworthy", Font.PLAIN, 15));
		txtrOutputTextPanel.setBorder(new EmptyBorder(8, 8, 0, 0));
		txtrOutputTextPanel.setEditable(false);
		txtrOutputTextPanel.setText(
				"To start, enter how many cups you would like to make in the field provided and press \"Next Day\".\n");
		panel.add(txtrOutputTextPanel, BorderLayout.CENTER);
		return panel;
	}

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
