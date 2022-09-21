/*
 * FNAM: ProductsController.java
 * DESC: Interface and Base controller class for products form view
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 19 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.controller.products;

import javafx.collections.ObservableList;
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

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Controller interface for handle methods of <code>Product</code> FXML view.
 *
 * @author Timothy Albert Kline
 * @version 1.0
 * @see ProductsController
 */
interface IProdCTRLR {

  /**
   * Save button event handler
   *
   * @param event action event
   * @throws Exception if failed to save.
   */
  void handleSaveBtnEvent(ActionEvent event) throws Exception;

  /**
   * Cancel button event handler
   *
   * @param event action event.
   */
  void handleCancelBtnEvent(ActionEvent event);

  /**
   * Add part button event handler
   *
   * @param event action event
   */
  void handleAddPartBtnEvent(ActionEvent event);

  /**
   * Remove part button event handler
   *
   * @param event action event
   */
  void handleRmvPartBtnEvent(ActionEvent event);
}

/**
 * Abstract controller class for the <code>Product</code>s FXML view.
 *
 * @author Timothy Albert Kline
 * @version 1.0
 * @see IProdCTRLR
 */
public abstract class ProductsController implements Initializable, IProdCTRLR {
  ///FXML FIELDS
  @FXML
  protected TableView<Part> associatedPartsTBLV;
  @FXML
  protected TableColumn<Part, Integer> assocInvLvlCol;
  @FXML
  protected TableColumn<Part, Integer> assocPartIDCol;
  @FXML
  protected TableColumn<Part, String> assocPartNameCol;
  @FXML
  protected TableColumn<Part, Double> assocPartUnitCol;
  @FXML
  protected TextField idTF;
  @FXML
  protected TextField nameTF;
  @FXML
  protected Text nameFeedbackTXT;
  @FXML
  protected TextField invTF;
  @FXML
  protected Text stockFeedbackTXT;
  @FXML
  protected TextField priceTF;
  @FXML
  protected Text priceFeedbackTXT;
  @FXML
  protected TextField maxProductsTF;
  @FXML
  protected Text maxFeedbackTXT;
  @FXML
  protected TextField minProductsTF;
  @FXML
  protected Text minFeedbackTXT;
  @FXML
  private Label productFormLBL;
  @FXML
  private VBox availPartsTbl;
  @FXML
  private DBTableController<Part> availPartsTblController;

  ///INSTANCE FIELDS
  protected ProductBuilder productBuilder;
  /**
   * Container to easily iterate through all <code>Text</code> fields.
   */
  protected Set<Text> feedbackMessageTexts;
  /**
   * Container to easily iterate through
   * each data structure of
   * a <code>TextField</code> field,
   * a <code>Text</code> field, and
   * its input data type.
   */
  protected Set<TextFieldContainer> editableTextFields;

  ///INJECTABLE FIELDS
  /**
   * The string for the title of the FXML view
   */
  String formLabelTitle;
  //Current Product Info
  int currProductID;
  String currProductName;
  double currProductPrice;
  int currProductStock;
  int currMaxProducts;
  int currMinProducts;

  /**
   * Default constructor.
   * (To be invoked by derived class constructors)
   */
  protected ProductsController() {
  }

  /**
   * Propagates FXML <code>Text</code> fields into a set for easy iteration.
   */
  private void initFeedbackTextsSet() {
    feedbackMessageTexts = new HashSet<>(Arrays.asList(
        this.nameFeedbackTXT,
        this.priceFeedbackTXT,
        this.stockFeedbackTXT,
        this.maxFeedbackTXT,
        this.minFeedbackTXT
    ));
  }

  /**
   * Propagates <code>TextFieldContainer</code>s into a set for easy iteration.
   *
   * @see TextFieldContainer
   */
  private void initTextFieldContainerSet() {
    editableTextFields = new HashSet<>(Arrays.asList(
        new TextFieldContainer(this.nameTF, TextFieldContainer.InputType.STRING, this.nameFeedbackTXT),
        new TextFieldContainer(this.priceTF, TextFieldContainer.InputType.DECIMAL, this.priceFeedbackTXT),
        new TextFieldContainer(this.invTF, TextFieldContainer.InputType.INTEGER, this.stockFeedbackTXT),
        new TextFieldContainer(this.maxProductsTF, TextFieldContainer.InputType.INTEGER, this.maxFeedbackTXT),
        new TextFieldContainer(this.minProductsTF, TextFieldContainer.InputType.INTEGER, this.minFeedbackTXT))
    );
  }

  /**
   * Resets <code>Text</code> fields that display input feedback.
   */
  private void resetFeedbackTexts() {
    for (var text : feedbackMessageTexts) {
      text.setText("");
      text.setVisible(false);
    }
  }

