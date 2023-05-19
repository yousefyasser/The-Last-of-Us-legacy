package views;

import engine.Game;
import exceptions.GameActionException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.characters.Direction;
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

public class Scene2 {

    public static Hero chosenHero;
    public static Label info = new Label();
    public static Label errors = new Label();
    public static HBox root2 = new HBox();
    public static GridPane grid = new GridPane();
    public static VBox vbox = new VBox();
    public static Scene scene2 = new Scene(root2,1200,700);
    public static Button[][] map = new Button[15][15];

    public static void draw() {
		info.setText("");
		errors.setText("");

		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				map[i][j] = new Button();
				Image img = null;
				ImageView view;
				
				if(Game.map[14-i][j].isVisible()) {
					if(Game.map[14-i][j] instanceof CollectibleCell) {
						if(((CollectibleCell)(Game.map[14-i][j])).getCollectible() instanceof Vaccine) {
							img = new Image(Main.resPath + "vaccine.jpg");
						}else {
							img = new Image(Main.resPath + "supply.jpg");
						}
					}else if(Game.map[14-i][j] instanceof CharacterCell){
						if(((CharacterCell)(Game.map[14-i][j])).getCharacter() instanceof Zombie) {
							img = new Image(Main.resPath + "zombie.jpg");
						}else if(((CharacterCell)(Game.map[14-i][j])).getCharacter() instanceof Hero){
							img = new Image(Main.resPath + "hero.jpg");
						}
					 }
                     else if(Game.map[14-i][j] instanceof TrapCell) {
						img = new Image(Main.resPath + "trap.jpg");
						
					}
				}else {
					img = new Image(Main.resPath + "mystery.jpg");
				}

				view = new ImageView(img);
				view.setFitWidth(50);
				view.setFitHeight(35);
				view.setPreserveRatio(true);
				map[i][j].setGraphic(view);
				
				Cell cell = Game.map[14-i][j];

				map[i][j].setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						if(cell instanceof CharacterCell){
							if(((CharacterCell)(cell)).getCharacter() instanceof Zombie && cell.isVisible()) {
								chosenHero.setTarget((Zombie)((CharacterCell)(cell)).getCharacter());
								String zombieInfo = "Zombie Name: " + ((CharacterCell)(cell)).getCharacter().getName() +
													"\nZombie Health: " + ((CharacterCell)(cell)).getCharacter().getCurrentHp() +
													"\nZombie Damage: " + ((CharacterCell)(cell)).getCharacter().getAttackDmg();
								info.setText(zombieInfo);
							}else if(((CharacterCell)(cell)).getCharacter() instanceof Hero && cell.isVisible()){
								chosenHero = (Hero)((CharacterCell)(cell)).getCharacter();
								String heroInfo = 	"Type: " + chosenHero.getClass().getSimpleName() +
													"\nHero Name: " + chosenHero.getName() +
													"\nHero Health: " + chosenHero.getCurrentHp() + 
													"\nHero Damage: " + chosenHero.getAttackDmg() + 
													"\nActions Available: " + chosenHero.getActionsAvailable() +
													"\nSupplies: " + chosenHero.getSupplyInventory().size() +
													"\nVaccines: " + chosenHero.getVaccineInventory().size();
								info.setText(heroInfo);
							}
						}
					}
				});

				map[i][j].setPrefSize(50, 35);
				grid.add(map[i][j], j, i);
			}
		}
		if(!vbox.getChildren().contains(info))
			vbox.getChildren().add(info);
		if(!vbox.getChildren().contains(errors))
			vbox.getChildren().add(errors);
	}

    public static void setup_scene2(){
        root2.setSpacing(10);
        vbox.setSpacing(20);
        root2.getChildren().addAll(Scene2.grid, Scene2.vbox);
        Game.startGame(chosenHero);
        Scene2.draw();
            
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
                    }else if (ke.getCode() == KeyCode.RIGHT) {
                        chosenHero.move(Direction.RIGHT);
                        ke.consume();
                        count = 4;
                    }else if(ke.getCode() == KeyCode.SPACE) {
                        chosenHero.attack();
                        ke.consume();
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
                    
                    if(count!= 0){
                        // if(Game.map[chosenHero.getLocation().x][chosenHero.getLocation().y] instanceof TrapCell){
                        //     System.out.println("hello");
                        // }

                        if(hpBefore != chosenHero.getCurrentHp()){
                            System.out.println("hello");
                            // map[14-chosenHero.getLocation().x][chosenHero.getLocation().y].setStyle("-fx-background-color: #ff0000");
                        }
                        count = 0;
                    }
                    // if(){
                    //     Animate Zombie Damage
                    // }
                    // if(){
                    //     Animate Hero Damage
                    // }
                    if(chosenHero.getCurrentHp() <= 0){
                        // FadeTransition fade = new FadeTransition();
                        // fade.setDuration(Duration.millis(1000));
                        // fade.setFromValue(10);
                        // fade.setToValue(0.1);
                        // fade.setCycleCount(1000);
                        // fade.setAutoReverse(true);
                        // fade.setNode(map[14 - chosenHero.getLocation().x][chosenHero.getLocation().y].getGraphic());
                        // fade.play();

                        // check if hero is dead -> fade away animation
                    }
                    // if(){
                    //         check if zombie is dead -> fade away animation
                    // }
                    if(Game.checkGameOver()){
                        GameOverScene.msg.getChildren().clear();
                        GameOverScene.setup_gameOverScene();
                        Main.primaryStage.setScene(GameOverScene.gameOverScene);
                    }
                    if(Game.checkWin()){
                        WinScene.msg.getChildren().clear();
                        WinScene.setup_winScene();
                        Main.primaryStage.setScene(WinScene.winScene);
                    }

                    draw();
                }catch(GameActionException e) {
                    errors.setText(e.getMessage());
                }
            }
        });
    }
}
