/*
 * FNAM: MainMenuController.java
 * DESC: Controller class for "main menu form" view
 * AUTH: Timothy Albert Kline
 * STRT: 11 Sep 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.controller;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import kline.qkmii.inventorymgmtsystem.DialogManager;
import kline.qkmii.inventorymgmtsystem.InvMgmtSysMain;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.controller.parts.AddPartController;
import kline.qkmii.inventorymgmtsystem.controller.parts.ModifyPartController;
import kline.qkmii.inventorymgmtsystem.controller.products.AddProductController;
import kline.qkmii.inventorymgmtsystem.controller.products.ModifyProductController;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.util.FeedbackMessage;
import kline.qkmii.inventorymgmtsystem.util.FilePath;

import java.net.URL;
import java.util.ResourceBundle;

/** 
 * Controller class for the MainMenu FXML view.
 * Handles button events for either <code>Parts</code> or <code>Products</code> tables.
 * Table logic is handled by the nested controller <code>DBTableController</code>.
 * Two references are stored to populate each table in the <code>initialize</code> method
 * called by JavaFX. Button events that trigger a scene change are managed by <code>SceneManager</code>.
 * Model objects that need to be passed between scenes are injected into the appropriate controller.
 * Injection of the controllers to the corresponding FXML is managed by <code>SceneManager</code>.
 * Dialog popups for feedback of button events are managed by <code>DialogManager</code>.
 * <p>
 *   <b>
 *     <i>FUTURE ENHANCEMENT</i>: Instead of changing the scene for the current stage (window),
 *                                display a separate stage for each add/modify <code>Part</code>/
 *                                <code>Product</code>. This would allow the user to have a reference
 *                                to the current inventory items (in main menu) while they are creating/
 *                                modifying an item so that the user does not accidentally duplicate
 *                                information. Ideally, implement a proper database system (like with SQL)
 *                                to make the inventory more robust. A login window would provide
 *                                security to the data being accessed.
 *   </b>
 * </p>
 *
 * @author Timothy Albert Kline
 * @version 1.0
 * @see #initialize(URL, ResourceBundle) 
 * @see DBTableController
 * @see SceneManager
 * @see DialogManager
 */
public class MainMenuController implements Initializable {
  ///FXML FIELDS
  @FXML
  private VBox partsTbl;
  @FXML
  private VBox productTbl;
  @FXML
  private DBTableController<Part> partsTblController;
  @FXML
  private DBTableController<Product> productTblController;

  ///CLASS METHODS
  /**
   * Initializes <code>TableView</code>s for current inventory of
   * <code>Part</code>s and <code>Product</code>s.
   * 
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
   * Invokes the main application's lifecycle method <code>stop</code>.
   * 
   * @param ignoredEvent action event
   * @see InvMgmtSysMain#stop()
   */
  @FXML
  void handleExitBtnEvent(ActionEvent ignoredEvent) {
    ignoredEvent.consume();
    Platform.exit();
  }

  /**
   * Wrapper function for getting a selected item from a tableview.
   * 
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
   * 
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
   * Displays a dialog window if no item was selected.
   * 
   * @param event action event
   * @see DialogManager
   * @see SceneManager#injectController(Object, String)
   * @see SceneManager#switchScene(ActionEvent, FXMLLoader)
   */
  @FXML
  void handlePartsModBtnEvent(ActionEvent event) {
    var selectedPart = getSelection(partsTblController.getTableView());

    try {
      ModifyPartController modifyPartController = new ModifyPartController(selectedPart);
      var fxmlLoader = SceneManager.injectController(modifyPartController, FilePath.PARTS_FORM_SCENE);
      SceneManager.switchScene(event, fxmlLoader);
    } catch (NullPointerException e) {
      System.out.println(e.getMessage() + FeedbackMessage.NULL_SELECTION);
      DialogManager.displaySelectionError();
    }
  }

  /**
   * Handles part delete button event.
   * Grabs the highlighted item from the <code>TableView</code>.
   * Displays a confirmation window before deleting part.
   * If deletion cancelled, displays dialog that part was not modified.
   * Attempts to remove part.
   * Displays a dialog window if no item was selected.
   * 
   * @param ignoredEvent action event
   * @see DialogManager
   */
  @FXML
  void handlePartsDelBtnEvent(ActionEvent ignoredEvent) {
    var selectedPart = getSelection(partsTblController.getTableView());
    if (selectedPart != null) {
      var result = DialogManager.getPartDeletionConfirmation().showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        if (!Inventory.deletePart(selectedPart)) {
          DialogManager.displayPartDeletionError();
        }
      } else {
        DialogManager.displayPartDeletionInfo();
      }
    } else {
      System.out.println(new NullPointerException() + FeedbackMessage.NULL_SELECTION);
      DialogManager.displaySelectionError();
    }
    ignoredEvent.consume();
  }

  ///PRODUCTS
  /**
   * Handles product add button event.
   * Creates a new <code>AddProductController</code> to be injected into
   * the "product form" FXML.
   * Calls static helper functions to set the controller and change scenes.
   * 
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
   * 
   * @param event action event
   * @see DialogManager
   * @see SceneManager#injectController(Object, String)
   * @see SceneManager#switchScene(ActionEvent, FXMLLoader)
   */
  @FXML
  void handleProdModBtnEvent(ActionEvent event) {
    var selectedProduct = getSelection(productTblController.getTableView());

    try {
      ModifyProductController modifyProductController = new ModifyProductController(selectedProduct);
      var fxmlLoader = SceneManager.injectController(modifyProductController, FilePath.PRODUCTS_FORM_SCENE);
      SceneManager.switchScene(event, fxmlLoader);
    } catch (NullPointerException e) {
      System.out.println(e.getMessage() + FeedbackMessage.NULL_SELECTION);
      DialogManager.displaySelectionError();
    }
  }

  /**
   * Handles product delete button event.
   * Grabs the highlighted item from the <code>TableView</code>.
   * Displays a confirmation window before deleting product.
   * If deletion cancelled, displays dialog that product was not deleted.
   * Attempts to remove product; displays dialog if failed.
   * Displays a dialog window if no item was selected.
   * 
   * @param ignoredEvent action event
   * @see DialogManager
   */
  @FXML
  void handleProdDelBtnEvent(ActionEvent ignoredEvent) {
    var selectedProduct = getSelection(productTblController.getTableView());
    if (selectedProduct != null) {
      var result = DialogManager.getProductDeletionConfirmation().showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        if (!Inventory.deleteProduct(selectedProduct)) {
          DialogManager.displayProductDeletionError();
        }
      } else {
        DialogManager.displayProductDeletionInfo();
      }
    } else {
      System.out.println(new NullPointerException() + FeedbackMessage.NULL_SELECTION);
      DialogManager.displaySelectionError();
    }
    ignoredEvent.consume();
  }
}