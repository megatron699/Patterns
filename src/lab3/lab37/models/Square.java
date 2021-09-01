package lab3.lab37.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square extends AbstractTemplateMethod {
    @Override
    public void drawFigure() {
        Rectangle rectangle = new Rectangle(25, 25, Color.GREEN);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);

        width = height = 25;

        node = rectangle;
    }

}
