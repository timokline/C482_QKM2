package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.OutSourced;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

interface IPartsCTRLR {

  void handleSaveBtnEvent(ActionEvent event) throws Exception;

  void handleCancelBtnEvent(ActionEvent event) throws IOException;

  void handleInSrcBtnEvent(ActionEvent actionEvent);

  void handleOutSrcBtnEvent(ActionEvent actionEvent);
}

public abstract class PartsController implements Initializable, IPartsCTRLR {

  final String ILLEGAL_INT_INPUT_MSG = "Input must be numerical";
  final String ILLEGAL_DBL_INPUT_MSG = "Input must be a decimal";
  final String EMPTY_FIELD_MSG = "Required Field";
  final String OUT_OF_BOUNDS_MSG = "Number must be greater than 0";

  @FXML
  protected Label partFormLBL;
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
  private Text nameFdbkMsg;
  @FXML
  protected TextField invTF;
  @FXML
  private Text invFdbkMsg;
  @FXML
  protected TextField unitTF;
  @FXML
  private Text unitFdbkMsg;
  @FXML
  protected TextField maxPartsTF;
  @FXML
  private Text maxPartsFdbkMsg;
  @FXML
  protected TextField minPartsTF;
  @FXML
  protected Text minPartsFdbkMsg;

  @FXML
  private Label sourceLBL;
  @FXML
  protected TextField sourceTF;
  @FXML
  private Text srcFdbkMsg;


  protected ArrayList<TextField> editableTextFields;
  String formLabelText;
  int partID;
  String partNameInput;
  double partPriceInput;
  int partInventoryInput;
  int maxPartsInput;
  int minPartsInput;
  String partCompanyNameInput;
  int partMachineIDInput;
  RadioButton selectedSrc;

  public PartsController() {
    editableTextFields = new ArrayList<>(Arrays.asList(this.nameTF, this.invTF, this.unitTF, this.maxPartsTF, this.minPartsTF, this.sourceTF));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    partFormLBL.setText(formLabelText);
    System.out.println("PartsController abstract class initialized.");
  }

  @FXML
  public void handleInSrcBtnEvent(ActionEvent ignoredEvent) {
    sourceLBL.setText("Machine ID");
  }

  @FXML
  public void handleOutSrcBtnEvent(ActionEvent ignoredEvent) {
    sourceLBL.setText("Company Name");
  }

  @FXML
  public void handleCancelBtnEvent(ActionEvent event) {
    SceneManager.returnToMenu(event);
  }

  protected void fetchSelectedSrc() {
    selectedSrc = (RadioButton) partSrcTG.getSelectedToggle();
  }

