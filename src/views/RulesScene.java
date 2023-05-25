package views;

import java.io.File;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RulesScene {

    public static VBox rules = new VBox();
    public static HBox buttons = new HBox();
    public static Scene rulesScene = new Scene(rules, 1300, 680);
    public static boolean keyBindingsOption1 = false;

    public static void draw(){
        rules.getChildren().clear();
        buttons.getChildren().clear();

        Label title = new Label("Key Bindings");
        title.setFont(Main.font1);

        String rulesText;
        if(keyBindingsOption1){
            rulesText = "Move: Arrows \n" +
                        "Select Hero / Target a zombie: Left mouse click \n" +
                        "Select Target for Medic: Right mouse click \n" +
                        "Attack: SpaceBar \n" +
                        "Use Special Action: 1 \n"+
                        "Cure: C \n" +
                        "End Turn: E \n";
        }else{
            rulesText = "Move: WASD \n" +
                        "Select Hero / Target a zombie: Left mouse click \n" +
                        "Select Target for Medic: Right mouse click \n" +
                        "Attack: X \n" +
                        "Use Special Action: R \n"+
                        "Cure: C \n" +
                        "End Turn: E \n";
        }

        Label rulesLabel = new Label(rulesText);
        rulesLabel.setFont(Main.font2);

        Button anotherOption = new Button("change key bindings");
        anotherOption.setPrefSize(150, 50);
        anotherOption.setOnMouseClicked(new EventHandler<Event>(){

            @Override
            public void handle(Event arg0) {
                keyBindingsOption1 = !keyBindingsOption1;
                draw();
            }
        });

        Button back = new Button("Back");
        back.setPrefSize(150, 50);
        back.setOnMouseClicked(new EventHandler<Event>(){

            @Override
            public void handle(Event arg0) {
                StartScene.setup_starting_scene();
                Main.primaryStage.setScene(StartScene.startScene);
                // Main.primaryStage.setFullScreen(true);
                // Main.primaryStage.setFullScreenExitHint("");
                String path = Main.csvPath + "\\resources\\changingTabs.mp3";
                Media media = new Media(new File(path).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
            }
        });

        buttons.getChildren().addAll(anotherOption, back);
        rules.getChildren().addAll(title, rulesLabel, buttons);
    }

    public static void setup_rulesScene() {
        rules.getChildren().clear();

        draw();
        rules.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        rules.setSpacing(30);
        buttons.setSpacing(20);
        
        // setting background image
        rules.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "background.jpg"), 
        BackgroundRepeat.REPEAT, 
        BackgroundRepeat.NO_REPEAT, 
        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));
        
    }
}
