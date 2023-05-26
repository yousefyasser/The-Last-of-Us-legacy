package views;



import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ControlsScene {
    public static VBox controls = new VBox();
    public static Scene controls_scene = new Scene(controls, 1300, 680);
    public static int volumeCountter = 0;
    public static int newVolume = 50;
    public static Slider volume;

    public static void setup_controlsScene() {
        controls.getChildren().clear();
        controls.setSpacing(100);

        // setting background image

        controls.setAlignment(Pos.CENTER);
        controls.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "bg1.png"), 
                        BackgroundRepeat.REPEAT, 
                        BackgroundRepeat.NO_REPEAT, 
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        Label title = new Label("Controls");
        title.setFont(Main.font1);
        title.setTextFill(Paint.valueOf("#000000"));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        title.setEffect(ds);

        // Adjusting volume
        
        HBox volInfo = new HBox();
        volInfo.setAlignment(Pos.CENTER);

        
        if(volumeCountter == 0){
            volume = new Slider(0, 100, 50);
            volume.setMinWidth(50);
            volume.setMaxWidth(200);
            volume.setStyle("-fx-background-color: #000000;");
            volumeCountter++;

        }
        else{
            volume = new Slider(0, 100, newVolume);
            volume.setMinWidth(50);
            volume.setMaxWidth(200);
            volume.setStyle("-fx-background-color: #000000;");
        }

        Label volText = new Label("Volume: " + newVolume);
        volText.setFont(Main.font2);
        volText.setStyle("-fx-font-size: 20px;");
        volText.setTextFill(Paint.valueOf("#87cefa"));
        volText.setPrefSize(150, 50);
        volume.valueProperty().addListener((observable, oldValue, newValue) -> {
            volText.setText("Volume: " + newValue.intValue());
            Main.mediaPlayer.setVolume(newValue.intValue() / 100.0);
            Main.mediaPlayer1.setVolume(newValue.intValue() / 100.0);
            ChooseHeroScene.mediaPlayer.setVolume(newValue.intValue() / 100.0);
            newVolume = newValue.intValue();
        });

        volInfo.getChildren().addAll(volText, volume);

        Label back = new Label("Back");
        back.setFont(Main.font6);
        back.setTextFill(Paint.valueOf("#ff4500"));
        back.setOnMouseClicked(new EventHandler<Event>(){

            @Override
            public void handle(Event arg0) {
                StartScene.setup_starting_scene();
                Main.primaryStage.setScene(StartScene.startScene);
            }
        });
        back.setOnMouseEntered(Main.e);
        back.setOnMouseExited(Main.e2);
        
        controls.getChildren().addAll(title, volInfo, back);
    }
}
