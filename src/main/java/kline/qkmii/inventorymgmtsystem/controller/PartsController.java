package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.OutSourced;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static kline.qkmii.inventorymgmtsystem.InvMgmtSysMain.getPartUID;

//Interface class for handle-event methods
interface IPartsCTRLR {
    void handleSaveBtnEvent(ActionEvent event) throws Exception;

    void handleCancelBtnEvent(ActionEvent event) throws IOException;

    void handleInSrcBtnEvent(ActionEvent actionEvent);

    void handleOutSrcBtnEvent(ActionEvent actionEvent);
}
public abstract class PartsController implements Initializable, IPartsCTRLR {

    protected SceneManager sceneManager;

    @FXML
    protected ToggleGroup partSrcTG;

    @FXML
    protected Label partLBL;

    @FXML
    protected Button cancelBtn;

    @FXML
    protected TextField idTF;

    @FXML
    protected RadioButton inSrcRBtn;

    @FXML
    protected TextField invTF;

    @FXML
    protected TextField maxPartsTF;

    @FXML
    protected TextField minPartsTF;

    @FXML
    protected TextField nameTF;

    @FXML
    protected RadioButton outSrcRBtn;

    @FXML
    protected Button saveBtn;

    @FXML
    protected Label sourceLBL;

    @FXML
    protected TextField sourceTF;

    @FXML
    protected TextField unitTF;

    int partID;
    String partNameInput;
    double partPriceInput;
    int partInventoryInput;
    int maxPartsInput;
    int minPartsInput;
    String partCompanyNameInput;
    String partMachineIDInput;
    RadioButton selectedSrc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneManager = new SceneManager() {/*No overrides. Use super*/
        };
        System.out.println("PartsController abstract class initialized.");
    }

    @FXML
    public void handleInSrcBtnEvent(ActionEvent actionEvent) {
        sourceLBL.setText("Machine ID");
        partCompanyNameInput = sourceTF.getText(); //store outsource value
        sourceTF.setText(partMachineIDInput);      //retrieve in-source value
    }

    @FXML
    public void handleOutSrcBtnEvent(ActionEvent actionEvent) {
        sourceLBL.setText("Company Name");
        partMachineIDInput = sourceTF.getText(); //store in-source value
        sourceTF.setText(partCompanyNameInput); //retrieve outsource value
    }

    @FXML
    public void handleCancelBtnEvent(ActionEvent event) throws IOException {
        sceneManager.returnToMenu(event);
    }

    protected void fetchSelectedSrc() {
        selectedSrc = (RadioButton) partSrcTG.getSelectedToggle();
    }

    protected void parseEditableTFInputs() {
        partNameInput = nameTF.getText();
        partPriceInput = Double.parseDouble(unitTF.getText());
        partInventoryInput = Integer.parseInt(invTF.getText());
        maxPartsInput = Integer.parseInt(maxPartsTF.getText());
        minPartsInput = Integer.parseInt(minPartsTF.getText());
        if(selectedSrc == inSrcRBtn) {
            partMachineIDInput = sourceTF.getText();
        } else if (selectedSrc == outSrcRBtn) {
            partCompanyNameInput = sourceTF.getText();
        }
    }

    protected Part createPart() {
        Part newPart = null;
        if(selectedSrc == inSrcRBtn) {
            newPart = new InHouse(partID, partNameInput, partPriceInput, partInventoryInput, maxPartsInput, minPartsInput, Integer.parseInt(partMachineIDInput));
        } else if (selectedSrc == outSrcRBtn){
            newPart = new OutSourced(partID, partNameInput, partPriceInput, partInventoryInput, maxPartsInput, minPartsInput, partCompanyNameInput);
        }
        return newPart;
    }
}
