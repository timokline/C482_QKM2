package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.Product;

import java.io.IOException;

public class ModifyProductController extends ProductsController {

    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws IOException {
        super.sceneManager.returnToMenu(event);
    }

    public void fetchProduct(Product selectedProduct) {

    }
}
