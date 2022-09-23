/*
 * FNAM: FeedbackMessage.java
 * DESC: Utility class for gui and console messages
 * AUTH: Timothy Albert Kline
 * STRT: 15 Sep 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.util;

/**
 * A static utility class of reusable error messages for console and GUI.
 * Used in conjunction with <code>Handler</code> subclasses.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see Handler
 * @see kline.qkmii.inventorymgmtsystem.model.ProductBuilder
 */
public final class FeedbackMessage {
  /**
   * Message for requiring numerical input.
   */
  public static final String ILLEGAL_INT_MSG = "Input must be numerical";
  /**
   * Message for requiring decimal input.
   */
  public static final String ILLEGAL_DBL_MSG = "Input must be a decimal";
  /**
   * Message for requiring positive numerical input.
   */
  public static final String NEGATIVE_NUM_MSG = "Number must be greater than 0";
  /**
   * Message for requiring non-empty input.
   */
  public static final String EMPTY_FIELD_MSG = "Required Field";
  /**
   * Exception message for a null selection in a <code>TableView</code>.
   */
  public static final String NULL_SELECTION = ": No item was selected in tableview.";

  /**
   * Constructor that stores the logs from Builder.
   */
  private FeedbackMessage() {
  }

}
