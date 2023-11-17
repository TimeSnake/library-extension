/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.chat;

import de.timesnake.library.chat.ChatColor;
import de.timesnake.library.chat.ExTextColor;
import de.timesnake.library.extension.util.cmd.Argument;
import de.timesnake.library.extension.util.cmd.Arguments;
import de.timesnake.library.extension.util.cmd.Sender;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.flattener.ComponentFlattener;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Collection;
import java.util.Iterator;

public interface Chat {

  static @NotNull Component getSplitter() {
    return Component.text("» ", NamedTextColor.DARK_GRAY);
  }

  static Component getOtherSplitter() {
    return Component.text("» ", NamedTextColor.DARK_PURPLE);
  }

  static Component getLineSeparator() {
    return Component.text("----------", NamedTextColor.WHITE);
  }

  static String getLineTDSeparator() {
    return "----------";
  }

  static Component getLongLineSeparator() {
    return Component.text("---------------", NamedTextColor.WHITE);
  }

  static String getLongLineTDSeparator() {
    return "---------------";
  }

  static Component getDoubleLineSeparator() {
    return Component.text("==========", NamedTextColor.WHITE);
  }

  static Component getSenderPlugin(Plugin plugin) {
    return Component.text(plugin.getName(), NamedTextColor.DARK_AQUA).append(getSplitter());
  }

  @Deprecated
  static Component getMessageCode(String codeType, int code, Plugin plugin) {
    return Component.text("(Code: " + codeType + code + " " + plugin.getCode() + ")");
  }

  static Component getMessageCode(Code code, ExTextColor color) {
    return Component.text("(Code: " + code.asStringCode() + ")", color);
  }

  static Component getTimeComponent(Integer time) {
    if (time >= 3600) {
      return Component.text(
          time / 3600 + "h " + (time % 3600) / 60 + "min " + time % 60 + "s");
    }
    if (time >= 60) {
      return Component.text(time / 60 + "min " + time % 60 + "s");
    } else {
      return Component.text(time % 60 + "s");
    }
  }

  static Component listToComponent(Collection<?> list) {
    if (list.isEmpty()) {
      return Component.empty();
    }

    TextComponent.Builder builder = Component.text();

    Iterator<?> iterator = list.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (obj instanceof Component) {
        builder.append(((Component) obj));
      } else {
        builder.append(Component.text(obj.toString()));
      }

      if (iterator.hasNext()) {
        builder.append(Component.text(", "));
      }
    }

