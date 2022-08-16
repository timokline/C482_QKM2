package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddProductController extends ProductsController {

    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws IOException {
        super.sceneManager.returnToMenu(event);
    }
}
