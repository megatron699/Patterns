package lab4.lab41;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import lab4.lab41.models.TableItem;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.*;


public class MainController implements Initializable {
    private final ObservableList<TableItem> items;
    private final Model model;
    public TableView chartTableView;
    public TableColumn columnX;
    public TextField textField;
    public LineChart chart;
    private ContextMenu contextMenu = new ContextMenu();

    public ObservableList<TableItem> getItems() {
        return items;
    }

    public MainController() {
        model = new Model();
        items = model.getItems();
        initContextMenu();
    }

    private void initContextMenu() {
        MenuItem menuItem = new MenuItem("Delete");
        menuItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                model.deleteItem((TableItem) chartTableView.getSelectionModel().getSelectedItem());
            }
        });
        contextMenu.getItems().add(menuItem);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindValue();
    }

    private void bindValue() {
        chartTableView.setEditable(true);
        columnX.setCellFactory(TextFieldTableCell.forTableColumn());
        columnX.setOnEditCommit(event -> {
            try {
                TableColumn.CellEditEvent<TableItem, String> cellEditEvent = (TableColumn.CellEditEvent<TableItem, String>) event;
                double newValue = Double.parseDouble(cellEditEvent.getNewValue());
                model.setNewModel(newValue, cellEditEvent.getRowValue());
            } catch (Exception ex) {
                showAlert();
            }
            chartTableView.refresh();
        });

        chart.getData().add(model.getChartData());
        chartTableView.getSortOrder().add(columnX);
    }

    public void showAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input error");
        alert.setHeaderText("Incorrect value!");
        alert.showAndWait();
    }

    public void clickOnAdd(ActionEvent actionEvent) {
        try {
            double newValue = Double.valueOf(textField.getText());
            model.setNewValue(newValue);
            textField.setText("");
        } catch (Exception ex) {
            showAlert();
        }

        chartTableView.refresh();
    }

    public void clickOnRow(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            contextMenu.show(chartTableView, mouseEvent.getScreenX(), mouseEvent.getScreenY());
        }
    }
}
