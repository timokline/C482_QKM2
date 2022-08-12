package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.InvMgmtSysMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    Stage stage;
    Parent scene;

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
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource("view/add-part-form.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400).getRoot();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("YAY!");
        stage.setScene(scene.getScene());
        stage.show();
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
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource("view/modify-part-form.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400).getRoot();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("YAY!");
        stage.setScene(scene.getScene());
        stage.show();
    }

    @FXML
    void handlePartsQueryInput(InputMethodEvent event) {
        //TODO: Partial search results/ exact input item
    }

    @FXML
    void handleProdAddBtnEvent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource("view/add-prod-form.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400).getRoot();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("YAY!");
        stage.setScene(scene.getScene());
        stage.show();
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
        FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource("view/modify-product-form.fxml"));
        scene = new Scene(fxmlLoader.load(), 600, 400).getRoot();
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("YAY!");
        stage.setScene(scene.getScene());
        stage.show();
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