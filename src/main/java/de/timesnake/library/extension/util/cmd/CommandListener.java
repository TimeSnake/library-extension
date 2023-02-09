/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import java.util.List;

public interface CommandListener<Sender extends de.timesnake.library.extension.util.cmd.Sender,
        Argument extends de.timesnake.library.extension.util.cmd.Argument>
        extends CommandListenerBasis<Sender, Argument> {

    void onCommand(Sender sender, ExCommand<Sender, Argument> cmd, Arguments<Argument> args);

    List<String> getTabCompletion(ExCommand<Sender, Argument> cmd, Arguments<Argument> args);

}