    return builder.build();
  }

  static Component listToComponent(Collection<?> list, TextColor valueColor,
                                   TextColor separaterColor) {
    if (list.isEmpty()) {
      return Component.empty();
    }

    TextComponent.Builder builder = Component.text();

    Iterator<?> iterator = list.iterator();
    while (iterator.hasNext()) {
      Object obj = iterator.next();
      if (obj instanceof Component) {
        builder.append(((Component) obj).color(valueColor));
      } else {
        builder.append(Component.text(obj.toString(), valueColor));
      }
      if (iterator.hasNext()) {
        builder.append(Component.text(", ", separaterColor));
      }
    }

    return builder.build();
  }

  static Arguments<Argument> createArguments(Sender sender, Argument... args) {
    return new Arguments<>(sender, args) {

      @Override
      public Argument createArgument(Sender sender, String arg) {
        return new Argument(sender, arg) {
        };
      }
    };
  }

  static float roundCoinAmount(float coins) {
    return ((int) (coins * 100)) / 100f;
  }

  /**
   * @param component to parse
   * @return Deprecated. Use {@code LegacyComponentSerializer.legacyAmpersand().serialize()}
   */
  @Deprecated
  static String parseComponentToString(Component component) {
    StringBuilder sb = new StringBuilder();
    ComponentFlattener.basic().flatten(component, sb::append);
    return sb.toString();
  }

  /**
   * @param string to parse
   * @return Deprecated. Use {@code LegacyComponentSerializer.legacyAmpersand().deserialize()}
   */
  @Deprecated
  static Component parseStringToComponent(String string) {
    TextComponent.Builder builder = Component.text();
    if (string == null) {
      return null;
    }
    for (String part : string.split("§")) {
      if (!part.isEmpty()) {
        if (part.length() == 1) {
          net.kyori.adventure.text.format.NamedTextColor color = parseChatColor(
              part.charAt(0));
          TextDecoration decoration = parseChatColorDecoration(part.charAt(0));
          if (color != null) {
            builder.color(color);
          } else if (decoration != null) {
            builder.decorate(decoration);
          } else {
            builder.append(Component.text("§" + part));
          }
        } else {
          part = part.substring(1);
          net.kyori.adventure.text.format.NamedTextColor color = parseChatColor(
              part.charAt(0));
          TextDecoration decoration = parseChatColorDecoration(part.charAt(0));
          if (color != null) {
            builder.append(Component.text(part, color));
          } else if (decoration != null) {
            builder.append(Component.text(part).decorate(decoration));
          } else {
            builder.append(Component.text("§" + part));
          }
        }
      }
    }
    return builder.build();
  }

  static net.kyori.adventure.text.format.NamedTextColor parseChatColor(char colorCode) {
    return switch (colorCode) {
      case '0' -> net.kyori.adventure.text.format.NamedTextColor.BLACK;
      case '1' -> net.kyori.adventure.text.format.NamedTextColor.DARK_BLUE;
      case '2' -> net.kyori.adventure.text.format.NamedTextColor.DARK_GREEN;
      case '3' -> net.kyori.adventure.text.format.NamedTextColor.DARK_AQUA;
      case '4' -> net.kyori.adventure.text.format.NamedTextColor.DARK_RED;
      case '5' -> net.kyori.adventure.text.format.NamedTextColor.DARK_PURPLE;
      case '6' -> net.kyori.adventure.text.format.NamedTextColor.GOLD;
      case '7' -> net.kyori.adventure.text.format.NamedTextColor.GRAY;
      case '8' -> net.kyori.adventure.text.format.NamedTextColor.DARK_GRAY;
      case '9' -> net.kyori.adventure.text.format.NamedTextColor.BLUE;
      case 'a' -> net.kyori.adventure.text.format.NamedTextColor.GREEN;
      case 'b' -> net.kyori.adventure.text.format.NamedTextColor.AQUA;
      case 'c' -> net.kyori.adventure.text.format.NamedTextColor.RED;
      case 'd' -> net.kyori.adventure.text.format.NamedTextColor.LIGHT_PURPLE;
      case 'e' -> net.kyori.adventure.text.format.NamedTextColor.YELLOW;
      case 'f' -> net.kyori.adventure.text.format.NamedTextColor.WHITE;
      default -> null;
    };
  }

  static TextDecoration parseChatColorDecoration(char actionCode) {
    return switch (actionCode) {
      case 'k' -> TextDecoration.OBFUSCATED;
      case 'l' -> TextDecoration.BOLD;
      case 'm' -> TextDecoration.STRIKETHROUGH;
      case 'n' -> TextDecoration.UNDERLINED;
      case 'o' -> TextDecoration.ITALIC;
      default -> null;
    };
  }


  @Deprecated
  static String getSplitterString() {
    return ChatColor.DARK_GRAY + "» " + ChatColor.RESET;
  }

  @Deprecated
  static String getOtherSplitterString() {
    return ChatColor.DARK_PURPLE + "» " + ChatColor.RESET;
  }

  @Deprecated
  static String getLineSeparatorString() {
    return ChatColor.WHITE + "----------";
  }

  @Deprecated
  static String getLongLineSeparatorString() {
    return ChatColor.WHITE + "---------------";
  }

  @Deprecated
  static String getDoubleLineSeparatorString() {
    return ChatColor.WHITE + "==========";
  }

  @Deprecated
  static String getSenderPluginString(Plugin plugin) {
    return ChatColor.DARK_AQUA + plugin.getName() + getSplitterString();
  }

  @Deprecated
  static String getMessageCodeString(String codeType, int code, Plugin plugin) {
    return "(Code: " + codeType + code + " " + plugin.getCode() + ")";
  }

  static String getTimeString(Integer timeInSec) {
    return getTimeString(Duration.ofSeconds(timeInSec));
  }

  static String getTimeString(Duration time) {
    StringBuilder sb = new StringBuilder();
    if (time.toHoursPart() > 0) {
      sb.append(time.toHoursPart()).append("h ");
    }
    if (time.toMinutesPart() > 0) {
      sb.append(time.toMinutesPart()).append("min ");
    }
    return sb.append(time.toSecondsPart()).append("s").toString();
  }

  @Deprecated
  static String listToString(Collection<?> list) {
    if (list.isEmpty()) {
      return "";
    }

    StringBuilder sb = new StringBuilder();

    for (Object obj : list) {
      sb.append(obj.toString()).append(", ");
    }

    sb.delete(sb.length() - 2, sb.length());

    return sb.toString();
  }

  static String getLocationString(int x, int y, int z) {
    return x + " " + y + " " + z;
  }

}
