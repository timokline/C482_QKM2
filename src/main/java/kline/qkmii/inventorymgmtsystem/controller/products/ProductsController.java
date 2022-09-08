package kline.qkmii.inventorymgmtsystem.controller.products;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import kline.qkmii.inventorymgmtsystem.controller.DBTableController;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class ProductsController implements Initializable, IProdCTRLR {

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

  protected ObservableList<Part> currentAssocList = FXCollections.observableArrayList();

    protected void populateAvailPartsTbl() {
        availablePartsTBLV.setItems(Inventory.getAllParts());
    }

    protected void populateAssocPartsTbl() {
        associatedPartsTBLV.setItems(currentAssocList);
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        availPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        availPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        availInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        availPartUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        populateAvailPartsTbl();
        assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        populateAssocPartsTbl();

        System.out.println("ProductsController abstract class initialized.");
    }
    @FXML
    public void handleAddPartBtnEvent(ActionEvent event) {
        //TODO: Check if a part is selected.
        var selectedPart = availablePartsTBLV.getSelectionModel().getSelectedItem();
        currentAssocList.add(selectedPart);
        populateAssocPartsTbl();
    }

  @FXML
  public void handleCancelBtnEvent(ActionEvent event) throws IOException {
    SceneManager.returnToMenu(event);
  }

  @FXML
  public void handleRmvPartBtnEvent(ActionEvent event) {
    var selectedPart = associatedPartsTBLV.getSelectionModel().getSelectedItem();
    //TODO: Dialog to confirm deletion.
    currentAssocList.remove(selectedPart);
    populateAssocPartsTbl();
  }

  int productID;
  String prodNameInput;
  double prodPriceInput;
  int prodInventoryInput;
  int maxProdInput;
  int minProdInput;

  protected void parseEditableTFInputs() {
    prodNameInput = nameTF.getText();
    prodPriceInput = Double.parseDouble(priceTF.getText());
    prodInventoryInput = Integer.parseInt(invTF.getText());
    maxProdInput = Integer.parseInt(maxProductsTF.getText());
    minProdInput = Integer.parseInt(minProductsTF.getText());
  }

  protected Product createProduct() {
    Product newProduct = new Product(productID, prodNameInput, prodPriceInput, prodInventoryInput, minProdInput, maxProdInput);
    newProduct.getAllAssociatedParts().setAll(currentAssocList);
    return newProduct;
  }
}
