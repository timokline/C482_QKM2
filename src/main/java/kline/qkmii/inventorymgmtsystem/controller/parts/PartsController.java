/*
 * FNAM: PartsController.java
 * DESC: Interface and Base controller class for parts form view
 * AUTH: Timothy Albert Kline
 * STRT: 12 Aug 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.model.PartFactory;
import kline.qkmii.inventorymgmtsystem.util.ErrorHandler;
import kline.qkmii.inventorymgmtsystem.util.TextFieldContainer;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

/** Controller interface for handle methods of <code>Part</code> FXML view.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see PartsController
 */
interface IPartsCTRLR {

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
   * In-Source radio button event handler
   * 
   * @param event action event
   */
  void handleInSrcBtnEvent(ActionEvent event);

  /** 
   * Outsourced radio button event handler
   * 
   * @param event action event
   */
  void handleOutSrcBtnEvent(ActionEvent event);
}

/** 
 * Abstract controller class for the <code>Part</code>s FXML view.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see IPartsCTRLR
 */
public abstract class PartsController implements Initializable, IPartsCTRLR {
  ///FXML FIELDS
  @FXML
  protected RadioButton inSrcRBtn;
  @FXML
  protected RadioButton outSrcRBtn;
  @FXML
  protected ToggleGroup partSrcTG;
  @FXML
  protected TextField idTF;
  @FXML
  protected TextField nameTF;
  @FXML
  protected Text nameFbkMsgTXT;
  @FXML
  protected TextField invTF;
  @FXML
  protected Text invFbkMsgTXT;
  @FXML
  protected TextField priceTF;
  @FXML
  protected Text priceFbkMsgTXT;
  @FXML
  protected TextField maxPartsTF;
  @FXML
  protected Text maxPartsFbkMsgTXT;
  @FXML
  protected TextField minPartsTF;
  @FXML
  protected Text minPartsFbkMsgTXT;
  @FXML
  private Label sourceLBL;
  @FXML
  protected TextField sourceTF;
  @FXML
  protected Text srcFbkMsgTXT;
  @FXML
  private Label partFormLBL;

  ///INSTANCE FIELDS
  protected PartFactory partFactory;
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
  protected TextFieldContainer currCompanyName;
  protected TextFieldContainer currMachineID;

  ///INJECTABLE FIELDS
  /**
   * The string for the title of the FXML view
   */
  String formLabelTitle;
  RadioButton selectedSrc;
  //Current Part Info
  int currPartID;
  String currPartName;
  double currPartPrice;
  int currPartStock;
  int currMaxParts;
  int currMinParts;
  PartFactory.PartSrcType currPartType;
  /**
   * Either a <code>String</code> or <code>int</code>.
   * Type must be cast accordingly before creating <code>Part</code>.
   */
  Object currPartSrcVal;


  /** 
   * Default constructor.
   * (To be invoked by derived class constructors)
   */
  protected PartsController() {
  }

  ///CLASS METHODS
  /** 
   * Propagates FXML <code>Text</code> fields into a set for easy iteration.
   */
  private void initFeedbackTextsSet() {
    feedbackMessageTexts = new HashSet<>(Arrays.asList(
        this.nameFbkMsgTXT,
        this.priceFbkMsgTXT,
        this.invFbkMsgTXT,
        this.maxPartsFbkMsgTXT,
        this.minPartsFbkMsgTXT,
        this.srcFbkMsgTXT
    ));
  }

  /** 
   * Propagates <code>TextFieldContainer</code>s into a set for easy iteration.
   * 
   * @see TextFieldContainer
   */
  private void initTFContainerSet() {
    editableTextFields = new HashSet<>(Arrays.asList(
        new TextFieldContainer(this.nameTF, TextFieldContainer.InputType.STRING, this.nameFbkMsgTXT),
        new TextFieldContainer(this.priceTF, TextFieldContainer.InputType.DECIMAL, this.priceFbkMsgTXT),
        new TextFieldContainer(this.invTF, TextFieldContainer.InputType.INTEGER, this.invFbkMsgTXT),
        new TextFieldContainer(this.maxPartsTF, TextFieldContainer.InputType.INTEGER, this.maxPartsFbkMsgTXT),
        new TextFieldContainer(this.minPartsTF, TextFieldContainer.InputType.INTEGER, this.minPartsFbkMsgTXT))
    );
  }

  /** 
   * Resets <code>Text</code> fields that display input feedback.
   */
  protected void resetFeedbackTexts() {
    for (var text : feedbackMessageTexts) {
      text.setText("");
      text.setVisible(false);
    }
  }

