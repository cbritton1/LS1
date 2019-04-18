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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

/**
 * Creates the game Lemonade Stand.
 * 
 * @author Cory Britton and Andrew McMullin
 *
 */
@SuppressWarnings({ "serial", "unused" })
public class DemoLemonadeStand extends JFrame {
	private CardLayout cardLayout = new CardLayout();
	private JPanel contentPane;
	public static JTextField cupsBeingMade;

	// set your amounts
	private int amountToWin = 100; //while running set at 500
	private static int money = 10; //while running set at 10
	
	// These are for testing purposes only.
	public static double chanceOfStorm = .05; // while running set at .05
	public static double minChanceOfBully = .051; // while running set at .051
	public static double maxChanceOfBully = .13; // while running set at .13	
	public static double minChanceOfLotto = .131; // while running set at .131
	public static double maxChanceOfLotto = .20; // while running set at .20
	
	int chanceToHit = 100;
	private static int day = 1;
	private int people = 11;
	private int randomPeople;
	private int cupsMade;
	private int cupsSold;
	private JLabel peopleCounter;
	protected static JLabel dayInt;

	public static JTextPane txtrOutputTextPanel;
	private static JLabel moneyInt;
	private JLabel lblTitle;
	private static JButton btnNewButton;
	public static ImagePanel imagePanel;
	protected static JPanel parentPanel;
	private JPanel lotteryPanel;
	public JTextField luckyNumber1;
	private JTextField luckyNumber2;
	private JTextField luckyNumber3;
	private JTextField luckyNumber4;
	private JTextField luckyNumber5;
	private JPanel luckyNumbersPanel_1;
	private JTextField wageredMoney;
	protected static JPanel likeToPlayLottoPanel;
	private JLabel numbersMatchedLbl;
	private JLabel cashWonLbl;
	private JPanel collectPlayButtonPanel;
	private JButton btnPlay;
	private int helpRate;
	private JLabel matchedNumbersLbl;
	private JPanel payoutsPanel_1;
	// added
	private JPanel storePanel;
	private JTextField nameField;
	private JPanel acceptInstructionsPanel_1;
	private static JLabel lastPlayerLabel;
	private JButton btnCollect;

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

		parentPanel = new JPanel();
		contentPane.add(parentPanel, BorderLayout.CENTER);

		imagePanel = new ImagePanel();
		parentPanel.setLayout(new CardLayout(0, 0));

		JPanel instructionPanel = new JPanel();
		createInstructionPanel(instructionPanel);

		lotteryPanel = new JPanel();
		lotteryPanel.setBorder(null);
		JPanel lottoryImage = createLottoryPanel();
		lotteryPanel.add(lottoryImage, BorderLayout.CENTER);

