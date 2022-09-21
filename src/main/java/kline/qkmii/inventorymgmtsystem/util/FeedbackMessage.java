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
 * Implements an inner builder pattern class for cataloging multiple logging messages.
 * @author Timothy Albert Kline
 * @version 1.0
 * @see Handler
 * @see kline.qkmii.inventorymgmtsystem.model.ProductBuilder
 */
public final class FeedbackMessage {
  public static final String ILLEGAL_INT_MSG = "Input must be numerical";
  public static final String ILLEGAL_DBL_MSG = "Input must be a decimal";
  public static final String NEGATIVE_NUM_MSG = "Number must be greater than 0";
  public static final String EMPTY_FIELD_MSG = "Required Field";
  private final StringBuilder logs;

  /**
   * Constructor that stores the logs from Builder
   * 
   * @param messages the logs built from Builder
   * @see Builder
   */
  private FeedbackMessage(StringBuilder messages) {
    logs = messages;
  }

  /**
   * Converts <code>logs</code> into a String.
   * 
   * @return the logs
   */
  public String toString() {
    return logs.toString();
  }


  /**
   * A builder pattern class for <code>FeedbackMessage</code>.
   * Uses a StringBuilder to format appended error/log messages.
   * @author Timothy Albert Kline
   * @version 1.0
   */
  public static class Builder {
    private final StringBuilder logMessages;

    /**
     * Default constructor
     */
    public Builder() {
      logMessages = new StringBuilder();
    }

    /**
     * Adds a log message
     */
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

    /**
     * @return the length of the log messages
     */
    public int length() {
      return logMessages.length();
    }

    /**
     * @return a FeedbackMessage containing the logs
     */
    public FeedbackMessage printLog() {
      return new FeedbackMessage(logMessages);
    }
  }
}
