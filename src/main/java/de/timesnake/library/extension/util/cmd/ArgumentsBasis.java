/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ArgumentsBasis<Argument extends de.timesnake.library.extension.util.cmd.Argument>
    implements Iterable<Argument> {

  protected final Sender sender;
  protected LinkedList<Argument> args;

  public ArgumentsBasis(Sender sender) {
    this.sender = sender;
  }

  public ArgumentsBasis(Sender sender, LinkedList<Argument> args) {
    this(sender);
    this.args = args;
  }

  @Override
  public Iterator<Argument> iterator() {
    return this.args.iterator();
  }

  public Sender getSender() {
    return this.sender;
  }

  public LinkedList<Argument> getAll() {
    return new LinkedList<>(this.args);
  }

  public Argument get(int index) {
    if (this.args.size() > index) {
      return this.args.get(index);
    }
    return null;
  }

  public Argument getIfElseExit(int index, Function<Argument, Boolean> condition) {
    if (this.args.size() > index) {
      Argument arg = this.args.get(index);
      if (condition.apply(arg)) {
        return arg;
      }
    }
    throw new CommandExitException();
  }

  public int getLength() {
    return this.args.size();
  }

  public int size() {
    return this.args.size();
  }

  public int length() {
    return this.args.size();
  }

  public String getString(int index) {
    if (this.args.size() > index) {
      return this.args.get(index).getString();
    }
    return null;
  }

  public Argument getFirst() {
    if (!this.args.isEmpty()) {
      return this.args.get(0);
    }
    return null;
  }

  public Argument getLast() {
    if (!this.args.isEmpty()) {
      return this.args.get(this.args.size() - 1);
    }
    return null;
  }

  public String toMessage() {
    StringBuilder msg = new StringBuilder();
    LinkedList<Argument> arguments = this.args;
    for (int i = 0; i < arguments.size(); i++) {
      if (i != 0) {
        msg.append(" ");
      }
      msg.append(arguments.get(i).getString());
    }
    return msg.toString();
  }

  public String toMessage(int begin) {
    StringBuilder msg = new StringBuilder();
    for (int i = begin; i < this.args.size(); i++) {
      if (i > begin) {
        msg.append(" ");
      }
      msg.append(args.get(i).getString());
    }
    return msg.toString();
  }

  /**
   * @param beginIndex The start index
   * @param endIndex   The end index
   * @return a {@link String}-Array with values from beginIndex to inclusive endIndex
   */
  public List<String> toStringList(int beginIndex, int endIndex) {
    LinkedList<String> array = new LinkedList<>();
    for (int i = beginIndex; i <= endIndex; i++) {
      array.addLast(this.args.get(i).getString());
    }
    return array;
  }

  public Collection<String> toTextArray(int beginIndex) {
    return new ArrayList<>(Arrays.asList(this.toMessage(beginIndex).split("\n")));
  }

  public void assertElseExit(Function<ArgumentsBasis<Argument>, Boolean> function) {
    if (!function.apply(this)) {
      throw new CommandExitException();
    }
  }

  public void assertElseExit(Supplier<Boolean> supplier) {
    if (!supplier.get()) {
      throw new CommandExitException();
    }
  }

  public void assertElseExit(boolean condition) {
    if (!condition) {
      throw new CommandExitException();
    }
  }

  /**
   * @param length
   * @param sendMessage send help message when false
   * @return
   */
  public boolean isLengthHigherEquals(int length, boolean sendMessage) {
    if (this.args.size() >= length) {
      return true;
    } else if (sendMessage) {
      this.sender.sendMessageTooFewArguments();
    }
    return false;
  }

  public void isLengthHigherEqualsElseExit(int length, boolean sendMessage) {
    if (!this.isLengthHigherEquals(length, sendMessage)) {
      throw new CommandExitException();
    }
  }

  /**
   * @param length
   * @param sendMessage send help message when false
   * @return
   */
  public boolean isLengthEquals(int length, boolean sendMessage) {
    if (this.args.size() == length) {
      return true;
    } else if (sendMessage) {
      this.sender.sendMessageTooFewManyArguments();
    }
    return false;
  }

  public void isLengthEqualsElseExit(int length, boolean sendMessage) {
    if (!this.isLengthEquals(length, sendMessage)) {
      throw new CommandExitException();
    }
  }

  /**
   * @param length
   * @param sendMessage send help message when false
   * @return
   */
  public boolean isLengthLowerEquals(int length, boolean sendMessage) {
    if (this.args.size() <= length) {
      return true;
    } else if (sendMessage) {
      this.sender.sendMessageTooManyArguments();
    }
    return false;
  }

  public void isLengthLowerEqualsElseExit(int length, boolean sendMessage) {
    if (!this.isLengthLowerEquals(length, sendMessage)) {
      throw new CommandExitException();
    }
  }
}
