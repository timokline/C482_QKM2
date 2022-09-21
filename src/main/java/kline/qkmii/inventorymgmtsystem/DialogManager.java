/*
 * FNAM: DialogManager.java
 * DESC: Manager class for dialog windows
 * AUTH: Timothy Albert Kline
 * STRT: 15 Sep 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem;

import javafx.scene.control.Alert;

/**
 * Static class that is responsible for creating dialog windows for various scenes.
 * @author Timothy Albert Kline
 * @version 1.0
 */
public final class DialogManager {
  private static final String DELETE_PROD_TITLE = "Delete Product";
  private static final String DELETE_PART_TITLE = "Delete Part";
  private static final String REMOVE_PART_TITLE = "Remove Part";

  /**
   * Default Constructor. Cannot be instantiated.
   */
  private DialogManager() {
  }

  ///TABLEVIEW DIALOGS
  /**
   * Creates and displays an error dialog that item was not found.
   */
  public static void displaySearchError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Search Error");
    alert.setContentText("Cannot find item in table.");
    alert.showAndWait();
  }

  /**
   * Creates and displays an error dialog that no selection was made.
   */
  public static void displaySelectionError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Selection Error");
    alert.setContentText("No item was selected in the table.");
    alert.showAndWait();
  }

  ///PRODUCT
  /**
   * Creates and displays an error dialog that a product cannot be deleted.
   */
  public static void displayProductDeletionError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(DELETE_PROD_TITLE);
    alert.setContentText("Cannot delete Product. Contains associated parts.");
    alert.showAndWait();
  }

  /**
   * Creates a confirmation dialog about deleting a product.
   * @return the dialog window
   */
  public static Alert getProductDeletionConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(DELETE_PROD_TITLE);
    alert.setHeaderText("Confirm Deletion");
    alert.setContentText("Are you sure you would like to delete this product?");
    return alert;
  }

  /**
   * Creates and displays an informational dialog that a product was not deleted.
   */
  public static void displayProductDeletionInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(DELETE_PROD_TITLE);
    alert.setHeaderText(null);
    alert.setContentText("Product was not deleted.");
    alert.showAndWait();
  }

  ///PART DIALOGS
  /**
   * Creates and displays an error dialog that a part cannot be deleted.
   */
  public static void displayPartDeletionError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(DELETE_PART_TITLE);
    alert.setHeaderText("Deletion Error");
    alert.setContentText("Part cannot be deleted.");
    alert.showAndWait();
  }

  /**
   * Creates a confirmation dialog about deleting a part.
   * @return the dialog window
   */
  public static Alert getPartDeletionConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(DELETE_PART_TITLE);
    alert.setHeaderText("Confirm Deletion?");
    alert.setContentText("Are you sure you would like to delete this part?");
    return alert;
  }

  /**
   * Creates and displays an informational dialog that a part was not deleted.
   */
  public static void displayPartDeletionInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(DELETE_PART_TITLE);
    alert.setHeaderText(null);
    alert.setContentText("Part was not deleted.");
    alert.showAndWait();
  }

  /**
   * Creates and displays an error dialog that a part cannot be removed.
   */
  public static void displayPartRemovalError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(REMOVE_PART_TITLE);
    alert.setHeaderText("Removal Error");
    alert.setContentText("Part cannot be removed.");
    alert.showAndWait();
  }

  /**
   * Creates a confirmation dialog about removing a part.
   * @return the dialog window
   */
  public static Alert getPartRemovalConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(REMOVE_PART_TITLE);
    alert.setHeaderText("Confirm Removal");
    alert.setContentText("Are you sure you would like to remove this part?");
    return alert;
  }

  /**
   * Creates and displays an informational dialog that a part was not removed.
   */
  public static void displayPartRemovalInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(REMOVE_PART_TITLE);
    alert.setHeaderText(null);
    alert.setContentText("Part was not removed.");
    alert.showAndWait();
  }
}
