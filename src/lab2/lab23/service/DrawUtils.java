package lab2.lab23.service;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DrawUtils {
    public static Circle drawCircle(double x, double y, double r) {
        Circle circle = new Circle();
        circle.setCenterX(x);
        circle.setCenterY(y);
        circle.setRadius(r);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        return circle;
    }

}
