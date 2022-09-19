package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.PartFactory;

public class AddPartController extends PartsController {

  public AddPartController() {
    super();
    partFactory = new PartFactory();
    formLabelText = "Add Part";
  }

  @FXML
  public void handleSaveBtnEvent(ActionEvent event) {
    try {
      validateInputs();
      Inventory.addPart(createPart());
      System.out.println(selectedSrc.getText() + " part was created.");
      SceneManager.returnToMenu(event);
    } catch (Exception e) {
      System.out.println(e.getMessage() + ": Part not added.");
    }
  }
}
