package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.util.FilePath;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainMenuController implements Initializable {

    private SceneManager sceneManager;

    @FXML
    private Button menuExitBtn;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partUnitCol;

    @FXML
    private Button partsAddBtn;

    @FXML
    private Button partsDeleteBtn;

    @FXML
    private Button partsModifyBtn;

    @FXML
    private TextField partsQueryTF;

    @FXML
    private TableView<Part> partsTBLV;

    @FXML
    private Button prodAddBtn;

    @FXML
    private Button prodDeleteBtn;

    @FXML
    private TableColumn<Product, Integer> prodIDCol;

    @FXML
    private TableColumn<Product, Integer> prodInvCol;

    @FXML
    private Button prodModifyBtn;

    @FXML
    private TableColumn<Product, String> prodNameCol;

    @FXML
    private TextField prodQueryTF;

    @FXML
    private TableView<Product> prodTBLV;

    @FXML
    private TableColumn<Product, Double> prodUnitCol;

    @FXML
    void handleExitBtnEvent(ActionEvent ignoredEvent) {
        System.exit(0);
    }

    ///PARTS
    @FXML
    void handlePartsAddBtnEvent(ActionEvent event) throws IOException {
        sceneManager.switchScene(event, FilePath.ADD_PARTS_SCENE);
    }

    @FXML
    void handlePartsModBtnEvent(ActionEvent event) throws IOException {
        var fxmlLoader = sceneManager.loadScene(FilePath.MODIFY_PARTS_SCENE);

        ModifyPartController MPMController = fxmlLoader.getController();
        //TODO: DIALOG FOR WHEN A PART IS NOT SELECTED.
        MPMController.fetchPart(partsTBLV.getSelectionModel().getSelectedItem());

        sceneManager.switchScene(event, fxmlLoader);
    }

    @FXML
    void handlePartsDelBtnEvent(ActionEvent ignoredEvent) {
        //TODO: Remove selected item from TableView
        //      Return dialogue if no highlighted item is focused on.
        //      If selection exists, prompt confirmation dialogue.
        //          - Delete item
        //TODO: Dialog if no item is selected.
        var selectedPart = partsTBLV.getSelectionModel().getSelectedItem();
        if(Inventory.deletePart(selectedPart)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handlePartsQueryInput(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            partsTBLV.setItems(Inventory.lookupPart(partsQueryTF.getText()));
            System.out.println("Product query received");
        }
    }

    ///PRODUCTS
    @FXML
    void handleProdAddBtnEvent(ActionEvent event) throws IOException {
        sceneManager.switchScene(event, FilePath.ADD_PRODUCT_SCENE);
    }

    @FXML
    void handleProdDelBtnEvent(ActionEvent ignoredEvent) {
        //TODO: Remove selected item from TableView
        //      A focused item on the TableView must exist
        //      Return dialogue if no highlighted item is focused on.
        //      If selection exists, prompt confirmation dialogue.
        //          - Delete item
        //TODO: DIALOG if item not selected.
        var selectedProduct = prodTBLV.getSelectionModel().getSelectedItem();
        if(Inventory.deleteProduct(selectedProduct)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleProdModBtnEvent(ActionEvent event) throws IOException {
        //TODO: create State Machine for Add/Modify
        //      - Change Labels & methods
        var fxmlLoader = sceneManager.loadScene(FilePath.MODIFY_PRODUCT_SCENE);

        ModifyProductController MPMController = fxmlLoader.getController();
        //TODO: DIALOG FOR WHEN A PART IS NOT SELECTED.
        MPMController.fetchProduct(prodTBLV.getSelectionModel().getSelectedItem());

        sceneManager.switchScene(event, fxmlLoader);
//        int index = -1;
//
//        for (var currentPart : Inventory.getAllParts()){
//            ++index;
//            if(currentPart.getId() == id) {
//                Inventory.getAllParts().set(index, part);
//                return true;
//            }
//        }
//        return false;
    }

    @FXML
    void handleProdQueryInput(KeyEvent event) {
        //TODO: Verify data type of query (int/String).
        if(event.getCode() == KeyCode.ENTER) {
            prodTBLV.setItems(Inventory.lookupProduct(prodQueryTF.getText()));
            System.out.println("Product query received");
        }
    }

    public boolean searchPart(int id) {
        var foundPart = false;
        for (var part : Inventory.getAllParts()) {
            foundPart = part.getId() == id;
            if(foundPart) {
                break;
            }
        }

        return foundPart;
    }

    public boolean updateProduct(int id, Product product) {
        int index = -1;

        for (var currentProduct : Inventory.getAllProducts()){
            ++index;
            if(currentProduct.getId() == id) {
                Inventory.getAllProducts().set(index, product);
                return true;
            }
        }
        return false;
    }

    public Part selectPart(int id) {
        for(var part : Inventory.getAllParts()){
            if(part.getId() == id) {
                return part;
            }
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sceneManager = new SceneManager() {};
        partsTBLV.setItems(Inventory.getAllParts());
        prodTBLV.setItems(Inventory.getAllProducts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        System.out.println("Main Menu initialized.");
    }
}