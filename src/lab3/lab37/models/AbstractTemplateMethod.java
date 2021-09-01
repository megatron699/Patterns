package lab3.lab37.models;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.Random;

public abstract class AbstractTemplateMethod implements Runnable {
    protected Node node;
    protected double width;
    protected double height;
    private final int widthAndHeight = 949;
    private final int startWidthAndHeight = 51;

    public abstract void drawFigure();

    public Node getNode() {
        return node;
    }

    public AbstractTemplateMethod() {
        drawFigure();
        setDefaultPosition();
    }

    private void setDefaultPosition() {
        node.setTranslateX(widthAndHeight - width);
        node.setTranslateY(widthAndHeight - height);
    }

    @Override
    public synchronized void run() {
        setDefaultPosition();

        Random random = new Random();
        while (true) {
            double x = node.getTranslateX(),
                    y = node.getTranslateY();

            while (x != startWidthAndHeight
                    && y != startWidthAndHeight
                    && x != widthAndHeight - width
                    && y != widthAndHeight - height) {
                x = node.getTranslateX();
                y = node.getTranslateY();

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            double newX = 0, newY = 0;

            int midl = 300;

            int randomMidlValue = random.nextInt(midl);
            randomMidlValue = randomMidlValue > midl / 2
                    ? randomMidlValue
                    : -randomMidlValue;

            int midlFigure = (widthAndHeight - 49) / 2;
            if (x == startWidthAndHeight) {
                // Лево
                newY = -y + widthAndHeight - height;
                newX = -x + midlFigure + randomMidlValue;
            } else if (x == widthAndHeight - width) {
                // Первый запуск
                // Низ
                newY = -y + startWidthAndHeight;
                newX = -x + midlFigure + randomMidlValue;
            } else if (y == startWidthAndHeight) {
                // Вверх
                newX = -x + startWidthAndHeight;
                newY = -y + midlFigure + randomMidlValue;
            } else {
                // Право
                newX = -x + widthAndHeight - width;
                newY = -y + midlFigure + randomMidlValue;
            }

            startAnimation(newX, newY);
        }
    }

    private void startAnimation(double newX, double newY) {
        TranslateTransition translateTransition = new TranslateTransition();
        int animationDuration = 1500;
        translateTransition.setDuration(Duration.millis(animationDuration));
        translateTransition.setNode(node);
        translateTransition.setByX(newX);
        translateTransition.setByY(newY);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(false);
        translateTransition.play();

        try {
            Thread.sleep(animationDuration / 4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
