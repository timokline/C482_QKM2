package kline.qkmii.inventorymgmtsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    void handleExitBtnEvent(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void handlePartsAddBtnEvent(ActionEvent event) throws IOException {
        sceneManager.switchScene(event, FilePath.ADD_PARTS_SCENE);
    }

    @FXML
    void handlePartsModBtnEvent(ActionEvent event) throws IOException {
        var fxmlLoader = sceneManager.loadScene(FilePath.MODIFY_PARTS_SCENE);

        ModifyPartController MPMController = fxmlLoader.getController();
        MPMController.sendPart(partsTBLV.getSelectionModel().getSelectedItem());

        sceneManager.switchScene(event, fxmlLoader);
    }

    @FXML
    void handlePartsDelBtnEvent(ActionEvent event) {
        //TODO: Remove selected item from TableView
        //      Return dialogue if no highlighted item is focused on.
        //      If selection exists, prompt confirmation dialogue.
        //          - Delete item
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
            System.out.println("");
        }
        partsTBLV.setItems(Inventory.lookupPart(partsQueryTF.getText()));
    }

    @FXML
    void handleProdAddBtnEvent(ActionEvent event) throws IOException {
        sceneManager.switchScene(event, FilePath.ADD_PRODUCT_SCENE);
    }

    @FXML
    void handleProdDelBtnEvent(ActionEvent event) {
        //TODO: Remove selected item from TableView
        //      A focused item on the TableView must exist
        //      Return dialogue if no highlighted item is focused on.
        //      If selection exists, prompt confirmation dialogue.
        //          - Delete item
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
        //MPMController.sendProduct();
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
        //TODO: Partial search results/ exact input item
        prodTBLV.setItems(Inventory.lookupProduct(prodQueryTF.getText()));
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

    public boolean searchProduct(int id) {
        var foundProduct = false;
        for (var product : Inventory.getAllProducts()) {
            foundProduct = product.getId() == id;
            if(foundProduct) {
                break;
            }
        }

        return foundProduct;
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
        //partsTBLV.setItems(filterPart("a"));
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        prodTBLV.setItems(Inventory.getAllProducts());
        prodIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        prodNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));

//        if(searchPart(2)){
//            System.out.println("MATCH");
//        }
//
//        if(updatePart(2, Inventory.getAllParts().get(0))) {
//            System.out.println("UPDATED");
//        }
//
//        if(deletePart(1)) {
//            System.out.println("REMOVED");
//        }

        //partsTBLV.getSelectionModel().select(selectPart(0));
        System.out.println("Main Menu initialized.");
    }
}