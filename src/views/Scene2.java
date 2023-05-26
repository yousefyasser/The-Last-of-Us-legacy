package views;

import java.io.File;
import java.util.ArrayList;

import engine.Game;
import exceptions.GameActionException;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Character;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.util.Duration;

public class Scene2 {

    public static Hero chosenHero;
    public static Character chosenTarget;
    
    public static HBox root2 = new HBox();
    public static VBox vbox = new VBox();
    public static GridPane grid = new GridPane();
    public static Scene scene2 = new Scene(root2, 1300, 680);

    public static Label info = new Label();
    public static Label errors = new Label();
    
    public static Button[][] map = new Button[15][15];
    public static Label[] allHeroInfo = new Label[6];

    public static HBox first2Heroes = new HBox(30);
    public static HBox second2Heroes = new HBox(30);

    public static ArrayList<Hero> heroesAttacked = new ArrayList<Hero>();

    public static int animationdirectionCounter = 0;
    public static String characterButtonColor;
    public static String[] heroColors = new String[8];

    public static String path1 = Main.csvPath + "\\resources\\collectibles.mp3";
    public static Media media1 = new Media(new File(path1).toURI().toString());
    public static MediaPlayer mediaPlayer1 = new MediaPlayer(media1);

    public static String path2 = Main.csvPath + "\\resources\\deadNotification.mp3";
    public static Media media2 = new Media(new File(path2).toURI().toString());
    public static MediaPlayer mediaPlayer2 = new MediaPlayer(media2);

    public static String path3 = Main.csvPath + "\\resources\\trap.mp3";
    public static Media media3 = new Media(new File(path3).toURI().toString());
    public static MediaPlayer mediaPlayer3 = new MediaPlayer(media3);

    public static String path4 = Main.csvPath + "\\resources\\punch.mp3";
    public static Media media4 = new Media(new File(path4).toURI().toString());
    public static MediaPlayer mediaPlayer4 = new MediaPlayer(media4);

    public static void draw() {

        info.setText("");
        errors.setText("");
        grid.setPrefHeight(scene2.getHeight());

        
        
        // place appropriate image on each button in the map

		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				map[i][j] = new Button();
				Image img = null;
				ImageView view;
				
				if(Game.map[14-i][j].isVisible()) {
                    map[i][j].setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 1px;");
					if(Game.map[14-i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(Game.map[14-i][j])).getCollectible() instanceof Vaccine) {
							img = new Image(Main.resPath + "vaccine.png");
						}else {
							img = new Image(Main.resPath + "supply.png");
						}
					}else if(Game.map[14-i][j] instanceof CharacterCell){
						if(((CharacterCell)(Game.map[14-i][j])).getCharacter() instanceof Zombie) {
							img = new Image(Main.resPath + "zombie.png");
                            
						}else if(((CharacterCell)(Game.map[14-i][j])).getCharacter() instanceof Hero){
                                img = new Image(Main.resPath + "heroes\\"+ ((CharacterCell)(Game.map[14-i][j])).getCharacter().getName() + ".png");
                            if((Hero)(((CharacterCell)(Game.map[14-i][j])).getCharacter()) instanceof Medic){
                                characterButtonColor = "lime";
                                map[i][j].setStyle("-fx-background-color: #00ff00;-fx-border-color: #000000; -fx-border-width: 1px;");
                            }else if((Hero)(((CharacterCell)(Game.map[14-i][j])).getCharacter()) instanceof Explorer){
                                characterButtonColor = "blue";
                                map[i][j].setStyle("-fx-background-color: #0000ff;-fx-border-color: #000000; -fx-border-width: 1px;");
                            }else if((Hero)(((CharacterCell)(Game.map[14-i][j])).getCharacter()) instanceof Fighter){
                                characterButtonColor = "orange";
                                map[i][j].setStyle("-fx-background-color: #ffa500;-fx-border-color: #000000; -fx-border-width: 1px;");
                            }
						}

					 }
                    //  else if(Game.map[14-i][j] instanceof TrapCell) { //remove b4 submission
					// 	// img = new Image(Main.resPath + "trap.png");
					// }
				}else {
					// img = new Image(Main.resPath + "mystery.png");
                    map[i][j].setStyle("-fx-background-color: #000000; -fx-border-color: #ffffff; -fx-border-width: 1px;");
				}

