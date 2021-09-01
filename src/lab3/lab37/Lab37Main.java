package lab3.lab37;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lab3.lab37.models.AbstractTemplateMethod;
import lab3.lab37.models.Circle;
import lab3.lab37.models.Square;
import lab3.lab37.models.Star;

import java.util.ArrayList;

public class Lab37Main extends Application {
    private final ArrayList<Thread> threads = new ArrayList<>();
    private String currentSelectElement;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Template Method");
        int width = 1000;
        int height = 1000;

        Group rootGroup = new Group();
        Button closeButton = new Button();
        Button startButton = new Button();


        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(
                "Мяч",
                "Квадрат",
                "Звезда"
        );

        comboBox.valueProperty().addListener((ChangeListener<String>) (ov, oldValue, newValue) -> currentSelectElement = newValue);

        comboBox.setValue("Мяч");

        closeButton.setText("Закрыть");
        startButton.setText("Пуск");

        int yButton = 970;

        closeButton.setTranslateX(900);
        closeButton.setTranslateY(yButton);

        closeButton.setOnAction(event -> {
            threads.forEach(Thread::stop);
            primaryStage.close();
        });

        startButton.setTranslateX(850);
        startButton.setTranslateY(yButton);

        comboBox.setTranslateX(750);
        comboBox.setTranslateY(yButton);

        Group rectangleScene = new Group();
        Rectangle rectangle = new Rectangle(900, 900, Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setTranslateX(50);
        rectangle.setTranslateY(50);
        rectangle.setStrokeWidth(1);

        rectangleScene.getChildren().add(rectangle);

        rootGroup.getChildren().addAll(rectangleScene, closeButton, startButton, comboBox);

        Scene scene = new Scene(rootGroup, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(event -> {
            AbstractTemplateMethod figure = currentSelectElement.equals("Мяч")
                    ? new Circle()
                    : currentSelectElement.equals("Звезда")
                    ? new Star()
                    : new Square();
            rootGroup.getChildren().add(figure.getNode());
            Thread thread = new Thread(figure);
            thread.start();
            threads.add(thread);
        });
    }


    public static void main(String[] args) {
        launch(args);
    }


}