  /**
   * Initializes select member variables and modifies form "title" label.
   * Calls helper functions to initialize containers for various FXML fields.
   * Injects <code>formLabelTitle</code> into <code>productFormLBL</code>.
   * Initialize tables for available parts and the current product's associated parts.
   *
   * @param url            url
   * @param resourceBundle resource bundle
   * @see #initTextFieldContainerSet()
   * @see #initFeedbackTextsSet()
   * @see #resetFeedbackTexts()
   * @see #populateAssocPartsTbl()
   * @see DBTableController#initDBTblController(String, String, String, String, ObservableList)
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initTextFieldContainerSet();
    initFeedbackTextsSet();
    resetFeedbackTexts();
    productFormLBL.setText(formLabelTitle);

    availPartsTblController.initDBTblController("", "Search by Part ID or Name", "Part ID", "Part Name",
                                                Inventory.getAllParts());

    assocPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    assocPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    assocInvLvlCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    assocPartUnitCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    populateAssocPartsTbl();
  }

  /**
   * Handles cancel button event.
   * Calls static helper function <code>returnToMenu</code>.
   *
   * @param event action event.
   * @see SceneManager#returnToMenu(ActionEvent)
   */
  @FXML
  public void handleCancelBtnEvent(ActionEvent event) {
    SceneManager.returnToMenu(event);
  }

  /**
   * Handles Add part button event.
   * Attempts to add an item selected from the available parts table to
   * the current product's list of associated parts.
   * Displays a dialog window if no item was selected.
   * Updates the <code>TableView</code> of the associated parts.
   *
   * @param ignoredEvent action event
   * @see DialogManager#displaySelectionError()
   * @see ProductBuilder#add(Part)
   */
  @FXML
  public void handleAddPartBtnEvent(ActionEvent ignoredEvent) {
    var selectedPart = availPartsTblController.getTableView().getSelectionModel().getSelectedItem();
    if (selectedPart != null) {
      productBuilder.add(selectedPart);
    } else {
      DialogManager.displaySelectionError();
    }
    populateAssocPartsTbl();
    ignoredEvent.consume();
  }

  /**
   * Wrapper function to update the items in the associated parts <code>TableView</code>
   */
  protected void populateAssocPartsTbl() {
    associatedPartsTBLV.setItems(productBuilder.viewAssocParts());
  }

  /**
   * Handles Remove associated part button event.
   * Attempts to remove an item selected from the associated parts table from
   * the current product's list of associated parts.
   * Displays a dialog window to confirm removal.
   * Notifies part was not removed if removal was cancelled.
   * Displays a dialog window if no item was selected.
   * Updates the <code>TableView</code> of the associated parts.
   *
   * @param ignoredEvent action event
   * @see DialogManager
   * @see ProductBuilder#delete(Part)
   */
  @FXML
  public void handleRmvPartBtnEvent(ActionEvent ignoredEvent) {
    var selectedPart = associatedPartsTBLV.getSelectionModel().getSelectedItem();
    if (selectedPart != null) {
      var result = DialogManager.getPartRemovalConfirmation().showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        try {
          productBuilder.delete(selectedPart);
        } catch (NullPointerException e) {
          e.printStackTrace();
          DialogManager.displayPartRemovalError();
        }
      } else {
        DialogManager.displayPartRemovalInfo();
      }
    } else {
      DialogManager.displaySelectionError();
    }
    populateAssocPartsTbl();
    ignoredEvent.consume();
  }

  /**
   * Handles save event.
   * Must call <code>validateInput()</code> to parse and check user inputs from FXML form.
   *
   * @param event button click. Used for redirecting to a new scene after saving.
   * @throws Exception error log.
   * @see #validateInputs()
   */
  @FXML
  public abstract void handleSaveBtnEvent(ActionEvent event) throws Exception;

  /**
   * Creates a new <code>Product</code> using <code>ProductBuilder</code>.
   *
   * @return the new <code>Part</code>.
   * @see ProductBuilder
   */
  protected Product createProduct() {
    return productBuilder
        .name(currProductName)
        .price(currProductPrice)
        .stock(currProductStock)
        .min(currMinProducts)
        .max(currMaxProducts)
        .build();
  }

  /**
   * Extracts each editable <code>TextField</code> text and parses data into corresponding instance field.
   *
   * @throws NumberFormatException if corresponding String is cannot be parsed into numerical data type.
   */
  private void parseUserInputs() {
    currProductName = nameTF.getText();
    currProductPrice = Double.parseDouble(priceTF.getText());
    currProductStock = Integer.parseInt(invTF.getText());
    currMaxProducts = Integer.parseInt(maxProductsTF.getText());
    currMinProducts = Integer.parseInt(minProductsTF.getText());
  }

  /**
   * Passes each input from the user through a series of checks.
   * Iterates through <code>editableTextFields</code> to pass each item into the global validator
   * checker, <code>ErrorHandler</code>. If user input is invalid, a flag is triggered and
   * the checking continues. Afterwards, if any checks are flagged, method throws.
   * Corresponding feedback for each text field is updated by <code>ErrorHandler</code> during the checks.
   *
   * @throws IllegalArgumentException if inputs flagged as invalid
   * @see ErrorHandler#processInput(TextFieldContainer)
   * @see ErrorHandler#validateIntInputs(int, int, int, Text, Text, Text)
   */
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
}
