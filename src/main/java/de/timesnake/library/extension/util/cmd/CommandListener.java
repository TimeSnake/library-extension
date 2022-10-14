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

import de.timesnake.library.extension.util.chat.Plugin;

import java.util.List;

public interface CommandListener<Sender extends de.timesnake.library.extension.util.cmd.Sender,
        Argument extends de.timesnake.library.extension.util.cmd.Argument> {

    default void onCommand(Sender sender, ExCommand<Sender, Argument> cmd, Arguments<Argument> args) {

    }

    default void onCommand(Sender sender, ExCommand<Sender, Argument> cmd, ExArguments<Argument> args) {

    }

    default List<String> getTabCompletion(ExCommand<Sender, Argument> cmd, Arguments<Argument> args) {
        return null;
    }

    default List<String> getTabCompletion(ExCommand<Sender, Argument> cmd, ExArguments<Argument> args) {
        return null;
    }

    void loadCodes(Plugin plugin);

    default ArgumentsType getArgumentType(String cmd, String[] args) {
        return ArgumentsType.DEFAULT;
    }

    default boolean allowDuplicates(String cmd, String[] args) {
        return true;
    }
}
