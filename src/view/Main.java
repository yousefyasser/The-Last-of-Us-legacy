// package view;

// import engine.Game;
// import exceptions.GameActionException;
// import javafx.application.Application;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.stage.Stage;
// import model.characters.Direction;
// import model.characters.Hero;
// import model.characters.Zombie;
// import model.collectibles.Vaccine;
// import model.world.Cell;
// import model.world.CharacterCell;
// import model.world.CollectibleCell;
// import model.world.TrapCell;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.KeyCode;
// import javafx.scene.input.KeyEvent;
// import javafx.scene.layout.GridPane;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;


// public class Main extends Application {
// 	private static final String csvPath = "C:\\Users\\OS\\Desktop\\guc coursework\\sem 4\\CS 4 (Game)\\project\\milestone 3\\src\\engine\\Heros.csv";
// 	private static final String imagesPath = "C:\\Users\\OS\\Desktop\\guc coursework\\sem 4\\CS 4 (Game)\\project\\milestone 3\\src\\engine\\resources\\";
// 	private static Hero chosenHero;

// 	private static Label info = new Label();
// 	private static Label errors = new Label();
// 	// private static HBox root = new HBox();
// 	private static HBox root2 = new HBox();
// 	private static GridPane grid = new GridPane();
// 	private static VBox vbox = new VBox();
// 	// private static Scene scene1 = new Scene(root, 1300, 500);
// 	private static Scene scene2 = new Scene(root2,1200,700);
	
// 	public static void draw() {
// 		Button[][] map = new Button[15][15];
// 		info.setText("");
// 		errors.setText("");

// 		for(int i = 0; i < 15; i++) {
// 			for(int j = 0; j < 15; j++) {
// 				map[i][j] = new Button();
// 				Image img = null;
// 				ImageView view;
				
// 				if(Game.map[14-i][j].isVisible()) {
// 					if(Game.map[14-i][j] instanceof CollectibleCell) {
// 						if(((CollectibleCell)(Game.map[14-i][j])).getCollectible() instanceof Vaccine) {
// 							img = new Image(imagesPath + "vaccine.jpg");
// 						}else {
// 							img = new Image(imagesPath + "supply.jpg");
// 						}
// 					}else if(Game.map[14-i][j] instanceof CharacterCell){
// 						if(((CharacterCell)(Game.map[14-i][j])).getCharacter() instanceof Zombie) {
// 							img = new Image(imagesPath + "zombie.jpg");
// 						}else if(((CharacterCell)(Game.map[14-i][j])).getCharacter() instanceof Hero){
// 							img = new Image(imagesPath + "hero.jpg");
// 						}
// 					}else if(Game.map[14-i][j] instanceof TrapCell) {
// 						img = new Image(imagesPath + "trap.jpg");
// 						//TODO: Animate trap damage
// 					}
// 				}else {
// 					img = new Image(imagesPath + "mystery.jpg");
// 				}

// 				view = new ImageView(img);
// 				view.setFitWidth(50);
// 				view.setFitHeight(35);
// 				view.setPreserveRatio(true);
// 				map[i][j].setGraphic(view);
				
// 				Cell cell = Game.map[14-i][j];

// 				map[i][j].setOnAction(new EventHandler<ActionEvent>() {
					
// 					@Override
// 					public void handle(ActionEvent event) {
// 						if(cell instanceof CharacterCell){
// 							if(((CharacterCell)(cell)).getCharacter() instanceof Zombie && cell.isVisible()) {
// 								chosenHero.setTarget((Zombie)((CharacterCell)(cell)).getCharacter());
// 								String zombieInfo = "Zombie Name: " + ((CharacterCell)(cell)).getCharacter().getName() +
// 													"\nZombie Health: " + ((CharacterCell)(cell)).getCharacter().getCurrentHp() +
// 													"\nZombie Damage: " + ((CharacterCell)(cell)).getCharacter().getAttackDmg();
// 								info.setText(zombieInfo);
// 							}else if(((CharacterCell)(cell)).getCharacter() instanceof Hero && cell.isVisible()){
// 								chosenHero = (Hero)((CharacterCell)(cell)).getCharacter();
// 								String heroInfo = 	"Type: " + chosenHero.getClass().getSimpleName() +
// 													"\nHero Name: " + chosenHero.getName() +
// 													"\nHero Health: " + chosenHero.getCurrentHp() + 
// 													"\nHero Damage: " + chosenHero.getAttackDmg() + 
// 													"\nActions Available: " + chosenHero.getActionsAvailable() +
// 													"\nSupplies: " + chosenHero.getSupplyInventory().size() +
// 													"\nVaccines: " + chosenHero.getVaccineInventory().size();
// 								info.setText(heroInfo);
// 							}
// 						}
// 					}
// 				});

