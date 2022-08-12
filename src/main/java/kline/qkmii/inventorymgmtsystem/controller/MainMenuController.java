package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import kline.qkmii.inventorymgmtsystem.util.FilePath;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends SceneManager implements Initializable {

    @FXML
    private Button menuExitBtn;

    @FXML
    private TableColumn<?, ?> partIDCol;

    @FXML
    private TableColumn<?, ?> partInvCol;

    @FXML
    private TableColumn<?, ?> partNameCol;

    @FXML
    private TableColumn<?, ?> partUnitCol;

    @FXML
    private Button partsAddBtn;

    @FXML
    private Button partsDeleteBtn;

    @FXML
    private Button partsModifyBtn;

    @FXML
    private TextField partsQueryTF;

    @FXML
    private TableView<?> partsTBLV;

    @FXML
    private Button prodAddBtn;

    @FXML
    private Button prodDeleteBtn;

    @FXML
    private TableColumn<?, ?> prodIDCol;

    @FXML
    private TableColumn<?, ?> prodInvCol;

    @FXML
    private Button prodModifyBtn;

    @FXML
    private TableColumn<?, ?> prodNameCol;

    @FXML
    private TextField prodQueryTF;

    @FXML
    private TableView<?> prodTBLV;

    @FXML
    private TableColumn<?, ?> prodUnitCol;

    @FXML
    void handleExitBtnEvent(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handlePartsAddBtnEvent(ActionEvent event) throws IOException {
        //TODO: create State Machine for Add/Modify
        //      - Change Labels & methods
        switchScenes(event, FilePath.PARTS_SCENE);
    }

    @FXML
    void handlePartsDelBtnEvent(ActionEvent event) {
        //TODO: Remove selected item from TableView
        //      A focused item on the TableView must exist
        //      Return dialogue if no highlighted item is focused on.
        //      If selection exists, prompt confirmation dialogue.
        //          - Delete item
    }

    @FXML
    void handlePartsModBtnEvent(ActionEvent event) throws IOException {
        //TODO: create State Machine for Add/Modify
        //      - Change Labels & methods
        switchScenes(event, FilePath.PARTS_SCENE);
    }

    @FXML
    void handlePartsQueryInput(InputMethodEvent event) {
        //TODO: Partial search results/ exact input item
    }

    @FXML
    void handleProdAddBtnEvent(ActionEvent event) throws IOException {
        //TODO: create State Machine for Add/Modify
        //      - Change Labels & methods
        switchScenes(event, FilePath.PRODUCT_SCENE);
    }

    @FXML
    void handleProdDelBtnEvent(ActionEvent event) {
        //TODO: Remove selected item from TableView
        //      A focused item on the TableView must exist
        //      Return dialogue if no highlighted item is focused on.
        //      If selection exists, prompt confirmation dialogue.
        //          - Delete item
    }

    @FXML
    void handleProdModBtnEvent(ActionEvent event) throws IOException {
        //TODO: create State Machine for Add/Modify
        //      - Change Labels & methods
        switchScenes(event, FilePath.MAIN_MENU);
    }

    @FXML
    void handleProdQueryInput(InputMethodEvent event) {
        //TODO: Partial search results/ exact input item
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main Menu initialized.");
    }

}