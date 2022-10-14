/*
 * library-extension.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.library.extension.util.cmd;

import java.util.*;

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

    /**
     * @param length
     * @param sendMessage send help message when false
     * @return
     */
    public boolean isLengthHigher(int length, boolean sendMessage) {
        if (this.args.size() > length) {
            return true;
        } else if (sendMessage) {
            this.sender.sendMessageTooFewArguments();
        }
        return false;
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

    /**
     * @param length
     * @param sendMessage send help message when false
     * @return
     */
    public boolean isLengthLower(int length, boolean sendMessage) {
        if (this.args.size() < length) {
            return true;
        } else if (sendMessage) {
            sender.sendMessageTooManyArguments();
        }
        return false;
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
}
