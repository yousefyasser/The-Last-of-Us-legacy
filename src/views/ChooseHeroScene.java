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
    
    public static void setup_chooseHeroScene(){
        heroes.getChildren().clear();

        // background (needs to be changed) maybe just a blank coloured theme backround

        heroes.setBackground(new Background(new BackgroundImage(new Image(Main.resPath + "chooseHeroBg.jpg"), 
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
        medic.setSpacing(20);
        explorer.setSpacing(20);
        fighter.setSpacing(20);
        types.setTranslateX(50);
        types.setTranslateY(-20);
        info.setSpacing(20);

        // labels

        Label title = new Label("CHOOSE YOUR HERO");
        title.setStyle("-fx-text-fill: #ffff00; -fx-font-weight: bold;");
        title.setFont(Main.font1);

        Label heroInfo = new Label();
        heroInfo.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
        heroInfo.setFont(Main.font3);

        Label medicTitle = new Label("Medic");
        medicTitle.setStyle(" -fx-text-fill: #32cd32;");
        medicTitle.setFont(Main.font2);

        Label explorerTitle = new Label("Explorer");
        explorerTitle.setStyle(" -fx-text-fill: #00bfff;");
        explorerTitle.setFont(Main.font2);

        Label fighterTitle = new Label("Fighter");
        fighterTitle.setStyle(" -fx-text-fill: #ff4500;");
        fighterTitle.setFont(Main.font2);

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
            }
        };

        EventHandler<Event> e2 = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
               
                mediaPlayer.setAutoPlay(true);

                ((Button) (arg0.getSource())).setOpacity(0.8);
                ((Button) (arg0.getSource())).setCursor(javafx.scene.Cursor.HAND);
                int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getId());
                Hero chosenHero = Game.availableHeroes.get(heroIndx);

                String heroData = 	"Name: " + chosenHero.getName() +
									"\n Health: " + chosenHero.getCurrentHp() + 
									"\n Damage: " + chosenHero.getAttackDmg() + 
									"\nActions Available: " + chosenHero.getActionsAvailable();
                                                    
               heroInfo.setText(heroData);
                if(!info.getChildren().contains(heroInfo))
                    info.getChildren().add(heroInfo);

                // preview image of each hero which gonna be used in game board

                Image img = new Image(Main.resPath + chosenHero.getName() + ".jpeg");
                ImageView view = new ImageView(img);
                view.setFitWidth(350);
                view.setFitHeight(300);
                view.setPreserveRatio(true);

                
                info.getChildren().add(view);
                    
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
            }
        };


        // HeroButtons

        for(int i = 0; i < Game.availableHeroes.size(); i++) {
            Image img = new Image(Main.resPath + "heroes\\" + Game.availableHeroes.get(i).getName() + ".jpg");
            ImageView view = new ImageView(img);
            view.setFitWidth(200);
            view.setFitHeight(100);
            view.setPreserveRatio(true);
            Button hero = new Button();
            hero.setPrefSize(200, 100);
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

        types.getChildren().addAll(medicTitle, medic , explorerTitle, explorer, fighterTitle, fighter);
        content.getChildren().addAll(types, info);
        heroes.getChildren().addAll(title, content);
    }
}
