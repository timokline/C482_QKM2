/*
 * FNAM: ErrorHandler.java
 * DESC: Utility class for validating input and displaying feedback in UI
 * AUTH: Timothy Albert Kline
 * STRT: 15 Sep 2022
 * UPDT: 21 Sep 2022
 * VERS: 1.0
 * COPR: 2022 Timothy Albert Kline <timothyal.kline@gmail.com>
 */
package kline.qkmii.inventorymgmtsystem.util;

import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Static helper class for handling user input. Implements a
 * Chain of Responsibility design pattern to pass input through a series of handlers.
 * Provides other methods to check for business logic.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see Handler
 */
public final class ErrorHandler {
  private static final Handler firstHandler = new HandleEmptyTextField();
  private static final List<Handler> numberHandlers = new LinkedList<>(Arrays.asList(firstHandler, new HandleNumberConversion(), new HandleNegativeNumbers()));

  /**
   * Default constructor. Cannot be instantiated.
   */
  private ErrorHandler() {
  }

  /**
   * Helper function to pass a <code>TextFieldContainer</code> fields to
   * class function <code>processInput()</code>
   *
   * @param textFieldInfo the TextFieldContainer to process
   * @return true, if input was valid; false, otherwise.
   * @see #processInput(String, TextFieldContainer.InputType, Text)
   */
  public static boolean processInput(TextFieldContainer textFieldInfo) {
    return processInput(textFieldInfo.input.getText(), textFieldInfo.inputType, textFieldInfo.feedback);
  }

  /**
   * Passes user's input from a <code>TextField</code> to validate if it is:
   * <ol>
   *   <li>Not empty</li>
   *   <li>The correct data type</li>
   *   <li>A logical value</li>
   * </ol>
   * <code>TextFieldContainer.InputType</code> provides insight for the data type
   * a text field box accepts. Input is handled by the appropriate series of handlers.
   * If input is invalid, is it flagged and the corresponding <code>Text</code> field
   * is displayed in UI.
   * <br><p>
   *   POST-COND: (side effect) Modifies the corresponding <code>Text</code> to display
   *              feedback/a hint about the input processed. Clears and disables visibility
   *              if input is valid.
   * </p>
   *
   * @param userInput the text entered into the TextField
   * @param requiredType the data type accepted by the TextField per business logic
   * @param feedbackMessage the text displayed in UI about the user's input
   * @return true, if input is valid; false, otherwise.
   * @see Handler
   */
  private static boolean processInput(String input, TextFieldContainer.InputType inputType, Text feedbackMessage) {
    boolean inputHandled = true;

    switch (requiredType) {
      case STRING:
        inputHandled = firstHandler.handle(userInput, requiredType, feedbackMessage);
        break;
      case INTEGER:
      case DECIMAL:
        for (var handler : numberHandlers) {
          if (!handler.handle(userInput, requiredType, feedbackMessage)) {
            inputHandled = false;
            break;
          }
        }
        break;
      default:
        break;
    }

    if (!inputHandled) {
      feedbackMessage.setVisible(true);
    } else {
      feedbackMessage.setText("");
      feedbackMessage.setVisible(false);
    }

    return inputHandled;
  }

  /**
   * Checks business logic for the stock level range and its current level; displays errors in UI.
   * The current inventory level of an item must be between its min and max capacity.
   * The range values must be set so that the min is less than the max.
   * <br><p>
   * POST-COND: (side effect) Modifies <code>Text</code> associated with the
   *            user's input to display feedback/help for incorrect logic.
   *            Disables its visibility otherwise.
   * </p>
   *
   * @param stock the stock of the item
   * @param min the minimum stock level
   * @param max the maximum stock level
   * @param stockFeedback the feedback for the stock input
   * @param minFeedback the feedback for the min input
   * @param maxFeedback the feedback for the max input
   * @return true, if inputs were logical. false, otherwise.
   */
  public static boolean validateIntInputs(int stock, int min, int max,
                                          Text stockFeedback, Text minFeedback, Text maxFeedback) {
    boolean errorCaught = false;

    //check if min is less than max
    if (max < min) {
      errorCaught = true;
      minFeedback.setText("Number must be less than max value");
      minFeedback.setVisible(true);
    }

    //check if inventory is between min and max
    //assume max and min values are either reversed or not.
    if (stock > Math.max(max, min) ||
        stock < Math.min(max, min)) {
      errorCaught = true;
      stockFeedback.setText("Number must be between min and max values");
      stockFeedback.setVisible(true);
    }

    if (!errorCaught) {
      stockFeedback.setVisible(false);
      minFeedback.setVisible(false);
      maxFeedback.setVisible(false);
    }

    return errorCaught;
  }

  /**
   * Parses a String to verify if the whole String contains only numeric values
   *
   * @param input the string to check
   * @return true, if the string is an integer; false, otherwise.
   */
  public static boolean isInteger(String input) {
    if (input == null || input.length() == 0) {
      return false;
    }

    //TODO: Implement an iteration through String in the future.
    //XXX: Expensive hacky way
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage() + ", input is not an integer.");

      return false;
    }

    return true;
  }
}
