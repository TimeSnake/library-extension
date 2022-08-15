package de.timesnake.library.extension.util.cmd;


import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.library.basic.util.chat.ExTextColor;
import de.timesnake.library.basic.util.chat.Plugin;
import de.timesnake.library.extension.util.chat.Chat;
import de.timesnake.library.extension.util.player.User;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.UUID;

public abstract class Sender {

    public static final String ERROR_PREFIX = "E";
    public static final String PERMISSION_PREFIX = "P";
    public static final String SPLITTER = " ";
    public static final String HINT_PREFIX = "H";

    protected final CommandSender cmdSender;
    protected final Plugin plugin;

    public Sender(CommandSender cmdSender, Plugin plugin) {
        this.cmdSender = cmdSender;
        this.plugin = plugin;
    }

    public boolean hasPermission(String permission, int code) {
        if (cmdSender.hasPermission(permission)) {
            return true;
        }
        this.sendMessageNoPermission(code);
        return false;
    }

    public boolean hasPermission(String permission, int code, boolean sendMessage) {
        if (cmdSender.hasPermission(permission)) {
            return true;
        }
        if (sendMessage) {
            this.sendMessageNoPermission(code);
        }
        return false;
    }

    public boolean hasPermission(String permission) {
        return cmdSender.hasPermission(permission);
    }

