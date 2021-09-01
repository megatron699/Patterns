package lab3.lab35.models;

import javafx.scene.Group;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import lab3.lab35.interfaces.Observer;

public class Mouth implements Observer {
    private Group mouthGroup;

    private double x1;
    private double x2;
    private double y1;

    private boolean isSmile;

    public Group getMouthGroup() {
        return mouthGroup;
    }

    public Mouth(double x1, double x2, double y1, String id) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;

        mouthGroup = new Group();
        mouthGroup.setId(id);

        drawMouth();
    }

    private void drawMouth() {
        Line line = new Line(x1, y1, x2, y1);
        line.setStrokeWidth(5);
        mouthGroup.getChildren().clear();
        mouthGroup.getChildren().add(line);

        isSmile = false;
    }


    private void drawSmileMouth() {
        mouthGroup.getChildren().clear();
        Arc arc = new Arc((x2+x1)/2.0, y1, 50, 50, 0, -180);

        mouthGroup.getChildren().add(arc);

        isSmile = true;
    }

    @Override
    public void update() {
        if (isSmile) {
            drawMouth();
            return;
        }

        drawSmileMouth();
    }


}
