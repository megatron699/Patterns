package lab2.lab23;

import javafx.application.Application;
import javafx.stage.Stage;
import lab2.lab23.models.Facade;

public class Lab23Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Facade facade = new Facade(primaryStage);
        facade.drawTrafficLight();
        facade.drawCar();
        facade.startAnimation();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
