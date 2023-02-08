/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import de.timesnake.library.extension.util.chat.Plugin;
import java.util.List;

public interface ExCommandListener
        <Sender extends de.timesnake.library.extension.util.cmd.Sender,
                Argument extends de.timesnake.library.extension.util.cmd.Argument>
        extends CommandListenerBasis<Sender, Argument> {

    void onCommand(Sender sender, ExCommand<Sender, Argument> cmd, ExArguments<Argument> args);

    List<String> getTabCompletion(ExCommand<Sender, Argument> cmd, ExArguments<Argument> args);

    void loadCodes(Plugin plugin);

    default boolean allowDuplicates(String cmd, String[] args) {
        return true;
    }
}
