package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Product;

import java.io.IOException;

public class ModifyProductController extends ProductsController {

    int currProductIndex;
    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws Exception {
        productID = Integer.parseInt(idTF.getText());
        parseEditableTFInputs();
        Inventory.updateProduct(currProductIndex, createProduct());
        System.out.println("Product was modified.");
        super.sceneManager.returnToMenu(event);
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
