/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;


import de.timesnake.database.util.Database;
import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.library.chat.ExTextColor;
import de.timesnake.library.chat.TimeDownParser;
import de.timesnake.library.extension.util.chat.Chat;
import de.timesnake.library.extension.util.chat.Code;
import de.timesnake.library.extension.util.chat.Code.Builder;
import de.timesnake.library.extension.util.chat.Code.Type;
import de.timesnake.library.extension.util.chat.Plugin;
import de.timesnake.library.extension.util.player.User;
import java.util.UUID;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public abstract class Sender {

  public static final Code PLAYER_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("Player not exists")
      .build();
  public static final Code WORLD_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("World not exists")
      .build();
  public static final Code WORLD_ALREADY_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("World already exists")
      .build();
  public static final Code GAMEMODE_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("gamemode not exists")
      .build();
  public static final Code WEATHER_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("weather not exists")
      .build();
  public static final Code KILL_ALL_TYPE_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("kill all type not exists")
      .build();
  public static final Code SERVER_NAME_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("server name not exists")
      .build();
  public static final Code SERVER_STATUS_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("server status not exists")
      .build();
  public static final Code TICKET_STATUS_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("ticket status not exists")
      .build();
  public static final Code PERMISSION_STATUS_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("permission status not exists")
      .build();
  public static final Code GAME_NOT_EXISTS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("Game not exists")
      .build();
  public static final Code INTEGER_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not an integer")
      .build();
  public static final Code FLOAT_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a float")
      .build();
  public static final Code DOUBLE_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a double")
      .build();
  public static final Code CHAT_COLOR_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a chat color")
      .build();
  public static final Code COLOR_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a color")
      .build();
  public static final Code HEX_COLOR_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a hex color")
      .build();
  public static final Code DATE_TIME_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a date time")
      .build();
  public static final Code TIME_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a time")
      .build();
  public static final Code HOUR_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a hour")
      .build();
  public static final Code MINUTE_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a minute")
      .build();
  public static final Code SECOND_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a second")
      .build();
  public static final Code UUID_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP).setDescription("not an uuid")
      .build();
  public static final Code ITEM_NAME_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not an item name")
      .build();
  public static final Code BOOLEAN_NON = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("not a boolean")
      .build();
  public static final Code TOO_MANY_ARGS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("Too many arguments")
      .build();
  public static final Code TOO_FEW_ARGS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("Too few arguments")
      .build();
  public static final Code TOO_FEW_MANY_ARGS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("Too few/many arguments")
      .build();
  public static final Code NO_PERMISSION_RANK = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.PERMISSION)
      .setDescription("Rank too low")
      .build();
  public static final Code ONLY_PLAYER = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("Only player")
      .build();
  public static final Code NUMBER_OUT_OF_BOUNDS = new Builder()
      .setPlugin(Plugin.SYSTEM)
      .setType(Type.HELP)
      .setDescription("number out of bounds")
      .build();

  protected final CommandSender cmdSender;
  protected final Plugin plugin;
  protected final TimeDownParser parser;

  public Sender(CommandSender cmdSender, Plugin plugin, TimeDownParser parser) {
    this.cmdSender = cmdSender;
    this.plugin = plugin;
    this.parser = parser;
  }

  @Override
  public int hashCode() {
    return this.cmdSender.getName().hashCode();
  }

  public boolean hasPermission(Code code) {
    return this.hasPermission(code, true);
  }

  public boolean hasPermission(Code code, boolean sendMessage) {
    if (code == null) {
      return false;
    }

    if (cmdSender.hasPermission(code.getPermission())) {
      return true;
    }
    if (sendMessage) {
      this.sendMessageNoPermission(code);
    }
    return false;
  }


  public void hasPermissionElseExit(Code code) {
    this.hasPermissionElseExit(code, true);
  }

  public void hasPermissionElseExit(Code code, boolean sendMessage) {
    if (!this.hasPermission(code, sendMessage)) {
      throw new CommandExitException();
    }
  }

  @Deprecated
  public boolean hasPermission(String permission, Code code) {
    if (cmdSender.hasPermission(permission)) {
      return true;
    }
    this.sendMessageNoPermission(code);
    return false;
  }

  @Deprecated
  public boolean hasPermission(String permission, Code code, boolean sendMessage) {
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
    } else if (this.getDbUser().getPermGroup().getRank() < Database.getUsers().getUser(uuid)
        .getPermGroup().getRank()) {
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
    } else if (this.getDbUser().getPermGroup().getRank() <= this.getDbUser().getPermGroup()
        .getRank()) {
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
    this.sendConsoleMessage("[" + plugin.getName() + "][Cmd] " + this.cmdSender.getName()
        + ": command execution " +
        "cancelled (" + code + this.plugin.getCode() + ")");
  }

  public void sendSystemMessage(Code code) {
    this.sendConsoleMessage("[" + plugin.getName() + "][Cmd] " + this.cmdSender.getName()
        + ": command execution " +
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

  public void sendTDMessage(String message) {
    this.cmdSender.sendMessage(this.parser.parse2Component(message));
  }

  public void sendMessage(Component message) {
    this.cmdSender.sendMessage(message);
  }

  public void sendPluginTDMessage(String message) {
    this.cmdSender.sendMessage(Chat.getSenderPlugin(plugin)
        .append(this.parser.parse2Component(message)));
  }

  public void sendPluginMessage(Component message) {
    this.cmdSender.sendMessage(Chat.getSenderPlugin(plugin).append(message));
  }

  public void assertElseExitWith(boolean condition, Consumer<Sender> consumer) {
    if (condition) {
      return;
    }

    consumer.accept(this);
    throw new CommandExitException();
  }

  public void assertElseExit(boolean condition) {
    if (condition) {
      return;
    }
    throw new CommandExitException();
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
  public void sendTDMessageCommandHelp(String text, String command) {
    this.sendMessageCommandHelp(this.parser.parse2Component(text)
            .color(ExTextColor.PERSONAL),
        this.parser.parse2Component(command)
            .color(ExTextColor.VALUE));
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
    cmdSender.sendMessage(
        this.getMessageNotExist(string, KILL_ALL_TYPE_NOT_EXISTS, "KillAll-type"));
    this.sendSystemMessage(KILL_ALL_TYPE_NOT_EXISTS, " [Args]");
  }

  public void sendMessageServerNameNotExist(String string) {
    cmdSender.sendMessage(
        this.getMessageNotExist(string, SERVER_NAME_NOT_EXISTS, "Server-name"));
    this.sendSystemMessage(SERVER_NAME_NOT_EXISTS, " [Args]");
  }

  public void sendMessageServerStatusNotExist(String string) {
    cmdSender.sendMessage(
        this.getMessageNotExist(string, SERVER_STATUS_NOT_EXISTS, "Server-status"));
    this.sendSystemMessage(SERVER_STATUS_NOT_EXISTS, " [Args]");
  }

  public void sendMessagePermissionStatusNotExist(String string) {
    cmdSender.sendMessage(
        this.getMessageNotExist(string, PERMISSION_STATUS_NOT_EXISTS, "Permission-status"));
    this.sendSystemMessage(PERMISSION_STATUS_NOT_EXISTS, " [Args]");
  }

  public void sendMessageTicketStatusNotExist(String string) {
    cmdSender.sendMessage(
        this.getMessageNotExist(string, TICKET_STATUS_NOT_EXISTS, "Ticket-status"));
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
    cmdSender.sendMessage(
        this.getMessageFormatException(string, INTEGER_NON, "int", "int: 12"));
    this.sendSystemMessage(INTEGER_NON, " [ArgsFormat]");
  }

  public void sendMessageNoFloat(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, FLOAT_NON, "float", "float: 0.123"));
    this.sendSystemMessage(FLOAT_NON, " [ArgsFormat]");
  }

  public void sendMessageNoDouble(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, DOUBLE_NON, "double", "double: 0.123456"));
    this.sendSystemMessage(DOUBLE_NON, " [ArgsFormat]");
  }

  public void sendMessageNumberOutOfBounds(String string, String lowerBound, String upperBound) {
    cmdSender.sendMessage(
        this.getMessageNumberOutOfBoundsException(string, NUMBER_OUT_OF_BOUNDS, lowerBound,
            upperBound));
    this.sendSystemMessage(NUMBER_OUT_OF_BOUNDS, " [ArgsBound]");
  }

  public void sendMessageNoChatColor(String string) {
    cmdSender.sendMessage(this.getMessageFormatException(string, CHAT_COLOR_NON, "chatcolor",
        "chatcolor: DARK_BLUE"));
    this.sendSystemMessage(CHAT_COLOR_NON, " [ArgsFormat]");
  }

  public void sendMessageNoColor(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, COLOR_NON, "color", "color: RED"));
    this.sendSystemMessage(COLOR_NON, " [ArgsFormat]");
  }

  public void sendMessageNoHexColor(String string) {
    cmdSender.sendMessage(this.getMessageFormatException(string, HEX_COLOR_NON, "hex-color",
        "hex-color: 123FA1"));
    this.sendSystemMessage(HEX_COLOR_NON, " [ArgsFormat]");
  }

  public void sendMessageNoDateTime(String string) {
    cmdSender.sendMessage(this.getMessageFormatException(string, DATE_TIME_NON, "date",
        "datetime: 1hour;2day;1month;" +
            "1year"));
    this.sendSystemMessage(DATE_TIME_NON, " [ArgsFormat]");
  }

  public void sendMessageNoTime(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, TIME_NON, "time", "time: 12:34"));
    this.sendSystemMessage(TIME_NON, " [ArgsFormat]");
  }

  public void sendMessageNoHour(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, HOUR_NON, "timehour", "hours: 0-23"));
    this.sendSystemMessage(HOUR_NON, " [ArgsFormat]");
  }

  public void sendMessageNoMinute(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, MINUTE_NON, "timeminute", "minutes: 0-59"));
    this.sendSystemMessage(MINUTE_NON, " [ArgsFormat]");
  }

  public void sendMessageNoSecond(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, SECOND_NON, "timesecond", "seconds: 0-59"));
    this.sendSystemMessage(SECOND_NON, " [ArgsFormat]");
  }

  public void sendMessageNoUuid(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, UUID_NON, "uuid", "uuid from player"));
    this.sendSystemMessage(UUID_NON, " [ArgsFormat]");
  }

  public void sendMessageNoBoolean(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, BOOLEAN_NON, "boolean", "true or false"));
    this.sendSystemMessage(BOOLEAN_NON, " [ArgsFormat]");
  }

  public void sendMessageNoItemName(String string) {
    cmdSender.sendMessage(
        this.getMessageFormatException(string, ITEM_NAME_NON, "item-name", "dirt"));
    this.sendSystemMessage(ITEM_NAME_NON, " [ArgsFormat]");
  }

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

  public void sendMessageNoPermission(Code code) {
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
    this.sendMessageUseHelp(
        LegacyComponentSerializer.legacyAmpersand().deserialize(helpCommand));
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

  public Component getMessageFormatException(String string, Code code, String type,
      String example) {
    return this.getSenderPlugin().append(Component.text(string, ExTextColor.VALUE))
        .append(Component.text(" isn't a " + type + " (" + example + ") (Code: ",
            ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageNumberOutOfBoundsException(String string, Code code,
      String lowerBound, String upperBound) {
    return this.getSenderPlugin().append(Component.text(string, ExTextColor.VALUE))
        .append(Component.text(
            " isn't between " + lowerBound + "and" + upperBound + " (Code: ",
            ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageTooManyArguments(Code code) {
    return this.getSenderPlugin()
        .append(Component.text("Too many arguments (Code: ", ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageTooFewArguments(Code code) {
    return this.getSenderPlugin()
        .append(Component.text("Too few arguments (Code: ", ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageTooFewManyArguments(Code code) {
    return this.getSenderPlugin()
        .append(Component.text("Too few/many arguments (Code: ").color(ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageNoPermission(Code code) {
    return this.getSenderPlugin()
        .append(Component.text("No permission (Code: ").color(ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageNoPermissionsRank(Code code) {
    return this.getSenderPlugin()
        .append(Component.text("No permission, your rank is too low (Code: ")
            .color(ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageOnlyPlayer(Code code) {
    return this.getSenderPlugin()
        .append(Component.text("You must be a player (Code: ").color(ExTextColor.WARNING))
        .append(code.asComponent(ExTextColor.WARNING))
        .append(Component.text(")", ExTextColor.WARNING));
  }

  public Component getMessageNoSpam() {
    return this.getSenderPlugin()
        .append(Component.text("This message is similar to your previous.")
            .color(ExTextColor.WARNING));
  }

  public Component getMessageMuted() {
    return this.getSenderPlugin().append(Component.text("You are muted! For questions use ")
            .color(ExTextColor.WARNING))
        .append(Component.text("/support").color(ExTextColor.VALUE));
  }

  public Component getMessageUnknownCommand() {
    return this.getSenderPlugin()
        .append(Component.text("Unknown command").color(ExTextColor.WARNING));
  }

  public Component getMessageUseHelp(Component helpCommand) {
    return this.getSenderPlugin().append(Component.text("Use ").color(ExTextColor.PERSONAL))
        .append(Component.text("/").color(ExTextColor.VALUE))
        .append(helpCommand)
        .append(Component.text(" for help").color(ExTextColor.PERSONAL));
  }

}
