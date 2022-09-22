/*
 * FNAM: TextFieldContainer.java
 * DESC: Utility class for a POJO of a text field form
 * AUTH: Timothy Albert Kline
 * STRT: 15 Sep 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.util;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * A C++-style struct.
 * Holds components of a typical text field form (exempt of the label):
 * <ul>
 *   <li>The text field box</li>
 *   <li>The validation text below the box</li>
 * </ul>
 * The <code>InputType</code> is a property that explicitly declares
 * the type of data the text field form should take. Validations are
 * handled by the static helper class <code>ErrorHandler</code>.
 *
 * @author Timothy Albert Kline
 * @version 1.0
 * @see ErrorHandler
 */
public class TextFieldContainer {
  /**
   * The field for user input.
   */
  public final TextField input;
  /**
   * The required data type for input.
   */
  public final InputType inputType;
  /**
   * The validation/helper message about the input.
   */
  public final Text feedback;

  /**
   * Defines the required data type of text fields.
   */
  public enum InputType {
    /**
     * <code>int</code> type
     */
    INTEGER,
    /**
     * <code>double</code> type
     */
    DECIMAL,
    /**
     * <code>String</code> type
     */
    STRING
  }

  /**
   * Constructor for the data structure.
   * Specifies
   * a JavaFX <code>TextField</code> field,
   * a JavaFX <code>Text</code> field,
   * and the data type of the input for the text field box.
   *
   * @param input the text field box
   * @param inputType the data type it accepts
   * @param feedback the validator/helper message
   */
  public TextFieldContainer(TextField input, InputType inputType, Text feedback) {
    this.input = input;
    this.inputType = inputType;
    this.feedback = feedback;
  }
}
