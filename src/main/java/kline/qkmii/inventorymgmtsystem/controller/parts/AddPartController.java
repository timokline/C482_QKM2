/*
 * FNAM: AddPartController.java
 * DESC: Derived controller class of PartsController; add
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 19 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.controller.parts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import kline.qkmii.inventorymgmtsystem.SceneManager;
import kline.qkmii.inventorymgmtsystem.model.Inventory;
import kline.qkmii.inventorymgmtsystem.model.PartFactory;


/** Controller class to handle adding a new <code>Part</code>.
 * Inherits from the base class <code>PartsController</code>.
 * Upon a save event, calls a validation method and adds the part to <code>Inventory</code>.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see PartsController
 */
public class AddPartController extends PartsController {
  /** Controller constructor.
   * Creates a new <code>PartFactory</code>.
   * Initializes <code>formLabelTitle</code> to be pre-injected into FXML
   * @see PartsController#formLabelTitle
   * @see PartFactory#PartFactory()
   */
  public AddPartController() {
    super();
    partFactory = new PartFactory();
    formLabelTitle = "Add Part";
  }

  /** Handles saving a new part.
   * Calls <code>validateInput()</code> to verify user inputs are correct
   * before adding a new part to <code>Inventory</code>. Returns to menu view if
   * succeeds.
   *
   * @param event save button click. To redirect back to menu.
   * @throws Exception if error logged from <code>validateInputs()</code>.
   *                   Prevents adding part to <code>inventory</code>.
   * @see PartsController#validateInputs()
   * @see SceneManager#returnToMenu(ActionEvent)
   */
  @FXML
  public void handleSaveBtnEvent(ActionEvent event) throws Exception {
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
