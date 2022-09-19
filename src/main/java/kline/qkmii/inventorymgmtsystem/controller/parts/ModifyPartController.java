/*
 * FNAM: ModifyPartController.java
 * DESC: Derived controller class of PartsController; modify
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 18 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.*;

import java.net.URL;
import java.util.ResourceBundle;

/** Controller class to handle modification of an existing <code>Part</code>.
 * Inherits from the base class <code>PartsController</code>. Given a <code>Part</code> object,
 * copies information about part to be injected into the corresponding FXML fields.
 * Upon a save event, calls a validation method and updates the part in <code>Inventory</code>.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see PartsController
*/
public class ModifyPartController extends PartsController {
  /**
   * index in <code>Inventory</code> of part being modified.
   */
  private int currPartIndex;

  /** Controller constructor specifying a part to modify.
   * Provided a <code>Part</code> object, calls <code>fetchPartInfo</code> to store information about the part.
   * Creates a new <code>PartFactory</code> using the part to be updated.
   * Initializes <code>formLabelText</code> to be pre-injected into FXML
   *
   * @param selectedPart the part to be modified.
   * @see PartsController#formLabelText
   * @see #fetchPartInfo(Part)
   * @see #initialize(URL, ResourceBundle)
   * @see PartFactory#PartFactory(Part)
   */
  public ModifyPartController(Part selectedPart) {
    super();
    fetchPartInfo(selectedPart);
    partFactory = new PartFactory(selectedPart);
    formLabelText = "Modify Part";
  }

  /** Handles saving a modified part.
   * Calls <code>validateInput()</code> to verify user inputs are correct
   * before updating part in <code>Inventory</code>. Returns to menu view if
   * succeeds.
   *
   * @param event save button click. To redirect back to menu.
   * @throws Exception error log from <code>validateInputs()</code>.
   * Prevents update of part in <code>inventory</code>
   * @see PartsController#validateInputs()
   * @see SceneManager#returnToMenu(ActionEvent)
   */
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

  /** Stores information about the part being modified.
   * Provided a <code>Part</code> object, copies part information into appropriate instance variables.
   * Stores the index in <code>Inventory</code> of the part.
   *
   * @param selectedPart the part to be modified.
   * @see #currPartIndex
   * @see PartsController#currPartID
   * @see PartsController#currPartName
   * @see PartsController#currPartStock
   * @see PartsController#currPartPrice
   * @see PartsController#currMinParts
   * @see PartsController#currMaxParts
   * @see PartsController#currPartSrc
   * @see PartsController#currPartType
   */
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

  /** Injects <code>Part</code> information into FXML fields.
   * The current <code>Part</code>'s information fetched during the constructor
   * instantiation is injected into the loaded FXML fields. <code>Radio Button</code>s
   * of view are updated per source of the current part.
   *
   * @param url url
   * @param resourceBundle resource bundle
   * @see #ModifyPartController(Part)
   * @see PartsController
   * @see PartsController#initialize(URL, ResourceBundle)
   */
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
