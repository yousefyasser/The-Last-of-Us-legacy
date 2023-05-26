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
        content.getChildren().clear();
        types.getChildren().clear();
        info.getChildren().clear();
        medic.getChildren().clear();
        explorer.getChildren().clear();
        fighter.getChildren().clear();

       
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
        info.setSpacing(20);
        info.setTranslateX(300);
        info.setTranslateY(120);

        // labels

        Label heroInfo = new Label();
        heroInfo.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold;");
        heroInfo.setFont(Main.font5);

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
            }
        };

        EventHandler<Event> e3 = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                mediaPlayer.setAutoPlay(false);
                mediaPlayer.stop();
                ((Button) (arg0.getSource())).setOpacity(1);
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
                medic.getChildren().add(hero);
            }else if(Game.availableHeroes.get(i) instanceof Explorer){
                explorer.getChildren().add(hero);
            }else if(Game.availableHeroes.get(i) instanceof Fighter){
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
            }
        });

        back.setOnMouseEntered(Main.e);
        back.setOnMouseExited(Main.e2);
            

        if(!types.getChildren().contains(medicTitle))
            types.getChildren().add(medicTitle);
        if(!types.getChildren().contains(medic))
            types.getChildren().add(medic); 
        if(!types.getChildren().contains(explorerTitle))
            types.getChildren().add(explorerTitle);       
        if(!types.getChildren().contains(explorer))
            types.getChildren().add(explorer);
        if(!types.getChildren().contains(fighterTitle))
            types.getChildren().add(fighterTitle);
        if(!types.getChildren().contains(fighter))
            types.getChildren().add(fighter);
        if (!content.getChildren().contains(types))
            content.getChildren().add(types);
        if (!content.getChildren().contains(info))
            content.getChildren().add(info);
        if(!heroes.getChildren().contains(content))
            heroes.getChildren().add(content);
        if(!heroes.getChildren().contains(back))
            heroes.getChildren().add(back);
    }
}
