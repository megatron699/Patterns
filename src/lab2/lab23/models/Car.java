package lab2.lab23.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import lab2.lab23.service.DrawUtils;

public class Car {
    private final Group carGroup;
    private final Rectangle mainRectangle;

    public Group getCarGroup() {
        return carGroup;
    }

    private double rightBoard;

    public Car() {
        Group leftWheel = drawWheel(110, 260, 40);
        Group rightWheel = drawWheel(300, 260, 40);

        Rectangle rectangleDoor = new Rectangle(15, 120, Color.WHITE);
        rectangleDoor.setStroke(Color.BLACK);
        rectangleDoor.setTranslateX(190);
        rectangleDoor.setTranslateY(140);

        mainRectangle = new Rectangle(380, 60, Color.WHITE);
        mainRectangle.setStroke(Color.BLACK);
        mainRectangle.setTranslateX(15);
        mainRectangle.setTranslateY(200);
        rightBoard = 15 + 380;

        Polygon polygonGlass = new Polygon(
                35, 200,
                148, 140,
                261, 140,
                375, 200
        );
        polygonGlass.setStroke(Color.BLACK);
        polygonGlass.setFill(Color.WHITE);

        carGroup = new Group(leftWheel, rightWheel, mainRectangle, polygonGlass, rectangleDoor);
    }

    public void translateX(double x) {
        rightBoard = x + 380;
        carGroup.setTranslateX(x);
    }

    public double getRightBorder() {
        return rightBoard;
    }

    private Group drawWheel(double x, double y, double r) {
        return new Group(
                DrawUtils.drawCircle(x, y, r),
                DrawUtils.drawCircle(x, y, r - 15)
        );
    }
}

