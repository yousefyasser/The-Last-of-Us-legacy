package views;

import java.io.File;


import engine.Game;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;

public class ChooseHeroScene{
    
    public static VBox heroes = new VBox();
    public static HBox content = new HBox();
    public static VBox types = new VBox();
    public static VBox info = new VBox();
    public static HBox medic = new HBox();
    public static HBox explorer = new HBox();
    public static HBox fighter = new HBox();
    public static String path = Main.csvPath + "\\resources\\heroSelect.mp3";
    public static Media media = new Media(new File(path).toURI().toString());
    public static MediaPlayer mediaPlayer = new MediaPlayer(media);
	public static Scene chooseHeroScene = new Scene(heroes, 1300, 680);
    public static DropShadow medicDropShadow = new DropShadow();
    public static DropShadow explDropShadow = new DropShadow();
    public static DropShadow fiDropShadow = new DropShadow();
					
    
    public static void setup_chooseHeroScene(){
        heroes.getChildren().clear();
       
       
        medicDropShadow.setOffsetY(3.0f);
        medicDropShadow.setRadius(30);
       	medicDropShadow.setColor(Color.web("#00ff00"));

        explDropShadow.setOffsetY(3.0f);
        explDropShadow.setRadius(30);
        explDropShadow.setColor(Color.web("#00bfff"));

        fiDropShadow.setOffsetY(3.0f);
        fiDropShadow.setRadius(30);
        fiDropShadow.setColor(Color.web("#ff4500"));

        // background (needs to be changed) maybe just a blank coloured theme backround

        heroes.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "bgTry.jfif"), 
        BackgroundRepeat.REPEAT, 
        BackgroundRepeat.NO_REPEAT, 
        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true), 
        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true))));

        // allignment

        // Main.primaryStage.setFullScreen(true);
        heroes.setAlignment(Pos.CENTER);
        
        // spacing

        heroes.setSpacing(50);
        content.setSpacing(200);
        types.setSpacing(20);
        types.setTranslateY(55);
        types.setTranslateX(50);
        medic.setSpacing(20);
        explorer.setSpacing(20);
        fighter.setSpacing(20);
        // types.setTranslateY(-20);
        info.setSpacing(20);
        info.setTranslateX(300);
        info.setTranslateY(120);

        // labels

        Label heroInfo = new Label();
        heroInfo.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold;");
        heroInfo.setFont(Main.font5);
        
        // Label title = new Label("CHOOSE YOUR HERO");
        // title.setStyle("-fx-text-fill: #ffff00; -fx-font-weight: bold;");
        // title.setFont(Main.font1);


        Label medicTitle = new Label("Medic");
        medicTitle.setStyle(" -fx-text-fill: #00ff00;");
        medicTitle.setFont(Main.font2);
        medicTitle.setEffect(medicDropShadow);

        Label explorerTitle = new Label("Explorer");
        explorerTitle.setStyle(" -fx-text-fill: #00bfff;");
        explorerTitle.setFont(Main.font2);
        explorerTitle.setEffect(explDropShadow);

        Label fighterTitle = new Label("Fighter");
        fighterTitle.setStyle(" -fx-text-fill: #ff4500;");
        fighterTitle.setFont(Main.font2);
        fighterTitle.setEffect(fiDropShadow);

        // events

        EventHandler<Event> e = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                    int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getId());
                    Scene2.chosenHero = Game.availableHeroes.get(heroIndx);

                    Scene2.setup_scene2();
                    Main.primaryStage.setScene(Scene2.scene2);
                    // Main.primaryStage.setFullScreen(true);
			        // Main.primaryStage.setFullScreenExitHint("");
                    // String path = Main.csvPath + "\\resources\\changingTabs.mp3";
                    // Media media = new Media(new File(path).toURI().toString());
                    // MediaPlayer mediaPlayer = new MediaPlayer(media);
                    // mediaPlayer.setAutoPlay(true);
            }
        };

        EventHandler<Event> e2 = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
               
                mediaPlayer.setAutoPlay(true);

                ((Button) (arg0.getSource())).setOpacity(0.8);
                ((Button) (arg0.getSource())).setCursor(javafx.scene.Cursor.HAND);
                int Id =  Integer.parseInt(((Button) (arg0.getSource())).getId());
                if(Game.availableHeroes.get(Id) instanceof Medic){
                    ((Button) (arg0.getSource())).setEffect(medicDropShadow);
                    
                }else if(Game.availableHeroes.get(Id) instanceof Explorer){
                    ((Button) (arg0.getSource())).setEffect(explDropShadow);
                    
                }else if(Game.availableHeroes.get(Id) instanceof Fighter){
                    ((Button) (arg0.getSource())).setEffect(fiDropShadow);
                    
                }
            
                int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getId());
                Hero chosenHero = Game.availableHeroes.get(heroIndx);

                String heroData = 	"Name: " + chosenHero.getName() +
									"\nHealth: " + chosenHero.getCurrentHp() + 
									"\nDamage: " + chosenHero.getAttackDmg() + 
									"\nActions Available: " + chosenHero.getActionsAvailable();
                                                    
               heroInfo.setText(heroData);
                if(!info.getChildren().contains(heroInfo))
                    info.getChildren().add(heroInfo);

                // preview image of each hero which gonna be used in game board

                // Image img = new Image(Main.resPath + chosenHero.getName() + ".jpeg");
                // ImageView view = new ImageView(img);
                // view.setFitWidth(350);
                // view.setFitHeight(300);
                // view.setPreserveRatio(true);

                
                // info.getChildren().add(view);
                    
            }
        };

        EventHandler<Event> e3 = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                mediaPlayer.setAutoPlay(false);
                mediaPlayer.stop();
                ((Button) (arg0.getSource())).setOpacity(1);
                // int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getId());
                heroInfo.setText("");
                info.getChildren().clear();
                info.getChildren().addAll(heroInfo);
                ((Button) (arg0.getSource())).setEffect(null);
            }
        };


        // HeroButtons

        for(int i = 0; i < Game.availableHeroes.size(); i++) {
            Image img = new Image(Main.resPath + "heroes\\" + Game.availableHeroes.get(i).getName() + ".png");
            ImageView view = new ImageView(img);
            view.setFitWidth(200);
            view.setFitHeight(100);
            view.setPreserveRatio(true);
            Button hero = new Button();
            hero.setPrefSize(100, 70);
            hero.setId(i+"");
            hero.setGraphic(view);
            hero.setStyle("-fx-background-color: transparent;");

            if(Game.availableHeroes.get(i) instanceof Medic){
                // hero.setEffect(medicDropShadow);
                medic.getChildren().add(hero);
            }else if(Game.availableHeroes.get(i) instanceof Explorer){
                // hero.setEffect(explDropShadow);
                explorer.getChildren().add(hero);
            }else if(Game.availableHeroes.get(i) instanceof Fighter){
                // hero.setEffect(fiDropShadow);
                fighter.getChildren().add(hero);
            }

            hero.setOnMouseClicked(e);
            hero.setOnMouseEntered(e2);
            hero.setOnMouseExited(e3);

       
        }
        
        Label back = new Label("Back");
        back.setFont(Main.font6);
        back.setTextFill(Paint.valueOf("#ff4500"));
        back.setOnMouseClicked(new EventHandler<Event>(){

            @Override
            public void handle(Event arg0) {
                StartScene.setup_starting_scene();
                Main.primaryStage.setScene(StartScene.startScene);
                // Main.primaryStage.setFullScreen(true);
                // Main.primaryStage.setFullScreenExitHint("");
                // String path = Main.csvPath + "\\resources\\changingTabs.mp3";
                // Media media = new Media(new File(path).toURI().toString());
                // MediaPlayer mediaPlayer = new MediaPlayer(media);
                // mediaPlayer.setAutoPlay(true);
            }
        });

        back.setOnMouseEntered(Main.e);
        back.setOnMouseExited(Main.e2);
            

        types.getChildren().addAll( medicTitle, medic , explorerTitle, explorer, fighterTitle ,fighter);
        content.getChildren().addAll(types, info, back);
        heroes.getChildren().addAll(content);
    }
}
