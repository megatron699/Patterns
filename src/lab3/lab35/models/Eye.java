package lab3.lab35.models;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import lab3.lab35.interfaces.Observer;

public class Eye implements Observer {
    private Group eyeGroup;
    private double x;
    private double y;
    private double r;
    private boolean isOpen;

    public Group getEyeGroup() {
        return eyeGroup;
    }

    public Eye(double x, double y, double r, String id) {
        eyeGroup = new Group();
        eyeGroup.setId(id);
        this.x = x;
        this.y = y;
        this.r = r;

        openEye();
    }

    public void openEye() {
        eyeGroup.getChildren().clear();
        eyeGroup.getChildren().addAll(new Circle[]{
                DrawHelper.drawCircle(x, y, r),
                DrawHelper.drawCircle(x, y, r / 4)
        });

        isOpen = true;
    }

    public void closeEye() {
        eyeGroup.getChildren().clear();
        eyeGroup.getChildren().add(DrawHelper.drawCircle(x, y, r));

        isOpen = false;
    }

    @Override
    public void update() {
        if (isOpen) {
            closeEye();
            return;
        }

        openEye();
    }
}
