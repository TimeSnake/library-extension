/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class IncCommandContext {

    protected LinkedHashMap<IncCommandOption<?>, Object> options = new LinkedHashMap<>();

    public <V> void addOption(IncCommandOption<V> option, V value) {
        if (value instanceof Collection<?>) {
            ((Collection) this.options.computeIfAbsent(option, o -> new LinkedList<>()))
                    .addAll(((Collection<?>) value));
        } else {
            this.options.put(option, value);
        }

    }

    public LinkedHashMap<IncCommandOption<?>, Object> getOptions() {
        return options;
    }

    public <V> V getOption(IncCommandOption<V> option) {
        return (V) this.options.get(option);
    }
}