				view = new ImageView(img);
				view.setFitWidth(40);
				view.setFitHeight(35);
				map[i][j].setGraphic(view);
				map[i][j].setPrefSize(45, scene2.getHeight()/15);
                
                // event for setting target

				map[i][j].setOnMouseClicked(event ->
                {
                    int buttonX = GridPane.getColumnIndex((Button)(event.getSource()));
                    int buttonY = GridPane.getRowIndex((Button)(event.getSource()));
                    Cell cell = Game.map[14-buttonY][buttonX];

                    if (event.getButton() == MouseButton.PRIMARY) {
                        if(cell instanceof CharacterCell) {
                            if(((CharacterCell)(cell)).getCharacter() instanceof Zombie) {
                                chosenTarget = (Zombie)((CharacterCell)(cell)).getCharacter();
                                chosenHero.setTarget(chosenTarget);
                                
                                String zombieInfo = "Zombie Name: " + ((CharacterCell)(cell)).getCharacter().getName() +
                                "\nZombie Damage: " + ((CharacterCell)(cell)).getCharacter().getAttackDmg() +
                                "\nZombie Health: " + ((CharacterCell)(cell)).getCharacter().getCurrentHp();
                                if(cell.isVisible()){
                                    info.setText(zombieInfo);
                                    info.setStyle("-fx-text-fill: #ff0000");
                                }
                        
                            }else if(((CharacterCell)(cell)).getCharacter() instanceof Hero) {
                                chosenHero = (Hero)(((CharacterCell)(cell)).getCharacter());
                                getAllHeroesInfo();
                            }
                        }
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        if(chosenHero instanceof Medic){
                            if(((CharacterCell)(cell)).getCharacter() instanceof Hero){
                                chosenTarget = (Hero)(((CharacterCell)(cell)).getCharacter());
                                chosenHero.setTarget(chosenTarget);
                            }
                        }
                    }
                });

