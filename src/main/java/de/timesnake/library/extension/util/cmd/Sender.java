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


import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.library.basic.util.chat.ExTextColor;
import de.timesnake.library.extension.util.chat.Chat;
import de.timesnake.library.extension.util.chat.Code;
import de.timesnake.library.extension.util.chat.Plugin;
import de.timesnake.library.extension.util.player.User;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

import java.util.UUID;

public abstract class Sender {

    public static final Code.Help PLAYER_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xnp", 1, "Player not exists");
    public static final Code.Help WORLD_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xnw", 1, "World not exists");
    public static final Code.Help WORLD_ALREADY_EXISTS = new Code.Help(Plugin.SYSTEM, "xaw", 2, "World already exists");
    public static final Code.Help GAMEMODE_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xnm", 1, "gamemode not exists");
    public static final Code.Help WEATHER_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xnw", 1, "weather not exists");
    public static final Code.Help KILL_ALL_TYPE_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xnk", 1, "kill all type not exists");
    public static final Code.Help SERVER_NAME_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xns", 1, "server name not exists");
    public static final Code.Help SERVER_STATUS_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xns", 1, "server status not exists");
    public static final Code.Help TICKET_STATUS_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xnt", 1, "ticket status not exists");
    public static final Code.Help PERMISSION_STATUS_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xnr", 1, "permission status not exists");
    public static final Code.Help GAME_NOT_EXISTS = new Code.Help(Plugin.SYSTEM, "xng", 1, "Game not exists");
    public static final Code INTEGER_NON = new Code.Help(Plugin.SYSTEM, "nin", 1, "not an integer");
    public static final Code FLOAT_NON = new Code.Help(Plugin.SYSTEM, "nfl", 1, "not a float");
    public static final Code DOUBLE_NON = new Code.Help(Plugin.SYSTEM, "ndo", 1, "not a double");
    public static final Code CHAT_COLOR_NON = new Code.Help(Plugin.SYSTEM, "ncc", 1, "not a chat color");
    public static final Code COLOR_NON = new Code.Help(Plugin.SYSTEM, "nco", 1, "not a color");
    public static final Code HEX_COLOR_NON = new Code.Help(Plugin.SYSTEM, "nhc", 1, "not a hex color");
    public static final Code DATE_TIME_NON = new Code.Help(Plugin.SYSTEM, "ndt", 1, "not a date time");
    public static final Code TIME_NON = new Code.Help(Plugin.SYSTEM, "nti", 1, "not a time");
    public static final Code HOUR_NON = new Code.Help(Plugin.SYSTEM, "nho", 1, "not a hour");
    public static final Code MINUTE_NON = new Code.Help(Plugin.SYSTEM, "nmi", 1, "not a minute");
    public static final Code SECOND_NON = new Code.Help(Plugin.SYSTEM, "nse", 1, "not a second");
    public static final Code UUID_NON = new Code.Help(Plugin.SYSTEM, "nuu", 1, "not an uuid");
    public static final Code ITEM_NAME_NON = new Code.Help(Plugin.SYSTEM, "nit", 1, "not an item name");
    public static final Code BOOLEAN_NON = new Code.Help(Plugin.SYSTEM, "nbo", 1, "not a boolean");
    public static final Code TOO_MANY_ARGS = new Code.Help(Plugin.SYSTEM, "atm", 1, "Too many arguments");
    public static final Code TOO_FEW_ARGS = new Code.Help(Plugin.SYSTEM, "atf", 1, "Too few arguments");
    public static final Code TOO_FEW_MANY_ARGS = new Code.Help(Plugin.SYSTEM, "afm", 1, "Too few/many arguments");
    public static final Code NO_PERMISSION_RANK = new Code.Permission(Plugin.SYSTEM, "prk", 1, "Rank too low");
    public static final Code ONLY_PLAYER = new Code.Help(Plugin.SYSTEM, "pop", 1, "Only player");
    public static final Code NUMBER_OUT_OF_BOUNDS = new Code.Help(Plugin.SYSTEM, "nob", 1, "number out of bounds");

    protected final CommandSender cmdSender;
    protected final Plugin plugin;

