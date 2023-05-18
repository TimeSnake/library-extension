/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import de.timesnake.library.extension.util.chat.Plugin;

public interface CommandListenerBasis<Sender extends de.timesnake.library.extension.util.cmd.Sender,
    Argument extends de.timesnake.library.extension.util.cmd.Argument> {

  default void loadCodes(Plugin plugin) {

  }

}
