package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kline.qkmii.inventorymgmtsystem.DialogManager;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.controller.DBTableController;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.Product;
import kline.qkmii.inventorymgmtsystem.model.ProductBuilder;
import kline.qkmii.inventorymgmtsystem.util.ErrorHandler;
import kline.qkmii.inventorymgmtsystem.util.TextFieldContainer;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

interface IProdCTRLR {

  void handleAddPartBtnEvent(ActionEvent event);

  void handleCancelBtnEvent(ActionEvent event) throws IOException;

  void handleRmvPartBtnEvent(ActionEvent event);

  void handleSaveBtnEvent(ActionEvent event) throws Exception;
}

public abstract class ProductsController implements Initializable, IProdCTRLR {

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
  protected TextField priceTF;
  @FXML
  protected Text nameFeedbackTXT;
  @FXML
  protected Text stockFeedbackTXT;
  @FXML
  protected Text priceFeedbackTXT;
  @FXML
  protected Text minFeedbackTXT;
  @FXML
  protected Text maxFeedbackTXT;
  protected ProductBuilder productBuilder;
  protected Set<Text> feedbackMessageTexts;
  protected Set<TextFieldContainer> editableTextFields;
  String formLabelText;
  //Current Product information
  int currProductID;
  String currProductName;
  double currProductPrice;
  int currProductStock;
  int currMaxProducts;
  int currMinProducts;
  @FXML
  private Label productFormLBL;
  @FXML
  private VBox availPartsTbl;
  @FXML
  private DBTableController<Part> availPartsTblController;

  public ProductsController() {
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initTextFieldSet();
    initFeedbackTextsSet();
    resetFeedbackTexts();
    productFormLBL.setText(formLabelText);
    availPartsTblController.initDBTblController("", "Search by Part ID or Name", "Part ID", "Part Name", Inventory.getAllParts());

    assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    assocInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    assocPartUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    populateAssocPartsTbl();

    System.out.println("ProductsController abstract class initialized.");
  }

  @FXML
  public void handleAddPartBtnEvent(ActionEvent ignoredEvent) {
    var selectedPart = availPartsTblController.getDatabase().getSelectionModel().getSelectedItem();
    if (selectedPart != null) {
      productBuilder.add(selectedPart);
    } else {
      DialogManager.SelectionError();
    }
    populateAssocPartsTbl();
    ignoredEvent.consume();
  }

  @FXML
  public void handleRmvPartBtnEvent(ActionEvent ignoredEvent) {
    var selectedPart = associatedPartsTBLV.getSelectionModel().getSelectedItem();
    if (selectedPart != null) {
      var result = DialogManager.PartRemovalConfirmation().showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        if (!productBuilder.delete(selectedPart)) {
          DialogManager.PartRemovalError();
        }
      } else {
        DialogManager.PartRemovalInfo();
      }
    } else {
      DialogManager.SelectionError();
    }
    populateAssocPartsTbl();
    ignoredEvent.consume();
  }

  @FXML
  public void handleCancelBtnEvent(ActionEvent event) {
    SceneManager.returnToMenu(event);
  }

  @FXML
  public abstract void handleSaveBtnEvent(ActionEvent event) throws Exception;

  protected void populateAssocPartsTbl() {
    associatedPartsTBLV.setItems(productBuilder.viewAssocParts());
  }

  private void initTextFieldSet() {
    editableTextFields = new HashSet<>(Arrays.asList(
        new TextFieldContainer(this.nameTF, TextFieldContainer.InputType.STRING, this.nameFeedbackTXT),
        new TextFieldContainer(this.priceTF, TextFieldContainer.InputType.DECIMAL, this.priceFeedbackTXT),
        new TextFieldContainer(this.invTF, TextFieldContainer.InputType.INTEGER, this.stockFeedbackTXT),
        new TextFieldContainer(this.maxProductsTF, TextFieldContainer.InputType.INTEGER, this.maxFeedbackTXT),
        new TextFieldContainer(this.minProductsTF, TextFieldContainer.InputType.INTEGER, this.minFeedbackTXT))
    );
  }

  private void initFeedbackTextsSet() {
    feedbackMessageTexts = new HashSet<>(Arrays.asList(
        this.nameFeedbackTXT,
        this.priceFeedbackTXT,
        this.stockFeedbackTXT,
        this.maxFeedbackTXT,
        this.minFeedbackTXT
    ));
  }

  private void resetFeedbackTexts() {
    for (var text : feedbackMessageTexts) {
      text.setText("");
      text.setVisible(false);
    }
  }

  private void parseUserInputs() {
    currProductName = nameTF.getText();
    currProductPrice = Double.parseDouble(priceTF.getText());
    currProductStock = Integer.parseInt(invTF.getText());
    currMaxProducts = Integer.parseInt(maxProductsTF.getText());
    currMinProducts = Integer.parseInt(minProductsTF.getText());
  }

  protected void validateInputs() throws IllegalArgumentException {
    boolean errorCaught = false;

    //Check empty fields and conversion
    for (var userInput : editableTextFields) {
      var inputHandled = ErrorHandler.processInput(userInput);
      if (!inputHandled) {
        errorCaught = true;
      }
    }

    //Check integer business logic
    if (!errorCaught) {
      parseUserInputs();
      errorCaught = ErrorHandler.validateIntInputs(currProductStock, currMinProducts, currMaxProducts,
          stockFeedbackTXT, minFeedbackTXT, maxFeedbackTXT);
    }

    //Throw for any tests above flagged
    if (errorCaught) {
      throw new RuntimeException(new IllegalArgumentException() + ": Invalid user inputs");
    }

  }

  protected Product createProduct() {
    return productBuilder
        .name(currProductName)
        .price(currProductPrice)
        .stock(currProductStock)
        .min(currMinProducts)
        .max(currMaxProducts)
        .build();
  }
}
