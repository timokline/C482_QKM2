/*
 * FNAM: InvMgmtSysMain.java
 * DESC: Main method for application
 * AUTH: Timothy Albert Kline
 * STRT: 11 Aug 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */

package kline.qkmii.inventorymgmtsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kline.qkmii.inventorymgmtsystem.model.*;
import kline.qkmii.inventorymgmtsystem.util.FilePath;

import java.io.IOException;

/**
 * Driver class creates the application for an inventory management system.
 * @author Timothy Albert Kline
 * @version 1.0
 */
public class InvMgmtSysMain extends Application {
  private Stage primaryStage;

  /**
   * This is the main method.
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    PartFactory partFactory = new PartFactory();

    Part myPart1 = partFactory.makePart("handle", 0.80, 50, 1, 500, 1000);
    Part myPart2 = partFactory.makePart("nail", 0.07, 100, 1, 1000, 10);
    Part myPart3 = partFactory.makePart("screw", 0.11, 100, 1, 1000, 11);
    Part theirPart1 = partFactory.makePart("bolt", 0.92, 200, 100, 999, "BigBennyBolts");

    Product myProduct = new ProductBuilder("hammer", 2.34, 10, 1, 100).add(myPart1).build();

    Inventory.addPart(myPart1);
    Inventory.addPart(myPart2);
    Inventory.addPart(myPart3);
    Inventory.addPart(theirPart1);
    Inventory.addProduct(myProduct);

    launch(args);
  }

  /**
   * Initializes and shows main menu scene.
   * @see FilePath
   */
  private void showMainMenu() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(InvMgmtSysMain.class.getResource(FilePath.MAIN_MENU));
      Scene scene = new Scene(fxmlLoader.load());
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Lifecycle: Initialize
   */
  @Override
  public void init() {
    System.out.println("Starting application....");
  }

  /**
   * Lifecycle: Start.
   * Stores the <code>Stage</code> object reference and sets the title of the window.
   * Displays the main menu.
   * @param stage the initial stage when starting the app
   * @see #showMainMenu()
   */
  @Override
  public void start(Stage stage) {
    primaryStage = stage;
    primaryStage.setTitle("Inventory Management System");
    showMainMenu();
  }

  /**
   * Lifecycle: Stop.
   */
  @Override
  public void stop() {
    System.out.println("Stopping application....");
  }
}