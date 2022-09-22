/*
 * FNAM: FilePath.java
 * DESC: Utility class for file path constants
 * AUTH: Timothy Albert Kline
 * STRT: 12 Aug 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
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
  /**
   * File path of the main menu form.
   */
  public static final String MAIN_MENU = "view/main-form.fxml";
  /**
   * File path of the add/modify part form.
   */
  public static final String PARTS_FORM_SCENE = "view/part-form.fxml";
  /**
   * File path of the add/modify product form.
   */
  public static final String PRODUCTS_FORM_SCENE = "view/product-form.fxml";

  /**
   * Default constructor. Cannot be instantiated.
   */
  private FilePath() {
  }
}