				grid.add(map[i][j], j, i);
			}
		}

        getAllHeroesInfo();
        playAnimations();
        
        // Adding all the elements to the vbox

		if(!vbox.getChildren().contains(info))
            vbox.getChildren().add(info);
        if(!vbox.getChildren().contains(first2Heroes))
            vbox.getChildren().add(first2Heroes);
        if(!vbox.getChildren().contains(second2Heroes))
            vbox.getChildren().add(second2Heroes);
        if(!vbox.getChildren().contains(errors))
			vbox.getChildren().add(errors);
        
        
        
        vbox.setStyle("-fx-background-color: #000000; -fx-background-radius: 10px; -fx-padding: 10px;");    
	}

    public static void playAnimations() {
        if(animationdirectionCounter == 2){
            animationdirectionCounter = 0;
            
            // mediaPlayer.setVolume(ControlsScene.newVolume/100);
            mediaPlayer1.setAutoPlay(true);
        }else if(animationdirectionCounter == 3){
            animationdirectionCounter = 0;
            
            // mediaPlayer.setVolume(ControlsScene.newVolume/100);
            mediaPlayer2.setAutoPlay(true);

            if(Game.heroes.size()>0){
                chosenHero = Game.heroes.get(0);
            } 

        }else if(animationdirectionCounter == 4){
            animationdirectionCounter = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateEffect(b, 0.3, "red");

            // mediaPlayer.setVolume(ControlsScene.newVolume/100);
            mediaPlayer3.setAutoPlay(true);

        }else if(animationdirectionCounter == 5 && chosenTarget != null){
            animationdirectionCounter = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateEffect(b, 0.3, "red"); 

            Button b2 = map[14-chosenTarget.getLocation().x][chosenTarget.getLocation().y];
            animateEffect(b2, 0.3, "red");

            // mediaPlayer.setVolume(ControlsScene.newVolume/100);
            mediaPlayer4.setAutoPlay(true);

        }else if(animationdirectionCounter == 6){
            animationdirectionCounter = 0;
            Button b = map[14-chosenTarget.getLocation().x][chosenTarget.getLocation().y];
            animateEffect(b, 0.3, "yellow"); 
        }
        else if(animationdirectionCounter == 9){
            animationdirectionCounter = 0;
            for(int i = 0 ; i<heroesAttacked.size();i++){


                

                Button b = map[14-heroesAttacked.get(i).getLocation().x][heroesAttacked.get(i).getLocation().y];
                animateEffect(b, 0.3, "red"); 

               
                mediaPlayer4.setAutoPlay(true);

            // Button b2 = map[14-heroesAttacked.get(i).getTarget().getLocation().x][heroesAttacked.get(i).getTarget().getLocation().y];
            // animateEffect(b2, 0.3, "red");
            // mediaPlayer.setVolume(ControlsScene.newVolume/100);
            }
           heroesAttacked =  new ArrayList<Hero>();
        }
    }

    public static void animateEffect(Button b, double duration, String color){
        b.setStyle("-fx-base: " + color + ";");
        PauseTransition pause = new PauseTransition(Duration.seconds(duration));
        pause.setOnFinished(event -> {
            b.setStyle("-fx-base: " + characterButtonColor + ";");
            draw();
        });
        pause.play();
    }

    public static void getAllHeroesInfo() {
        first2Heroes.getChildren().clear();
        second2Heroes.getChildren().clear();

        for(int i = 0; i < Game.heroes.size(); i++){
            if(Game.heroes.get(i) instanceof Medic){
                heroColors[i] = "lime";
            }else if(Game.heroes.get(i) instanceof Explorer){
                heroColors[i] = "blue";
            }else{
                heroColors[i] = "orange";
            }
        }

        if(chosenHero instanceof Medic){
            characterButtonColor = "lime";
        }else if(chosenHero instanceof Explorer){
            characterButtonColor = "blue";
        }else{
            characterButtonColor = "orange";
        }

        // Chosen Hero Info

        int heroIndex = 0;

        String heroInfo = 	"Type: " + chosenHero.getClass().getSimpleName() +
                            "\nHero Name: " + chosenHero.getName() +
                            "\nHero Health: " + chosenHero.getCurrentHp() + 
                            "\nHero Damage: " + chosenHero.getAttackDmg() + 
                            "\nActions Available: " + chosenHero.getActionsAvailable() +
                            "\nSupplies: " + chosenHero.getSupplyInventory().size() +
                            "\nVaccines: " + chosenHero.getVaccineInventory().size() 
                            ;
        info.setText(heroInfo);
        info.setStyle("-fx-text-fill: "+ characterButtonColor +";");

        // All Heroes Info

        for(int i = 0; i < Game.heroes.size(); i++){
            if(chosenHero != Game.heroes.get(i)){
                heroInfo = 	"Type: " + Game.heroes.get(i).getClass().getSimpleName() +
                                    "\nHero Name: " + Game.heroes.get(i).getName() +
                                    "\nHero Health: " + Game.heroes.get(i).getCurrentHp() + 
                                    "\nHero Damage: " + Game.heroes.get(i).getAttackDmg() + 
                                    "\nActions Available: " + Game.heroes.get(i).getActionsAvailable();

                allHeroInfo[i] = new Label(heroInfo);
                allHeroInfo[i].setFont(Main.font4);
                allHeroInfo[i].setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-padding: 10px; -fx-text-fill: "+ heroColors[i] +";");
                if(heroIndex < 2){
                    first2Heroes.getChildren().add(allHeroInfo[i]);
                    
                }else if(heroIndex >= 2 && heroIndex < 4){
                    second2Heroes.getChildren().add(allHeroInfo[i]);
                   
                }
                heroIndex++;
            }
        }
    }

    public static void setup_scene2(){
        root2.getChildren().clear();
        root2.setSpacing(10);
        vbox.setSpacing(20);
        vbox.setPrefWidth(400);
        vbox.setPrefHeight(scene2.getHeight()-300);
        // vbox.setPadding(new Insets(10, 10, 10, 10));
        

        root2.getChildren().addAll(grid, vbox);
        Game.startGame(chosenHero);

        info.setFont(Main.font3);
        errors.setFont(Main.font9);

   

        first2Heroes.setSpacing(10);
        second2Heroes.setSpacing(10);


        draw();

        scene2.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            int moveCounter=0;
            public void handle(KeyEvent ke) {
                try {

                    ArrayList <Integer> heroHp = new ArrayList<Integer>();

                    for(int i = 0; i < Game.heroes.size(); i++){
                       heroHp.add(Game.heroes.get(i).getCurrentHp());
                    }

                    int hpBefore = chosenHero.getCurrentHp();
                    int collectiblesBefore = chosenHero.getSupplyInventory().size() + chosenHero.getVaccineInventory().size();

                    if ((RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.UP) || (!RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.W)) {
                        chosenHero.move(Direction.UP);
                        ke.consume();
                        moveCounter = 1;
                    }else if ((RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.DOWN) || (!RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.S)) {
                        chosenHero.move(Direction.DOWN);
                        ke.consume();
                        moveCounter = 2;
                    }else if ((RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.LEFT) || (!RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.A)) {
                        chosenHero.move(Direction.LEFT);
                        ke.consume();
                        moveCounter = 3;
                        
                    }else if ((RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.RIGHT) || (!RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.D)) {
                        chosenHero.move(Direction.RIGHT);
                        ke.consume();
                        moveCounter = 4;
                        
                    }else if((RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.SPACE) || (!RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.X)) {
                        chosenHero.attack();
                        ke.consume();
                        animationdirectionCounter = 5;
                    }else if((RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.DIGIT1) || (!RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.R)) {
                        chosenHero.useSpecial();
                        ke.consume();

                        if(chosenHero instanceof Medic)
                            animationdirectionCounter = 6;
                    }else if(ke.getCode() == KeyCode.E){
                        Game.endTurn();
                        for(int i = 0; i < Game.heroes.size(); i++){
                            if(heroHp.get(i) != Game.heroes.get(i).getCurrentHp()){                        
                                heroesAttacked.add(Game.heroes.get(i));
                                animationdirectionCounter = 9;
                            }
                         }
                        grid.getChildren().clear();
                        ke.consume();
                    }else if(ke.getCode() == KeyCode.C){
                        chosenHero.cure();
                        ke.consume();
                    }
                    if(moveCounter != 0){
                        if(hpBefore != chosenHero.getCurrentHp()){
                            animationdirectionCounter = 4;
                        }
                        if(collectiblesBefore != chosenHero.getSupplyInventory().size() + chosenHero.getVaccineInventory().size()){
                            animationdirectionCounter = 2;
                        }
                        moveCounter = 0;
                    }
                    if(chosenHero.getCurrentHp() <= 0){
                        animationdirectionCounter = 3;
                    }
                    if(Game.checkGameOver()){
                        GameOverScene.setup_gameOverScene();
                        Main.primaryStage.setScene(GameOverScene.gameOverScene);
                        return;
                    }
                    if(Game.checkWin()){
                        WinScene.setup_winScene();
                        Main.primaryStage.setScene(WinScene.winScene);
                        return;
                    }
                    draw();
                }catch(GameActionException e) {
                    errors.setText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + e.getMessage());
                    errors.setStyle("-fx-text-fill: #ff1493;");
                }
            }
        });
    }
}
