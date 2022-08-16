package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

interface IProdCTRLR {

    void handleAddPartBtnEvent(ActionEvent event);

    void handleCancelBtnEvent(ActionEvent event) throws IOException;

    void handleQueryInput(InputMethodEvent event);

    void handleRemPartBtnEvent(ActionEvent event);

    void handleSaveBtnEvent(ActionEvent event) throws IOException;
}

public abstract class ProductsController implements Initializable, IProdCTRLR {

    protected SceneManager sceneManager;
    @FXML
    private Button addPartBtn;

    @FXML
    private TableColumn<?, ?> assocInvLvlCol;

    @FXML
    private TableColumn<?, ?> assocPartIDCol;

    @FXML
    private TableColumn<?, ?> assocPartNameCol;

    @FXML
    private TableColumn<?, ?> assocPartUnitCol;

    @FXML
    private TableView<?> associatedPartsTBLV;

    @FXML
    private TableColumn<?, ?> availInvLvlCol;

    @FXML
    private TableColumn<?, ?> availPartIdCol;

    @FXML
    private TableColumn<?, ?> availPartNameCol;

    @FXML
    private TableColumn<?, ?> availPartUnitCol;

    @FXML
    private TableView<?> availablePartsTBLV;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField idTF;

    @FXML
    private TextField invTF;

    @FXML
    private TextField maxProductsTF;

    @FXML
    private TextField minProductsTF;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField partQueryTF;

    @FXML
    private TextField priceTF;

    @FXML
    private Label productLBL;

    @FXML
    private Button removePartBtn;

    @FXML
    private Button saveBtn;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        sceneManager = new SceneManager() { };
        System.out.println("ProductsController abstract class initialized.");
    }
    @FXML
    public void handleAddPartBtnEvent(ActionEvent event) {
    }

    @FXML
    public void handleCancelBtnEvent(ActionEvent event) throws IOException {
        sceneManager.returnToMenu(event);
    }

    @FXML
    public void handleQueryInput(InputMethodEvent event) {
    }

    @FXML
    public void handleRemPartBtnEvent(ActionEvent event) {
    }
}