    public boolean hasGroupRankLower(Integer rank) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() < rank) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() == 1) {
            return true;
        }
        this.sendMessageNoPermissionsRank();
        return false;
    }

    public boolean hasGroupRankLower(UUID uuid) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() < Database.getUsers().getUser(uuid).getPermGroup().getRank()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() == 1) {
            return true;
        }
        this.sendMessageNoPermissionsRank();
        return false;
    }

    public boolean hasGroupRankLowerEquals(User user) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() <= this.getDbUser().getPermGroup().getRank()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() == 1) {
            return true;
        }
        this.sendMessageNoPermissionsRank();
        return false;
    }

    public boolean hasGroupRankLower(DbUser user) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() < user.getPermGroup().getRank()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() == 1) {
            return true;
        }
        this.sendMessageNoPermissionsRank();
        return false;
    }

    public boolean hasGroupRankLowerEquals(DbUser user) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() <= user.getPermGroup().getRank()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() == 1) {
            return true;
        }
        this.sendMessageNoPermissionsRank();
        return false;
    }

    public boolean hasGroupRankLowerEquals(DbPermGroup group) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() <= group.getRank()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() == 1) {
            return true;
        }
        this.sendMessageNoPermissionsRank();
        return false;
    }

    public boolean hasGroupRankLower(DbPermGroup group) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() < group.getRank()) {
            return true;
        } else if (this.getDbUser().getPermGroup().getRank() == 1) {
            return true;
        }
        this.sendMessageNoPermissionsRank();
        return false;
    }

    public boolean isPlayer(boolean sendMessage) {
        if (!cmdSender.isConsole()) {
            return true;
        } else if (sendMessage) {
            this.sendMessageOnlyPlayer();
        }
        return false;
    }

    public boolean isConsole(boolean sendMessage) {
        if (cmdSender.isConsole()) {
            return true;
        } else if (sendMessage) {
            this.sendMessageOnlyPlayer();
        }
        return false;
    }

    public void sendSystemMessage(String code) {
        this.sendConsoleMessage("[" + plugin.getName() + "][Cmd] " + this.cmdSender.getName() + ": command execution " +
                "cancelled (" + code + this.plugin.getCode() + ")");
    }

    public abstract void sendConsoleMessage(String message);

    public Component getHintCode(Integer code) {
        return Component.text(HINT_PREFIX + code + Sender.SPLITTER + this.plugin.getCode());
    }

    public Component getPermissionCode(Integer code) {
        return Component.text(Sender.PERMISSION_PREFIX + code + Sender.SPLITTER + this.plugin.getCode());

    }

    public String getName() {
        return cmdSender.getName();
    }

    @Deprecated
    public void sendMessage(String message) {
        this.cmdSender.sendMessage(message);
    }

    public void sendMessage(Component message) {
        this.cmdSender.sendMessage(message);
    }

    @Deprecated
    public void sendPluginMessage(String message) {
        this.cmdSender.sendMessage(Chat.getSenderPlugin(plugin)
                .append(LegacyComponentSerializer.legacyAmpersand().deserialize(message)));
    }

    public void sendPluginMessage(Component message) {
        this.cmdSender.sendMessage(Chat.getSenderPlugin(plugin).append(message));
    }

    public CommandSender getCommandSender() {
        return this.cmdSender;
    }

    public String getPluginName() {
        return this.plugin.getName();
    }

    public DbUser getDbUser() {
        return cmdSender.getUser().getDatabase();
    }

    @Deprecated
    public String getSenderPluginString() {
        return Chat.getSenderPluginString(this.plugin);
    }

    public Component getSenderPlugin() {
        return Chat.getSenderPlugin(this.plugin);
    }

    /**
     * @param command command without slash
     **/
    public void sendMessageCommandHelp(String text, String command) {
        this.sendMessageCommandHelp(LegacyComponentSerializer.legacyAmpersand().deserialize(text),
                LegacyComponentSerializer.legacyAmpersand().deserialize(command));
    }

    public void sendMessageCommandHelp(Component text, Component command) {
        cmdSender.sendMessage(this.getMessageCommandHelp(text, command));
    }

    public void sendNotEnoughCoinsMessage(float missingCoins) {
        cmdSender.sendMessage(this.getSenderPlugin()
                .append(Component.text("Not enough coins (", ExTextColor.WARNING))
                .append(Component.text(Chat.roundCoinAmount(missingCoins), ExTextColor.VALUE))
                .append(Component.text(" too few)", ExTextColor.WARNING)));
    }

    //hint
    //not exist
    public void sendMessageNotExist(String string, Integer code, String type) {
        Component fullCode = this.getHintCode(code);
        cmdSender.sendMessage(this.getMessageNotExist(string, fullCode, type));
        this.sendSystemMessage(fullCode + " [Args]");
    }

    public void sendMessagePlayerNotExist(String string) {
        Component code = this.getHintCode(700);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Player"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageWorldNotExist(String string) {
        Component code = this.getHintCode(701);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "World"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageGamemodeNotExist(String string) {
        Component code = this.getHintCode(702);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Gamemode"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageWeatherTypeNotExist(String string) {
        Component code = this.getHintCode(703);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Weather-type"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageKillAllTypeNotExist(String string) {
        Component code = this.getHintCode(704);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "KillAll-type"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageServerNameNotExist(String string) {
        Component code = this.getHintCode(705);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Server-name"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageServerStatusNotExist(String string) {
        Component code = this.getHintCode(706);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Server-status"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessagePermissionStatusNotExist(String string) {
        Component code = this.getHintCode(707);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Permission-status"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageGameNotExist(String string) {
        Component code = this.getHintCode(723);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Game"));
        this.sendSystemMessage(code + " [Args]");
    }

    //already exist

    public void sendMessageAlreadyExist(String string, Integer code, String type) {
        Component fullCode = this.getHintCode(code);
        cmdSender.sendMessage(this.getMessageAlreadyExist(string, fullCode, type));
        this.sendSystemMessage(fullCode + " [Args]");
    }

    public void sendMessageWorldAlreadyExist(String string) {
        Component code = this.getHintCode(735);
        cmdSender.sendMessage(this.getMessageAlreadyExist(string, code, "World"));
        this.sendSystemMessage(code + " [Args]");
    }

    //format exception

    public void sendMessageFormatException(String string, Integer code, String type, String example) {
        Component fullCode = this.getHintCode(code);
        cmdSender.sendMessage(this.getMessageFormatException(string, fullCode, type, example));
        this.sendSystemMessage(fullCode + " [ArgsFormat]");
    }

    public void sendMessageNoInteger(String string) {
        Component code = this.getHintCode(710);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "int", "int: 12"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoFloat(String string) {
        Component code = this.getHintCode(711);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "float", "float: 0.123"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoDouble(String string) {
        Component code = this.getHintCode(729);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "double", "double: 0.123456"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoChatColor(String string) {
        Component code = this.getHintCode(712);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "chatcolor", "chatcolor: DARK_BLUE"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoColor(String string) {
        Component code = this.getHintCode(713);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "color", "color: RED"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoHexColor(String string) {
        Component code = this.getHintCode(741);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "hex-color", "hex-color: 123FA1"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoDateTime(String string) {
        Component code = this.getHintCode(730);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "date", "datetime: 1hour;2day;1month;" +
                "1year"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoTime(String string) {
        Component code = this.getHintCode(714);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "time", "time: 12:34"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoHour(String string) {
        Component code = this.getHintCode(715);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "timehour", "hours: 0-23"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoMinute(String string) {
        Component code = this.getHintCode(716);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "timeminute", "minutes: 0-59"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoSecond(String string) {
        Component code = this.getHintCode(717);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "timesecond", "seconds: 0-59"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoUuid(String string) {
        Component code = this.getHintCode(718);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "uuid", "uuid from player"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoBoolean(String string) {
        Component code = this.getHintCode(719);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "boolean", "true or false"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoItemName(String string) {
        Component code = this.getHintCode(731);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "item-name", "dirt"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    //argument amount
    public void sendMessageTooManyArguments() {
        Component code = this.getHintCode(722);
        cmdSender.sendMessage(this.getMessageTooManyArguments(code));
        this.sendSystemMessage(code + " [ArgsLength]");
    }

    public void sendMessageTooFewArguments() {
        Component code = this.getHintCode(721);
        cmdSender.sendMessage(this.getMessageTooFewArguments(code));
        this.sendSystemMessage(code + " [ArgsLength]");
    }

    public void sendMessageTooFewManyArguments() {
        Component code = this.getHintCode(720);
        cmdSender.sendMessage(this.getMessageTooFewManyArguments(code));
        this.sendSystemMessage(code + " [ArgsLength]");
    }

    //permission
    public void sendMessageNoPermission(Integer code) {
        Component fullCode = this.getPermissionCode(code);
        cmdSender.sendMessage(this.getMessageNoPermission(fullCode));
        this.sendSystemMessage(code + " [Perm]");
    }

    public void sendMessageNoPermissionsRank() {
        Component code = this.getPermissionCode(603);
        cmdSender.sendMessage(this.getMessageNoPermissionsRank(code));
        this.sendSystemMessage(code + " [Rank]");
    }


    public void sendMessageOnlyPlayer() {
        Component code = this.getHintCode(726);
        cmdSender.sendMessage(this.getMessageOnlyPlayer(code));
    }

    //chat
    public void sendMessageNoSpam() {
        cmdSender.sendMessage(this.getMessageNoSpam());
    }

    public void sendMessageMuted() {
        cmdSender.sendMessage(this.getMessageMuted());
    }

    public void sendMessageUnknownCommand() {
        cmdSender.sendMessage(this.getMessageUnknownCommand());
    }

    public void sendMessageUseHelp(Component helpCommand) {
        cmdSender.sendMessage(this.getMessageUseHelp(helpCommand));
    }

    public void sendMessageUseHelp(String helpCommand) {
        this.sendMessageUseHelp(LegacyComponentSerializer.legacyAmpersand().deserialize(helpCommand));
    }


    /**
     * @param command command without slash
     **/
    public Component getMessageCommandHelp(Component text, Component command) {
        return this.getSenderPlugin().append(Component.text(text + ": ", ExTextColor.PERSONAL))
                .append(Component.text("/" + command, ExTextColor.VALUE));
    }

    //hint
    public Component getMessageNotExist(String string, Component code, String type) {
        return this.getSenderPlugin().append(Component.text(" " + type + " (", ExTextColor.WARNING))
                .append(Component.text(string, ExTextColor.VALUE))
                .append(Component.text(") doesn't exist (Code: " + code + ")", ExTextColor.WARNING));
    }

    public Component getMessageAlreadyExist(String string, Component code, String type) {
        return this.getSenderPlugin().append(Component.text(" " + type + " (", ExTextColor.WARNING))
                .append(Component.text(string, ExTextColor.VALUE))
                .append(Component.text(") already exist (Code: " + code + ")", ExTextColor.WARNING));
    }

    //format exception
    public Component getMessageFormatException(String string, Component code, String type, String example) {
        return this.getSenderPlugin().append(Component.text(string, ExTextColor.VALUE))
                .append(Component.text(" isn't a " + type + " (" + example + ") (Code: " + code + ")", ExTextColor.WARNING));
    }

    //argument amount
    public Component getMessageTooManyArguments(Component code) {
        return this.getSenderPlugin().append(Component.text("Too many arguments (Code: " + code + ")", ExTextColor.WARNING));
    }

    public Component getMessageTooFewArguments(Component code) {
        return this.getSenderPlugin().append(Component.text("Too few arguments (Code: " + code + ")", ExTextColor.WARNING));
    }

    public Component getMessageTooFewManyArguments(Component code) {
        return this.getSenderPlugin().append(Component.text("Too few/many arguments (Code: " + code + ")").color(ExTextColor.WARNING));
    }

    //permission
    public Component getMessageNoPermission(Component code) {
        return this.getSenderPlugin().append(Component.text("No permission (Code: " + code + ")").color(ExTextColor.WARNING));
    }

    public Component getMessageNoPermissionsRank(Component code) {
        return this.getSenderPlugin().append(Component.text("No permission, your rank is too low (Code: " + code + ")").color(ExTextColor.WARNING));
    }

    public Component getMessageOnlyPlayer(Component code) {
        return this.getSenderPlugin().append(Component.text("You must be a player (Code: " + code + ")").color(ExTextColor.WARNING));
    }


    //chat
    public Component getMessageNoSpam() {
        return this.getSenderPlugin().append(Component.text("This message is similar to your previous.").color(ExTextColor.WARNING));
    }

    public Component getMessageMuted() {
        return this.getSenderPlugin().append(Component.text("You are muted! For questions use ").color(ExTextColor.WARNING))
                .append(Component.text("/support").color(ExTextColor.VALUE));
    }

    public Component getMessageUnknownCommand() {
        return this.getSenderPlugin().append(Component.text("Unknown command").color(ExTextColor.WARNING));
    }

    public Component getMessageUseHelp(Component helpCommand) {
        return this.getSenderPlugin().append(Component.text("Use ").color(ExTextColor.PERSONAL))
                .append(Component.text("/" + helpCommand).color(ExTextColor.VALUE))
                .append(Component.text(" for help").color(ExTextColor.PERSONAL));
    }

}
