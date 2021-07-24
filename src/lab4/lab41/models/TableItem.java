package lab4.lab41.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableItem {
    private final StringProperty x = new SimpleStringProperty();
    private final StringProperty y = new SimpleStringProperty();

    public String getX() {
        return x.get();
    }

    public StringProperty xProperty() {
        return x;
    }

    public void setX(String x) {
        this.x.set(x);
    }

    public String getY() {
        return y.get();
    }

    public StringProperty yProperty() {
        return y;
    }

    public void setY(String y) {
        this.y.set(y);
    }

    public Double getDoubleX() {
        return Double.valueOf(this.getX());
    }

    public Double getDoubleY() {
        return Double.valueOf(this.getY());
    }

    public TableItem() {
    }

    public TableItem(String x, String y) {
        this.setX(x);
        this.setY(y);
    }

    public void setNewValue(TableItem objectItem) {
        this.setX(objectItem.getX());
        this.setY(objectItem.getY());
    }
}

