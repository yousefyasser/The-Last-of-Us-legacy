package views;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ControlsScene {
    public static VBox controls = new VBox();
    public static Scene controls_scene = new Scene(controls, 700, 500);

    public static void setup_controlsScene() {
        controls.setSpacing(20);

        // setting background image
        controls.setAlignment(Pos.CENTER);
        controls.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "background.jpg"), 
                        BackgroundRepeat.REPEAT, 
                        BackgroundRepeat.NO_REPEAT, 
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        Label title = new Label("Controls");
        title.setFont(new Font("Arial", 50));

        // Adjusting volume
        
        HBox volInfo = new HBox();
        volInfo.setAlignment(Pos.CENTER);

        Slider volume = new Slider(0, 100, 50);
        volume.setMinWidth(50);
        volume.setMaxWidth(200);

        Label volText = new Label("Volume: 50");
        volText.setFont(new Font("Arial", 20));
        volText.setPrefSize(150, 50);
        volume.valueProperty().addListener((observable, oldValue, newValue) -> {
            volText.setText("Volume: " + newValue.intValue());
            Main.mediaPlayer.setVolume(newValue.intValue() / 100.0);
        });

        volInfo.getChildren().addAll(volText, volume);

        Button back = new Button("Back");
        back.setPrefSize(150, 50);
        back.setOnMouseClicked(new EventHandler<Event>(){

            @Override
            public void handle(Event arg0) {
                StartScene.root.getChildren().clear();
                StartScene.setup_starting_scene();
                Main.primaryStage.setScene(StartScene.startScene);
            }
        });

        controls.getChildren().addAll(title, volInfo, back);
    }
}