  protected void parseEditableTFInputs() throws Exception {
    String nameErrorMsg, unitErrorMsg, invErrorMsg, maxErrorMsg, minErrorMsg, srcErrorMsg;
    nameErrorMsg = unitErrorMsg = invErrorMsg = maxErrorMsg = minErrorMsg = srcErrorMsg = "";
    boolean errorCaught = false;

    try {
      if ((partNameInput = nameTF.getText()).equals("")) {
        errorCaught = true;
        nameErrorMsg = EMPTY_FIELD_MSG;

      }

      var unitInput = unitTF.getText();
      if (unitInput.isEmpty()) {
        errorCaught = true;
        unitErrorMsg = EMPTY_FIELD_MSG;
      } else {
        try {
          partPriceInput = Double.parseDouble(unitInput);
        } catch (NumberFormatException e) {
          errorCaught = true;
          unitErrorMsg = ILLEGAL_DBL_INPUT_MSG;
        }
      }


      var inventoryInput = invTF.getText();
      if (inventoryInput.isEmpty()) {
        errorCaught = true;
        invErrorMsg = EMPTY_FIELD_MSG;
      } else {
        try {
          partInventoryInput = Integer.parseInt(inventoryInput);
        } catch (NumberFormatException e) {
          errorCaught = true;
          invErrorMsg = ILLEGAL_INT_INPUT_MSG;
        }
      }

      var maxParts = maxPartsTF.getText();
      if (maxParts.isEmpty()) {
        errorCaught = true;
        maxErrorMsg = EMPTY_FIELD_MSG;
      } else {
        try {
          maxPartsInput = Integer.parseInt(maxParts);
        } catch (NumberFormatException e) {
          errorCaught = true;
          maxErrorMsg = ILLEGAL_INT_INPUT_MSG;
        }
      }

      var minParts = minPartsTF.getText();
      if (minParts.isEmpty()) {
        errorCaught = true;
        minErrorMsg = EMPTY_FIELD_MSG;
      } else {
        try {
          minPartsInput = Integer.parseInt(minParts);
        } catch (NumberFormatException e) {
          errorCaught = true;
          minErrorMsg = ILLEGAL_INT_INPUT_MSG;
        }
      }

      var partSource = sourceTF.getText();
      if (partSource.isEmpty()) {
        errorCaught = true;
        srcErrorMsg = EMPTY_FIELD_MSG;
      } else {
        if (selectedSrc == inSrcRBtn) {
          try {
            partMachineIDInput = Integer.parseInt(partSource);
          } catch (NumberFormatException e) {
            errorCaught = true;
            srcErrorMsg = ILLEGAL_INT_INPUT_MSG;
          }
        } else if (selectedSrc == outSrcRBtn) {
          partCompanyNameInput = partSource;
        }
      }

      if (errorCaught) {
        throw new Exception();
      }

    } finally {
      nameFdbkMsg.setText(nameErrorMsg);
      unitFdbkMsg.setText(unitErrorMsg);
      invFdbkMsg.setText(invErrorMsg);
      maxPartsFdbkMsg.setText(maxErrorMsg);
      minPartsFdbkMsg.setText(minErrorMsg);
      srcFdbkMsg.setText(srcErrorMsg);
    }
  }

  protected Part createPart() {
    Part newPart = null;
    if (selectedSrc == inSrcRBtn) {
      newPart = new InHouse(partID, partNameInput, partPriceInput, partInventoryInput, minPartsInput, maxPartsInput, partMachineIDInput);
    } else if (selectedSrc == outSrcRBtn) {
      newPart = new OutSourced(partID, partNameInput, partPriceInput, partInventoryInput, minPartsInput, maxPartsInput, partCompanyNameInput);
    }

    return newPart;
  }


  protected void validateInputs() throws Exception {
    String invErrorMsg, maxErrorMsg, minErrorMsg, unitErrorMsg;
    invErrorMsg = maxErrorMsg = minErrorMsg = unitErrorMsg = "";

    try {
      //check if min is less than max
      if (maxPartsInput < minPartsInput) {
        minErrorMsg = "Number must be less than max value";
      }

      //check if inventory is between min and max
      //assume max and min values are either reversed or not.
      if (partInventoryInput > Math.max(maxPartsInput, minPartsInput) ||
          partInventoryInput < Math.min(maxPartsInput, minPartsInput)) {
        invErrorMsg = "Number must be between min and max values";
      }

      //check if numbers are non-negative
      if (maxPartsInput < 0) {
        maxErrorMsg = OUT_OF_BOUNDS_MSG;
      }
      if (minPartsInput < 0) {
        minErrorMsg = OUT_OF_BOUNDS_MSG;
      }
      if (partInventoryInput < 0) {
        invErrorMsg = OUT_OF_BOUNDS_MSG;
      }
      if (partPriceInput < 0.0) {
        unitErrorMsg = OUT_OF_BOUNDS_MSG;
      }

      if (!maxErrorMsg.isEmpty() || !minErrorMsg.isEmpty()
          || !invErrorMsg.isEmpty() || !unitErrorMsg.isEmpty()) {
        throw new Exception();
      }
    } finally {
      invFdbkMsg.setText(invErrorMsg);
      maxPartsFdbkMsg.setText(maxErrorMsg);
      minPartsFdbkMsg.setText(minErrorMsg);
      unitFdbkMsg.setText(unitErrorMsg);
    }

  }
}

