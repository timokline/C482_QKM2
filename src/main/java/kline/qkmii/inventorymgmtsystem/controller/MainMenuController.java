package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
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


public class MainMenuController implements Initializable {

  @FXML
  private VBox partsTbl;
  @FXML
  private VBox productTbl;
  @FXML
  private DBTableController<Part> partsTblController;
  @FXML
  private DBTableController<Product> productTblController;

  @FXML
  void handleExitBtnEvent(ActionEvent ignoredEvent) {
    ignoredEvent.consume();
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
    var selectedPart = partsTblController.getDatabase().getSelectionModel().getSelectedItem();

    try {
      ModifyPartController modifyPartController = new ModifyPartController(selectedPart);
      var fxmlLoader = SceneManager.injectController(modifyPartController, FilePath.PARTS_FORM_SCENE);
      SceneManager.switchScene(event, fxmlLoader);
    } catch (NullPointerException e) {
      DialogManager.SelectionError();
    }
  }

  @FXML
  void handlePartsDelBtnEvent(ActionEvent ignoredEvent) {
    var selectedPart = partsTblController.getDatabase().getSelectionModel().getSelectedItem();
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
  @FXML
  void handleProdAddBtnEvent(ActionEvent event) {
    AddProductController addProductController = new AddProductController();
    var fxmlLoader = SceneManager.injectController(addProductController, FilePath.PRODUCTS_FORM_SCENE);
    SceneManager.switchScene(event, fxmlLoader);
  }

  @FXML
  void handleProdModBtnEvent(ActionEvent event) {
    var selectedProduct = productTblController.getDatabase().getSelectionModel().getSelectedItem();

    try {
      ModifyProductController modifyProductController = new ModifyProductController(selectedProduct);
      var fxmlLoader = SceneManager.injectController(modifyProductController, FilePath.PRODUCTS_FORM_SCENE);
      SceneManager.switchScene(event, fxmlLoader);
    } catch (NullPointerException e) {
      DialogManager.SelectionError();
    }
  }

  @FXML
  void handleProdDelBtnEvent(ActionEvent ignoredEvent) {
    var selectedProduct = productTblController.getDatabase().getSelectionModel().getSelectedItem();
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

  @FXML
  public void initialize(URL url, ResourceBundle resourceBundle) {
    partsTblController.initDBTblController("Parts", "Search by Part ID or Name", "Part ID", "Part Name",
        Inventory.getAllParts());
    productTblController.initDBTblController("Products", "Search by Product ID or Name", "Product ID", "Product Name",
        Inventory.getAllProducts());

    System.out.println("Main Menu initialized.");
  }
}