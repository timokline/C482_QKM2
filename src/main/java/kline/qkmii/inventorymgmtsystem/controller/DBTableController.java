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
import kline.qkmii.inventorymgmtsystem.DialogManager;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.util.ErrorHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class DBTableController<T> implements Initializable {

  @FXML
  private Label tableviewLBL;
  @FXML
  private TextField queryTF;
  @FXML
  private TableView<T> databaseTblV;
  @FXML
  private TableColumn<Object, Integer> IDCol;
  @FXML
  private TableColumn<Object, Integer> InvCol;
  @FXML
  private TableColumn<Object, String> NameCol;
  @FXML
  private TableColumn<Object, Double> UnitCol;

  private ObservableList<T> databaseEntries = FXCollections.observableArrayList();

  @FXML
  private void handleQueryInput(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
      var query = getSearchQuery();
      FilteredList<T> filteredList = new FilteredList<>(databaseEntries);

      if (ErrorHandler.isInteger(query)) {
        filteredList.setPredicate(entry -> {
          if (entry instanceof Part) {
            return entry == Inventory.lookupPart(Integer.parseInt(query));
          } else if (entry instanceof Product) {
            return entry == Inventory.lookupProduct(Integer.parseInt(query));
          }

          return false;
        });
        if (filteredList.isEmpty()) {
          populateTable();
          DialogManager.SearchError();
        } else {
          databaseTblV.getSelectionModel().select(filteredList.get(0));
        }
      } else {
        filteredList.setPredicate(entry -> {
          if (entry instanceof Part) {
            return ((Part) entry).getName().contains(query);
          } else if (entry instanceof Product) {
            return ((Product) entry).getName().contains(query);
          }

          return false;
        });

        SortedList<T> sortedList = new SortedList<>(filteredList);
        if (sortedList.isEmpty()) {
          populateTable();
          DialogManager.SearchError();
        } else {
          populateTable(sortedList);
        }
      }

    }
    event.consume();
  }

  public void setTableLabel(String text) {
    tableviewLBL.setText(text);
  }

  public void setIDColText(String text) {
    IDCol.setText(text);
  }

  public void setNameColText(String text) {
    NameCol.setText(text);
  }

  public void setQueryTFPromptText(String prompt) {
    queryTF.setPromptText(prompt);
  }

  public String getSearchQuery() {
    return queryTF.getText();
  }

  public TableView<T> getDatabase() {
    return this.databaseTblV;
  }

  public void setDatabase(ObservableList<T> observableList) {
    this.databaseEntries = observableList;
  }

  public void populateTable() {
    databaseTblV.setItems(databaseEntries);
  }

  public void populateTable(ObservableList<T> database) {
    databaseTblV.setItems(database);
  }

  public void initDBTblController(String tableLabel, String textFieldPrompt, String idColText, String nameColText,
                                  ObservableList<T> database) {
    this.setTableLabel(tableLabel);
    this.setQueryTFPromptText(textFieldPrompt);
    this.setIDColText(idColText);
    this.setNameColText(nameColText);
    this.setDatabase(database);
    this.populateTable();
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
