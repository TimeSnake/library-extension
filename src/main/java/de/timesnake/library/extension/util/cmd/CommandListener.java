/*
 * Copyright (C) 2022 timesnake
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
