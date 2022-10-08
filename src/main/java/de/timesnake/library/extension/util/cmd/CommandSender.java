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


    

