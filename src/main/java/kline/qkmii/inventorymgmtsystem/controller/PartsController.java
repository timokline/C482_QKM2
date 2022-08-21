package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

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

    protected ArrayList<TextField> editableTextFields;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        editableTextFields = new ArrayList<>(Arrays.asList(nameTF, invTF, unitTF, maxPartsTF, minPartsTF, sourceTF));
        sceneManager = new SceneManager() {/*No overrides. Use super*/
        };
        System.out.println("PartsController abstract class initialized.");
    }

    @FXML
    public void handleInSrcBtnEvent(ActionEvent actionEvent) {
        sourceLBL.setText("Machine ID");
        System.out.println("sourceLBL was changed to \"Machine ID\"");
    }

    @FXML
    public void handleOutSrcBtnEvent(ActionEvent actionEvent) {
        sourceLBL.setText("Company Name");
        System.out.println("sourceLBL was changed to \"Company Name\"");
    }

    @FXML
    public void handleCancelBtnEvent(ActionEvent event) throws IOException {
        sceneManager.returnToMenu(event);
    }
}
