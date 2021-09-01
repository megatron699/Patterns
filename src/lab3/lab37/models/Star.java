package lab3.lab37.models;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Arrays;

public class Star extends AbstractTemplateMethod{
    @Override
    public void drawFigure() {
        Polygon polygon = new Polygon();
        Double[] doubles = Arrays.stream(new double[]{0.0, 75.0,
                75.0, 65.0,
                100.0, 0.0,
                125.0, 65.0,
                200.0, 75.0,
                150.0, 115.0,
                160.0, 180.0,
                100.0, 140.0,
                40.0, 180.0,
                50.0, 115.0}).map(el -> el * 0.125).boxed().toArray(Double[]::new);

        polygon.maxWidth(25);
        polygon.maxHeight(25);
        polygon.setFill(Color.YELLOW);
        polygon.setStroke(Color.BLACK);

        width = height = 25;
        polygon.getPoints().addAll(doubles);

        node = polygon;
    }

}
