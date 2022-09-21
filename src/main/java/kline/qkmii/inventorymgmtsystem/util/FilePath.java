/*
 * FNAM: FilePath.java
 * DESC: Utility class for file path constants
 * AUTH: Timothy Albert Kline
 *
 * UPDT: 20 Sept 2022
 * VERS: 1.0
 * COPR: N/A
 */
package kline.qkmii.inventorymgmtsystem.util;

/**
 * A static utility class for FXML file locations.
 * Used in conjunction with <code>SceneManager</code> and other FXMLLoader usages.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see kline.qkmii.inventorymgmtsystem.SceneManager
 * @see kline.qkmii.inventorymgmtsystem.InvMgmtSysMain
 */
public final class FilePath {
  public static final String MAIN_MENU = "view/main-form.fxml";
  public static final String PARTS_FORM_SCENE = "view/part-form.fxml";
  public static final String PRODUCTS_FORM_SCENE = "view/product-form.fxml";

  /**
   * Sole constructor. Cannot be instantiated.
   */
  private FilePath() {
  }
}
