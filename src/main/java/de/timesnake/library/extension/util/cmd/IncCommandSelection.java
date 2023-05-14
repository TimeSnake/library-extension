/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import de.timesnake.library.basic.util.BuilderNotFullyInstantiatedException;
import de.timesnake.library.chat.ExTextColor;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;

public class IncCommandSelection {

    private final IncCommandOption<?> option;
    private final String cmd;
    private final String title;
    private final LinkedList<String> values;

    public IncCommandSelection(Builder builder) {
        this.cmd = builder.cmd;
        this.option = builder.option;
        this.title = builder.title;
        this.values = builder.values;
    }

    public IncCommandOption<?> getOption() {
        return option;
    }

    public String getCmd() {
        return cmd;
    }

    public String getTitle() {
        return title;
    }

    public LinkedList<String> getValues() {
        return values;
    }

    public Component toMessage(ExTextColor color) {
        Component msg = Component.text(this.title + ":", color);
        for (String value : this.values) {
            msg = msg.append(Component.text(" " + value + " ", ExTextColor.VALUE)
                    .clickEvent(ClickEvent.runCommand(
                            "/" + this.cmd + " " + this.option.getName() + " " + value)));
        }
        return msg;
    }

    public static class Builder {

        private String title;
        private String cmd;
        private IncCommandOption<?> option;
        private final LinkedList<String> values = new LinkedList<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder command(String cmd) {
            this.cmd = cmd;
            return this;
        }

        public Builder option(IncCommandOption<?> option) {
            this.option = option;
            if (option.title != null) {
                this.title = option.title;
            }
            return this;
        }

        public Builder addValue(String value) {
            this.values.add(value);
            return this;
        }

        public Builder addValues(String... values) {
            for (String value : values) {
                this.addValue(value);
            }
            return this;
        }

        public Builder addValues(Collection<String> values) {
            for (String value : values) {
                this.addValue(value);
            }
            return this;
        }

        public <V> Builder addValues(Collection<V> values, Function<V, String> extractor) {
            return this.addValues(values.stream().map(extractor).toList());
        }

        public <V> Builder addValues(V[] values, Function<V, String> extractor) {
            return this.addValues(Arrays.stream(values).map(extractor).toList());
        }

        public void checkBuild() {
            if (this.option == null) {
                throw new BuilderNotFullyInstantiatedException("option is null");
            }

            if (this.cmd == null) {
                throw new BuilderNotFullyInstantiatedException("cmd is null");
            }

            if (this.title == null) {
                throw new BuilderNotFullyInstantiatedException("title is null");
            }

            if (this.values.size() == 0) {
                throw new BuilderNotFullyInstantiatedException("values is empty");
            }
        }

        public IncCommandSelection build() {
            this.checkBuild();
            return new IncCommandSelection(this);
        }

        public Component buildToMessage(ExTextColor color) {
            return this.build().toMessage(color);
        }
    }
}
