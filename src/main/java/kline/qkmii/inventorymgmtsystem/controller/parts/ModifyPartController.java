package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.OutSourced;
import kline.qkmii.inventorymgmtsystem.model.Part;

public class ModifyPartController extends PartsController {

    int currPartIndex;

    public ModifyPartController() {
        labelText = "Modify Part";
    }

    @FXML
    public void handleSaveBtnEvent(ActionEvent event) throws Exception {
        //TODO:     - Create dialogue to alert error exception
        //          - Create confirm dialogue
        try {
            fetchSelectedSrc();
            parseEditableTFInputs();
            validateInputs();
            partID = Integer.parseInt(idTF.getText());
            Inventory.updatePart(currPartIndex, createPart());
            System.out.println(selectedSrc.getText() + " part was modified.");
            super.sceneManager.returnToMenu(event);
        } catch (Exception e) {
            System.out.println(e.getMessage() + ": Part was not modified");
        }
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
            inSrcRBtn.fireEvent(new ActionEvent());
        } else if (selectedPart instanceof OutSourced) {
            sourceTF.setText(String.valueOf(((OutSourced) selectedPart).getCompanyName()));
            partSrcTG.selectToggle(outSrcRBtn);
            outSrcRBtn.fireEvent(new ActionEvent());
        }
        currPartIndex = Inventory.getAllParts().indexOf(selectedPart);
    }
}
