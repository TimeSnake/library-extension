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

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Arguments<Argument extends de.timesnake.library.extension.util.cmd.Argument>
        extends ArgumentsBasis<Argument> {

    public Arguments(Sender sender, String[] args) {
        super(sender);
        this.args = new LinkedList<>();
        for (String arg : args) {
            this.args.addLast(this.createArgument(sender, arg));
        }
    }

    public Arguments(Sender sender, LinkedList<Argument> args) {
        super(sender, args);
    }

    public Arguments(Arguments<Argument> args) {
        this(args.sender, args.getAll());
    }

    @SafeVarargs
    public Arguments(Sender sender, Argument... args) {
        super(sender);
        this.args = new LinkedList<>();
        for (Argument arg : args) {
            this.args.addLast(arg);
        }
    }

    public abstract Argument createArgument(Sender sender, String arg);

    public List<String> toStringList() {
        return this.args.stream().map(Argument::getString).collect(Collectors.toList());
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

    @Deprecated
    public Arguments<de.timesnake.library.extension.util.cmd.Argument> removeLowerEquals(int index) {

        LinkedList<de.timesnake.library.extension.util.cmd.Argument> args = new LinkedList<>();

        for (int i = index + 1; i < this.args.size(); i++) {
            args.addLast(this.args.get(i));
        }

        return new Arguments<>(this.sender, args) {
            @Override
            public de.timesnake.library.extension.util.cmd.Argument createArgument(Sender sender, String arg) {
                return new de.timesnake.library.extension.util.cmd.Argument(sender, arg);
            }
        };
    }

    @Deprecated
    public Argument getArgumentByString(String arg) {
        for (Argument a : this) {
            if (a.getString().equals(arg)) {
                return a;
            }
        }
        return null;
    }

    @Deprecated
    public Argument getArgumentByStringPart(String part) {
        for (Argument a : this) {
            if (a.getString().contains(part)) {
                return a;
            }
        }
        return null;
    }

    @Deprecated
    public Argument getArgumentByStringStart(String start) {
        for (Argument a : this) {
            if (a.getString().startsWith(start)) {
                return a;
            }
        }
        return null;
    }

}
