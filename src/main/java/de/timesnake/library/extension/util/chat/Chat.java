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
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
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

  static String getLocationString(int x, int y, int z) {
    return x + " " + y + " " + z;
  }

}
