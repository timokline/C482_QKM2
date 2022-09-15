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

  public void fetchProductInfo(Product selectedProduct) {
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
  public void handleSaveBtnEvent(ActionEvent event) throws Exception {
    productID = Integer.parseInt(idTF.getText());
    parseEditableTFInputs();
    Inventory.updateProduct(currProductIndex, createProduct());
    System.out.println("Product was modified.");
    SceneManager.returnToMenu(event);
  }

  public void fetchProduct(Product selectedProduct) {
    idTF.setText(String.valueOf(selectedProduct.getId()));
    nameTF.setText(selectedProduct.getName());
    invTF.setText(String.valueOf(selectedProduct.getStock()));
    priceTF.setText(String.valueOf(selectedProduct.getPrice()));
    maxProductsTF.setText(String.valueOf(selectedProduct.getMax()));
    minProductsTF.setText(String.valueOf(selectedProduct.getMin()));

    currProductIndex = Inventory.getAllProducts().indexOf(selectedProduct);
    currentAssocList.setAll(selectedProduct.getAllAssociatedParts());

    populateAssocPartsTbl();
  }
}
