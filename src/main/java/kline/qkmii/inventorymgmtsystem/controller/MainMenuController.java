/*
 * FNAM: MainMenuController.java
 * DESC: Controller class for "main menu form" view
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 19 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import kline.qkmii.inventorymgmtsystem.DialogManager;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.controller.parts.AddPartController;
import kline.qkmii.inventorymgmtsystem.controller.parts.ModifyPartController;
import kline.qkmii.inventorymgmtsystem.controller.products.AddProductController;
import kline.qkmii.inventorymgmtsystem.controller.products.ModifyProductController;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.util.FilePath;

import java.net.URL;
import java.util.ResourceBundle;

/** Abstract controller class for the MainMenu FXML view.
 * @author Timothy Albert Kline
 * @version 1.0
 */
public class MainMenuController implements Initializable {
  @FXML
  private VBox partsTbl;
  @FXML
  private VBox productTbl;
  @FXML
  private DBTableController<Part> partsTblController;
  @FXML
  private DBTableController<Product> productTblController;

  /**
   * Initializes <code>TableView</code>s for current inventory of
   * <code>Part</code>s and <code>Product</code>s.
   * @param url            url
   * @param resourceBundle resource bundle
   * @see DBTableController#initDBTblController(String, String, String, String, ObservableList)
   */
  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    partsTblController.initDBTblController("Parts", "Search by Part ID or Name", "Part ID", "Part Name",
        Inventory.getAllParts());
    productTblController.initDBTblController("Products", "Search by Product ID or Name", "Product ID", "Product Name",
        Inventory.getAllProducts());
  }

  /**
   * Terminates the program.
   * @param ignoredEvent action event
   */
  @FXML
  void handleExitBtnEvent(ActionEvent ignoredEvent) {
    ignoredEvent.consume();
    System.exit(0);
  }

  /**
   * Wrapper function for getting a selected item from a tableview.
   * @param database the tableview
   * @return the selected item
   * @param <T> the object type of the tableview
   */
  private <T> T getSelection(TableView<T> database) {
    return database.getSelectionModel().getSelectedItem();
  }

  ///PARTS
  /**
   * Handles part add button event.
   * Creates a new <code>AddPartController</code> to be injected into 
   * the "parts form" FXML.
   * Calls static helper functions to set the controller and change scenes.
   * @param event action event
   * @see SceneManager#injectController(Object, String)
   * @see SceneManager#switchScene(ActionEvent, FXMLLoader)
   */
  @FXML
  void handlePartsAddBtnEvent(ActionEvent event) {
    AddPartController addPartController = new AddPartController();
    var fxmlLoader = SceneManager.injectController(addPartController, FilePath.PARTS_FORM_SCENE);
    SceneManager.switchScene(event, fxmlLoader);
  }

  /**
   * Handles part modify button event.
   * Grabs the highlighted item from the <code>TableView</code>.
   * Passes the selection into a new <code>ModifyPartController</code>
   * to be injected into the "parts form" FXML.
   * Calls static helper functions to set the controller and change scenes.
   * Displays a dialog window if no item was selected
   * @param event action event
   * @see DialogManager
   * @see SceneManager#injectController(Object, String)
   * @see SceneManager#switchScene(ActionEvent, FXMLLoader)
   */
  @FXML
  void handlePartsModBtnEvent(ActionEvent event) {
    var selectedPart = getSelection(partsTblController.getDatabase());

    try {
      ModifyPartController modifyPartController = new ModifyPartController(selectedPart);
      var fxmlLoader = SceneManager.injectController(modifyPartController, FilePath.PARTS_FORM_SCENE);
      SceneManager.switchScene(event, fxmlLoader);
    } catch (NullPointerException e) {
      e.printStackTrace();
      DialogManager.SelectionError();
    }
  }

  /**
   * Handles part delete button event.
   * Grabs the highlighted item from the <code>TableView</code>.
   * Displays a confirmation window before deleting part.
   * If deletion cancelled, displays dialog that part was not modified.
   * Attempts to remove part.
   * Displays a dialog window if no item was selected.
   * @param ignoredEvent action event
   * @see DialogManager
   */
  @FXML
  void handlePartsDelBtnEvent(ActionEvent ignoredEvent) {
    var selectedPart = getSelection(partsTblController.getDatabase());
    if (selectedPart != null) {
      var result = DialogManager.PartDeletionConfirmation().showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        if (!Inventory.deletePart(selectedPart)) {
          DialogManager.PartDeletionError();
        }
      } else {
        DialogManager.PartDeletionInfo();
      }
    } else {
      System.out.println(new NullPointerException() + "No item was selected in tableview.");
      DialogManager.SelectionError();
    }
    ignoredEvent.consume();
  }

  ///PRODUCTS
  /**
   * Handles product add button event.
   * Creates a new <code>AddProductController</code> to be injected into
   * the "product form" FXML.
   * Calls static helper functions to set the controller and change scenes.
   * @param event action event
   * @see SceneManager#injectController(Object, String)
   * @see SceneManager#switchScene(ActionEvent, FXMLLoader)
   */
  @FXML
  void handleProdAddBtnEvent(ActionEvent event) {
    AddProductController addProductController = new AddProductController();
    var fxmlLoader = SceneManager.injectController(addProductController, FilePath.PRODUCTS_FORM_SCENE);
    SceneManager.switchScene(event, fxmlLoader);
  }

  /**
   * Handles product modify button event.
   * Grabs the highlighted item from the <code>TableView</code>.
   * Passes the selection into a new <code>ModifyProductController</code>
   * to be injected into the "product form" FXML.
   * Calls static helper functions to set the controller and change scenes.
   * Displays a dialog window if no item was selected
   * @param event action event
   * @see DialogManager
   * @see SceneManager#injectController(Object, String)
   * @see SceneManager#switchScene(ActionEvent, FXMLLoader)
   */
  @FXML
  void handleProdModBtnEvent(ActionEvent event) {
    var selectedProduct = getSelection(productTblController.getDatabase());

    try {
      ModifyProductController modifyProductController = new ModifyProductController(selectedProduct);
      var fxmlLoader = SceneManager.injectController(modifyProductController, FilePath.PRODUCTS_FORM_SCENE);
      SceneManager.switchScene(event, fxmlLoader);
    } catch (NullPointerException e) {
      e.printStackTrace();
      DialogManager.SelectionError();
    }
  }

  /**
   * Handles product delete button event.
   * Grabs the highlighted item from the <code>TableView</code>.
   * Displays a confirmation window before deleting product.
   * If deletion cancelled, displays dialog that product was not deleted.
   * Attempts to remove product; displays dialog if failed.
   * Displays a dialog window if no item was selected.
   * @param ignoredEvent action event
   * @see DialogManager
   */
  @FXML
  void handleProdDelBtnEvent(ActionEvent ignoredEvent) {
    var selectedProduct = getSelection(productTblController.getDatabase());
    if (selectedProduct != null) {
      var result = DialogManager.ProductDeletionConfirmation().showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        if (!Inventory.deleteProduct(selectedProduct)) {
          DialogManager.ProductDeletionError();
        }
      } else {
        DialogManager.ProductDeletionInfo();
      }
    } else {
      System.out.println(new NullPointerException() + "No item was selected in tableview.");
      DialogManager.SelectionError();
    }
    ignoredEvent.consume();
  }
}