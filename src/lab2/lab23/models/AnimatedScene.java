package lab2.lab23.models;


import lab2.lab23.enums.ETrafficLightColor;

import java.util.HashMap;
import java.util.Map;

public class AnimatedScene {
    private final Car car;
    private final TrafficLight trafficLight;
    private final Map<ETrafficLightColor, Integer> sleepTrafficLight;

    private volatile ETrafficLightColor currentColorTrafficLight;

    public synchronized ETrafficLightColor getCurrentColorTrafficLight() {
        return currentColorTrafficLight;
    }

    public synchronized void setCurrentColorTrafficLight(ETrafficLightColor currentColorTrafficLight) {
        this.currentColorTrafficLight = currentColorTrafficLight;
    }

    public AnimatedScene(Car car, TrafficLight trafficLight) {
        this.car = car;
        this.trafficLight = trafficLight;

        sleepTrafficLight = new HashMap<ETrafficLightColor, Integer>() {{
            put(ETrafficLightColor.GREEN, 4000);
            put(ETrafficLightColor.YELLOW, 1000);
            put(ETrafficLightColor.RED, 3000);
        }};
    }

    public void startAnimation() {
        new Thread(this::startCarAnimation).start();
        new Thread(this::startTrafficLight).start();
    }

    private void startCarAnimation() {
        while (true) {
            double rightBorder = car.getRightBorder();

            if (getCurrentColorTrafficLight() == ETrafficLightColor.GREEN
                    || !(rightBorder >= 580) || !(rightBorder <= 700)) {
                car.translateX(car.getCarGroup().getTranslateX() + 20);
            }

            if (rightBorder >= 1500) {
                car.translateX(-500);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void startTrafficLight() {
        while (true) {
            setCurrentColorTrafficLight(trafficLight.changeColor());

            try {
                Thread.sleep(sleepTrafficLight.get(getCurrentColorTrafficLight()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

