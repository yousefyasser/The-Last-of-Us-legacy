package views;

import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameOverScene {
    public static VBox msg = new VBox();
    public static Scene gameOverScene = new Scene(msg, 700, 500);

    public static void setup_gameOverScene(){
        msg.getChildren().clear();
        
        msg.setSpacing(25);
        msg.setAlignment(Pos.CENTER);

        // setting background image
        msg.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "GameOverBg.jpeg"), 
                        BackgroundRepeat.REPEAT, 
                        BackgroundRepeat.NO_REPEAT, 
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        Label l = new Label("You Lose");
        l.setFont(Main.font7);
        l.setTextFill(Paint.valueOf("#000000"));
        DropShadow ds = new DropShadow();
        ds.setRadius(30.0);
        // ds.setOffsetY(3.0f);
        ds.setColor(Color.RED);
        l.setEffect(ds);

        msg.getChildren().add(l);
    }
}
