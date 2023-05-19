package views;

import java.io.File;

import engine.Game;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	public static final String csvPath = "C:\\Users\\OS\\Desktop\\guc coursework\\sem 4\\CS 4 (Game)\\project\\milestone 3\\src\\engine\\";
	public static final String resPath = "file:\\" + csvPath + "resources\\";
	public static Stage primaryStage;
	public static MediaPlayer mediaPlayer;

	@Override
	public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			primaryStage.setX(0);
			primaryStage.setY(0);
			Game.loadHeroes(csvPath + "heros.csv");
			primaryStage.setTitle("The Last of Us");

			// background music
			String path = csvPath + "\\resources\\bgMusic.mp3";
			Media media = new Media(new File(path).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
				  mediaPlayer.seek(Duration.ZERO);
				}
			});

			StartScene.setup_starting_scene();
			primaryStage.setScene(StartScene.startScene);

			// Scene1.setup_scene1();
			// primaryStage.setScene(Scene1.scene1);

			// DO NOT RUN SCENE 2 DIRECTLY

			// WinScene.setup_winScene();
			// primaryStage.setScene(WinScene.winScene);

			// GameOverScene.setup_gameOverScene();
			// primaryStage.setScene(GameOverScene.gameOverScene);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch();
	}
}
