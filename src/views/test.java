package views;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class test extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        HBox root = new HBox();
        root.setSpacing(2);
        Button r1 = new Button();
        r1.setPrefSize(25, 25);
        r1.setDisable(true);
        r1.setStyle("-fx-background-color: #000000");
        Button r2 = new Button();
        r2.setPrefSize(25, 25);
        r2.setDisable(true);
        r2.setStyle("-fx-background-color: #000000");
        Button r3 = new Button();
        r3.setPrefSize(25, 25);
        r3.setDisable(true);
        r3.setStyle("-fx-background-color: #000000");
        Button r4 = new Button();
        r4.setPrefSize(25, 25);
        r4.setDisable(true);
        r4.setStyle("-fx-background-color: #000000");
        Button r5 = new Button();
        r5.setPrefSize(25, 25);
        r5.setDisable(true);
        r5.setStyle("-fx-background-color: #000000");
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(r1, r2, r3, r4, r5);
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
