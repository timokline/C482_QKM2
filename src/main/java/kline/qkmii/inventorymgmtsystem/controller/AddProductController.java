package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Product;

import java.io.IOException;

import static kline.qkmii.inventorymgmtsystem.InvMgmtSysMain.getProductUID;

public class AddProductController extends ProductsController {
    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws IOException {
        int id = getProductUID();
        String name = nameTF.getText();
        double unit = Double.parseDouble(priceTF.getText());
        int inv = Integer.parseInt(invTF.getText());
        int max = Integer.parseInt(maxProductsTF.getText());
        int min = Integer.parseInt(minProductsTF.getText());
        Product newProduct = new Product(id,name,unit,inv,max,min);
        newProduct.getAllAssociatedParts().setAll(currentAssocList);
        Inventory.addProduct(newProduct);
        super.sceneManager.returnToMenu(event);
    }
}
