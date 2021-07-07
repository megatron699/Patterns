package lab2.lab23.models;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import lab2.lab23.enums.ETrafficLightColor;
import lab2.lab23.service.DrawUtils;


import java.util.HashMap;
import java.util.Map;

public class TrafficLight {
    private final Group trafficLightGroup;
    private final Map<ETrafficLightColor, TrafficLightCircle> trafficLightCircleMap;
    private final double defaultOpacity = 0.2;

    private ETrafficLightColor currentColor = ETrafficLightColor.GREEN;

    public Group getTrafficLightGroup() {
        return trafficLightGroup;
    }

    public TrafficLight() {
        Line trafficLine = new Line(700, 300, 700, 100);

        Rectangle trafficRectangle = new Rectangle(50, 100, Color.WHITE);
        trafficRectangle.setStroke(Color.BLACK);
        trafficRectangle.setTranslateX(675);
        trafficRectangle.setTranslateY(0);

        int firstCircleCenterY = 18;
        Circle red = DrawUtils.drawCircle(700, firstCircleCenterY, 14);
        Circle yellow = DrawUtils.drawCircle(700, firstCircleCenterY + 4 + 28, 14);
        Circle green = DrawUtils.drawCircle(700, firstCircleCenterY + 4 + 28 + 4 + 28, 14);

        setColorAndOpacity(red, Color.RED, defaultOpacity);
        setColorAndOpacity(green, Color.GREEN, defaultOpacity);
        setColorAndOpacity(yellow, Color.YELLOW, defaultOpacity);


        trafficLightCircleMap = new HashMap<ETrafficLightColor, TrafficLightCircle>() {{
            put(ETrafficLightColor.GREEN, new TrafficLightCircle(green, Color.GREEN));
            put(ETrafficLightColor.RED, new TrafficLightCircle(red, Color.RED));
            put(ETrafficLightColor.YELLOW, new TrafficLightCircle(yellow, Color.YELLOW));
        }};

        trafficLightGroup = new Group(trafficLine, trafficRectangle, red, yellow, green);
    }

    public ETrafficLightColor changeColor() {
        TrafficLightCircle currentCircle = trafficLightCircleMap.get(currentColor);
        setColorAndOpacity(currentCircle.circle, currentCircle.color, defaultOpacity);

        currentColor = currentColor == ETrafficLightColor.GREEN
                ? ETrafficLightColor.YELLOW
                : currentColor == ETrafficLightColor.YELLOW
                ? ETrafficLightColor.RED
                : ETrafficLightColor.GREEN;

        currentCircle = trafficLightCircleMap.get(currentColor);
        setColorAndOpacity(currentCircle.circle, currentCircle.color, 1);

        return currentColor;
    }

    private void setColorAndOpacity(Circle circle, Color color, double opacity) {
        circle.setFill(new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity));
        circle.setStroke(Color.BLACK);
    }

    private class TrafficLightCircle {
        private final Circle circle;
        private final Color color;

        public Circle getCircle() {
            return circle;
        }

        public Color getColor() {
            return color;
        }

        public TrafficLightCircle(Circle circle, Color color) {
            this.circle = circle;
            this.color = color;
        }
    }
}