    public Sender(CommandSender cmdSender, Plugin plugin) {
        this.cmdSender = cmdSender;
        this.plugin = plugin;
    }

    public boolean hasPermission(Code.Permission code) {
        if (cmdSender.hasPermission(code.getPermission())) {
            return true;
        }
        this.sendMessageNoPermission(code);
        return false;
    }

    @Deprecated
    public boolean hasPermission(String permission, Code.Permission code) {
        if (cmdSender.hasPermission(permission)) {
            return true;
        }
        this.sendMessageNoPermission(code);
        return false;
    }

    @Deprecated
    public boolean hasPermission(String permission, Code.Permission code, boolean sendMessage) {
        if (cmdSender.hasPermission(permission)) {
            return true;
        }
        if (sendMessage) {
            this.sendMessageNoPermission(code);
        }
        return false;
    }

    public boolean hasPermission(Code.Permission code, boolean sendMessage) {
        if (cmdSender.hasPermission(code.getPermission())) {
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

    @Deprecated
    public void sendSystemMessage(String code) {
        this.sendConsoleMessage("[" + plugin.getName() + "][Cmd] " + this.cmdSender.getName() + ": command execution " +
                "cancelled (" + code + this.plugin.getCode() + ")");
    }

    public void sendSystemMessage(Code code) {
        this.sendConsoleMessage("[" + plugin.getName() + "][Cmd] " + this.cmdSender.getName() + ": command execution " +
                "cancelled (" + code.asStringCode() + ")");
    }

    @Deprecated
    public void sendSystemMessage(Component code, String info) {
        this.sendSystemMessage(PlainTextComponentSerializer.plainText().serialize(code) + info);
    }

    public void sendSystemMessage(Code code, String info) {
        this.sendSystemMessage(code.asStringCode() + info);
    }

    public abstract void sendConsoleMessage(String message);

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

    //already exist

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

    //format exception

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
    @Deprecated
    public void sendMessageCommandHelp(String text, String command) {
        this.sendMessageCommandHelp(LegacyComponentSerializer.legacyAmpersand().deserialize(text).color(ExTextColor.PERSONAL),
                LegacyComponentSerializer.legacyAmpersand().deserialize(command).color(ExTextColor.VALUE));
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
    public void sendMessageNotExist(String string, Code code, String type) {
        cmdSender.sendMessage(this.getMessageNotExist(string, code, type));
        this.sendSystemMessage(code, " [Args]");
    }

    public void sendMessagePlayerNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, PLAYER_NOT_EXISTS, "Player"));
        this.sendSystemMessage(PLAYER_NOT_EXISTS, " [Args]");
    }