		storePanel = new StorePanel();
		parentPanel.add(storePanel, "name_85186154351856");

	}

	private void createInstructionPanel(JPanel instructionPanel) {
		parentPanel.add(instructionPanel, "name_26012592013971");
		instructionPanel.setLayout(new BorderLayout(0, 0));

		acceptInstructionsPanel_1 = new JPanel();
		createInstructionPanel(instructionPanel, acceptInstructionsPanel_1);

		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.setOpaque(true);
		btnStartGame.setBackground(new Color(204, 255, 102));
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabledNextDay();
				try (PrintWriter writer = new PrintWriter("src/lemonadeStand/Resources/PastUsers.txt")) {
					writer.println(nameField.getText());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				parentPanel.removeAll();
				parentPanel.add(imagePanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});
		acceptInstructionsPanel_1.add(btnStartGame, BorderLayout.EAST);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 51));
		acceptInstructionsPanel_1.add(panel_2, BorderLayout.WEST);

		JLabel lblLastPlayerWas = new JLabel("Last player was: ");
		lblLastPlayerWas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastPlayerWas.setFont(new Font("Noteworthy", Font.BOLD, 17));
		panel_2.add(lblLastPlayerWas);

		lastPlayerLabel = new JLabel("New label");
		try (Scanner reader = new Scanner(new File("src/lemonadeStand/Resources/PastUsers.txt"))) {
			while (reader.hasNextLine()) {
				lastPlayerLabel.setText(reader.nextLine());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lastPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastPlayerLabel.setFont(new Font("Noteworthy", Font.PLAIN, 17));
		panel_2.add(lastPlayerLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 51));
		acceptInstructionsPanel_1.add(panel_3, BorderLayout.CENTER);

		JLabel lblEnterYourName = new JLabel("Enter Your Name: ");
		panel_3.add(lblEnterYourName);
		lblEnterYourName.setFont(new Font("Noteworthy", Font.BOLD, 17));

		nameField = new JTextField();
		panel_3.add(nameField);
		nameField.setFont(new Font("Noteworthy", Font.PLAIN, 16));
		nameField.setColumns(10);

		JLabel lblInstructions = new JLabel("Instructions");
		lblInstructions.setOpaque(true);
		lblInstructions.setBackground(new Color(255, 255, 51));
		lblInstructions.setFont(new Font("Noteworthy", Font.PLAIN, 28));
		lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		instructionPanel.add(lblInstructions, BorderLayout.NORTH);

		JTextPane txtpnInstructionsGoHere = new JTextPane();
		txtpnInstructionsGoHere.setBackground(new Color(255, 255, 204));
		txtpnInstructionsGoHere.setEditable(false);
		txtpnInstructionsGoHere.setBorder(new EmptyBorder(0, 10, 0, 10));
		txtpnInstructionsGoHere.setFont(new Font("Noteworthy", Font.PLAIN, 16));
		txtpnInstructionsGoHere.setText(
				"We will give you $10 to start with. When you collect $500 you win!\nEach cup of lemonade costs $1.00 to make. "
						+ "Each cup will sell for $2.00.\nYou won't know how many people will "
						+ "show up to buy your delicious beverage. See the information panel to the left to get "
						+ "a sence of how many people could possibly show up.\nFor each person that shows up that you can't provide a "
						+ "glass of lemonade, you will need to reimburse them $1.00 for waiting in this crazy heat.\n\n"
						+ "Keep an eye out for an incoming storm, a passing bully, or a lucky lottery ticket blowing down the "
						+ "street.\n\nOh, and be sure to visit the store to purchase helpful items!");
		instructionPanel.add(txtpnInstructionsGoHere, BorderLayout.CENTER);
	}

	private void createInstructionPanel(JPanel instructionPanel, JPanel acceptInstructionsPanel) {
		instructionPanel.add(acceptInstructionsPanel, BorderLayout.SOUTH);
		acceptInstructionsPanel_1.setLayout(new BorderLayout(0, 0));
		parentPanel.add(imagePanel, "name_30405707861963");
		imagePanel.setBackground(new Color(0, 191, 255));
	}

	private JPanel createLottoryPanel() {

		likeToPlayLottoPanel = new JPanel();
		JPanel lblNewLabel = createLikeToPlayLottoPanel(likeToPlayLottoPanel);
		likeToPlayLottoPanel.add(lblNewLabel, BorderLayout.SOUTH);

		JLabel lblMegaMillions = new JLabel("Mega Millions");
		createLblMegaMillions(lblMegaMillions);
		lotteryPanel.add(lblMegaMillions, BorderLayout.NORTH);

		JPanel lottoryImage = new JPanel();
		lottoryImage.setLayout(new BorderLayout(0, 0));

		collectPlayButtonPanel = new JPanel();
		collectPlayButtonPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		collectPlayButtonPanel.setBackground(new Color(255, 255, 255));
		createLottoCollectPlayButtons(collectPlayButtonPanel);
		lottoryImage.add(collectPlayButtonPanel, BorderLayout.SOUTH);

		JPanel mainLottoryPanel = new JPanel();
		createMainLotteryPanel(lottoryImage, mainLottoryPanel);
		mainLottoryPanel.setLayout(new GridLayout(0, 2, 0, 0));
		return lottoryImage;
	}

	private JPanel createLikeToPlayLottoPanel(JPanel likeToPlayLottoPanel) {
		parentPanel.add(likeToPlayLottoPanel, "name_56485579090336");
		likeToPlayLottoPanel.setLayout(new BorderLayout(0, 0));

		JPanel lblNewLabel = new JPanel();
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setBackground(new Color(204, 255, 153));

		JLabel lblNewLabel_2 = new JLabel("Would you like to play the lottery");
		lblNewLabel_2.setFont(new Font("Noteworthy", Font.PLAIN, 18));
		lblNewLabel.add(lblNewLabel_2);

		JButton btnYes = new JButton("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCollect.setEnabled(false);
				parentPanel.removeAll();
				parentPanel.add(lotteryPanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});
		lblNewLabel.add(btnYes);

		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabledNextDay();
				parentPanel.removeAll();
				parentPanel.add(imagePanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});
		lblNewLabel.add(btnNo);

		JTextArea lblNewLabel_1 = new JTextArea();
		lblNewLabel_1.setBackground(new Color(204, 255, 153));
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setText("   You see a lottery ticket blowing down the sidewalk.\n   You pick it up and notice "
				+ "it is blank!\n   Would you like to test your luck and wager some of your hard earned money "
				+ "playing\n   the lottery?");
		lblNewLabel_1.setFont(new Font("Noteworthy", Font.PLAIN, 25));
		likeToPlayLottoPanel.add(lblNewLabel_1, BorderLayout.NORTH);

		JLabel lottoImage = new JLabel("");
		lottoImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lottoImage.setHorizontalAlignment(SwingConstants.CENTER);
		lottoImage
				.setIcon(new ImageIcon(DemoLemonadeStand.class.getResource("/lemonadeStand/Images/RainingMoney.jpg")));
		likeToPlayLottoPanel.add(lottoImage, BorderLayout.WEST);
		lotteryPanel.setBackground(new Color(204, 255, 153));
		parentPanel.add(lotteryPanel, "name_31126836811327");
		lotteryPanel.setLayout(new BorderLayout(0, 0));
		return lblNewLabel;
	}

	private void createMainLotteryPanel(JPanel lottoryImage, JPanel mainLottoryPanel) {
		lottoryImage.add(mainLottoryPanel, BorderLayout.CENTER);

		JPanel pickYourNumbersPanel = new JPanel();
		mainLottoryPanel.add(pickYourNumbersPanel);
		pickYourNumbersPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblPickYourNumbers = new JLabel("Pick Your Numbers 1 - 20");
		createLblPickYourNumbers(lblPickYourNumbers);
		pickYourNumbersPanel.add(lblPickYourNumbers, BorderLayout.NORTH);

		luckyNumbersPanel_1 = new JPanel();
		luckyNumbersPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		createLuckyNumbersPanel(pickYourNumbersPanel, luckyNumbersPanel_1);

		JLabel lblClickToPlay = new JLabel("");
		lblClickToPlay.setHorizontalAlignment(SwingConstants.RIGHT);
		luckyNumbersPanel_1.add(lblClickToPlay);

		btnPlay = new JButton("Play");
		btnPlay.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnPlay.setOpaque(true);
		luckyNumbersPanel_1.add(btnPlay);
		btnPlay.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				playLottery();
			}
		});
		btnPlay.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnPlay.setForeground(new Color(255, 0, 0));
		btnPlay.setBackground(new Color(204, 255, 0));

		JPanel payoutRecievedInfoPanel = new JPanel();
		mainLottoryPanel.add(payoutRecievedInfoPanel);
		payoutRecievedInfoPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblPayoutRecievedAs = new JLabel("Payout recieved as follows:");
		createLblPayoutRecievedAs(lblPayoutRecievedAs);
		payoutRecievedInfoPanel.add(lblPayoutRecievedAs, BorderLayout.NORTH);

		payoutsPanel_1 = new JPanel();
		payoutsPanel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		createPayoutPanel(payoutRecievedInfoPanel, payoutsPanel_1);

	}

	private void createLblPayoutRecievedAs(JLabel lblPayoutRecievedAs) {
		lblPayoutRecievedAs.setOpaque(true);
		lblPayoutRecievedAs.setForeground(new Color(255, 0, 0));
		lblPayoutRecievedAs.setBackground(new Color(0, 0, 0));
		lblPayoutRecievedAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblPayoutRecievedAs.setFont(new Font("Kokonor", Font.BOLD, 30));
	}

	private void createPayoutPanel(JPanel payoutRecievedInfoPanel, JPanel payoutsPanel) {
		payoutsPanel.setBackground(new Color(255, 255, 153));
		payoutRecievedInfoPanel.add(payoutsPanel, BorderLayout.CENTER);
		payoutsPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel emptyLblNorth = new JLabel("");
		emptyLblNorth.setOpaque(true);
		emptyLblNorth.setBackground(new Color(204, 255, 153));
		payoutsPanel.add(emptyLblNorth);

		JLabel lblMatch1 = new JLabel("Match 1 = Win your money back");
		lblMatch1.setOpaque(true);
		lblMatch1.setBackground(new Color(204, 255, 153));
		lblMatch1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMatch1.setHorizontalAlignment(SwingConstants.CENTER);
		payoutsPanel.add(lblMatch1);

		JLabel lblMatch2 = new JLabel("Match 2 = Win 2x your money");
		lblMatch2.setBackground(new Color(204, 255, 153));
		lblMatch2.setOpaque(true);
		lblMatch2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMatch2.setHorizontalAlignment(SwingConstants.CENTER);
		payoutsPanel.add(lblMatch2);

		JLabel lblMatch3 = new JLabel("Match 3 = Win 3x your money");
		lblMatch3.setOpaque(true);
		lblMatch3.setBackground(new Color(204, 255, 153));
		lblMatch3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		payoutsPanel.add(lblMatch3);
		lblMatch3.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblMatch4 = new JLabel("Match 4 = Win 4x your money");
		lblMatch4.setBackground(new Color(204, 255, 153));
		lblMatch4.setOpaque(true);
		lblMatch4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMatch4.setHorizontalAlignment(SwingConstants.CENTER);
		payoutsPanel.add(lblMatch4);

		JLabel lblMatch5 = new JLabel("Match 5 = Win 5x your money");
		lblMatch5.setOpaque(true);
		lblMatch5.setBackground(new Color(204, 255, 153));
		lblMatch5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblMatch5.setHorizontalAlignment(SwingConstants.CENTER);
		payoutsPanel.add(lblMatch5);

		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(204, 255, 153));
		payoutsPanel_1.add(label);

		matchedNumbersLbl = new JLabel("Winning Numbers are: ");
		matchedNumbersLbl.setBorder(new LineBorder(new Color(0, 0, 0)));
		matchedNumbersLbl.setForeground(new Color(255, 0, 0));
		matchedNumbersLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		matchedNumbersLbl.setHorizontalAlignment(SwingConstants.CENTER);
		matchedNumbersLbl.setBackground(new Color(153, 255, 51));
		matchedNumbersLbl.setOpaque(true);
		payoutsPanel.add(matchedNumbersLbl);

		JPanel winningNumbersLbl = new JPanel();
		winningNumbersLbl.setOpaque(true);
		winningNumbersLbl.setBackground(new Color(255, 255, 102));
		payoutsPanel.add(winningNumbersLbl);

		JLabel lblNumbersMatched = new JLabel("Numbers Matched:");
		winningNumbersLbl.add(lblNumbersMatched);

		numbersMatchedLbl = new JLabel("0");
		winningNumbersLbl.add(numbersMatchedLbl);

		JPanel youWonThisMuchLbl = new JPanel();
		youWonThisMuchLbl.setOpaque(true);
		youWonThisMuchLbl.setBackground(new Color(255, 255, 102));
		payoutsPanel.add(youWonThisMuchLbl);

		JLabel lblAmmountOfCold = new JLabel("Amount of cold, hard cash you won:");
		youWonThisMuchLbl.add(lblAmmountOfCold);
		cashWonLbl = new JLabel("0");
		youWonThisMuchLbl.add(cashWonLbl);
	}

	private void createLuckyNumbersPanel(JPanel pickYourNumbersPanel, JPanel luckyNumbersPanel) {
		luckyNumbersPanel.setBackground(new Color(204, 255, 153));
		pickYourNumbersPanel.add(luckyNumbersPanel, BorderLayout.CENTER);
		luckyNumbersPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lblHowMuchMoney = new JLabel("How much do you wager");
		lblHowMuchMoney.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel_1.add(lblHowMuchMoney);

		wageredMoney = new JTextField();
		wageredMoney.setHorizontalAlignment(SwingConstants.CENTER);
		wageredMoney.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		wageredMoney.setBorder(new LineBorder(new Color(255, 51, 0), 3));
		luckyNumbersPanel_1.add(wageredMoney);
		wageredMoney.setColumns(10);

		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(Color.BLACK);
		luckyNumbersPanel_1.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setBackground(Color.BLACK);
		label_1.setOpaque(true);
		luckyNumbersPanel_1.add(label_1);

		JLabel lblLuckyNumber1 = new JLabel("Lucky Number 1:");
		lblLuckyNumber1.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(lblLuckyNumber1);

		luckyNumber1 = new JTextField();
		luckyNumber1.setForeground(new Color(0, 153, 0));
		luckyNumber1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		luckyNumber1.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(luckyNumber1);
		luckyNumber1.setColumns(10);

		JLabel lblLuckyNumber2 = new JLabel("Lucky Number 2:");
		lblLuckyNumber2.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(lblLuckyNumber2);

		luckyNumber2 = new JTextField();
		luckyNumber2.setForeground(new Color(0, 153, 0));
		luckyNumber2.setSelectedTextColor(new Color(0, 0, 0));
		luckyNumber2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		luckyNumber2.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(luckyNumber2);
		luckyNumber2.setColumns(10);

		JLabel lblLuckyNumber3 = new JLabel("Lucky Number 3:");
		lblLuckyNumber3.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(lblLuckyNumber3);

		luckyNumber3 = new JTextField();
		luckyNumber3.setForeground(new Color(0, 153, 0));
		luckyNumber3.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		luckyNumber3.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(luckyNumber3);
		luckyNumber3.setColumns(10);

		JLabel lblLuckyNumber4 = new JLabel("Lucky Number 4:");
		lblLuckyNumber4.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(lblLuckyNumber4);

		luckyNumber4 = new JTextField();
		luckyNumber4.setForeground(new Color(0, 153, 0));
		luckyNumber4.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		luckyNumber4.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(luckyNumber4);
		luckyNumber4.setColumns(10);

		JLabel lblLuckyNumber5 = new JLabel("Lucky Number 5:");
		lblLuckyNumber5.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(lblLuckyNumber5);

		luckyNumber5 = new JTextField();
		luckyNumber5.setForeground(new Color(0, 153, 0));
		luckyNumber5.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		luckyNumber5.setHorizontalAlignment(SwingConstants.CENTER);
		luckyNumbersPanel.add(luckyNumber5);
		luckyNumber5.setColumns(10);
	}

	private void createLblPickYourNumbers(JLabel lblPickYourNumbers) {
		lblPickYourNumbers.setBackground(new Color(0, 0, 0));
		lblPickYourNumbers.setForeground(new Color(255, 0, 0));
		lblPickYourNumbers.setOpaque(true);
		lblPickYourNumbers.setFont(new Font("Kokonor", Font.BOLD, 30));
		lblPickYourNumbers.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void createLottoCollectPlayButtons(JPanel panel) {
		panel.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel EmptyPlaceholderLeft = new JLabel("");
		EmptyPlaceholderLeft.setOpaque(true);
		EmptyPlaceholderLeft.setBackground(new Color(0, 0, 0));
		panel.add(EmptyPlaceholderLeft);

		JLabel emptyPlaceholderRight = new JLabel("");
		emptyPlaceholderRight.setOpaque(true);
		emptyPlaceholderRight.setBackground(new Color(0, 0, 0));
		panel.add(emptyPlaceholderRight);

		btnCollect = new JButton("Collect");
		btnCollect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setEnabledNextDay();
				matchedNumbersLbl.setText("Winning Numbers are: ");
				txtrOutputTextPanel.setText("You now have  " + Lottery.getFinalAccountBal());
				btnPlay.setEnabled(true);
				parentPanel.removeAll();
				parentPanel.add(imagePanel);
				parentPanel.repaint();
				parentPanel.revalidate();
				wageredMoney.setText("");
				luckyNumber1.setText("");
				luckyNumber2.setText("");
				luckyNumber3.setText("");
				luckyNumber4.setText("");
				luckyNumber5.setText("");
				numbersMatchedLbl.setText("0");
				cashWonLbl.setText("0");
			}
		});

		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setBackground(new Color(0, 0, 0));
		collectPlayButtonPanel.add(label);
		btnCollect.setForeground(new Color(255, 0, 0));
		btnCollect.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnCollect.setOpaque(true);
		btnCollect.setBackground(new Color(204, 255, 153));
		panel.add(btnCollect);
	}

	private void createLblMegaMillions(JLabel lblMegaMillions) {
		lblMegaMillions.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		lblMegaMillions.setFont(new Font("Kokonor", Font.BOLD, 43));
		lblMegaMillions.setHorizontalAlignment(SwingConstants.CENTER);
	}

	/**
	 * Creates the game information panel.
	 * 
	 * @return
	 */
	private JPanel createInfoLabel() {
		JPanel gameInformationPanel = new JPanel();
		gameInformationPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
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

		JLabel label_1 = new JLabel("");
		gameInformationPanel.add(label_1);

		JButton btnStore = new JButton("Enter Store");
		btnStore.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		btnStore.setFont(new Font("Noteworthy", Font.PLAIN, 18));
		btnStore.setOpaque(true);
		btnStore.setBackground(new Color(153, 102, 0));
		btnStore.addActionListener(new ActionListener() {
			JPanel temp = null;

			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				Music.DoorClosing();
				parentPanel.removeAll();
				parentPanel.add(storePanel);
				parentPanel.repaint();
				parentPanel.revalidate();
			}
		});
		gameInformationPanel.add(btnStore);
		return gameInformationPanel;
	}

	/**
	 * Creates the input panel.
	 * 
	 * @return
	 */
	private JPanel createOutputTextPanel() {
		JPanel mainInputPanel = new JPanel();
		mainInputPanel.setPreferredSize(new Dimension(10, 175));
		mainInputPanel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		mainInputPanel.setBackground(Color.LIGHT_GRAY);

		mainInputPanel.setLayout(new BorderLayout(0, 0));

		JPanel inputPanel = new JPanel();
		createInputPanel(mainInputPanel, inputPanel);

		txtrOutputTextPanel = new JTextPane();
		txtrOutputTextPanel.setFont(new Font("Noteworthy", Font.PLAIN, 15));
		txtrOutputTextPanel.setBorder(new EmptyBorder(8, 8, 0, 0));
		txtrOutputTextPanel.setEditable(false);
		txtrOutputTextPanel.setText(
				"To start, enter how many cups you would like to make in the field provided and press \"Next Day\".\n");
		mainInputPanel.add(txtrOutputTextPanel, BorderLayout.CENTER);
		return mainInputPanel;
	}

	private void createInputPanel(JPanel mainInputPanel, JPanel inputPanel) {
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

		cupsBeingMade = new JTextField();
		cupsBeingMade.setHorizontalAlignment(SwingConstants.CENTER);
		cupsBeingMade.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		inputPanel.add(cupsBeingMade);
		cupsBeingMade.setColumns(10);

//NEXT DAY ACTION BUTTON

		btnNewButton = new JButton("Next Day");
		btnNewButton.setFont(new Font("Noteworthy", Font.PLAIN, 13));
		btnNewButton.setOpaque(true);
		setDisabledNextDay();
		btnNewButton.setBackground(new Color(204, 255, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePlay();
			}

		});
		inputPanel.add(btnNewButton);
	}

	/**
	 * Creates the title bar.
	 * 
	 * @return
	 */
	private JLabel createLblTitle() {
		lblTitle = new JLabel("Lemonade Stand");
		lblTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTitle.setOpaque(true);
		lblTitle.setBackground(new Color(255, 255, 204));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Noteworthy", Font.BOLD, 22));
		return lblTitle;
	}

	/**
	 * Sets the amount of money you have.
	 * 
	 * @param cash
	 */
	public static void setMoney(int cash) {
		money = cash;
		moneyInt.setText("" + money);
	}

	/**
	 * Returns how much money you have.
	 * 
	 * @return
	 */
	public static int getMoney() {
		return money;
	}

	/**
	 * Enables the next day button.
	 */
	public static void setEnabledNextDay() {
		btnNewButton.setEnabled(true);
	}

	/**
	 * Disables the next day button
	 */
	public static void setDisabledNextDay() {
		btnNewButton.setEnabled(false);
	}

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
	 * This is the lottery.
	 */
	private void playLottery() {
		btnCollect.setEnabled(true);
		int lucky1 = Integer.parseInt(luckyNumber1.getText());
		int lucky2 = Integer.parseInt(luckyNumber2.getText());
		int lucky3 = Integer.parseInt(luckyNumber3.getText());
		int lucky4 = Integer.parseInt(luckyNumber4.getText());
		int lucky5 = Integer.parseInt(luckyNumber5.getText());
		int wageredInt = Integer.parseInt(wageredMoney.getText());

		int[] userArrays = { lucky1, lucky2, lucky3, lucky4, lucky5 };

		if (wageredInt > money) {
			txtrOutputTextPanel.setText("You do not have enough money to wager!");
			Music.notEnoughMoney();
		} else {
			btnPlay.setEnabled(false);
			Lottery.lottery(money, userArrays, wageredInt);
			money = Lottery.getFinalAccountBal();
			moneyInt.setText("" + Lottery.getFinalAccountBal());

			if (Lottery.getPercent() >= 1) {
				txtrOutputTextPanel
						.setText(String.format("You got %d numbers correct and will be credited %d00%%%n",
								Lottery.getMatchCheck(), Lottery.getPercent()));
				numbersMatchedLbl.setText("" + Lottery.getMatchCheck());
				cashWonLbl.setText("" + Lottery.getMoneyWon());
			} else {
				txtrOutputTextPanel.setText("You got " + Lottery.getMatchCheck()
						+ " numbers correct and will be credited NOTHING BUT HUGS!");
			}
			matchedNumbersLbl.setText("Winning Numbers were: " + Lottery.getRandom());
			if (money <= 0) {
				imagePanel.setLoser(true);
			}

		}
	}

	/**
	 * This executes the game-play.
	 */
	private void gamePlay() {
		try {
			// reset storm to false
			imagePanel.setStorm(false);
			imagePanel.setBully(false);
			ImagePanel.setLottery(false);

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
				if (!(imagePanel.isStorm()) && !(imagePanel.isBully()) && !(imagePanel.isLottery())) {

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
						// playAgain.main(null);
						imagePanel.setLoser(true);
					} else if (money >= amountToWin) {
						txtrOutputTextPanel.setText("You win! Go treat yo self!");
						imagePanel.setWinner(true);
						// playAgain.main(null);
					} else {
						txtrOutputTextPanel.setText("You made " + cupsMade + " cups.\n" + randomPeople
								+ " people showed up for your delicious beverage.\n" + "You sold " + cupsSold
								+ " cups.");
					}

				}
			}
		} catch (NumberFormatException ex) {
			txtrOutputTextPanel.setText("Must enter a number");
		}
	}

}
