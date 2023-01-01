/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

public class Argument extends ArgumentBasis {

    public static final String SPACE = "\\";

    public Argument(Sender sender, String string) {
        super(sender, string);
    }

    protected void addString(String toAdd) {
        this.string = this.string + toAdd;
    }

    //type check

    public String toSpacedString() {
        return this.string.replace(SPACE, " ");
    }

}
