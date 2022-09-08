package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;

import static kline.qkmii.inventorymgmtsystem.InvMgmtSysMain.getProductUID;

public class AddProductController extends ProductsController {
  @FXML
  public void handleSaveBtnEvent(ActionEvent event) throws IOException {
    productID = getProductUID();
    parseEditableTFInputs();
    Inventory.addProduct(createProduct());
    System.out.println("Product was created.");
    SceneManager.returnToMenu(event);
  }
}
