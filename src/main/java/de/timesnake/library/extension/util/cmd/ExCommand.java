/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;


import de.timesnake.library.extension.util.chat.Plugin;

public class ExCommand<Sender extends de.timesnake.library.extension.util.cmd.Sender,
    Argument extends de.timesnake.library.extension.util.cmd.Argument> {

  private final String command;
  private final CommandListenerBasis<? extends Sender, ? extends Argument> listener;
  private final Plugin plugin;

  public ExCommand(String command,
      CommandListenerBasis<? extends Sender, ? extends Argument> listener, Plugin plugin) {
    this.command = command;
    this.listener = listener;
    this.plugin = plugin;
  }

  public String getName() {
    return command;
  }

  public CommandListenerBasis<? extends Sender, ? extends Argument> getListener() {
    return listener;
  }

  public Plugin getPlugin() {
    return plugin;
  }

}
