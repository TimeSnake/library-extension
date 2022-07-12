package de.timesnake.library.extension.util.chat;

import de.timesnake.library.basic.util.chat.ChatColor;
import de.timesnake.library.basic.util.chat.Plugin;
import de.timesnake.library.extension.util.cmd.Argument;
import de.timesnake.library.extension.util.cmd.Arguments;
import de.timesnake.library.extension.util.cmd.Sender;

import java.util.Collection;

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

    static float roundCoinAmount(float coins) {
        return ((int) (coins * 100)) / 100f;
    }

    static String getTimeString(Integer time) {
        if (time >= 3600) {
            return time / 3600 + "h " + (time % 3600) / 60 + "min " + time % 60 + "s";
        }
        if (time >= 60) {
            return time / 60 + "min " + time % 60 + "s";
        } else {
            return time % 60 + "s";
        }
    }

    static String listToString(Collection<?> list) {
        if (list.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (Object obj : list) {
            sb.append(obj.toString()).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length() - 1);

        return sb.toString();
    }

}
