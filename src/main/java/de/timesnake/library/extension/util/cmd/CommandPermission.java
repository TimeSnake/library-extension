/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.library.extension.util.cmd;

public class CommandPermission {

    private final int code;
    private final String permission;

    public CommandPermission(int code, String permission) {
        this.code = code;
        this.permission = permission;
    }

    public int getCode() {
        return code;
    }

    public String getPermission() {
        return permission;
    }
}
