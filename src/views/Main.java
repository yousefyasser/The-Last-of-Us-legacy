package views;

import java.io.File;

import engine.Game;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	public static final String csvPath = "D:\\CMS SEM 4 SEIF (MET)\\(CSEN401) Computer Programming Lab\\GAME\\Milestone 3\\Working\\The-Last-of-Us-legacy-main\\src\\engine\\";
	public static final String resPath = "file:\\" + csvPath + "resources\\";
	public static Stage primaryStage;
	public static MediaPlayer mediaPlayer;
	public static Font font1 = Font.loadFont(Main.resPath + "mk5style.ttf", 90);
	public static Font font2 = Font.loadFont(Main.resPath + "mkda.ttf", 40);
	public static Font font3 = Font.loadFont(Main.resPath + "mk1.ttf", 25);
	public static Font font4 = Font.loadFont(Main.resPath + "mk1.ttf", 18);
	public static Font font5 = Font.loadFont(Main.resPath + "MK4.TTF", 30);
	public static EventHandler<Event> e;
	public static EventHandler<Event> e2;

	@Override
	public void start(Stage primaryStage) {
		try {
			e = new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					((Button) (arg0.getSource())).setOpacity(0.8);
				}
			};

			e2 = new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					((Button) (arg0.getSource())).setOpacity(1);
				}
			};

			Main.primaryStage = primaryStage;
			primaryStage.setX(0);
			primaryStage.setY(0);
			Game.loadHeroes(csvPath + "heros.csv");

			Image icon = new Image(resPath + "icon.jpeg");
			primaryStage.getIcons().add(icon);

			primaryStage.setTitle("The Last of Us");
			// primaryStage.setFullScreen(true);
			// primaryStage.setFullScreenExitHint("");
			primaryStage.setResizable(false);

			// background music
			String path = csvPath + "\\resources\\bgMusic.mp3";
			Media media = new Media(new File(path).toURI().toString());
			mediaPlayer = new MediaPlayer(media);
			mediaPlayer.setAutoPlay(true);
			mediaPlayer.setVolume(0.1);
			mediaPlayer.setOnEndOfMedia(new Runnable() {
				public void run() {
				  mediaPlayer.seek(Duration.ZERO);
				}
			});

			StartScene.setup_starting_scene();
			primaryStage.setScene(StartScene.startScene);

			// ChooseHeroScene.setup_chooseHeroScene();
			// primaryStage.setScene(ChooseHeroScene.chooseHeroScene);

			// DO NOT RUN SCENE 2 DIRECTLY

			// WinScene.setup_winScene();
			// primaryStage.setScene(WinScene.winScene);

			// GameOverScene.setup_gameOverScene();
			// primaryStage.setScene(GameOverScene.gameOverScene);
			
			// primaryStage.setFullScreen(true);
			// primaryStage.setFullScreenExitHint("");

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch();
	}
}
