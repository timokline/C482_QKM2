package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import kline.qkmii.inventorymgmtsystem.controller.parts.AddPartController;
import kline.qkmii.inventorymgmtsystem.controller.parts.ModifyPartController;
import kline.qkmii.inventorymgmtsystem.controller.products.ModifyProductController;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.util.FilePath;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

  @FXML
  void handleExitBtnEvent(ActionEvent ignoredEvent) {
    System.exit(0);
  }

  ///PARTS
  @FXML
  void handlePartsAddBtnEvent(ActionEvent event) {
    AddPartController addPartController = new AddPartController();
    var fxmlLoader = SceneManager.injectController(addPartController, FilePath.PARTS_FORM_SCENE);
    SceneManager.switchScene(event, fxmlLoader);
  }

  @FXML
  void handlePartsModBtnEvent(ActionEvent event) {
    ModifyPartController modifyPartController = new ModifyPartController();
    var fxmlLoader = SceneManager.injectController(modifyPartController, FilePath.PARTS_FORM_SCENE);

    try {
      modifyPartController.fetchPart(partsTblController.getDatabase().getSelectionModel().getSelectedItem());
    } catch (NullPointerException e) {
      //TODO: DIALOG FOR WHEN A PART IS NOT SELECTED.
    }

    SceneManager.switchScene(event, fxmlLoader);
  }

  @FXML
  void handlePartsDelBtnEvent(ActionEvent ignoredEvent) {
    //TODO: Remove selected item from TableView
    //      Return dialogue if no highlighted item is focused on.
    //      If selection exists, prompt confirmation dialogue.
    //          - Delete item

    try {
      var selectedPart = partsTblController.getDatabase().getSelectionModel().getSelectedItem();
      if (Inventory.deletePart(selectedPart)) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
      }
    } catch (NullPointerException e) {
      //TODO: Dialog if no item is selected.
    }
  }

  ///PRODUCTS
  @FXML
  void handleProdAddBtnEvent(ActionEvent event) {
    SceneManager.switchScene(event, FilePath.ADD_PRODUCT_SCENE);
  }

  @FXML
  void handleProdDelBtnEvent(ActionEvent ignoredEvent) {
    //TODO: Remove selected item from TableView
    //      A focused item on the TableView must exist
    //      Return dialogue if no highlighted item is focused on.
    //      If selection exists, prompt confirmation dialogue.
    //          - Delete item
    //TODO: DIALOG if item not selected.
    var selectedProduct = productTblController.getDatabase().getSelectionModel().getSelectedItem();
    if (Inventory.deleteProduct(selectedProduct)) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
    }
  }

  @FXML
  void handleProdModBtnEvent(ActionEvent event) throws IOException {
    //TODO: create State Machine for Add/Modify
    //      - Change Labels & methods
    var fxmlLoader = SceneManager.loadScene(FilePath.MODIFY_PRODUCT_SCENE);

    ModifyProductController MPMController = fxmlLoader.getController();
    //TODO: DIALOG FOR WHEN A PART IS NOT SELECTED.
    MPMController.fetchProduct(productTblController.getDatabase().getSelectionModel().getSelectedItem());

    SceneManager.switchScene(event, fxmlLoader);
//        int index = -1;
//
//        for (var currentPart : Inventory.getAllParts()){
//            ++index;
//            if(currentPart.getId() == id) {
//                Inventory.getAllParts().set(index, part);
//                return true;
//            }
//        }
//        return false;
  }

  public boolean searchPart(int id) {
    var foundPart = false;
    for (var part : Inventory.getAllParts()) {
      foundPart = part.getId() == id;
      if (foundPart) {
        break;
      }
    }

    return foundPart;
  }

  public boolean updateProduct(int id, Product product) {
    int index = -1;

    for (var currentProduct : Inventory.getAllProducts()) {
      ++index;
      if (currentProduct.getId() == id) {
        Inventory.getAllProducts().set(index, product);
        return true;
      }
    }
    return false;
  }

  public Part selectPart(int id) {
    for (var part : Inventory.getAllParts()) {
      if (part.getId() == id) {
        return part;
      }
    }
    return null;
  }

  @FXML
  private VBox partsTbl;
  @FXML
  private VBox productTbl;
  @FXML
  private DBTableController<Part> partsTblController;
  @FXML
  private DBTableController<Product> productTblController;

  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    partsTblController.initDBTblController("Parts", "Search by Part ID or Name", "Part ID", "Part Name",
        Inventory.getAllParts());
    productTblController.initDBTblController("Products", "Search by Product ID or Name", "Product ID", "Product Name",
        Inventory.getAllProducts());

    System.out.println("Main Menu initialized.");
  }
}