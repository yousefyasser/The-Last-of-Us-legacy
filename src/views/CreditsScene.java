package views;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class CreditsScene {
    public static VBox credits = new VBox(20);
    public static Scene creditsScene = new Scene(credits, 1300, 680);

    public static void setup_creditsScene(){
        credits.getChildren().clear();
        credits.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "bg1.png"), 
                        BackgroundRepeat.REPEAT, 
                        BackgroundRepeat.NO_REPEAT, 
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        credits.setAlignment(Pos.CENTER);
        Label l = new Label("Credits");
        l.setFont(Main.font1);
        l.setTextFill(Paint.valueOf("#000000"));
        Label l1 = new Label("Developed by: ");
        l1.setFont(Main.font3);
        l1.setTextFill(Paint.valueOf("#ffffff"));
        Label l2 = new Label("Seif eldin Khaled 55-25218");
        l2.setFont(Main.font3);
        l2.setTextFill(Paint.valueOf("#ffffff"));
        Label l3 = new Label("Youssef Malaak Rasmy 55-4772");
        l3.setFont(Main.font3);
        l3.setTextFill(Paint.valueOf("#ffffff"));
        Label l4 = new Label("Yousef Yasser Mohamed 55-3437");
        l4.setFont(Main.font3);
        l4.setTextFill(Paint.valueOf("#ffffff"));
        
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
        
        
        
        credits.getChildren().addAll(l, l1, l2, l3, l4);
        if(!credits.getChildren().contains(back)){
            credits.getChildren().add(back);
        }
    }
}
