package views;

import java.io.File;

import engine.Game;
import exceptions.GameActionException;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
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
import model.world.TrapCell;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    public static GridPane grid = new GridPane();
    public static VBox vbox = new VBox();
    public static HBox vaccineInventory = new HBox();
    public static HBox supplyInventory = new HBox();
    public static Scene scene2 = new Scene(root2, 1300, 680);

    public static Label info = new Label();
    public static Label errors = new Label();
    
    public static ProgressBar heroHpBar = new ProgressBar();
    public static ProgressBar zombieHpBar = new ProgressBar();

    public static Button[][] map = new Button[15][15];
    public static Label[] allHeroInfo = new Label[6];
    public static Button[] vaccines = new Button[5];
    public static Button[] supplies = new Button[5];

    public static HBox first2Heroes = new HBox();
    public static HBox second2Heroes = new HBox();

    public static int directionCounter = 0;
    public static int animationdirectionCounter = 0;
    public static String characterButtonColor;
    public static boolean zombieClicked;

    public static void draw() {
        zombieClicked = false;

        info.setText("");
        errors.setText("");
        
        updateHeroHpBar();  
        if(vbox.getChildren().contains(zombieHpBar))
            vbox.getChildren().remove(zombieHpBar);     
        
        // place appropriate image on each button in the map

		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				map[i][j] = new Button();
				Image img = null;
				ImageView view;
				
				if(Game.map[14-i][j].isVisible()) {
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
							// if(directionCounter == 0){
                            //     img = new Image(Main.resPath + chosenHero.getName() + ".png");
                            // }else if(directionCounter == 1){
                            //     img = new Image(Main.resPath + chosenHero.getName() + "2.png");
                            // }
                            img = new Image(Main.resPath + "hero.png");

                            if((Hero)(((CharacterCell)(Game.map[14-i][j])).getCharacter()) instanceof Medic){
                                characterButtonColor = "yellow";
                                map[i][j].setStyle("-fx-background-color: #ffff00");
                            }else if((Hero)(((CharacterCell)(Game.map[14-i][j])).getCharacter()) instanceof Explorer){
                                characterButtonColor = "blue";
                                map[i][j].setStyle("-fx-background-color: #0000ff");
                            }else if((Hero)(((CharacterCell)(Game.map[14-i][j])).getCharacter()) instanceof Fighter){
                                characterButtonColor = "darkgrey";
                                map[i][j].setStyle("-fx-background-color: #808080");
                            }
						}
					 }
                     else if(Game.map[14-i][j] instanceof TrapCell) {
						img = new Image(Main.resPath + "trap.png");
						
					}
				}else {
					img = new Image(Main.resPath + "mystery.png");
				}

				view = new ImageView(img);
				view.setFitWidth(45);
				view.setFitHeight(35);
				map[i][j].setGraphic(view);
				map[i][j].setPrefSize(45, 35);


                     // event for setting target

				map[i][j].setOnMouseClicked(event ->
                {
                    int buttonX = GridPane.getColumnIndex((Button)(event.getSource()));
                    int buttonY = GridPane.getRowIndex((Button)(event.getSource()));
                    Cell cell = Game.map[14-buttonY][buttonX];

                    if (event.getButton() == MouseButton.PRIMARY) {
                        if(cell instanceof CharacterCell) {
                            if(((CharacterCell)(cell)).getCharacter() instanceof Zombie) {
                                zombieClicked = true;
                                vbox.getChildren().remove(vaccineInventory);
                                vbox.getChildren().remove(supplyInventory);
                                vbox.getChildren().remove(heroHpBar);

                                chosenTarget = (Zombie)((CharacterCell)(cell)).getCharacter();
                                chosenHero.setTarget(chosenTarget);
                                
                                String zombieInfo = "Zombie Name: " + ((CharacterCell)(cell)).getCharacter().getName() +
                                "\nZombie Damage: " + ((CharacterCell)(cell)).getCharacter().getAttackDmg() +
                                "\nZombie Health: "  ;
                                info.setText(zombieInfo);

                                zombieHpBar.setProgress((double)(((CharacterCell)(cell)).getCharacter().getCurrentHp()) / (((CharacterCell)(cell)).getCharacter().getMaxHp()));

                                if(!vbox.getChildren().contains(zombieHpBar))
                                    vbox.getChildren().add(zombieHpBar);
                            }else if(((CharacterCell)(cell)).getCharacter() instanceof Hero) {
                                zombieClicked = false;
                                if(!vbox.getChildren().contains(vaccineInventory))
                                    vbox.getChildren().add(vaccineInventory);   
                                if(!vbox.getChildren().contains(supplyInventory))
                                    vbox.getChildren().add(supplyInventory);

                                chosenHero = (Hero)(((CharacterCell)(cell)).getCharacter());
                                getAllHeroesInfo();
                                updateHeroHpBar();
                                if(vbox.getChildren().contains(zombieHpBar))
                                    vbox.getChildren().remove(zombieHpBar); 
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

        playAnimations();
        getAllHeroesInfo();
        
        // Adding all the elements to the vbox

		if(!vbox.getChildren().contains(info))
            vbox.getChildren().add(info);
        if(!zombieClicked){
            if(!vbox.getChildren().contains(vaccineInventory))
                vbox.getChildren().add(vaccineInventory);
            if(!vbox.getChildren().contains(supplyInventory))
                vbox.getChildren().add(supplyInventory);
        }
        if(!vbox.getChildren().contains(first2Heroes))
            vbox.getChildren().add(first2Heroes);
        if(!vbox.getChildren().contains(second2Heroes))
            vbox.getChildren().add(second2Heroes);
        if(!vbox.getChildren().contains(errors))
			vbox.getChildren().add(errors);
	}

    public static void updateHeroHpBar(){
        heroHpBar.setProgress((double)(chosenHero.getCurrentHp()) / (chosenHero.getMaxHp()));
                
        if(!vbox.getChildren().contains(heroHpBar))
            vbox.getChildren().add(heroHpBar);
    }

    public static void playAnimations() {
        if(animationdirectionCounter == 2){
            animationdirectionCounter = 0;
            String path = Main.csvPath + "\\resources\\collectibles.mp3";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            
        }else if(animationdirectionCounter == 3){
            animationdirectionCounter = 0;
            String path = Main.csvPath + "\\resources\\deadNotification.mp3";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            if(Game.heroes.size()>0){
                chosenHero = Game.heroes.get(0);
            }

            updateHeroHpBar();

        }else if(animationdirectionCounter == 4){
            animationdirectionCounter = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateEffect(b, 0.3, "red");
            String path = Main.csvPath + "\\resources\\trap.mp3";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

        }else if(animationdirectionCounter == 5 && chosenTarget != null){
            animationdirectionCounter = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateEffect(b, 0.3, "red"); 

            Button b2 = map[14-chosenTarget.getLocation().x][chosenTarget.getLocation().y];
            animateEffect(b2, 0.3, "red");
            String path = Main.csvPath + "\\resources\\punch.mp3";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

        }else if(animationdirectionCounter == 6){
            animationdirectionCounter = 0;
            Button b = map[14-chosenTarget.getLocation().x][chosenTarget.getLocation().y];
            animateEffect(b, 0.3, "green"); 
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
        vaccineInventory.getChildren().clear();
        supplyInventory.getChildren().clear();
        first2Heroes.getChildren().clear();
        second2Heroes.getChildren().clear();

        // Chosen Hero's Vaccine Inventory

        for(int i = 0; i < vaccines.length; i++){
            vaccines[i] = new Button();
            vaccines[i].setPrefSize(45, 35);
            vaccines[i].setDisable(true);
            vaccines[i].setOpacity(1);
            
            if(i < chosenHero.getVaccineInventory().size()){
                Image img = new Image(Main.csvPath + "\\resources\\vaccine.png");
                ImageView view = new ImageView(img);
                view.setFitHeight(25);
                view.setFitWidth(25);
                vaccines[i].setGraphic(view);
                vaccines[i].setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
            }else{
                vaccines[i].setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #ffffff");
            }

            vaccineInventory.getChildren().add(vaccines[i]);
        }

        // Chosen Hero's Supply Inventory

        for(int i = 0; i < supplies.length; i++){
            supplies[i] = new Button();
            supplies[i].setPrefSize(45, 35);
            supplies[i].setDisable(true);
            supplies[i].setOpacity(1);
            
            if(i < chosenHero.getSupplyInventory().size()){
                Image img = new Image(Main.csvPath + "\\resources\\supply.png");
                ImageView view = new ImageView(img);
                view.setFitHeight(25);
                view.setFitWidth(25);
                supplies[i].setGraphic(view);
                supplies[i].setStyle("-fx-border-color: #000000; -fx-border-width: 1px;");
            }else{
                supplies[i].setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #ffffff");
            }

            supplyInventory.getChildren().add(supplies[i]);
        }

        // Chosen Hero Info

        int heroIndex = 0;

        String heroInfo = 	"Type: " + chosenHero.getClass().getSimpleName() +
                            "\nHero Name: " + chosenHero.getName() +
                            "\nHero Health: " + chosenHero.getCurrentHp() + 
                            "\nHero Damage: " + chosenHero.getAttackDmg() + 
                            "\nActions Available: " + chosenHero.getActionsAvailable() +
                            "\nSupplies: " + chosenHero.getSupplyInventory().size() +
                            "\nVaccines: " + chosenHero.getVaccineInventory().size() +
                            "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        info.setText(heroInfo);

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
        root2.getChildren().addAll(grid, vbox);
        Game.startGame(chosenHero);

        info.setFont(Main.font3);
        errors.setFont(Main.font4);

        zombieHpBar.setProgress(1);
        zombieHpBar.setPrefWidth(150);
        zombieHpBar.setPrefHeight(20);
        zombieHpBar.setStyle("-fx-accent: red;");
        zombieHpBar.setTranslateX(100);

        heroHpBar.setProgress(1);
        heroHpBar.setPrefWidth(150);
        heroHpBar.setPrefHeight(20);
        heroHpBar.setStyle("-fx-accent: red;");
        heroHpBar.setTranslateX(100);

        first2Heroes.setSpacing(10);
        second2Heroes.setSpacing(10);

        vaccineInventory.setSpacing(2);
        supplyInventory.setSpacing(2);

        draw();

        scene2.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            int moveCounter=0;
            public void handle(KeyEvent ke) {
                try {
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
                        directionCounter = 1;
                    }else if ((RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.RIGHT) || (!RulesScene.keyBindingsOption1 && ke.getCode() == KeyCode.D)) {
                        chosenHero.move(Direction.RIGHT);
                        ke.consume();
                        moveCounter = 4;
                        directionCounter = 0;
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
                    errors.setText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + e.getMessage());
                }
            }
        });
    }
}
