package views;

import java.io.File;

import engine.Game;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;

import javafx.scene.control.Label;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	public static final String csvPath = "D:\\CMS SEM 4 SEIF (MET)\\(CSEN401) Computer Programming Lab\\GAME\\Milestone 3\\Working\\The-Last-of-Us-legacy-main\\src\\engine\\";
	public static final String resPath = "file:\\" + csvPath + "resources\\";
	public static Stage primaryStage;
	public static String path = csvPath + "\\resources\\startsceneSelect.wav";
	public static Media media = new Media(new File(path).toURI().toString());
	public static MediaPlayer mediaPlayer = new MediaPlayer(media);
	public static String path1 = csvPath + "\\resources\\The Last of Us Theme.mp3";
	public static Media media1 = new Media(new File(path1).toURI().toString());
	public static MediaPlayer mediaPlayer1 = new MediaPlayer(media1);
	
	public static Font font1 = Font.loadFont(Main.resPath + "mk5style.ttf", 90);
	public static Font font2 = Font.loadFont(Main.resPath + "mkda.ttf", 40);
	public static Font font3 = Font.loadFont(Main.resPath + "mk1.ttf", 25);
	public static Font font4 = Font.loadFont(Main.resPath + "mk1.ttf", 18);
	public static Font font5 = Font.loadFont(Main.resPath + "MK4.TTF", 30);
	public static Font font6 = Font.loadFont(Main.resPath + "MK4.TTF", 40);
	public static Font font7 = Font.loadFont(Main.resPath + "MK4.TTF", 90);
	public static Font font8 = Font.loadFont(Main.resPath + "MK4.TTF", 70);
	public static Font font9 = Font.loadFont(Main.resPath + "mk2.ttf", 15);
	public static EventHandler<Event> e;
	public static EventHandler<Event> e2;

	@Override
	public void start(Stage primaryStage) {
		mediaPlayer.setVolume(0.1);
		try {
			e = new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					Label l = (Label) (arg0.getSource());
					l.setOpacity(0.8);

					l.setCursor(javafx.scene.Cursor.HAND);
					// l.setTextFill(Paint.valueOf("#7fff00"));
					mediaPlayer.setAutoPlay(true);
					DropShadow ds = new DropShadow();
					ds.setOffsetY(3.0f);
       				ds.setColor(Color.BLUE);
					ds.setRadius(30);
					l.setEffect(ds);
					// mediaPlayer.play();
					
					// Button b = (Button) (arg0.getSource());
					// b.setOpacity(0.8);
					// b.setBorder(Border.EMPTY);
					
					// b.setBlendMode(BlendMode.DARKEN);
					// b.setWrapText(true);
					
				}
			};

			e2 = new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					((Label) (arg0.getSource())).setOpacity(1);
					mediaPlayer.setAutoPlay(false);
					mediaPlayer.stop();
					((Label) (arg0.getSource())).setEffect(null);
				}
			};

			Main.primaryStage = primaryStage;
			primaryStage.setX(0);
			primaryStage.setY(0);
			Game.loadHeroes(csvPath + "heros.csv");

			Image icon = new Image(resPath + "icon.jpeg");
			primaryStage.getIcons().add(icon);

			primaryStage.setTitle("The Last of Us");
			// primaryStage.setFullScreenExitHint("");
			primaryStage.setFullScreen(false);
			primaryStage.setResizable(false);

			// background music

			mediaPlayer.setAutoPlay(true);
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
			// primaryStage.setFullScreen(true);
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
