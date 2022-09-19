package kline.qkmii.inventorymgmtsystem;

import javafx.scene.control.Alert;

public final class DialogManager {
  private DialogManager() {
  }

  public static void SearchError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Search Error");
    alert.setContentText("Cannot find item in table.");
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
    alert.setTitle("Delete Product");
    alert.setContentText("Cannot delete Product. Contains associated parts.");
    alert.showAndWait();
  }

  public static Alert ProductDeletionConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Product");
    alert.setHeaderText("Confirm Deletion?");
    alert.setContentText("Are you sure you would like to delete this product?");
    return alert;
  }

  public static void ProductDeletionInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Delete Product");
    alert.setHeaderText(null);
    alert.setContentText("Product was not deleted.");
    alert.showAndWait();
  }

  public static void PartDeletionError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Delete Part");
    alert.setHeaderText("Deletion Error");
    alert.setContentText("FIXME: This should not be seen");
    alert.showAndWait();
  }

  public static Alert PartDeletionConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Delete Part");
    alert.setHeaderText("Confirm Deletion?");
    alert.setContentText("Are you sure you would like to delete this part?");
    return alert;
  }

  public static void PartDeletionInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Delete Part");
    alert.setHeaderText(null);
    alert.setContentText("Part was not deleted.");
    alert.showAndWait();
  }

  public static void PartRemovalError() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Remove Part");
    alert.setHeaderText("Removal Error");
    alert.setContentText("FIXME: This should not be seen");
    alert.showAndWait();
  }

  public static Alert PartRemovalConfirmation() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Remove Part");
    alert.setHeaderText("Confirm Removal?");
    alert.setContentText("Are you sure you would like to remove this part?");
    return alert;
  }

  public static void PartRemovalInfo() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Remove Part");
    alert.setHeaderText(null);
    alert.setContentText("Part was not removed.");
    alert.showAndWait();
  }
}
