package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.Inventory;

import java.io.IOException;

import static kline.qkmii.inventorymgmtsystem.InvMgmtSysMain.getPartUID;

public class AddPartController extends PartsController {

    @FXML
    public void handleSaveBtnEvent(ActionEvent event) {
        //TODO:     - Create dialogue to alert error exception
        //          - Create confirm dialogue
        try {
            fetchSelectedSrc();
            parseEditableTFInputs();
            validateInputs(); //throws if any field is incorrect
            partID = getPartUID();
            Inventory.addPart(createPart());
            System.out.println(selectedSrc.getText() + " part was created.");
            super.sceneManager.returnToMenu(event);
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": Part not added, bad input.");
        }
    }
}
