package views;

import engine.Game;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.characters.Hero;

class Scene1{
    
    public static HBox heroes = new HBox();
    public static VBox[] info = new VBox[Game.availableHeroes.size()];
    public static Button[] heroButtons = new Button[Game.availableHeroes.size()];
    public static Label[] heroInfo = new Label[Game.availableHeroes.size()];
	public static Scene scene1 = new Scene(heroes, 1300, 700);
    
    public static void setup_scene1(){

        EventHandler<Event> e = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                    int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getId());
                    Scene2.chosenHero = Game.availableHeroes.get(heroIndx);

                    Scene2.root2.getChildren().clear();
                    Scene2.setup_scene2();
                    Main.primaryStage.setScene(Scene2.scene2);
            }
        };

        EventHandler<Event> e2 = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getId());
                Hero chosenHero = Game.availableHeroes.get(heroIndx);

                String heroData = 	"Type: " + chosenHero.getClass().getSimpleName() +
									"\nHero Name: " + chosenHero.getName() +
									"\nHero Health: " + chosenHero.getCurrentHp() + 
									"\nHero Damage: " + chosenHero.getAttackDmg() + 
									"\nActions Available: " + chosenHero.getActionsAvailable();
                                                    
                heroInfo[heroIndx].setText(heroData);
                if(!info[heroIndx].getChildren().contains(heroInfo[heroIndx]))
                    info[heroIndx].getChildren().addAll(heroInfo[heroIndx]);
            }
        };

        EventHandler<Event> e3 = new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getId());
                heroInfo[heroIndx].setText("");
                info[heroIndx].getChildren().clear();
                info[heroIndx].getChildren().addAll(heroButtons[heroIndx], heroInfo[heroIndx]);
            }
        };
        
        for(int i = 0; i < Game.availableHeroes.size(); i++) {
            
            Image img = new Image(Main.resPath + "heroes\\" + Game.availableHeroes.get(i).getName() + ".jpg");
            ImageView view = new ImageView(img);
            view.setFitWidth(1300/Game.availableHeroes.size());
            view.setFitHeight(500);
            view.setPreserveRatio(true);
            
            info[i] = new VBox();
            info[i].setSpacing(20);
            heroButtons[i] = new Button();
            heroInfo[i] = new Label("");

            heroButtons[i].setId(i+"");
            heroButtons[i].setGraphic(view);
            heroButtons[i].setPrefSize(1300/Game.availableHeroes.size(), 500);
            
            heroButtons[i].setOnMouseClicked(e);
            heroButtons[i].setOnMouseEntered(e2);
            heroButtons[i].setOnMouseExited(e3);

            info[i].getChildren().addAll(heroButtons[i], heroInfo[i]);
            heroes.getChildren().add(info[i]);
        }
    }
}