// 				map[i][j].setPrefSize(50, 35);
// 				grid.add(map[i][j], j, i);
// 			}
// 		}
// 		if(!vbox.getChildren().contains(info))
// 			vbox.getChildren().add(info);
// 		if(!vbox.getChildren().contains(errors))
// 			vbox.getChildren().add(errors);
// 	}

// 	@Override
// 	public void start(Stage primaryStage) {
// 		try {
// 			root2.setSpacing(10);
// 			vbox.setSpacing(20);
// 			root2.getChildren().addAll(grid, vbox);
			
			
// 			Game.loadHeroes(csvPath);
// 			chosenHero = Game.availableHeroes.get(2);
			
// 			// second scene (GamePlay)

// 			Game.startGame(chosenHero);
// 			draw();

// 			scene2.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
// 			    public void handle(KeyEvent ke) {
// 			    	try {
// 				        if (ke.getCode() == KeyCode.UP) {
// 				        	chosenHero.move(Direction.UP);
// 				            ke.consume();
// 				        }else if (ke.getCode() == KeyCode.DOWN) {
// 				        	chosenHero.move(Direction.DOWN);
// 				            ke.consume();
// 				        }else if (ke.getCode() == KeyCode.LEFT) {
// 				        	chosenHero.move(Direction.LEFT);
// 				            ke.consume();
// 				        }else if (ke.getCode() == KeyCode.RIGHT) {
// 				        	chosenHero.move(Direction.RIGHT);
// 				            ke.consume();
// 				        }else if(ke.getCode() == KeyCode.SPACE) {
// 				        	chosenHero.attack();
// 						}else if(ke.getCode() == KeyCode.DIGIT1) {
// 							chosenHero.useSpecial();
// 						}else if(ke.getCode() == KeyCode.E){
// 							Game.endTurn();
// 							grid.getChildren().clear();
// 						}else if(ke.getCode() == KeyCode.C){
// 							chosenHero.cure();
// 						}
// 						draw();
// 			    	}catch(GameActionException e) {
// 						errors.setText(e.getMessage());
// 				    }
// 			    }
// 			});
			
// 			// first scene (Hero Selection)
			
// // 			for(int i = 0; i < Game.availableHeroes.size(); i++) {
// // 				Hero h = Game.availableHeroes.get(i);
				
// // 				EventHandler<MouseEvent> e = new EventHandler<MouseEvent>() {

// // 					@Override
// // 					public void handle(MouseEvent arg0) {
// // //						chosenHero = h;
// // //						primaryStage.setScene(scene2);
// // 					}
// // 				};
				
// // 				Image img = new Image(imagesPath + "heroes\\" + Game.availableHeroes.get(i).getName() + ".jpg");
// // 				ImageView view = new ImageView(img);
// // 				view.setFitWidth(1300/Game.availableHeroes.size());
// // 				view.setFitHeight(500);
// // 			    view.setPreserveRatio(true);
			    
// // 				Button hero = new Button();
// // 				hero.setGraphic(view);
// // 				hero.setPrefSize(1300/Game.availableHeroes.size(), 500);
// // 				root.getChildren().add(hero);
				
// // 				hero.setOnMouseClicked(e);
// // 			}
			
// 			primaryStage.setScene(scene2);
// 			primaryStage.show();
			
// 		} catch(Exception e) {
// 			e.printStackTrace();
// 		}
// 	}
	
// 	public static void main(String[] args) {
// 		launch();
// 	}
// }
