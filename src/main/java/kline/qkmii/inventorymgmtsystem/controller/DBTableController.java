package kline.qkmii.inventorymgmtsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


import java.net.URL;
import java.util.ResourceBundle;

public class DBTableController<T> implements Initializable {

    @FXML private Label tableviewLBL;
    @FXML private TextField queryTF;
    @FXML private TableView<T> databaseTblV;
    @FXML private TableColumn<Object, Integer> IDCol;
    @FXML private TableColumn<Object, Integer> InvCol;
    @FXML private TableColumn<Object, String> NameCol;
    @FXML private TableColumn<Object, Double> UnitCol;

    private ObservableList<T> databaseEntries = FXCollections.observableArrayList();

    @FXML private void handleQueryInput(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            FilteredList<T> filteredList = new FilteredList<>(databaseEntries, predicate -> true);
            var query = getSearchQuery().toLowerCase();
            filteredList.setPredicate(entry -> {
                if (query.isEmpty()) {
                    return true;
                }

                if(entry.getClass().getName().toLowerCase().contains(query)) {
                    return true;
                } else {
                    //TODO: MAKE DIALOG POPUP
                }

                //No Match
                return false;
            });

            SortedList<T> sortedList = new SortedList<>(filteredList);

            this.populateTable(sortedList);
        }
    }

    public String getSearchQuery() {
        return queryTF.getText();
    }

    public void setDatabase(ObservableList<T> observableList) {
        this.databaseEntries = observableList;
    }

    public void setTableLabel(String text) {
        tableviewLBL.setText(text);
    }

    public TableView<T> getDatabase() {
        return this.databaseTblV;
    }

//    public DBTableController() {
//        //this(new MainMenuController(), FXCollections.observableArrayList());
//
//        System.out.println("Table controller created");
//    }
//    public DBTableController(String label, ObservableList<T> database) {
//        this.tableviewLBL = new Label(label);
//        this.databaseEntries = database;
//        System.out.println("Table controller created w/ params");
//    }

    public void populateTable() {
        databaseTblV.setItems(databaseEntries);
    }

    public void populateTable(ObservableList<T> database) {
        databaseTblV.setItems(database);
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        UnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        this.populateTable();
        System.out.println("Table initialized created");
    }
}
