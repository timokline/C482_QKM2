package kline.qkmii.inventorymgmtsystem.util;

import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Top-Level static wrapper class for handling user input
//Chain of Responsibility design pattern.
public final class ErrorHandler {
  public static Handler firstHandler = new HandleEmptyTextField();
  public static List<Handler> numberHandlers = new LinkedList<>(Arrays.asList(firstHandler, new HandleNumberConversion(), new HandleNegativeNumbers()));

  private ErrorHandler() {
  }

  static public boolean processInput(TextFieldContainer textFieldInfo) {
    return processInput(textFieldInfo.input.getText(), textFieldInfo.inputType, textFieldInfo.feedback);
  }

  static public boolean processInput(String input, TextFieldContainer.InputType inputType, Text feedbackMessage) {
    boolean inputHandled = true;

    switch (inputType) {
      case STRING:
        inputHandled = firstHandler.handle(input, inputType, feedbackMessage);
        break;
      case INTEGER:
      case DECIMAL:
        for (var handler : numberHandlers) {
          if (!handler.handle(input, inputType, feedbackMessage)) {
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

  public static boolean validateIntInputs(int stock, int min, int max,
                                          Text invFdbkMsg, Text minFdbkMsg, Text maxFdbkMsg) {
    boolean errorCaught = false;

    //check if min is less than max
    if (max < min) {
      errorCaught = true;
      minFdbkMsg.setText("Number must be less than max value");
      minFdbkMsg.setVisible(true);
    }

    //check if inventory is between min and max
    //assume max and min values are either reversed or not.
    if (stock > Math.max(max, min) ||
        stock < Math.min(max, min)) {
      errorCaught = true;
      invFdbkMsg.setText("Number must be between min and max values");
      invFdbkMsg.setVisible(true);
    }

    if (!errorCaught) {
      invFdbkMsg.setVisible(false);
      minFdbkMsg.setVisible(false);
      maxFdbkMsg.setVisible(false);
    }

    return errorCaught;
  }

  //parses a String to verify if the whole String contains only numeric values
  public static boolean isInteger(String input) {
    if (input == null || input.length() == 0) {

      return false;
    }

    //XXX: Expensive hacky way, implement an iteration through String in the future.
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage() + " :String is not an integer.");

      return false;
    }

    return true;
  }
}
