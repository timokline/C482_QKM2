package kline.qkmii.inventorymgmtsystem.util;

import javafx.scene.text.Text;

public interface Handler {
  default boolean handle(String textFieldInput, TextFieldContainer.InputType inputType, Text feedbackMessage) {
    return true;
  }
}

class HandleEmptyTextField implements Handler {
  @Override
  public boolean handle(String textFieldInput, TextFieldContainer.InputType ignoredInputType, Text feedbackMessage) {
    if (textFieldInput.isEmpty()) {
      System.out.println("Empty text field:" + new NullPointerException());
      feedbackMessage.setText(FeedbackMessage.EMPTY_FIELD_MSG);

      return false;
    }

    return true;
  }
}

class HandleNumberConversion implements Handler {
  @Override
  public boolean handle(String textFieldInput, TextFieldContainer.InputType inputType, Text feedbackMessage) {
    switch (inputType) {
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

class HandleNegativeNumbers implements Handler {
  @Override
  public boolean handle(String textFieldInput, TextFieldContainer.InputType inputType, Text feedbackMessage) {
    switch (inputType) {
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
