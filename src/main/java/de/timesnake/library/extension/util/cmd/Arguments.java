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
import java.util.stream.Collectors;

public class Arguments<Argument extends de.timesnake.library.extension.util.cmd.Argument> implements Iterable<Argument> {


    private final Sender sender;
    private final LinkedList<Argument> args;

    public Arguments(Sender sender, LinkedList<Argument> args) {
        this.args = args;
        this.sender = sender;
    }

    public Arguments(Arguments<Argument> args) {
        this.args = args.getAll();
        this.sender = args.getSender();
    }

    @SafeVarargs
    public Arguments(Sender sender, Argument... args) {
        this.args = new LinkedList<>();
        for (Argument arg : args) {
            this.args.addLast(arg);
        }
        this.sender = sender;
    }

    public Iterator<Argument> iterator() {
        return this.args.iterator();
    }


    public Sender getSender() {
        return this.sender;
    }

    public LinkedList<Argument> getAll() {
        return this.args;
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
     * @param beginIndex The the start index
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

    public List<String> toStringList() {
        return this.args.stream().map(Argument::getString).collect(Collectors.toList());
    }

    public Collection<String> toTextArray(int beginIndex) {
        return new ArrayList<>(Arrays.asList(this.toMessage(beginIndex).split("\n")));
    }

    public String collapse(int beginIndex) {
        Argument begin = this.get(beginIndex);

        if (begin == null) {
            return null;
        }

        if (!begin.getString().startsWith("\"")) {
            return begin.getString();
        }

        int index = beginIndex;
        StringBuilder sb = new StringBuilder(begin.getString().replaceFirst("\"", "")).append(" ");

        Argument current;
        do {
            current = this.get(++index);

            if (current.getString().endsWith("\"")) {
                sb.append(current.getString().replace("\"", ""));
                break;
            }

            sb.append(current.getString()).append(" ");
        } while (current != null);

        return sb.toString();
    }

    public Arguments<Argument> removeLowerEquals(int index) {

        LinkedList<Argument> args = new LinkedList<>();

        for (int i = index + 1; i < this.args.size(); i++) {
            args.addLast(this.args.get(i));
        }

        return new Arguments<>(this.sender, args);
    }

    public Argument getArgumentByString(String arg) {
        for (Argument a : this) {
            if (a.getString().equals(arg)) {
                return a;
            }
        }
        return null;
    }

    public Argument getArgumentByStringPart(String part) {
        for (Argument a : this) {
            if (a.getString().contains(part)) {
                return a;
            }
        }
        return null;
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
