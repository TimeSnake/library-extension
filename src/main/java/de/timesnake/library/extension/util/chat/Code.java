/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.library.extension.util.chat;

import de.timesnake.library.basic.util.chat.ExTextColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class Code {

    private final Plugin plugin;
    private final String typeSymbol;
    private final String name;
    private final int code;

    public Code(Plugin plugin, String typeSymbol, String name, int code) {
        this.plugin = plugin;
        this.typeSymbol = typeSymbol;
        this.name = name;
        this.code = code;
    }

    public Component asComponent(ExTextColor color) {
        return Component.text(this.asStringCode(), color)
                .hoverEvent(HoverEvent.hoverEvent(HoverEvent.Action.SHOW_TEXT, Component.text("Click to copy")))
                .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, this.asStringCode()));
    }

    public String asStringCode() {
        return this.typeSymbol + " " + plugin.getCode() + "#" + this.name + this.code;
    }

    public static class Help extends Code {

        private final String description;

        public Help(Plugin plugin, String name, int code, String description) {
            super(plugin, "H", name, code);
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static class Permission extends Code {

        private String command;
        private String permission;

        public Permission(Plugin plugin, String name, int code, String command, String permission) {
            this(plugin, name, code, permission);
            this.command = command;
        }

        public Permission(Plugin plugin, String name, int code, String permission) {
            super(plugin, "H", name, code);
            this.permission = permission;
        }

        public Permission(Plugin plugin, String name, int code) {
            super(plugin, "H", name, code);
        }

        public String getCommand() {
            return command;
        }

        public String getPermission() {
            return permission;
        }
    }

    public static class Error extends Code {

        private final String description;

        public Error(Plugin plugin, String name, int code, String description) {
            super(plugin, "H", name, code);
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public static class Builder {


    }
}
