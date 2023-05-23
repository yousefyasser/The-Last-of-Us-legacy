package views;

import engine.Game;
import exceptions.GameActionException;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.characters.Direction;
import model.characters.Character;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Scene2 {

    public static Hero chosenHero;
    public static Character chosenTarget;
    public static Label info = new Label();
    public static Label errors = new Label();
    public static HBox root2 = new HBox();
    public static GridPane grid = new GridPane();
    public static VBox vbox = new VBox();

    public static Scene scene2 = new Scene(root2, 1300, 680);
    public static Button[][] map = new Button[15][15];
    public static Label[] allHeroInfo = new Label[6];
    public static HBox first2Heroes = new HBox();
    public static HBox second2Heroes = new HBox();
    public static HBox final2Heroes = new HBox();
    public static int counter = 0;
    public static int counter2 = 0;

    public static void draw() {
        info.setText("");
        errors.setText("");
        
        // On click event for each button on grid to show character info

        EventHandler<Event> e = new EventHandler<Event>(){

            @Override
            public void handle(Event arg0) {
                int buttonX = GridPane.getColumnIndex((Button)(arg0.getSource()));
                int buttonY = GridPane.getRowIndex((Button)(arg0.getSource()));
                Cell cell = Game.map[14-buttonY][buttonX];

                if(cell instanceof CharacterCell){
                    if(((CharacterCell)(cell)).getCharacter() instanceof Zombie) {
                        chosenTarget = (Zombie)((CharacterCell)(cell)).getCharacter();
                        chosenHero.setTarget(chosenTarget);
                        String zombieInfo = "Zombie Name: " + ((CharacterCell)(cell)).getCharacter().getName() +
                                            "\nZombie Health: " + ((CharacterCell)(cell)).getCharacter().getCurrentHp() +
                                            "\nZombie Damage: " + ((CharacterCell)(cell)).getCharacter().getAttackDmg();
                        info.setText(zombieInfo);
                    }else if(((CharacterCell)(cell)).getCharacter() instanceof Hero) {
                        chosenHero = (Hero)(((CharacterCell)(cell)).getCharacter());
                        getAllHeroesInfo();
                    }
                }
            }
        };

        // event for setting target

        EventHandler<KeyEvent> e2 = new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent ke) {
                if(ke.getCode() == KeyCode.Q){
                    int buttonX = GridPane.getColumnIndex((Button)(ke.getSource()));
                    int buttonY = GridPane.getRowIndex((Button)(ke.getSource()));
                    Cell cell = Game.map[14-buttonY][buttonX];

                    if(cell instanceof CharacterCell){
                        if(((CharacterCell) cell).getCharacter() instanceof Hero){
                            chosenTarget = (Hero)(((CharacterCell)(cell)).getCharacter());
                            chosenHero.setTarget(chosenTarget);
                        }
                    }
                }
            }
        };

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

				map[i][j].setOnMouseClicked(e);
                map[i][j].addEventFilter(KeyEvent.KEY_PRESSED, e2);

				grid.add(map[i][j], j, i);
			}
		}

        // Animations

        if(counter2 == 5 && chosenTarget != null){
            counter2 = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateDmg(b, 0.5); 

            Button b2 = map[14-chosenTarget.getLocation().x][chosenTarget.getLocation().y];
            animateDmg(b2, 0.5);
        }else if(counter2 == 4){
            counter2 = 0;
            Button b = map[14-chosenHero.getLocation().x][chosenHero.getLocation().y];
            animateDmg(b, 0.5); 
        }else if(counter2 == 3){
            counter2 = 0;
            FadeTransition fade = new FadeTransition();
            fade.setDuration(Duration.millis(1000));
            fade.setFromValue(10);
            fade.setToValue(0.1);
            fade.setCycleCount(1);
            fade.setAutoReverse(true);
            fade.setNode(map[14 - chosenHero.getLocation().x][chosenHero.getLocation().y].getGraphic());
            fade.play();
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
                        counter2 = 5;
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
                            counter2 = 4;
                        }
                        count = 0;
                    }
                    if(chosenHero.getCurrentHp() <= 0){
                        counter2 = 3;
                    }
                    // if(){
                    //         check if zombie is dead -> fade away animation
                    // }
                    if(Game.checkGameOver()){
                        GameOverScene.setup_gameOverScene();
                        Main.primaryStage.setScene(GameOverScene.gameOverScene);
                    }
                    if(Game.checkWin()){
                        WinScene.setup_winScene();
                        Main.primaryStage.setScene(WinScene.winScene);
                    }

                    draw();
                }catch(GameActionException e) {
                    errors.setText("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + e.getMessage());
                }
            }
        });
    }
}
