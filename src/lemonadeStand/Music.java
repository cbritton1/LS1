package lemonadeStand;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.media.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

@SuppressWarnings("unused")
public class Music {
	public static void playBackgroundMusic() {
		for (int n = 0; n < 200; n++) {
			try {
				FileInputStream fileInputStream = new FileInputStream("src/lemonadeStand/Audio/backgroundMusic.mp3");
				Player player = new Player(fileInputStream);
				player.play();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
	}

	public static void playPourSound() {
		createMusicPlayer("Pouring");
	}

	public static void playWinner() {
		createMusicPlayer("Winner");
	}

	public static void notEnoughMoney() {
		createMusicPlayer("OutOfMoney");
	}

	public static void youLose() {
		createMusicPlayer("lose");
	}

	public static void storm() {
		createMusicPlayer("Storm5");
	}

	public static void lotto() {
		createMusicPlayer("Lotto");
	}
	
	public static void bully() {
		createMusicPlayer("Bully");
	}

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
