package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Product;

public class ModifyProductController extends ProductsController {

    int currProductIndex;
    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws Exception {
        int id = Integer.parseInt(idTF.getText());
        String name = nameTF.getText();
        double unit = Double.parseDouble(priceTF.getText());
        int inv = Integer.parseInt(invTF.getText());
        int max = Integer.parseInt(maxProductsTF.getText());
        int min = Integer.parseInt(minProductsTF.getText());

        Product newProduct = new Product(id, name, unit, inv, min, max);
        newProduct.getAllAssociatedParts().setAll(currentAssocList);

        Inventory.updateProduct(currProductIndex, newProduct);

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
