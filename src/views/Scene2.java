package views;

import java.io.File;

import engine.Game;
import exceptions.GameActionException;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.characters.Direction;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Scene2 {

    public static Hero chosenHero;
    public static Character chosenTarget;
    public static Label info = new Label();
    public static Label errors = new Label();
    public static HBox root2 = new HBox();
    public static GridPane grid = new GridPane();
    public static VBox vbox = new VBox();
    
    public static ProgressBar heroHpBar = new ProgressBar();
    public static ProgressBar zombieHpBar = new ProgressBar();

    public static Scene scene2 = new Scene(root2, 1300, 680);
    public static Button[][] map = new Button[15][15];
    public static Label[] allHeroInfo = new Label[6];
    public static HBox first2Heroes = new HBox();
    public static HBox second2Heroes = new HBox();
    public static HBox final2Heroes = new HBox();
    public static int counter = 0;
    public static int animationCounter = 0;

    public static void draw() {
        info.setText("");
        errors.setText("");
        
        if(animationCounter == 3){
            animationCounter = 0;
            // FadeTransition fade = new FadeTransition();
            // fade.setDuration(Duration.millis(1000));
            // fade.setFromValue(10);
            // fade.setToValue(0);
            // fade.setCycleCount(1);
            // fade.setAutoReverse(true);
            // fade.setNode(map[14 - chosenHero.getLocation().x][chosenHero.getLocation().y].getGraphic());
            // fade.play();
            if(Game.heroes.size()>0){
                chosenHero = Game.heroes.get(0);
            }
        }
       

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
							// if(counter == 0){
                            //     img = new Image(Main.resPath + chosenHero.getName() + ".png");
                            // }else if(counter == 1){
                            //     img = new Image(Main.resPath + chosenHero.getName() + "2.png");
                            // }
                            img = new Image(Main.resPath + "hero.png");
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
                    if (event.getButton() == MouseButton.PRIMARY)
                    {
                        
        
                        if(cell instanceof CharacterCell){
                            if(((CharacterCell)(cell)).getCharacter() instanceof Zombie) {
                                chosenTarget = (Zombie)((CharacterCell)(cell)).getCharacter();
                                chosenHero.setTarget(chosenTarget);
                                zombieHpBar.setProgress(((CharacterCell)(cell)).getCharacter().getCurrentHp()/((CharacterCell)(cell)).getCharacter().getMaxHp());
                                String zombieInfo = "Zombie Name: " + ((CharacterCell)(cell)).getCharacter().getName() +
                                "\nZombie Damage: " + ((CharacterCell)(cell)).getCharacter().getAttackDmg() +
                                "\nZombie Health: "  ;
                                info.setText(zombieInfo);
                                // if(!vbox.getChildren().contains(zombieHpBar))
                                // vbox.getChildren().add(zombieHpBar);
                            }else if(((CharacterCell)(cell)).getCharacter() instanceof Hero) {
                               
                                chosenHero = (Hero)(((CharacterCell)(cell)).getCharacter());
                                getAllHeroesInfo();
                            }
                        }
                    } else if (event.getButton() == MouseButton.SECONDARY)
                    {
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

        // Animations

        if(animationCounter == 5 && chosenTarget != null){
            animationCounter = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateDmg(b, 0.7); 

            Button b2 = map[14-chosenTarget.getLocation().x][chosenTarget.getLocation().y];
            animateDmg(b2, 0.7);
            String path = Main.csvPath + "\\resources\\punch.mp3";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            // mediaPlayer.set
        }else if(animationCounter == 4){
            animationCounter = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateDmg(b, 0.7);
            String path = Main.csvPath + "\\resources\\trap.mp3";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

        }


        getAllHeroesInfo();
        
		if(!vbox.getChildren().contains(info))
            vbox.getChildren().add(info);
        if(!vbox.getChildren().contains(first2Heroes))
            vbox.getChildren().add(first2Heroes);
        if(!vbox.getChildren().contains(second2Heroes))
            vbox.getChildren().add(second2Heroes);
        if(!vbox.getChildren().contains(final2Heroes))
            vbox.getChildren().add(final2Heroes);
        if(!vbox.getChildren().contains(errors))
			vbox.getChildren().add(errors);
	}

    public static void animateDmg(Button b, double duration){
        b.setStyle("-fx-base: red;");
        PauseTransition pause = new PauseTransition(Duration.seconds(duration));
        pause.setOnFinished(event -> {
            b.setStyle(null);
        });
        pause.play();
    }

    public static void getAllHeroesInfo() {
        first2Heroes.getChildren().clear();
        second2Heroes.getChildren().clear();
        final2Heroes.getChildren().clear();
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
                   
                }else{
                    final2Heroes.getChildren().add(allHeroInfo[i]);
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
        draw();

        info.setFont(Main.font3);
        errors.setFont(Main.font4);

        first2Heroes.setSpacing(10);
        second2Heroes.setSpacing(10);
        final2Heroes.setSpacing(10);

        scene2.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            int count=0;
            public void handle(KeyEvent ke) {
                try {
                    int hpBefore = chosenHero.getCurrentHp();
                    if (ke.getCode() == KeyCode.UP) {
                        chosenHero.move(Direction.UP);
                        ke.consume();
                        count = 1;
                    }else if (ke.getCode() == KeyCode.DOWN) {
                        chosenHero.move(Direction.DOWN);
                        ke.consume();
                        count = 2;
                    }else if (ke.getCode() == KeyCode.LEFT) {
                        chosenHero.move(Direction.LEFT);
                        ke.consume();
                        count = 3;
                        counter = 1;
                    }else if (ke.getCode() == KeyCode.RIGHT) {
                        chosenHero.move(Direction.RIGHT);
                        ke.consume();
                        count = 4;
                        counter = 0;
                    }else if(ke.getCode() == KeyCode.SPACE) {
                        chosenHero.attack();
                        ke.consume();
                        animationCounter = 5;
                    }else if(ke.getCode() == KeyCode.DIGIT1) {
                        chosenHero.useSpecial();
                        ke.consume();
                    }else if(ke.getCode() == KeyCode.E){
                        Game.endTurn();
                        grid.getChildren().clear();
                        ke.consume();
                    }else if(ke.getCode() == KeyCode.C){
                        chosenHero.cure();
                        ke.consume();
                    }
                    if(count != 0){
                        if(hpBefore != chosenHero.getCurrentHp()){
                            animationCounter = 4;
                        }
                        count = 0;
                    }
                    if(chosenHero.getCurrentHp() <= 0){
                        animationCounter = 3;
                       
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
