package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController extends PartsController {

  private int currPartIndex;

  public ModifyPartController(Part selectedPart) {
    super();
    fetchPartInfo(selectedPart);
    partFactory = new PartFactory(selectedPart);
    formLabelText = "Modify Part";
  }

  @FXML
  public void handleSaveBtnEvent(ActionEvent event) throws Exception {
    try {
      validateInputs();
      Inventory.updatePart(currPartIndex, createPart());
      System.out.println(selectedSrc.getText() + " part was modified.");
      SceneManager.returnToMenu(event);
    } catch (Exception e) {
      System.out.println(e.getMessage() + ": Part was not modified");
    }
  }

  //FOR PART G:
  //IMPLEMENT COMMAND DESIGN PATTERN TO CALL EACH ACCESSOR METHOD PER PROPERTY/FIELD OF PART
  private void fetchPartInfo(Part selectedPart) {
    currPartID = selectedPart.getId();
    currPartName = selectedPart.getName();
    currPartStock = selectedPart.getStock();
    currPartPrice = selectedPart.getPrice();
    currMaxParts = selectedPart.getMax();
    currMinParts = selectedPart.getMin();
    if (selectedPart instanceof InHouse) {
      currPartSrc = ((InHouse) selectedPart).getMachineId();
      currPartType = PartFactory.PartSrcType.IN_HOUSE;
    } else if (selectedPart instanceof OutSourced) {
      currPartSrc = ((OutSourced) selectedPart).getCompanyName();
      currPartType = PartFactory.PartSrcType.OUTSOURCED;
    }
    currPartIndex = Inventory.getAllParts().indexOf(selectedPart);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    idTF.setText(String.valueOf(currPartID));
    nameTF.setText(currPartName);
    invTF.setText(String.valueOf(currPartStock));
    unitTF.setText(String.valueOf(currPartPrice));
    maxPartsTF.setText(String.valueOf(currMaxParts));
    minPartsTF.setText(String.valueOf(currMinParts));
    sourceTF.setText(String.valueOf(currPartSrc));
    switch (currPartType) {
      case IN_HOUSE -> {
        partSrcTG.selectToggle(inSrcRBtn);
        inSrcRBtn.fireEvent(new ActionEvent());
      }
      case OUTSOURCED -> {
        partSrcTG.selectToggle(outSrcRBtn);
        outSrcRBtn.fireEvent(new ActionEvent());
      }
    }
    super.initialize(url, resourceBundle);
  }
}
