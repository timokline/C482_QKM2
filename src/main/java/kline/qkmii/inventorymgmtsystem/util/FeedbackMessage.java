package kline.qkmii.inventorymgmtsystem.util;

public final class FeedbackMessage {
  public static final String ILLEGAL_INT_MSG = "Input must be numerical";
  public static final String ILLEGAL_DBL_MSG = "Input must be a decimal";
  public static final String NEGATIVE_NUM_MSG = "Number must be greater than 0";
  public static final String EMPTY_FIELD_MSG = "Required Field";
  private final StringBuilder logs;

  private FeedbackMessage(StringBuilder messages) {
    logs = messages;
  }

  public String toString() {
    return logs.toString();
  }

  public static class Builder {
    private final StringBuilder logMessages;

    public Builder() {
      logMessages = new StringBuilder();
    }

    public Builder append(String message) {
      if (message.isEmpty()) {
        return this;
      }
      if (logMessages.isEmpty()) {
        logMessages.append("\n\r");
      }
      logMessages.append(message).append("\n\r");

      return this;
    }

    public int length() {
      return logMessages.length();
    }

    public FeedbackMessage printLog() {
      return new FeedbackMessage(logMessages);
    }
  }
}
