package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.ProductBuilder;

public class AddProductController extends ProductsController {

  public AddProductController() {
    super();
    productBuilder = new ProductBuilder();
    formLabelText = "Add Product";
  }

  @FXML
  public void handleSaveBtnEvent(ActionEvent event) {
    try {
      validateInputs();
      Inventory.addProduct(createProduct());
      System.out.println("Product was created.");
      SceneManager.returnToMenu(event);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
