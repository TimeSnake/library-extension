package de.timesnake.library.extension.util.cmd;

import de.timesnake.library.extension.util.chat.Plugin;

import java.util.List;

public interface CommandListener<Sender extends de.timesnake.library.extension.util.cmd.Sender,
        Argument extends de.timesnake.library.extension.util.cmd.Argument> {

    void onCommand(Sender sender, ExCommand<Sender, Argument> cmd, Arguments<Argument> args);

    List<String> getTabCompletion(ExCommand<Sender, Argument> cmd, Arguments<Argument> args);

    void loadCodes(Plugin plugin);
}
