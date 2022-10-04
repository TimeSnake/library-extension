package de.timesnake.library.extension.util.cmd;


import de.timesnake.library.extension.util.chat.Plugin;

public class ExCommand<Sender extends de.timesnake.library.extension.util.cmd.Sender,
        Argument extends de.timesnake.library.extension.util.cmd.Argument> {

    private final String command;
    private final CommandListener<Sender, Argument> listener;
    private final Plugin plugin;

    public ExCommand(String command, CommandListener<Sender, Argument> listener, Plugin plugin) {
        this.command = command;
        this.listener = listener;
        this.plugin = plugin;
    }

    public String getName() {
        return command;
    }

    public CommandListener<Sender, Argument> getListener() {
        return listener;
    }

    public Plugin getPlugin() {
        return plugin;
    }

}
