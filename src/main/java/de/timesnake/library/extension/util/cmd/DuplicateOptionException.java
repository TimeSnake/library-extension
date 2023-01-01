/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

public class DuplicateOptionException extends RuntimeException {

    private final String name;

    public DuplicateOptionException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
