/*
 * FNAM: DBTableController.java
 * DESC: Controller class for "main menu form" tableviews
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 20 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
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

/** Controller class for the MainMenu FXML TableView database table.
 * TODO: DESCRIPTION
 * @author Timothy Albert Kline
 * @version 1.0
 */
public class DBTableController<T> implements Initializable {
  ///FXML FIELDS
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

  ///INSTANCE FIELDS
  private ObservableList<T> databaseEntries = FXCollections.observableArrayList();

  ///CLASS METHODS
  /**
   * Sets information about the table after initialization.
   *
   * @param tableLabel the name of the table
   * @param textFieldPrompt the text prompt in the search bar
   * @param idColText the text for the ID column
   * @param nameColText the text for the Name column
   * @param database the list of items for the <code>TableView</code>
   */
  public void initDBTblController(String tableLabel, String textFieldPrompt, String idColText, String nameColText,
                                  ObservableList<T> database) {
    tableviewLBL.setText(tableLabel);
    queryTF.setPromptText(textFieldPrompt);
    IDCol.setText(idColText);
    NameCol.setText(nameColText);
    databaseEntries = database;
    populateTable();
  }

  /**
   * Initializes the <code>TableColumn</code>s and populates the table.
   *
   * @param url            url
   * @param resourceBundle resource bundle
   */
  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    IDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    UnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    populateTable();
  }

  /**
   * Sets the <code>TableView</code>'s items to the currently stored
   * <code>ObservableList</code>.
   */
  private void populateTable() {
    databaseTblV.setItems(databaseEntries);
  }

  /**
   * Sets the <code>TableView</code>'s items to a given <code>ObservableList</code>.
   * @param database the list of objects to set the TableViews items to.
   */
  private void populateTable(ObservableList<T> database) {
    databaseTblV.setItems(database);
  }

  /**
   * @return the TableView of T objects
   */
  public TableView<T> getTableView() {
    return this.databaseTblV;
  }

  /**
   * @return the user's query
   */
  public String getSearchQuery() {
    return queryTF.getText();
  }

  /**
   * Handles "enter" key press in search bar
   *
   * @param event the keyboard event
   * @see #searchByID(int)
   * @see #searchByName(String)
   * @see ErrorHandler#isInteger(String)
   */
  @FXML
  private void handleQueryInput(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
      var query = getSearchQuery();

      if (ErrorHandler.isInteger(query)) {
        searchByID(Integer.parseInt(query));
      } else {
        searchByName(query);
      }
    }
    event.consume();
  }

  /**
   * Given an integer, searches the current database for a matching ID.
   * Highlights the item in the <code>TableView</code> if found.
   * Displays dialog if no item was found.
   * @param query the ID of an item to search for
   * @see DialogManager
   */
  private void searchByID(int query) {
    FilteredList<T> filteredList = new FilteredList<>(databaseEntries);
    //TODO: Implement a safer solution to generalize any object T
    filteredList.setPredicate(entry -> {
      if (entry instanceof Part) {
        return entry == Inventory.lookupPart(query);
      } else if (entry instanceof Product) {
        return entry == Inventory.lookupProduct(query);
      }
      return false;
    });

    populateTable();
    if (filteredList.isEmpty()) {
      DialogManager.SearchError();
    } else {
      databaseTblV.getSelectionModel().select(filteredList.get(0));
    }
  }

  /**
   * Given a string, searches the current database for a partial or exact matching name.
   * Highlights the item in the <code>TableView</code> if found.
   * Displays dialog if no item was found.
   * @param query the name of an item to search for
   * @see DialogManager
   */
  private void searchByName(String query) {
    FilteredList<T> filteredList = new FilteredList<>(databaseEntries);
    //TODO: Implement a safer solution to generalize any object T
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
