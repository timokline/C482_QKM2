package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.OutSourced;
import kline.qkmii.inventorymgmtsystem.model.Inventory;

import java.io.IOException;

import static kline.qkmii.inventorymgmtsystem.InvMgmtSysMain.getPartUID;

public class AddPartController extends PartsController {

    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws IOException {
        //TODO: Create new part from input in text fields;
        //      Check which radio button was selected
        //      Check if all fields are filled
        //          - Create dialogue to alert error exception
        //          - Create confirm dialogue
        //              - Construct Part()
        int id = getPartUID();
        String name = nameTF.getText();
        double unit = Double.parseDouble(unitTF.getText());
        int inv = Integer.parseInt(invTF.getText());
        int max = Integer.parseInt(maxPartsTF.getText());
        int min = Integer.parseInt(minPartsTF.getText());

        if(inSrcRBtn.isSelected()) {
            var machineId = Integer.parseInt(sourceTF.getText());
            Inventory.addPart(new InHouse(id, name, unit, inv, max, min, machineId));
        } else if (outSrcRBtn.isSelected()) {
            var company = sourceTF.getText();
            Inventory.addPart(new OutSourced(id, name, unit, inv, max, min, company));
        } else {
            Alert alert = new Alert(AlertType.ERROR);
        }
        super.sceneManager.returnToMenu(event);
    }
}
