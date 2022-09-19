package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.model.ProductBuilder;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController extends ProductsController {

  private int currProductIndex;

  public ModifyProductController(Product selectedProduct) {
    super();
    fetchProductInfo(selectedProduct);
    productBuilder = new ProductBuilder(selectedProduct);
    formLabelText = "Modify Product";
  }

  private void fetchProductInfo(Product selectedProduct) {
    currProductID = selectedProduct.getId();
    currProductName = selectedProduct.getName();
    currProductStock = selectedProduct.getStock();
    currProductPrice = selectedProduct.getPrice();
    currMaxProducts = selectedProduct.getMax();
    currMinProducts = selectedProduct.getMin();
    currProductIndex = Inventory.getAllProducts().indexOf(selectedProduct);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    idTF.setText(String.valueOf(currProductID));
    nameTF.setText(currProductName);
    invTF.setText(String.valueOf(currProductStock));
    priceTF.setText(String.valueOf(currProductPrice));
    maxProductsTF.setText(String.valueOf(currMaxProducts));
    minProductsTF.setText(String.valueOf(currMinProducts));
    super.initialize(url, resourceBundle);
  }

  @FXML
  public void handleSaveBtnEvent(ActionEvent event) {
    try {
      validateInputs();
      Inventory.updateProduct(currProductIndex, createProduct());
      System.out.println("Product was modified.");
      SceneManager.returnToMenu(event);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
