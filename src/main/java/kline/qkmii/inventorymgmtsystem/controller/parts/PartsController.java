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

  protected Part createPart() {
    Part newPart = null;
    if (selectedSrc == inSrcRBtn) {
      newPart = new InHouse(currPartID, currPartName, currPartPrice, currPartStock, currMinParts, currMaxParts, (int) currPartSrc);
    } else if (selectedSrc == outSrcRBtn) {
      newPart = new OutSourced(currPartID, currPartName, currPartPrice, currPartStock, currMinParts, currMaxParts, partCompanyName);
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

  protected void validateInputs() throws IllegalArgumentException {
    fetchSelectedSrc();
    if (selectedSrc == inSrcRBtn) {
      editableTextFields.add(currMachineID);
    } else if (selectedSrc == outSrcRBtn) {
      editableTextFields.add(currCompanyName);
    }

    try {
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
        errorCaught = ErrorHandler.validateIntInputs(currPartStock, currMinParts, currMaxParts,
            invFdbkMsg, minPartsFdbkMsg, maxPartsFdbkMsg);
      }

      //Throw for any tests above flagged
      if (errorCaught) {
        throw new RuntimeException(new IllegalArgumentException() + ": Invalid user inputs");
      }

    } finally {
      editableTextFields.remove(currCompanyName);
      editableTextFields.remove(currMachineID);
      System.out.println("Cleanup.");
    }
  }
}

