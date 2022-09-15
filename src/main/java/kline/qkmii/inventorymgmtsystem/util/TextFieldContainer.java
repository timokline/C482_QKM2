package kline.qkmii.inventorymgmtsystem.util;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class TextFieldContainer {
  public final TextField input;
  public final InputType inputType;
  public Text feedback;
  public TextFieldContainer(TextField input, InputType inputType, Text feedback) {
    this.input = input;
    this.inputType = inputType;
    this.feedback = feedback;
  }

  public enum InputType {INTEGER, DECIMAL, STRING}
}