    public void sendMessageWorldNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, WORLD_NOT_EXISTS, "World"));
        this.sendSystemMessage(WORLD_NOT_EXISTS, " [Args]");
    }

    public void sendMessageGamemodeNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, GAMEMODE_NOT_EXISTS, "Gamemode"));
        this.sendSystemMessage(GAMEMODE_NOT_EXISTS, " [Args]");
    }

    public void sendMessageWeatherTypeNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, WEATHER_NOT_EXISTS, "Weather-type"));
        this.sendSystemMessage(WEATHER_NOT_EXISTS, " [Args]");
    }

    public void sendMessageKillAllTypeNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, KILL_ALL_TYPE_NOT_EXISTS, "KillAll-type"));
        this.sendSystemMessage(KILL_ALL_TYPE_NOT_EXISTS, " [Args]");
    }

    public void sendMessageServerNameNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, SERVER_NAME_NOT_EXISTS, "Server-name"));
        this.sendSystemMessage(SERVER_NAME_NOT_EXISTS, " [Args]");
    }

    public void sendMessageServerStatusNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, SERVER_STATUS_NOT_EXISTS, "Server-status"));
        this.sendSystemMessage(SERVER_STATUS_NOT_EXISTS, " [Args]");
    }

    public void sendMessagePermissionStatusNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, PERMISSION_STATUS_NOT_EXISTS, "Permission-status"));
        this.sendSystemMessage(PERMISSION_STATUS_NOT_EXISTS, " [Args]");
    }

    public void sendMessageTicketStatusNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, TICKET_STATUS_NOT_EXISTS, "Ticket-status"));
        this.sendSystemMessage(TICKET_STATUS_NOT_EXISTS, " [Args]");
    }

    public void sendMessageGameNotExist(String string) {
        cmdSender.sendMessage(this.getMessageNotExist(string, GAME_NOT_EXISTS, "Game"));
        this.sendSystemMessage(GAME_NOT_EXISTS, " [Args]");
    }

    public void sendMessageAlreadyExist(String string, Code code, String type) {
        cmdSender.sendMessage(this.getMessageAlreadyExist(string, code, type));
        this.sendSystemMessage(code, " [Args]");
    }

    public void sendMessageWorldAlreadyExist(String string) {
        cmdSender.sendMessage(this.getMessageAlreadyExist(string, WORLD_ALREADY_EXISTS, "World"));
        this.sendSystemMessage(WORLD_ALREADY_EXISTS, " [Args]");
    }

    public void sendMessageFormatException(String string, Code code, String type, String example) {
        cmdSender.sendMessage(this.getMessageFormatException(string, code, type, example));
        this.sendSystemMessage(code, " [ArgsFormat]");
    }

    public void sendMessageNoInteger(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, INTEGER_NON, "int", "int: 12"));
        this.sendSystemMessage(INTEGER_NON, " [ArgsFormat]");
    }

    public void sendMessageNoFloat(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, FLOAT_NON, "float", "float: 0.123"));
        this.sendSystemMessage(FLOAT_NON, " [ArgsFormat]");
    }

    public void sendMessageNoDouble(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, DOUBLE_NON, "double", "double: 0.123456"));
        this.sendSystemMessage(DOUBLE_NON, " [ArgsFormat]");
    }

    public void sendMessageNumberOutOfBounds(String string, String lowerBound, String upperBound) {
        cmdSender.sendMessage(this.getMessageNumberOutOfBoundsException(string, NUMBER_OUT_OF_BOUNDS, lowerBound, upperBound));
        this.sendSystemMessage(NUMBER_OUT_OF_BOUNDS, " [ArgsBound]");
    }

    public void sendMessageNoChatColor(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, CHAT_COLOR_NON, "chatcolor", "chatcolor: DARK_BLUE"));
        this.sendSystemMessage(CHAT_COLOR_NON, " [ArgsFormat]");
    }

    public void sendMessageNoColor(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, COLOR_NON, "color", "color: RED"));
        this.sendSystemMessage(COLOR_NON, " [ArgsFormat]");
    }

    public void sendMessageNoHexColor(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, HEX_COLOR_NON, "hex-color", "hex-color: 123FA1"));
        this.sendSystemMessage(HEX_COLOR_NON, " [ArgsFormat]");
    }

    public void sendMessageNoDateTime(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, DATE_TIME_NON, "date", "datetime: 1hour;2day;1month;" +
                "1year"));
        this.sendSystemMessage(DATE_TIME_NON, " [ArgsFormat]");
    }

    public void sendMessageNoTime(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, TIME_NON, "time", "time: 12:34"));
        this.sendSystemMessage(TIME_NON, " [ArgsFormat]");
    }

    public void sendMessageNoHour(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, HOUR_NON, "timehour", "hours: 0-23"));
        this.sendSystemMessage(HOUR_NON, " [ArgsFormat]");
    }

    public void sendMessageNoMinute(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, MINUTE_NON, "timeminute", "minutes: 0-59"));
        this.sendSystemMessage(MINUTE_NON, " [ArgsFormat]");
    }

    public void sendMessageNoSecond(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, SECOND_NON, "timesecond", "seconds: 0-59"));
        this.sendSystemMessage(SECOND_NON, " [ArgsFormat]");
    }

    public void sendMessageNoUuid(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, UUID_NON, "uuid", "uuid from player"));
        this.sendSystemMessage(UUID_NON, " [ArgsFormat]");
    }

    public void sendMessageNoBoolean(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, BOOLEAN_NON, "boolean", "true or false"));
        this.sendSystemMessage(BOOLEAN_NON, " [ArgsFormat]");
    }

    public void sendMessageNoItemName(String string) {
        cmdSender.sendMessage(this.getMessageFormatException(string, ITEM_NAME_NON, "item-name", "dirt"));
        this.sendSystemMessage(ITEM_NAME_NON, " [ArgsFormat]");
    }

    //argument amount
    public void sendMessageTooManyArguments() {
        cmdSender.sendMessage(this.getMessageTooManyArguments(TOO_MANY_ARGS));
        this.sendSystemMessage(TOO_MANY_ARGS, " [ArgsLength]");
    }

    public void sendMessageTooFewArguments() {
        cmdSender.sendMessage(this.getMessageTooFewArguments(TOO_FEW_ARGS));
        this.sendSystemMessage(TOO_FEW_ARGS, " [ArgsLength]");
    }

    public void sendMessageTooFewManyArguments() {
        cmdSender.sendMessage(this.getMessageTooFewManyArguments(TOO_FEW_MANY_ARGS));
        this.sendSystemMessage(TOO_FEW_MANY_ARGS, " [ArgsLength]");
    }

    public void sendMessageNoPermission(Code.Permission code) {
        cmdSender.sendMessage(this.getMessageNoPermission(code));
        this.sendSystemMessage(code + " [Perm]");
    }

    public void sendMessageNoPermissionsRank() {
        cmdSender.sendMessage(this.getMessageNoPermissionsRank(NO_PERMISSION_RANK));
        this.sendSystemMessage(NO_PERMISSION_RANK, " [Rank]");
    }


    public void sendMessageOnlyPlayer() {
        cmdSender.sendMessage(this.getMessageOnlyPlayer(ONLY_PLAYER));
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
        return this.getSenderPlugin().append(text.color(ExTextColor.PERSONAL))
                .append(Component.text(": ", ExTextColor.PERSONAL))
                .append(Component.text("/", ExTextColor.VALUE))
                .append(command.color(ExTextColor.VALUE));
    }

    //hint
    public Component getMessageNotExist(String string, Code code, String type) {
        return this.getSenderPlugin().append(Component.text(" " + type + " (", ExTextColor.WARNING))
                .append(Component.text(string, ExTextColor.VALUE))
                .append(Component.text(") doesn't exist (Code: ", ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    public Component getMessageAlreadyExist(String string, Code code, String type) {
        return this.getSenderPlugin().append(Component.text(" " + type + " (", ExTextColor.WARNING))
                .append(Component.text(string, ExTextColor.VALUE))
                .append(Component.text(") already exist (Code: ", ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    //format exception
    public Component getMessageFormatException(String string, Code code, String type, String example) {
        return this.getSenderPlugin().append(Component.text(string, ExTextColor.VALUE))
                .append(Component.text(" isn't a " + type + " (" + example + ") (Code: ", ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    public Component getMessageNumberOutOfBoundsException(String string, Code code, String lowerBound, String upperBound) {
        return this.getSenderPlugin().append(Component.text(string, ExTextColor.VALUE))
                .append(Component.text(" isn't between " + lowerBound + "and" + upperBound + " (Code: ", ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    //argument amount
    public Component getMessageTooManyArguments(Code code) {
        return this.getSenderPlugin().append(Component.text("Too many arguments (Code: ", ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    public Component getMessageTooFewArguments(Code code) {
        return this.getSenderPlugin().append(Component.text("Too few arguments (Code: ", ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    public Component getMessageTooFewManyArguments(Code code) {
        return this.getSenderPlugin().append(Component.text("Too few/many arguments (Code: ").color(ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    //permission
    public Component getMessageNoPermission(Code code) {
        return this.getSenderPlugin().append(Component.text("No permission (Code: ").color(ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    public Component getMessageNoPermissionsRank(Code code) {
        return this.getSenderPlugin().append(Component.text("No permission, your rank is too low (Code: ").color(ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
    }

    public Component getMessageOnlyPlayer(Code code) {
        return this.getSenderPlugin().append(Component.text("You must be a player (Code: ").color(ExTextColor.WARNING))
                .append(code.asComponent(ExTextColor.WARNING))
                .append(Component.text(")", ExTextColor.WARNING));
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
                .append(Component.text("/").color(ExTextColor.VALUE))
                .append(helpCommand)
                .append(Component.text(" for help").color(ExTextColor.PERSONAL));
    }

}
