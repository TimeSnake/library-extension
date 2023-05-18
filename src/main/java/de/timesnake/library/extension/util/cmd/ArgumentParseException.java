/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

public class ArgumentParseException extends RuntimeException {

  private int errorOffset = 0;

  public ArgumentParseException(String message) {
    super(message);
  }

  public ArgumentParseException(String message, int errorOffset) {
    super(message);
    this.errorOffset = errorOffset;
  }

  public int getErrorOffset() {
    return errorOffset;
  }
}
