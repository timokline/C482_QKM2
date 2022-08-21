package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.OutSourced;
import kline.qkmii.inventorymgmtsystem.model.Part;

import java.io.IOException;

public class ModifyPartController extends PartsController {


    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws Exception {
        //TODO:     - Create dialogue to alert error exception
        //          - Create confirm dialogue

        //Put in try-catch
        int id = Integer.parseInt(idTF.getText());
        String name = nameTF.getText();
        double unit = Double.parseDouble(unitTF.getText());
        int inv = Integer.parseInt(invTF.getText());
        int max = Integer.parseInt(maxPartsTF.getText());
        int min = Integer.parseInt(minPartsTF.getText());

        var selectedSrc = (RadioButton) partSrcTG.getSelectedToggle();
        if(selectedSrc == inSrcRBtn) {
            int machineId = Integer.parseInt(sourceTF.getText());
            Inventory.updatePart(id, new InHouse(id, name, unit, inv, max, min, machineId));
        } else if (selectedSrc == outSrcRBtn) {
            String company = sourceTF.getText();
            Inventory.updatePart(id, new OutSourced(id, name, unit, inv, max, min, company));
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
        System.out.println(selectedSrc.getText()+ " part was modified.");
        super.sceneManager.returnToMenu(event);
    }

    //FOR PART G:
    //IMPLEMENT COMMAND DESIGN PATTERN TO CALL EACH ACCESSOR METHOD PER PROPERTY/FIELD OF PART
    public void fetchPart(Part selectedPart) {
        idTF.setText(String.valueOf(selectedPart.getId()));
        nameTF.setText(selectedPart.getName());
        invTF.setText(String.valueOf(selectedPart.getStock()));
        unitTF.setText(String.valueOf(selectedPart.getPrice()));
        maxPartsTF.setText(String.valueOf(selectedPart.getMax()));
        minPartsTF.setText(String.valueOf(selectedPart.getMin()));

        if(selectedPart instanceof InHouse) {
            sourceTF.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
            partSrcTG.selectToggle(inSrcRBtn);
        } else if (selectedPart instanceof OutSourced) {
            sourceTF.setText(String.valueOf(((OutSourced) selectedPart).getCompanyName()));
            partSrcTG.selectToggle(outSrcRBtn);
        }
    }
}
