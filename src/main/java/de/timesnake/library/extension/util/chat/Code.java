/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.chat;

import de.timesnake.library.basic.util.BuilderBasis;
import de.timesnake.library.basic.util.BuilderNotFullyInstantiatedException;
import de.timesnake.library.chat.ExTextColor;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class Code {

  private static int codeCounter = 1001;
  private static final Map<Integer, Code> CODE_BY_ID = new HashMap<>();

  public static Map<Integer, Code> getCodeById() {
    return CODE_BY_ID;
  }

  private final Plugin plugin;
  private final Type type;
  private final int code;
  private final String command;
  private final String permission;
  private final String description;
  private final Reference reference;

  public Code(Builder builder) {
    this.plugin = builder.plugin;
    this.type = builder.type;
    this.code = codeCounter++;
    this.command = builder.command;
    this.permission = builder.permission;
    this.description = builder.description;
    this.reference = builder.reference;

    CODE_BY_ID.put(this.code, this);
  }

  public Component asComponent(ExTextColor color) {
    return Component.text(this.asStringCode(), color)
        .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT,
            Component.text("Click to copy")))
        .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD,
            this.asStringCode()));
  }

  public String asStringCode() {
    return "#" + this.code;
  }

  public Plugin getPlugin() {
    return plugin;
  }

  public Type getType() {
    return type;
  }

  public int getCode() {
    return code;
  }

  public String getCommand() {
    return command;
  }

  public String getDescription() {
    return description;
  }

  public String getPermission() {
    return permission;
  }

  public Reference getReference() {
    return reference;
  }

  public enum Type {
    HELP("H"), PERMISSION("P"), ERROR("E");

    private final String symbol;

    Type(String symbol) {
      this.symbol = symbol;
    }

    public String getSymbol() {
      return symbol;
    }
  }

  public static class Builder implements BuilderBasis {

    private Plugin plugin;
    private Type type;
    private String command;
    private String permission;
    private String description;
    private Reference reference;

    public Builder setPlugin(Plugin plugin) {
      this.plugin = plugin;
      return this;
    }

    public Builder setType(Type type) {
      this.type = type;
      return this;
    }

    public Builder setCommand(String command) {
      this.command = command;
      return this;
    }

    public Builder setPermission(String permission) {
      this.permission = permission;
      return this;
    }

    public Builder setDescription(String description) {
      this.description = description;
      return this;
    }

    public Builder setReference(Reference reference) {
      this.reference = reference;
      return this;
    }

    @Override
    public void checkBuild() {
      if (this.plugin == null) {
        throw new BuilderNotFullyInstantiatedException("plugin is null");
      }

      if (this.type == null) {
        throw new BuilderNotFullyInstantiatedException("type is null");
      }
    }

    public Code build() {
      this.checkBuild();
      return new Code(this);
    }
  }

  public static class Reference {

    private String name;

    public Reference(Class<?> clazz) {
      this.name = clazz.getName();
    }

    public Reference(Class<?> clazz, int line) {
      this(clazz);
      this.name += ":" + line;
    }

    public Reference(Object obj) {
      this(obj.getClass());
    }

    public Reference(Object obj, int line) {
      this(obj.getClass(), line);
    }

    public Reference(String string) {
      this.name = string;
    }

    public String getName() {
      return this.name;
    }

  }
}
