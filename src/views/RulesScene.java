package views;

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
import javafx.scene.layout.VBox;

public class RulesScene {

    public static VBox rules = new VBox();
    public static Scene rulesScene = new Scene(rules, 1300, 680);

    public static void setup_rulesScene() {
        rules.setAlignment(Pos.CENTER);
        rules.setSpacing(30);

        // setting background image
        rules.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "background.jpg"), 
                        BackgroundRepeat.REPEAT, 
                        BackgroundRepeat.NO_REPEAT, 
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        Label title = new Label("Key Bindings");
        title.setFont(Main.font1);

        String rulesText = "Move: Arrows \n" +
                           "Set Target: Q \n" +
                           "Attack: SpaceBar \n" +
                           "Use Special Action: 1 \n"+
                           "Cure: C \n" +
                           "End Turn: E \n";
        Label rulesLabel = new Label(rulesText);
        rulesLabel.setFont(Main.font2);

        Button back = new Button("Back");
        back.setPrefSize(150, 50);
        back.setOnMouseClicked(new EventHandler<Event>(){

            @Override
            public void handle(Event arg0) {
                StartScene.root.getChildren().clear();
                StartScene.setup_starting_scene();
                Main.primaryStage.setScene(StartScene.startScene);
                // Main.primaryStage.setFullScreen(true);
                // Main.primaryStage.setFullScreenExitHint("");
            }
        });

        rules.getChildren().addAll(title, rulesLabel, back);
    }
}
