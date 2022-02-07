package de.timesnake.library.extension.util.chat;

import de.timesnake.library.basic.util.chat.ChatColor;
import de.timesnake.library.basic.util.chat.Plugin;
import de.timesnake.library.extension.util.cmd.Argument;
import de.timesnake.library.extension.util.cmd.Arguments;
import de.timesnake.library.extension.util.cmd.Sender;

public interface Chat {

    static String getSplitter() {
        return ChatColor.DARK_GRAY + "» " + ChatColor.RESET;
    }

    static String getOtherSplitter() {
        return ChatColor.DARK_PURPLE + "» " + ChatColor.RESET;
    }

    static String getLineSeparator() {
        return ChatColor.WHITE + "----------";
    }

    static String getLongLineSeparator() {
        return ChatColor.WHITE + "---------------";
    }

    static String getDoubleLineSeparator() {
        return ChatColor.WHITE + "==========";
    }

    static String getSenderPlugin(Plugin plugin) {
        return ChatColor.DARK_AQUA + plugin.getName() + getSplitter();
    }

    static String getMessageCode(String codeType, int code, Plugin plugin) {
        return "(Code: " + codeType + code + " " + plugin.getCode() + ")";
    }

    static Arguments<Argument> createArguments(Sender sender, Argument... args) {
        return new Arguments<>(sender, args);
    }

}
