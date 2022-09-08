package kline.qkmii.inventorymgmtsystem.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.InvMgmtSysMain;

import java.io.IOException;

public final class SceneManager {
  static Stage stage;
  static Parent scene;
  static FXMLLoader loader;

  private SceneManager() {
  }

  public static void switchScene(ActionEvent event, String location) {
    try {
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      loader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
      scene = new Scene(loader.load()).getRoot();
      stage.setScene(scene.getScene());
      stage.show();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public static void switchScene(ActionEvent event, FXMLLoader fxmlLoader) {
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = fxmlLoader.getRoot();
    stage.setScene(new Scene(scene));
    stage.show();
  }

  public static FXMLLoader injectController(Object controller, String location) {
    var fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
    fxmlLoader.setController(controller);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return fxmlLoader;
  }

  public static FXMLLoader loadScene(String location) throws IOException {
    loader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
    loader.load();

    return loader;
  }

  public static void returnToMenu(ActionEvent event) {
    switchScene(event, FilePath.MAIN_MENU);
  }
}
