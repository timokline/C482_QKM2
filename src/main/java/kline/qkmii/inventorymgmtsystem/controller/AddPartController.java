package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.InvMgmtSysMain;
import kline.qkmii.inventorymgmtsystem.util.FilePath;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddPartController extends SceneManager implements Initializable {

    Stage stage;
    Parent scene;
    @FXML
    private ToggleGroup PartSrc;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField idTF;

    @FXML
    private RadioButton inSrcRBtn;

    @FXML
    private TextField invTF;

    @FXML
    private TextField maxPartsTF;

    @FXML
    private TextField minPartsTF;

    @FXML
    private TextField nameTF;

    @FXML
    private RadioButton outSrcRBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label sourceLBL;

    @FXML
    private TextField sourceTF;

    @FXML
    private TextField unitTF;

    private ArrayList<TextField> editableTextFields;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editableTextFields = new ArrayList<>(Arrays.asList(nameTF,invTF,unitTF,maxPartsTF,minPartsTF,sourceTF));
    }

    public void handleInSrcBtnEvent(ActionEvent actionEvent) {
        sourceLBL.setText("Machine ID");
        System.out.println("sourceLBL was changed to \"Machine ID\"");
    }

    public void handleOutSrcBtnEvent(ActionEvent actionEvent) {
        sourceLBL.setText("Company Name");
        System.out.println("sourceLBL was changed to \"Company Name\"");
    }

    @FXML
    void handleCancelBtnEvent(ActionEvent event) throws IOException {
        for (var textField : editableTextFields) {
            textField.clear();
        }
        returnToMenu(event);
    }

    @FXML
    void handleSaveBtnEvent(ActionEvent event) throws IOException {
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
        returnToMenu(event);
    }

    void returnToMenu(ActionEvent event) throws IOException {
        super.switchScenes(event, FilePath.MAIN_MENU);
    }
}
