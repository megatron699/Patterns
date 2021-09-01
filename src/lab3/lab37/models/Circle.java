package lab3.lab37.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Circle extends AbstractTemplateMethod{
    @Override
    public void drawFigure() {
        double radius = 12.5;
        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(radius);
        Group group = new Group();
        group.getChildren().add(circle);

        circle.setTranslateX(radius);
        circle.setTranslateY(radius);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);

        width = height = radius * 2;

        node = group;
    }

}
