package kline.qkmii.inventorymgmtsystem;

import javafx.scene.control.Alert;

public final class DialogManager {
  private static DialogManager instance = null;

  private DialogManager() {
  }

  public synchronized static DialogManager getInstance() {
    return (instance == null) ? (instance = new DialogManager()) : instance;
  }

  public static void SearchError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Search Error");
    alert.setContentText("No such item was found in the table.");
    alert.showAndWait();
  }

  public static void SelectionError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Selection Error");
    alert.setContentText("No item was selected in the table.");
    alert.showAndWait();
  }

  public static void ProductDeletionError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Deletion Error");
    alert.setContentText("Product has parts associated with it. Modify item to remove parts.");
    alert.showAndWait();
  }

  public static Alert ProductDeletionConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletion");
    alert.setContentText("Are you sure you would like to delete this product?");
    return alert;
  }

  public static void ProductDeletionInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Deletion Canceled");
    alert.setHeaderText(null);
    alert.setContentText("Product was not deleted.");
    alert.showAndWait();
  }

  public static void PartDeletionError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Deletion Error");
    alert.setContentText("FIXME: This should not be seen");
    alert.showAndWait();
  }

  public static Alert PartDeletionConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Deletion");
    alert.setContentText("Are you sure you would like to delete this part?");
    return alert;
  }

  public static void PartDeletionInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Deletion Canceled");
    alert.setHeaderText(null);
    alert.setContentText("Part was not deleted.");
    alert.showAndWait();
  }

  public static void PartRemovalError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Removal Error");
    alert.setContentText("FIXME: This should not be seen");
    alert.showAndWait();
  }

  public static Alert PartRemovalConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Removal");
    alert.setContentText("Are you sure you would like to remove this part?");
    return alert;
  }

  public static void PartRemovalInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Removal Canceled");
    alert.setHeaderText(null);
    alert.setContentText("Part was not removed.");
    alert.showAndWait();
  }
}
