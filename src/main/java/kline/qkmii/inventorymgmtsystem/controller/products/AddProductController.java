/*
 * FNAM: AddProductController.java
 * DESC: Derived controller class of ProductsController; add
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 19 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.ProductBuilder;

/**
 * Controller class to handle adding a new <code>Product</code>.
 * Inherits from the base class <code>ProductsController</code>.
 * Upon a save event, calls a validation method
 * and adds the product to <code>Inventory.allProducts</code>.
 *
 * @author Timothy Albert Kline
 * @version 1.0
 * @see ProductsController
 */
public class AddProductController extends ProductsController {
  /**
   * Controller constructor.
   * Creates a new <code>ProductBuilder</code>.
   * Initializes <code>formLabelTitle</code> to be pre-injected into FXML
   *
   * @see ProductsController#formLabelTitle
   * @see ProductBuilder#ProductBuilder()
   */
  public AddProductController() {
    super();
    productBuilder = new ProductBuilder();
    formLabelTitle = "Add Product";
  }

  /**
   * Handles saving a new product.
   * Calls <code>validateInput()</code> to verify user inputs are correct
   * before adding a new product to <code>Inventory.allProducts</code>.
   * Calls <code>createProduct</code> to create a new product from user input.
   * Returns to menu view if succeeds.
   *
   * @param event save button click. To redirect back to menu.
   * @throws Exception if error logged from <code>validateInputs()</code>.
   *                   Prevents adding product to <code>Inventory.allProducts</code>.
   * @see ProductsController#validateInputs()
   * @see SceneManager#returnToMenu(ActionEvent)
   */
  @FXML
  public void handleSaveBtnEvent(ActionEvent event) throws Exception {
    try {
      validateInputs();
      Inventory.addProduct(createProduct());
      System.out.println("Product was created.");
      SceneManager.returnToMenu(event);
    } catch (Exception e) {
      System.out.println(e.getMessage() + ": Product not added.");
    }
  }
}
