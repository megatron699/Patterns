package lab3.lab35.models;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab2.lab23.service.DrawUtils;
import lab3.lab35.enums.ENameFigure;

public class SmileScene {
    private final Group defaultGroup;
    private EventManager eventManager = new EventManager();

    public SmileScene(Stage primaryStage) {
        defaultGroup = new Group();
        primaryStage.setScene(new Scene(defaultGroup, 300, 300));

        drawEyesAndFace();
        drawNose();
        drawMonth();

        defaultGroup.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getTarget() instanceof Node) {
                Node circle = (Node) mouseEvent.getTarget();
                try {
                    Parent parent = circle.getParent();
                    if (parent == null) {
                        return;
                    }

                    String id = parent.getId();
                    if (id == null) {
                        return;
                    }

                    eventManager.notify(ENameFigure.fromString(id));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return;
            }

        });

        primaryStage.show();
    }

    private void drawMonth() {
        Mouth mouth = new Mouth(100, 200, 200, ENameFigure.MOUTH.getNameFigure());
        defaultGroup.getChildren().add(mouth.getMouthGroup());

        eventManager.subscribe(ENameFigure.MOUTH, mouth);
    }

    private void drawNose() {
        Nose nose = new Nose(150, 150, 25, ENameFigure.NOSE.getNameFigure());
        defaultGroup.getChildren()
                .add(nose.getNoseGroup());

        eventManager.subscribe(ENameFigure.NOSE, nose);
    }

    private void drawEyesAndFace() {
        defaultGroup.getChildren().add(DrawUtils.drawCircle(150, 150, 145));

        Eye leftEye = new Eye(90, 100, 50, ENameFigure.LEFT_EYE.getNameFigure());
        Eye rightEye = new Eye(210, 100, 50, ENameFigure.RIGHT_EYE.getNameFigure());

        eventManager.subscribe(ENameFigure.LEFT_EYE, leftEye);
        eventManager.subscribe(ENameFigure.RIGHT_EYE, rightEye);

        defaultGroup.getChildren().addAll(new Group[]{
                leftEye.getEyeGroup(),
                rightEye.getEyeGroup()
        });
    }

}