  /** 
   * Initializes select member variables and modifies form "title" label.
   *  Calls helper functions to initialize containers for various FXML fields.
   *  Injects <code>formLabelTitle</code> into <code>partFormLBL</code>.
   * 
   * @param url url
   * @param resourceBundle resource bundle
   * @see #initTFContainerSet()
   * @see #initFeedbackTextsSet()
   * @see #resetFeedbackTexts()
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    initFeedbackTextsSet();
    initTFContainerSet();
    resetFeedbackTexts();
    currCompanyName = new TextFieldContainer(sourceTF, TextFieldContainer.InputType.STRING, srcFbkMsgTXT);
    currMachineID = new TextFieldContainer(sourceTF, TextFieldContainer.InputType.INTEGER, srcFbkMsgTXT);

    partFormLBL.setText(String.valueOf(formLabelTitle));
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
   * Handles In-house radio button event.
   * Changes label text of <code>sourceLBL</code>.
   * 
   * @param ignoredEvent action event
   */
  @FXML
  public void handleInSrcBtnEvent(ActionEvent ignoredEvent) {
    sourceLBL.setText("Machine ID");
    ignoredEvent.consume();
  }

  /** 
   * Handles Outsource radio button event.
   * Changes label text of <code>sourceLBL</code>.
   * 
   * @param ignoredEvent action event
   */
  @FXML
  public void handleOutSrcBtnEvent(ActionEvent ignoredEvent) {
    sourceLBL.setText("Company Name");
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
   * Creates a new <code>Part</code> using <code>PartFactory</code>.
   * 
   * @return the new <code>Part</code>.
   * @see PartFactory#makePart(PartFactory.PartSrcType, String, double, int, int, int, Object)
   */
  protected Part createPart() {
    return partFactory.makePart(currPartType, currPartName, currPartPrice, currPartStock, currMinParts, currMaxParts, currPartSrcVal);
  }

  /** 
   * Extracts each editable <code>TextField</code> text and parses data into corresponding instance field.
   * 
   * @throws NumberFormatException if corresponding String is cannot be parsed into numerical data type.
   */
  private void parseUserInputs() {
    currPartName = nameTF.getText();
    currPartStock = Integer.parseInt(invTF.getText());
    currPartPrice = Double.parseDouble(priceTF.getText());
    currMaxParts = Integer.parseInt(maxPartsTF.getText());
    currMinParts = Integer.parseInt(minPartsTF.getText());
    if (selectedSrc == inSrcRBtn) {
      currPartSrcVal = Integer.parseInt(sourceTF.getText());
    } else if (selectedSrc == outSrcRBtn) {
      currPartSrcVal = sourceTF.getText();
    }
  }

  /** 
   * Determines the current <code>Part</code>'s source type by checking which radio button is selected.
   */
  protected void fetchSelectedSrc() {
    selectedSrc = (RadioButton) partSrcTG.getSelectedToggle();
    if (selectedSrc == inSrcRBtn) {
      currPartType = PartFactory.PartSrcType.IN_HOUSE;
    } else if (selectedSrc == outSrcRBtn) {
      currPartType = PartFactory.PartSrcType.OUTSOURCED;
    }
  }

  /** 
   * Passes each input from the user through a series of checks.
   * Calls <code>fetchSelectedSrc</code> to add appropriate source information to
   * the current set of editable text field containers. Iterates through
   * <code>editableTextFields</code> to pass each item into the global validator
   * checker, <code>ErrorHandler</code>. If user input is invalid, a flag is triggered and
   * the checking continues. Afterwards, if any checks are flagged, method throws.
   * Corresponding feedback for each text field is updated by <code>ErrorHandler</code> during the checks.
   *
   * @throws IllegalArgumentException if inputs flagged as invalid
   * @see ErrorHandler
   */
  protected void validateInputs() throws IllegalArgumentException {
    fetchSelectedSrc();
    if (selectedSrc == inSrcRBtn) {
      editableTextFields.add(currMachineID);
    } else if (selectedSrc == outSrcRBtn) {
      editableTextFields.add(currCompanyName);
    }

    try {
      boolean errorCaught = false;

      //I. Check empty fields and conversion
      for (var userInput : editableTextFields) {
        boolean inputHandled = ErrorHandler.processInput(userInput);
        if (!inputHandled) {
          errorCaught = true;
        }
      }

      //II. Check integer business logic
      if (!errorCaught) {
        parseUserInputs();
        errorCaught = ErrorHandler.validateIntInputs(currPartStock, currMinParts, currMaxParts,
            invFbkMsgTXT, minPartsFbkMsgTXT, maxPartsFbkMsgTXT);
      }

      //III. Throw for any tests above if flagged.
      if (errorCaught) {
        throw new RuntimeException(new IllegalArgumentException() + ": Invalid user inputs");
      }

    } finally {
      editableTextFields.remove(currCompanyName);
      editableTextFields.remove(currMachineID);
    }
  }
}

