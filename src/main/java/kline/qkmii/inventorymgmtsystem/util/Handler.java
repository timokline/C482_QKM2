/*
 * FNAM: Handler.java
 * DESC: Collection of handle methods for validating input
 * AUTH: Timothy Albert Kline
 * STRT: 15 Sep 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.util;

import javafx.scene.text.Text;

/**
 * Template for a Handler class.
 * Defines one method that will be called for validating user input,
 * and setting the corresponding <code>Text</code> with feedback
 * if invalid.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see ErrorHandler
 */
public interface Handler {
  /**
   * Template method for a Handler class
   *
   * @param textFieldInput the user's input
   * @param requiredType the required data type
   * @param feedbackMessage the message to be displayed
   * @return true, if input was handled; false, otherwise
   */
  default boolean handle(final String textFieldInput, final TextFieldContainer.InputType requiredType, final Text feedbackMessage) {
    return true;
  }
}

/**
 * Handler class that checks for null inputs.
 * @author Timothy Albert Kline
 * @version 1.0
 */
class HandleEmptyTextField implements Handler {
  /**
   * Determines if the user did not enter input.
   * <br><p>
   *   POST-COND: <code>feedbackMessage</code> is modified with a message if
   *              input was invalid.
   * </p>
   *
   * @param textFieldInput the user's input
   * @param ignoredRequiredType the required data type (unused)
   * @param feedbackMessage the message to be displayed
   * @return true, if input was handled; false, otherwise
   */
  @Override
  public boolean handle(final String textFieldInput, final TextFieldContainer.InputType ignoredRequiredType, final Text feedbackMessage) {
    if (textFieldInput.isEmpty()) {
      System.out.println("Empty text field:" + new NullPointerException());
      feedbackMessage.setText(FeedbackMessage.EMPTY_FIELD_MSG);

      return false;
    }

    return true;
  }
}

/**
 * Handler class that checks if a string can be converted to a number.
 * @author Timothy Albert Kline
 * @version 1.0
 */
class HandleNumberConversion implements Handler {
  /**
   * Determines if the user did not enter a number for a field requiring an integer or double.
   * Skips a field requiring a <code>String</code>.
   * <br><p>
   *   POST-COND: <code>feedbackMessage</code> is modified with a message if
   *              input was invalid.
   * </p>
   *
   * @param textFieldInput the user's input
   * @param requiredType the required input data type
   * @param feedbackMessage the message to be displayed
   * @return true, if input was handled; false, otherwise
   */
  @Override
  public boolean handle(final String textFieldInput, final TextFieldContainer.InputType requiredType, final Text feedbackMessage) {
    switch (requiredType) {
      case INTEGER:
        try {
          Integer.parseInt(textFieldInput);
        } catch (NumberFormatException e) {
          System.out.println("Conversion error:" + e);
          feedbackMessage.setText(FeedbackMessage.ILLEGAL_INT_MSG);

          return false;
        }
        break;
      case DECIMAL:
        try {
          Double.parseDouble(textFieldInput);
        } catch (NumberFormatException e) {
          System.out.println("Conversion error:" + e);
          feedbackMessage.setText(FeedbackMessage.ILLEGAL_DBL_MSG);

          return false;
        }
        break;
      default:
        break;
    }

    return true;
  }
}

/**
 * Handler class that checks for negative numbers.
 * @author Timothy Albert Kline
 * @version 1.0
 */
class HandleNegativeNumbers implements Handler {
  /**
   * Determines if the user entered a negative number for a field requiring an integer or double.
   * Assumes input was validated by <code>HandleNumberConversion.handle()</code>.
   * Skips a field requiring a <code>String</code>.
   * <br><p>
   *   POST-COND: <code>feedbackMessage</code> is modified with a message if
   *              input was invalid.
   * </p>
   *
   * @param textFieldInput the user's input
   * @param requiredType the required input data type
   * @param feedbackMessage the message to be displayed
   * @return true, if input was handled; false, otherwise
   */
  @Override
  public boolean handle(final String textFieldInput, final TextFieldContainer.InputType requiredType, final Text feedbackMessage) {
    switch (requiredType) {
      case INTEGER:
        if (!(Integer.parseInt(textFieldInput) >= 0)) {
          System.out.println("Negative integer input:" + new ArithmeticException());
          feedbackMessage.setText(FeedbackMessage.NEGATIVE_NUM_MSG);

          return false;
        }
        break;
      case DECIMAL:
        if (!(Double.parseDouble(textFieldInput) >= 0.0)) {
          System.out.println("Negative double input:" + new ArithmeticException());
          feedbackMessage.setText(FeedbackMessage.NEGATIVE_NUM_MSG);

          return false;
        }
        break;
      case STRING:
      default:
        break;
    }

    return true;
  }
}
