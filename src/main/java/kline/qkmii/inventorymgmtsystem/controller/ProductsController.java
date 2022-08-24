package kline.qkmii.inventorymgmtsystem.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

interface IProdCTRLR {

    void handleAddPartBtnEvent(ActionEvent event);

    void handleCancelBtnEvent(ActionEvent event) throws IOException;

    void handleQueryInput(InputMethodEvent event);

    void handleRmvPartBtnEvent(ActionEvent event);

    void handleSaveBtnEvent(ActionEvent event) throws Exception;
}

public abstract class ProductsController implements Initializable, IProdCTRLR {

    protected SceneManager sceneManager = new SceneManager() {
    };
    @FXML
    private Button addPartBtn;

    @FXML
    protected TableColumn<Part, Integer> assocInvLvlCol;

    @FXML
    protected TableColumn<Part, Integer> assocPartIDCol;

    @FXML
    protected TableColumn<Part, String> assocPartNameCol;

    @FXML
    protected TableColumn<Part, Double> assocPartUnitCol;

    @FXML
    protected TableView<Part> associatedPartsTBLV;

    @FXML
    private TableColumn<Part, Integer> availInvLvlCol;

    @FXML
    private TableColumn<Part, Integer> availPartIdCol;

    @FXML
    private TableColumn<Part, String> availPartNameCol;

    @FXML
    private TableColumn<Part, Double> availPartUnitCol;

    @FXML
    private TableView<Part> availablePartsTBLV;

    @FXML
    private Button cancelBtn;

    @FXML
    protected TextField idTF;

    @FXML
    protected TextField invTF;

    @FXML
    protected TextField maxProductsTF;

    @FXML
    protected TextField minProductsTF;

    @FXML
    protected TextField nameTF;

    @FXML
    private TextField partQueryTF;

    @FXML
    protected TextField priceTF;

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
    public void handleRmvPartBtnEvent(ActionEvent event) {
        var selectedPart = associatedPartsTBLV.getSelectionModel().getSelectedItem();
        //TODO: Dialog to confirm deletion.
        currentAssocList.remove(selectedPart);
        populateAssocPartsTbl();
    }
}
