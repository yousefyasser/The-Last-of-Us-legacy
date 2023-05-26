package views;



import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class StartScene {
    public static VBox root = new VBox();
    public static Scene startScene = new Scene(root, 1300, 680);

    public static void setup_starting_scene(){
        root.getChildren().clear();
        // Main.primaryStage.setFullScreen(true);
        root.setSpacing(40);
        root.setAlignment(Pos.CENTER);

        // setting background image
        root.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "bg1.png"), 
                        BackgroundRepeat.REPEAT, 
                        BackgroundRepeat.NO_REPEAT, 
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        EventHandler<MouseEvent> e = new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent arg0) {
                if(((Label)(arg0.getSource())).getText().equals("Start Game")){
                    ChooseHeroScene.setup_chooseHeroScene();
                    Main.primaryStage.setScene(ChooseHeroScene.chooseHeroScene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }else if(((Label)(arg0.getSource())).getText().equals("Rules")){
                    RulesScene.setup_rulesScene();
                    Main.primaryStage.setScene(RulesScene.rulesScene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }else if(((Label)(arg0.getSource())).getText().equals("Controls")){
                    ControlsScene.setup_controlsScene();
                    Main.primaryStage.setScene(ControlsScene.controls_scene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }else if(((Label)(arg0.getSource())).getText().equals("Credits")){
                    // go to credits scene
                    // Main.primaryStage.setScene(CreditsScene.credits_scene);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                }
                // String path = Main.csvPath + "\\resources\\changingTabs.mp3";
                // Media media = new Media(new File(path).toURI().toString());
                // MediaPlayer mediaPlayer = new MediaPlayer(media);
                // mediaPlayer.setAutoPlay(true);
            }

        };

        Label title = new Label("THE LAST OF US");
        // title.setFont(new Font("Arial", 50));
        title.setFont(Main.font1);
        title.setTextFill(Paint.valueOf("#000000"));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        title.setEffect(ds);
        // Reflection r = new Reflection();
        // r.setFraction(0.7f);
        // title.setEffect(r);
        
       // Button startGame = new Button("Start Game");
    //    startGame.setGraphic(startGameLabel);
    //    startGame.setOnMouseClicked(e);
    //    startGame.setOnMouseEntered(Main.e);
    //    startGame.setOnMouseExited(Main.e2);
    // startGame.setPrefHeight(Control.USE_COMPUTED_SIZE);
    // startGame.setPrefWidth(Control.USE_COMPUTED_SIZE);
    // startGame.setFont(Main.font5);
    // startGame.setStyle("-fx-background-color: transparent;");

        Label startGameLabel = new Label("Start Game");
        startGameLabel.setFont(Main.font6);
        startGameLabel.setTextFill(Paint.valueOf("#ff4500"));
        startGameLabel.setOnMouseEntered(Main.e);

        startGameLabel.setOnMouseExited(Main.e2);
        startGameLabel.setOnMouseClicked(e);
        
        // startGameLabel.setTextFill(Paint.valueOf("#FFFFFF"));

        
        Label rules = new Label("Rules");
        rules.setOnMouseClicked(e);
        rules.setOnMouseEntered(Main.e);
        rules.setOnMouseExited(Main.e2);
        rules.setFont(Main.font6);
        rules.setTextFill(Paint.valueOf("#ff4500"));
        

        // Button rules = new Button("Rules");
        // rules.setOnMouseClicked(e);
        // rules.setOnMouseEntered(Main.e);
        // rules.setOnMouseExited(Main.e2);
        // rules.setPrefHeight(Control.USE_COMPUTED_SIZE);
        // rules.setPrefWidth(Control.USE_COMPUTED_SIZE);
        // rules.setFont(Main.font5);
        // rules.setStyle("-fx-background-color: transparent;");
        
        Label controls = new Label("Controls");
        controls.setOnMouseClicked(e);
        controls.setOnMouseEntered(Main.e);
        controls.setOnMouseExited(Main.e2);
        controls.setFont(Main.font6);
        controls.setTextFill(Paint.valueOf("#ff4500"));

        // Button controls = new Button("Controls");
        // controls.setOnMouseClicked(e);
        // controls.setOnMouseEntered(Main.e);
        // controls.setOnMouseExited(Main.e2);
        // controls.setPrefHeight(Control.USE_COMPUTED_SIZE);
        // controls.setPrefWidth(Control.USE_COMPUTED_SIZE);
        // controls.setFont(Main.font5);
        // controls.setStyle("-fx-background-color: transparent;");
        
        Label credits = new Label("Credits");
        credits.setOnMouseClicked(e);
        credits.setOnMouseEntered(Main.e);
        credits.setOnMouseExited(Main.e2);
        credits.setFont(Main.font6);
        credits.setTextFill(Paint.valueOf("#ff4500"));
        

        // Button credits = new Button("Credits");
        // credits.setOnMouseClicked(e);
        // credits.setOnMouseEntered(Main.e);
        // credits.setOnMouseExited(Main.e2);
        // credits.setPrefHeight(Control.USE_COMPUTED_SIZE);
        // credits.setPrefWidth(Control.USE_COMPUTED_SIZE);
        // credits.setFont(Main.font5);
        // credits.setStyle("-fx-background-color: transparent;");
        
        root.getChildren().addAll(title, startGameLabel, rules, controls, credits);
    }
}
