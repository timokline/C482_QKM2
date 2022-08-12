package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AddPartController extends PartsController {

    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws IOException {
        //TODO: Create new part from input in text fields;
        //      Check which radio button was selected
        //      Check if all fields are filled
        //          - Create dialogue to alert error exception
        //          - Create confirm dialogue
        //              - Construct Part()
        if(inSrcRBtn.isSelected()) {
            //handle sourceTF input
        } else if (outSrcRBtn.isSelected()) {
            //handle sourceTF input
        } else {
            //Branch should not execute... Error occurred.
        }
        super.sceneManager.returnToMenu(event);
    }
}
