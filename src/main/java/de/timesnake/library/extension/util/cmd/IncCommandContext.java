/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import java.util.LinkedHashMap;

public class IncCommandContext {

    protected LinkedHashMap<IncCommandOption<?>, Object> options = new LinkedHashMap<>();

    public <V> void addOption(IncCommandOption<V> option, V value) {
        this.options.put(option, value);
    }

    public LinkedHashMap<IncCommandOption<?>, Object> getOptions() {
        return options;
    }

    public <V> V getOption(IncCommandOption<V> option) {
        return (V) this.options.get(option);
    }
}
