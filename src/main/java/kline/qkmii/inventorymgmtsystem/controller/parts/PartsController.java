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
import kline.qkmii.inventorymgmtsystem.model.InHouse;
import kline.qkmii.inventorymgmtsystem.model.OutSourced;
import kline.qkmii.inventorymgmtsystem.model.Part;
import kline.qkmii.inventorymgmtsystem.util.ErrorHandler;
import kline.qkmii.inventorymgmtsystem.util.TextFieldContainer;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

interface IPartsCTRLR {

  void handleSaveBtnEvent(ActionEvent event) throws Exception;

  void handleCancelBtnEvent(ActionEvent event) throws IOException;

  void handleInSrcBtnEvent(ActionEvent actionEvent);

  void handleOutSrcBtnEvent(ActionEvent actionEvent);
}

public abstract class PartsController implements Initializable, IPartsCTRLR {

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
  protected Text nameFdbkMsg;
  @FXML
  protected TextField invTF;
  @FXML
  protected Text invFdbkMsg;
  @FXML
  protected TextField unitTF;
  @FXML
  protected Text unitFdbkMsg;
  @FXML
  protected TextField maxPartsTF;
  @FXML
  protected Text maxPartsFdbkMsg;
  @FXML
  protected TextField minPartsTF;
  @FXML
  protected Text minPartsFdbkMsg;
  @FXML
  protected TextField sourceTF;
  @FXML
  protected Text srcFdbkMsg;
  protected TextFieldContainer currCompanyName;
  protected TextFieldContainer currMachineID;
  protected Set<TextFieldContainer> editableTextFields;
  protected Set<Text> feedbackMessageTexts;
  String formLabelText;
  int currPartID;
  String currPartName;
  double currPartPrice;
  int currPartStock;
  int currMaxParts;
  int currMinParts;
  Set<Object> productInfo;
  String partCompanyName;
  Object currPartSrc;
  RadioButton selectedSrc;
  @FXML
  private Label sourceLBL;

  public PartsController() {
  }

  private void resetFeedbackTexts() {
    for (var text : feedbackMessageTexts) {
      text.setText("");
      text.setVisible(false);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    productInfo = new HashSet<>(Arrays.asList(
        currPartName,
        currPartPrice,
        currPartStock,
        currMaxParts,
        currMinParts
    ));
    feedbackMessageTexts = new HashSet<>(Arrays.asList(
        this.nameFdbkMsg,
        this.unitFdbkMsg,
        this.invFdbkMsg,
        this.maxPartsFdbkMsg,
        this.minPartsFdbkMsg,
        this.srcFdbkMsg
    ));
    editableTextFields = new HashSet<>(Arrays.asList(
        new TextFieldContainer(this.nameTF, TextFieldContainer.InputType.STRING, this.nameFdbkMsg),
        new TextFieldContainer(this.unitTF, TextFieldContainer.InputType.DECIMAL, this.unitFdbkMsg),
        new TextFieldContainer(this.invTF, TextFieldContainer.InputType.INTEGER, this.invFdbkMsg),
        new TextFieldContainer(this.maxPartsTF, TextFieldContainer.InputType.INTEGER, this.maxPartsFdbkMsg),
        new TextFieldContainer(this.minPartsTF, TextFieldContainer.InputType.INTEGER, this.minPartsFdbkMsg))
    );
    currCompanyName = new TextFieldContainer(sourceTF, TextFieldContainer.InputType.STRING, srcFdbkMsg);
    currMachineID = new TextFieldContainer(sourceTF, TextFieldContainer.InputType.INTEGER, srcFdbkMsg);

    partFormLBL.setText(String.valueOf(formLabelText));
    resetFeedbackTexts();
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

  @FXML
  public abstract void handleSaveBtnEvent(ActionEvent event) throws Exception;

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

  private void parseUserInputs() {
    currPartName = nameTF.getText();
    currPartStock = Integer.parseInt(invTF.getText());
    currPartPrice = Double.parseDouble(unitTF.getText());
    currMaxParts = Integer.parseInt(maxPartsTF.getText());
    currMinParts = Integer.parseInt(minPartsTF.getText());
    if (selectedSrc == inSrcRBtn) {
      currPartSrc = Integer.parseInt(sourceTF.getText());
    } else if (selectedSrc == outSrcRBtn) {
      currPartSrc = sourceTF.getText();
    }
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

