package lemonadeStand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.media.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 * Creates a music player to play background music and sound effects.
 * 
 * @author Cory Britton
 *
 */
@SuppressWarnings("unused")
public class Music {

	/**
	 * Creates the background music.
	 */
	public static void playBackgroundMusic() {
		for (int i = 0; i < 50; i++) {
			createMusicPlayer("backgroundMusic");
		}
	}

	/**
	 * Plays the pour sound effect.
	 */
	public static void playPourSound() {
		createMusicPlayer("Pouring");
	}

	/**
	 * Plays the winner sound effect.
	 */
	public static void playWinner() {
		createMusicPlayer("Winner");
	}

	/**
	 * Plays the not enough money sound effect.
	 */
	public static void notEnoughMoney() {
		createMusicPlayer("OutOfMoney");
	}

	/**
	 * Plays the loser music.
	 */
	public static void youLose() {
		createMusicPlayer("lose");
	}

	/**
	 * Plays the storm sound effect.
	 */
	public static void storm() {
		createMusicPlayer("Storm5");
	}

	/**
	 * Plays lotto music.
	 */
	public static void lotto() {
		createMusicPlayer("Lotto");
	}

	/**
	 * Plays bully music.
	 */
	public static void bully() {
		createMusicPlayer("Bully");
	}

	/**
	 * Plays tada music.
	 */
	public static void tada() {
		createMusicPlayer("Tada");
	}

	/**
	 * plays crowd boo music.
	 */
	public static void crowdBoo() {
		createMusicPlayer("CrowdBoo");
	}

	/**
	 * plays crowd cheering music.
	 */
	public static void Cheering() {
		createMusicPlayer("Cheering");
	}

	/**
	 * Plays door closing music.
	 */
	public static void DoorClosing() {
		createMusicPlayer("Doorclosing");
	}

	/**
	 * Plays cash register music.
	 */
	public static void CashRegister() {
		createMusicPlayer("CashRegister1");
	}

	/**
	 * Creates a music player.
	 * 
	 * @param file
	 */
	private static void createMusicPlayer(String file) {
		try {
			FileInputStream fileInputStream = new FileInputStream("src/lemonadeStand/Audio/" + file + ".mp3");
			Player player = new Player(fileInputStream);
			player.play();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}

}
