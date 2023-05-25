package views;

import java.io.File;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;

public class StartScene {
    public static VBox root = new VBox();
    public static Scene startScene = new Scene(root, 1300, 680);

    public static void setup_starting_scene(){
        root.getChildren().clear();
        // Main.primaryStage.setFullScreen(true);
        root.setSpacing(25);
        root.setAlignment(Pos.CENTER);

        // setting background image
        root.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "background.jpg"), 
                        BackgroundRepeat.REPEAT, 
                        BackgroundRepeat.NO_REPEAT, 
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        EventHandler<MouseEvent> e = new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                if(((Button)(arg0.getSource())).getText().equals("Start Game")){
                    ChooseHeroScene.setup_chooseHeroScene();
                    Main.primaryStage.setScene(ChooseHeroScene.chooseHeroScene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }else if(((Button)(arg0.getSource())).getText().equals("Rules")){
                    RulesScene.setup_rulesScene();
                    Main.primaryStage.setScene(RulesScene.rulesScene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }else if(((Button)(arg0.getSource())).getText().equals("Controls")){
                    ControlsScene.setup_controlsScene();
                    Main.primaryStage.setScene(ControlsScene.controls_scene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }else if(((Button)(arg0.getSource())).getText().equals("Credits")){
                    // go to credits scene
                    // Main.primaryStage.setScene(CreditsScene.credits_scene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }
                String path = Main.csvPath + "\\resources\\changingTabs.mp3";
                Media media = new Media(new File(path).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
            }

        };

        Label title = new Label("The Last of Us");
        title.setFont(new Font("Arial", 50));
        title.setFont(Main.font1);
        
        Button startGame = new Button("Start Game");
        startGame.setOnMouseClicked(e);
        startGame.setOnMouseEntered(Main.e);
        startGame.setOnMouseExited(Main.e2);
        startGame.setPrefSize(250, 50);
        startGame.setFont(Main.font5);

        Button rules = new Button("Rules");
        rules.setOnMouseClicked(e);
        rules.setOnMouseEntered(Main.e);
        rules.setOnMouseExited(Main.e2);
        rules.setPrefSize(250, 50);
        rules.setFont(Main.font5);
        
        Button controls = new Button("Controls");
        controls.setOnMouseClicked(e);
        controls.setOnMouseEntered(Main.e);
        controls.setOnMouseExited(Main.e2);
        controls.setPrefSize(250, 50);
        controls.setFont(Main.font5);
        
        Button credits = new Button("Credits");
        credits.setOnMouseClicked(e);
        credits.setOnMouseEntered(Main.e);
        credits.setOnMouseExited(Main.e2);
        credits.setPrefSize(250, 50);
        credits.setFont(Main.font5);
        
        root.getChildren().addAll(title, startGame, rules, controls, credits);
    }
}
