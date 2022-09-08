package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.util.SceneManager;

import static kline.qkmii.inventorymgmtsystem.InvMgmtSysMain.getPartUID;

public class AddPartController extends PartsController {

  public AddPartController() {
    super();
    formLabelText = "Add Part";
  }

  @FXML
  public void handleSaveBtnEvent(ActionEvent event) {
    //TODO:     - Create dialogue to alert error exception
    //          - Create confirm dialogue
    try {
      fetchSelectedSrc();
      parseEditableTFInputs();
      validateInputs(); //throws if any field is incorrect

      partID = getPartUID();
      Inventory.addPart(createPart());
      System.out.println(selectedSrc.getText() + " part was created.");
      SceneManager.returnToMenu(event);
    } catch (Exception e) {
      System.out.println(e.getMessage() + ": Part not added, bad input.");
    }
  }
}
