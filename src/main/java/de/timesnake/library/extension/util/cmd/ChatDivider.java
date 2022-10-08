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

import de.timesnake.library.basic.util.chat.ChatColor;
import de.timesnake.library.basic.util.chat.ExTextColor;
import net.kyori.adventure.text.Component;

public class ChatDivider {

    public static final Component SPLITTER = Component.text("» ", ExTextColor.DARK_GRAY);
    public static final Component COLORED_SPLITTER = Component.text(ChatColor.DARK_PURPLE + "» ");

    public static final Component COLORED_OUT = Component.text("<- ", ExTextColor.DARK_PURPLE);
    public static final Component COLORED_IN = Component.text("-> ", ExTextColor.DARK_PURPLE);
    public static final Component COLORED_OUT_IN = Component.text("<-> ", ExTextColor.DARK_PURPLE);

    public static final Component SECTION = Component.text("---------------", ExTextColor.WHITE);
    public static final Component LONG_SECTION = Component.text("--------------------", ExTextColor.WHITE);

    public static final Component BOLD_SECTION = Component.text("===============", ExTextColor.WHITE);
    public static final Component LONG_BOLD_SECTION = Component.text("====================", ExTextColor.WHITE);

}
