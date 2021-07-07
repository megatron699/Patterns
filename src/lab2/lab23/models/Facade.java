package lab2.lab23.models;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Facade {
    private final Stage stage;
    private final Car car;
    private final TrafficLight trafficLight;
    private final Group rootGroup;
    private final AnimatedScene animatedScene;

    public Facade(Stage primaryStage) {
        stage = primaryStage;
        car = new Car();
        trafficLight = new TrafficLight();
        animatedScene = new AnimatedScene(car, trafficLight);

        primaryStage.setTitle("Facade");
        int width = 1000;
        int height = 400;
        int lineHeight = height - 100;

        Line line = new Line(0, lineHeight, width, lineHeight);

        rootGroup = new Group(line);

        Scene scene = new Scene(rootGroup, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawCar() {
        rootGroup.getChildren().add(car.getCarGroup());
    }

    public void drawTrafficLight() {
        rootGroup.getChildren().add(trafficLight.getTrafficLightGroup());
    }

    public void startAnimation() {
        animatedScene.startAnimation();
    }
}
