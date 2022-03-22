package de.timesnake.library.extension.util.cmd;


import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.library.basic.util.chat.ChatColor;
import de.timesnake.library.basic.util.chat.Plugin;
import de.timesnake.library.extension.util.chat.Chat;
import de.timesnake.library.extension.util.player.User;

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

    public String getHintCode(Integer code) {
        return HINT_PREFIX + code + Sender.SPLITTER + this.plugin.getCode();
    }

    public String getPermissionCode(Integer code) {
        return Sender.PERMISSION_PREFIX + code + Sender.SPLITTER + this.plugin.getCode();

    }

    public String getName() {
        return cmdSender.getName();
    }

    public void sendMessage(String message) {
        this.cmdSender.sendMessage(message);
    }

    public void sendPluginMessage(String message) {
        this.cmdSender.sendMessage(Chat.getSenderPlugin(plugin) + message);
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

    public String getSenderPlugin() {
        return Chat.getSenderPlugin(this.plugin);
    }

    /**
     * @param command command without slash
     **/
    public void sendMessageCommandHelp(String text, String command) {
        cmdSender.sendMessage(this.getMessageCommandHelp(text, command));
    }

    public void sendNotEnoughCoinsMessage(float missingCoins) {
        cmdSender.sendMessage(this.getSenderPlugin() + ChatColor.WARNING + "Not enough coins (" + ChatColor.VALUE + Chat.roundCoinAmount(missingCoins) + ChatColor.WARNING + " too few)");
    }

    //hint
    //not exist
    public void sendMessageNotExist(String string, Integer code, String type) {
        String fullCode = this.getHintCode(code);
        cmdSender.sendMessage(this.getMessageNotExist(string, fullCode, type));
        this.sendSystemMessage(fullCode + " [Args]");
    }

    public void sendMessagePlayerNotExist(String string) {
        String code = this.getHintCode(700);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Player"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageWorldNotExist(String string) {
        String code = this.getHintCode(701);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "World"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageGamemodeNotExist(String string) {
        String code = this.getHintCode(702);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Gamemode"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageWeatherTypeNotExist(String string) {
        String code = this.getHintCode(703);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Weather-type"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageKillAllTypeNotExist(String string) {
        String code = this.getHintCode(704);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "KillAll-type"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageServerNameNotExist(String string) {
        String code = this.getHintCode(705);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Server-name"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageServerStatusNotExist(String string) {
        String code = this.getHintCode(706);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Server-status"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessagePermissionStatusNotExist(String string) {
        String code = this.getHintCode(707);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Permission-status"));
        this.sendSystemMessage(code + " [Args]");
    }

    public void sendMessageGameNotExist(String string) {
        String code = this.getHintCode(723);
        cmdSender.sendMessage(this.getMessageNotExist(string, code, "Game"));
        this.sendSystemMessage(code + " [Args]");
    }

    //already exist

    public void sendMessageAlreadyExist(String string, Integer code, String type) {
        String fullCode = this.getHintCode(code);
        cmdSender.sendMessage(this.getMessageAlreadyExist(string, fullCode, type));
        this.sendSystemMessage(fullCode + " [Args]");
    }

    public void sendMessageWorldAlreadyExist(String string) {
        String code = this.getHintCode(735);
        cmdSender.sendMessage(this.getMessageAlreadyExist(string, code, "World"));
        this.sendSystemMessage(code + " [Args]");
    }

    //format exception

    public void sendMessageFormatException(String string, Integer code, String type, String example) {
        String fullCode = this.getHintCode(code);
        cmdSender.sendMessage(this.getMessageFormatException(string, fullCode, type, example));
        this.sendSystemMessage(fullCode + " [ArgsFormat]");
    }

    public void sendMessageNoInteger(String string) {
        String code = this.getHintCode(710);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "int", "int: 12"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoFloat(String string) {
        String code = this.getHintCode(711);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "float", "float: 0.123"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoDouble(String string) {
        String code = this.getHintCode(729);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "double", "double: 0.123456"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoChatColor(String string) {
        String code = this.getHintCode(712);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "chatcolor", "chatcolor: DARK_BLUE"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoColor(String string) {
        String code = this.getHintCode(713);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "color", "color: RED"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoHexColor(String string) {
        String code = this.getHintCode(741);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "hex-color", "hex-color: 123FA1"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoDateTime(String string) {
        String code = this.getHintCode(730);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "date", "datetime: 1hour;2day;1month;1year"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoTime(String string) {
        String code = this.getHintCode(714);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "time", "time: 12:34"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoHour(String string) {
        String code = this.getHintCode(715);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "timehour", "hours: 0-23"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoMinute(String string) {
        String code = this.getHintCode(716);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "timeminute", "minutes: 0-59"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoSecond(String string) {
        String code = this.getHintCode(717);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "timesecond", "seconds: 0-59"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoUuid(String string) {
        String code = this.getHintCode(718);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "uuid", "uuid from player"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoBoolean(String string) {
        String code = this.getHintCode(719);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "boolean", "true or false"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    public void sendMessageNoItemName(String string) {
        String code = this.getHintCode(731);
        cmdSender.sendMessage(this.getMessageFormatException(string, code, "item-name", "dirt"));
        this.sendSystemMessage(code + " [ArgsFormat]");
    }

    //argument amount
    public void sendMessageTooManyArguments() {
        String code = this.getHintCode(722);
        cmdSender.sendMessage(this.getMessageTooManyArguments(code));
        this.sendSystemMessage(code + " [ArgsLength]");
    }

    public void sendMessageTooFewArguments() {
        String code = this.getHintCode(721);
        cmdSender.sendMessage(this.getMessageTooFewArguments(code));
        this.sendSystemMessage(code + " [ArgsLength]");
    }

    public void sendMessageTooFewManyArguments() {
        String code = this.getHintCode(720);
        cmdSender.sendMessage(this.getMessageTooFewManyArguments(code));
        this.sendSystemMessage(code + " [ArgsLength]");
    }

    //permission
    public void sendMessageNoPermission(Integer code) {
        String fullCode = this.getPermissionCode(code);
        cmdSender.sendMessage(this.getMessageNoPermission(fullCode));
        this.sendSystemMessage(code + " [Perm]");
    }

    public void sendMessageNoPermissionsRank() {
        String code = this.getPermissionCode(603);
        cmdSender.sendMessage(this.getMessageNoPermissionsRank(code));
        this.sendSystemMessage(code + " [Rank]");
    }


    public void sendMessageOnlyPlayer() {
        String code = this.getHintCode(726);
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

    public void sendMessageUseHelp(String helpCommand) {
        cmdSender.sendMessage(this.getMessageUseHelp(helpCommand));
    }

    /**
     * @param command command without slash
     **/
    public String getMessageCommandHelp(String text, String command) {
        return this.getSenderPlugin() + ChatColor.PERSONAL + text + ": " + ChatColor.VALUE + "/" + command;
    }

    //hint
    public String getMessageNotExist(String string, String code, String type) {
        return this.getSenderPlugin() + ChatColor.WARNING + " " + type + " (" + ChatColor.VALUE + string + ChatColor.WARNING + ") doesn't exist (Code: " + code + ")";
    }

    public String getMessageAlreadyExist(String string, String code, String type) {
        return this.getSenderPlugin() + ChatColor.WARNING + " " + type + " (" + ChatColor.VALUE + string + ChatColor.WARNING + ") already exist (Code: " + code + ")";
    }

    //format exception
    public String getMessageFormatException(String string, String code, String type, String example) {
        return this.getSenderPlugin() + ChatColor.VALUE + string + ChatColor.WARNING + " isn't a " + type + " (" + example + ") (Code: " + code + ")";
    }

    //argument amount
    public String getMessageTooManyArguments(String code) {
        return this.getSenderPlugin() + ChatColor.WARNING + "Too many arguments (Code: " + code + ")";
    }

    public String getMessageTooFewArguments(String code) {
        return this.getSenderPlugin() + ChatColor.WARNING + "Too few arguments (Code: " + code + ")";
    }

    public String getMessageTooFewManyArguments(String code) {
        return this.getSenderPlugin() + ChatColor.WARNING + "Too few/many arguments (Code: " + code + ")";
    }

    //permission
    public String getMessageNoPermission(String code) {
        return this.getSenderPlugin() + ChatColor.WARNING + "No permission (Code: " + code + ")";
    }

    public String getMessageNoPermissionsRank(String code) {
        return this.getSenderPlugin() + ChatColor.WARNING + "No permission, your rank is too low (Code: " + code + ")";
    }

    public String getMessageOnlyPlayer(String code) {
        return this.getSenderPlugin() + ChatColor.WARNING + "You must be a player (Code: " + code + ")";
    }


    //chat
    public String getMessageNoSpam() {
        return this.getSenderPlugin() + ChatColor.WARNING + "This message is similar to " + "your previous.";
    }

    public String getMessageMuted() {
        return this.getSenderPlugin() + ChatColor.WARNING + "You are muted! For questions use " + ChatColor.VALUE + "/support";
    }

    public String getMessageUnknownCommand() {
        return this.getSenderPlugin() + ChatColor.WARNING + "Unknown command";
    }

    public String getMessageUseHelp(String helpCommand) {
        return this.getSenderPlugin() + ChatColor.PERSONAL + "Use " + ChatColor.VALUE + "/" + helpCommand + ChatColor.PERSONAL + " for help";
    }

}
