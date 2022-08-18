package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.OutSourced;
import kline.qkmii.inventorymgmtsystem.model.Part;

import java.io.IOException;

public class ModifyPartController extends PartsController {
    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws IOException {
        super.sceneManager.returnToMenu(event);
    }

    //FOR PART G:
    //IMPLEMENT COMMAND DESIGN PATTERN TO CALL EACH ACCESSOR METHOD PER PROPERTY/FIELD OF PART
    public void sendPart(Part selectedPart) {
        idTF.setText(String.valueOf(selectedPart.getId()));
        nameTF.setText(selectedPart.getName());
        invTF.setText(String.valueOf(selectedPart.getStock()));
        unitTF.setText(String.valueOf(selectedPart.getPrice()));
        maxPartsTF.setText(String.valueOf(selectedPart.getMax()));
        minPartsTF.setText(String.valueOf(selectedPart.getMin()));

        if(selectedPart instanceof InHouse) {
            sourceTF.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        } else if (selectedPart instanceof OutSourced) {
            sourceTF.setText(String.valueOf(((OutSourced) selectedPart).getCompanyName()));
        }
    }
}
