package lab3.lab35;

import javafx.application.Application;
import javafx.stage.Stage;
import lab3.lab35.models.SmileScene;

public class Lab35Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        new SmileScene(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
