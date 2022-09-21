/*
 * FNAM: SceneManager.java
 * DESC: Manager class for setting scenes
 * AUTH: Timothy Albert Kline
 * STRT: 12 Aug 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.util.FilePath;

import java.io.IOException;

/**
 * Static class that is responsible for handling scenes changes with FXMLLoader.
 * @author Timothy Albert Kline
 * @version 1.0
 */
public final class SceneManager {
  static Stage stage;
  static Parent scene;
  static FXMLLoader loader;

  /**
   * Default constructor. Cannot be instantiated.
   */
  private SceneManager() {
  }

  /**
   * Given an event and an FXML file's location, attempts to set the scene to the
   * FXML via an <code>FXMLLoader</code>.
   * 
   * @param event the action event from the scene
   * @param location the FXML file to be loaded
   */
  public static void switchScene(ActionEvent event, final String location) {
    try {
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      loader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
      scene = new Scene(loader.load()).getRoot();
      stage.setScene(scene.getScene());
      stage.show();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Given an event and a preloaded FXMLLoader, attempts to set the scene from the
   * <code>FXMLLoader</code>.
   * 
   * @param event the action event from the scene
   * @param fxmlLoader the loaded FXML. load() must be invoked prior
   */
  public static void switchScene(ActionEvent event, final FXMLLoader fxmlLoader) {
    try {
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = fxmlLoader.getRoot();
      stage.setScene(new Scene(scene));
      stage.show();
    } catch (NullPointerException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Given a controller class and an FXML file, sets the controller for the FXML.
   * Attempts to load the file via <code>FXMLLoader</code>.
   *
   * @param controller the controller class to inject into FXML
   * @param location the FXML file's location.
   * @return the FXMLLoader of the FXML with the injected controller
   */
  public static FXMLLoader injectController(final Object controller, final String location) {
    var fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource(location));
    fxmlLoader.setController(controller);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return fxmlLoader;
  }

  /**
   * Helper function to return to the main menu scene.
   *
   * @param event the action event from the current scene.
   * @see #switchScene(ActionEvent, String)
   * @see FilePath
   */
  public static void returnToMenu(ActionEvent event) {
    switchScene(event, FilePath.MAIN_MENU);
  }
}
