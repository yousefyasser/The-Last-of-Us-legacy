package views;

import engine.Game;
import exceptions.GameActionException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import views.Scene2;

public class Main extends Application {
	private static final String csvPath = "D:\\CMS SEM 4 SEIF (MET)\\(CSEN401) Computer Programming Lab\\GAME\\Milestone 3\\Working\\The-Last-of-Us-legacy-main\\src\\engine\\Heros.csv";
	private static final String imagesPath = "file:" + "D:\\CMS SEM 4 SEIF (MET)\\(CSEN401) Computer Programming Lab\\GAME\\Milestone 3\\Working\\The-Last-of-Us-legacy-main\\src\\engine\\resources\\";
	private static Hero chosenHero;

	// private static Label info = new Label();
	// private static Label errors = new Label();
	 private static HBox root = new HBox();
	// private static HBox root2 = new HBox();
	// private static GridPane grid = new GridPane();
	// private static VBox vbox = new VBox();
	 private static Scene scene1 = new Scene(root, 1300, 500);
	// private static Scene scene2 = new Scene(root2,1200,700);
	
	

	@Override
	public void start(Stage primaryStage) {
		try {

			Game.loadHeroes(csvPath);
			//chosenHero = Game.availableHeroes.get(2);
			primaryStage.setTitle("The Last of Us");
			// first scene (Hero Selection)
			EventHandler<MouseEvent> e = new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
						arg0.getSource();
						int heroIndx = Integer.parseInt(((Button) (arg0.getSource())).getText());
						Scene2.chosenHero = Game.availableHeroes.get(heroIndx);
						Scene2.setup_scene2();
						primaryStage.setScene(Scene2.scene2);
				}
			};
			
			for(int i = 0; i < Game.availableHeroes.size(); i++) {
				
				Image img = new Image(imagesPath + "heroes\\" + Game.availableHeroes.get(i).getName() + ".jpg");
				ImageView view = new ImageView(img);
				view.setFitWidth(1300/Game.availableHeroes.size());
				view.setFitHeight(500);
			    view.setPreserveRatio(true);
			    
				Button hero = new Button(i + "");
				hero.setGraphic(view);
				hero.setPrefSize(1300/Game.availableHeroes.size(), 500);
				root.getChildren().add(hero);
				
				hero.setOnMouseClicked(e);
			}
			
			primaryStage.setScene(scene1);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch();
		// System.out.println(Game.availableHeroes.size());
	}
}
