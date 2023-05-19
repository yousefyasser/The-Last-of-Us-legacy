package views;

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
import javafx.scene.text.Font;

public class StartScene {
    public static VBox root = new VBox();
    public static Scene startScene = new Scene(root, 700, 500);

    public static void setup_starting_scene(){
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
                    Scene1.heroes.getChildren().clear();
                    Scene1.setup_scene1();
                    Main.primaryStage.setScene(Scene1.scene1);
                }
                else if(((Button)(arg0.getSource())).getText().equals("Controls")){
                    ControlsScene.controls.getChildren().clear();
                    ControlsScene.setup_controlsScene();
                    Main.primaryStage.setScene(ControlsScene.controls_scene);
                }
                else if(((Button)(arg0.getSource())).getText().equals("Credits")){
                    // go to credits scene
                }
            }

        };

        Label title = new Label("The Last of Us");
        // title.setTranslateX(scene.getWidth() / 2);
        // title.setTranslateY(50);
        title.setFont(new Font("Arial", 50));
        
        Button startGame = new Button("Start Game");
        startGame.setOnMouseClicked(e);
        // startGame.setTranslateX(scene.getWidth() / 2);
        // startGame.setTranslateY(50);
        startGame.setPrefSize(200, 50);
        
        Button controls = new Button("Controls");
        controls.setOnMouseClicked(e);
        // controls.setTranslateX(scene.getWidth() / 2);
        // controls.setTranslateY(50);
        controls.setPrefSize(200, 50);
        
        Button credits = new Button("Credits");
        credits.setOnMouseClicked(e);
        // credits.setTranslateX(scene.getWidth() / 2);
        // credits.setTranslateY(50);
        credits.setPrefSize(200, 50);
        
        root.getChildren().addAll(title, startGame, controls, credits);
    }
}
