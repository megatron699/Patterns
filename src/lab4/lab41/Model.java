package lab4.lab41;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import lab4.lab41.models.TableItem;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Model {
    public final ObservableList<TableItem> items = FXCollections.observableArrayList();
    public final ObservableList<XYChart.Series<Double, Double>> lineChartList = FXCollections.observableArrayList();
    public XYChart.Series<Double, Double> series;

    public Model() {
        series = new XYChart.Series<>();
        series.setName("Function: x^2 - 5 * x / 2.5");

        items.setAll(IntStream.rangeClosed(-5, 5)
                .mapToObj(i -> getObjectItem(i))
                .collect(Collectors.toList())
        );

        updateChartValue(series);
    }

    private void updateChartValue(XYChart.Series<Double, Double> series) {
        Collections.sort(items, (el1, el2) -> {
            double sign = el1.getDoubleX() - el2.getDoubleX();
            return sign > 0 ? 1 : -1;
        });
        series.getData().clear();

        lineChartList.clear();
        items.forEach(el -> {
            series.getData().add(new XYChart.Data<Double, Double>(
                            el.getDoubleX(),
                            el.getDoubleY()
                    )
            );
        });
        lineChartList.add(series);
    }

    public TableItem getObjectItem(double x) {
        return new TableItem(
                String.valueOf(x),
                String.valueOf(x * x - 5 * x / 2.5)
        );
    }

    public ObservableList<TableItem> getItems() {
        return items;
    }

    public void setNewModel(double newValue, TableItem rowValue) {
        rowValue.setNewValue(getObjectItem(newValue));

        updateChartValue(series);
    }

    public void setNewValue(double newValue) {
        items.add(getObjectItem(newValue));

        updateChartValue(series);
    }

    public XYChart.Series<Double, Double> getChartData() {
        return lineChartList.get(0);
    }

    public void deleteItem(TableItem selectedItem) {
        items.remove(selectedItem);

        updateChartValue(series);
    }

}
