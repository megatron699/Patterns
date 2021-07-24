package lab3.lab35.enums;

public enum ENameFigure {
    LEFT_EYE("left_eye"),
    RIGHT_EYE("right_eye"),
    MOUTH("mouth"),
    NOSE("nose"),
    NONE("");

    private String nameFigure;

    ENameFigure(String envUrl) {
        this.nameFigure = envUrl;
    }

    public String getNameFigure() {
        return nameFigure;
    }

    public static ENameFigure fromString(String text) {
        for (ENameFigure nameFigure : ENameFigure.values()) {
            if (nameFigure.nameFigure.equalsIgnoreCase(text)) {
                return nameFigure;
            }
        }

        return null;
    }
}
