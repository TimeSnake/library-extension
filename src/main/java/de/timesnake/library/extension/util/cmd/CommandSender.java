/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import net.kyori.adventure.text.Component;

public interface CommandSender {

    @Deprecated
    void sendMessage(String s);

    void sendMessage(Component s);

    @Deprecated
    void sendMessage(String[] strings);

    void sendMessage(Component[] components);

    String getName();

    boolean hasPermission(String s);

    boolean isConsole();

    <User extends de.timesnake.library.extension.util.player.User> User getUser();

    <Player> Player getPlayer();
}


    

