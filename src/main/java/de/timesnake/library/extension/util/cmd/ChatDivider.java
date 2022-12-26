/*
 * Copyright (C) 2022 timesnake
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
