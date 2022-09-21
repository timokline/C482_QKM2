/*
 * FNAM: ModifyProductController.java
 * DESC: Derived controller class of ProductsController; modify
 * AUTH: Timothy Albert Kline
 * STRT: 11 Aug 2022
 * UPDT: 19 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.model.ProductBuilder;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class to handle modification of an existing <code>Product</code>.
 * Inherits from the base class <code>ProductsController</code>. Given a <code>Product</code> object,
 * copies information about product to be injected into the corresponding FXML fields.
 * Upon a save event, calls a validation method and updates the product in <code>Inventory</code>.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see ProductsController
 */
public class ModifyProductController extends ProductsController {
  /**
   * index in <code>Inventory.allProducts</code> of product being modified.
   */
  private int currProductIndex;

  /**
   * Controller constructor specifying a product to modify.
   * Provided a <code>Product</code> object,
   * calls <code>fetchProductInfo</code> to store information about the product.
   * Creates a new <code>ProductBuilder</code> using the product to be updated.
   * Initializes <code>formLabelTitle</code> to be pre-injected into FXML
   *
   * @param selectedProduct the product to be modified. Cannot be null
   * @see ProductsController#formLabelTitle
   * @see #fetchProductInfo(Product)
   * @see #initialize(URL, ResourceBundle)
   * @see ProductBuilder#ProductBuilder(Product)
   */
  public ModifyProductController(Product selectedProduct) {
    super();
    fetchProductInfo(selectedProduct);
    productBuilder = new ProductBuilder(selectedProduct);
    formLabelTitle = "Modify Product";
  }

  /**
   * Stores information about the product being modified.
   * Provided a <code>Product</code> object,
   * copies product information into appropriate instance variables.
   * Stores the index in <code>Inventory.allProducts</code> of the product.
   *
   * @param selectedProduct the product to be modified. Cannot be null.
   *                        Must exist in <code>Inventory.allProducts</code>.
   * @throws NullPointerException if selected product is null.
   * @see #currProductIndex
   */
  private void fetchProductInfo(Product selectedProduct) {
    currProductID = selectedProduct.getId();
    currProductName = selectedProduct.getName();
    currProductStock = selectedProduct.getStock();
    currProductPrice = selectedProduct.getPrice();
    currMaxProducts = selectedProduct.getMax();
    currMinProducts = selectedProduct.getMin();
    currProductIndex = Inventory.getAllProducts().indexOf(selectedProduct);
  }

  /**
   * Injects <code>Product</code> information into FXML fields.
   * The current <code>Product</code>'s information fetched during the constructor
   * instantiation is injected into the loaded FXML fields.
   * Calls <code>super</code> method to finish the rest of the initialization.
   *
   * @param url            url
   * @param resourceBundle resource bundle
   * @see #ModifyProductController(Product)
   * @see ProductsController
   * @see ProductsController#initialize(URL, ResourceBundle)
   */
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

  /**
   * Handles saving a modified product.
   * Calls <code>validateInput()</code> to verify user inputs are correct
   * before updating product in <code>Inventory.allProducts</code>.
   * Calls <code>createProduct</code> to replace product being modified with its updated data.
   * Returns to menu view if succeeds.
   *
   * @param event save button click. To redirect back to menu.
   * @throws Exception if error flagged from <code>validateInputs()</code>.
   *                   Prevents update of product in <code>Inventory.allProducts</code>
   * @see ProductsController#validateInputs()
   * @see SceneManager#returnToMenu(ActionEvent)
   */
  @FXML
  public void handleSaveBtnEvent(ActionEvent event) throws Exception {
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
