/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import java.util.*;

public abstract class ExArguments<Argument extends de.timesnake.library.extension.util.cmd.Argument>
        extends ArgumentsBasis<Argument> implements Iterable<Argument> {

    private final Map<String, CmdOption> options = new HashMap<>();
    private final Set<Character> flags = new HashSet<>();

    public ExArguments(Sender sender, String[] args, boolean allowDuplicateOptions) {
        super(sender);

        this.args = new LinkedList<>();
        for (String arg : args) {
            if (arg.startsWith("--")) {
                arg = arg.replaceFirst("--", "");
                String[] argWithValue = arg.split("=");
                if (argWithValue.length != 2) {
                    throw new ArgumentParseException("Error while parsing option", arg.indexOf('='));
                }

                String name = argWithValue[0].toLowerCase();
                if (name.isEmpty()) {
                    throw new ArgumentParseException("Error while parsing option, no identifier found", arg.indexOf('='));
                }

                String value = argWithValue[1];
                if (value.isEmpty()) {
                    throw new ArgumentParseException("Error while parsing option, no value found", arg.indexOf('='));
                }

                CmdOption old = this.options.put(name, new CmdOption(sender, value));
                if (old != null && !allowDuplicateOptions) {
                    throw new DuplicateOptionException(name);
                }
            } else if (arg.startsWith("-")) {
                arg = arg.replaceFirst("-", "");
                String[] argWithValue = arg.split("=");

                // only flags
                if (argWithValue.length == 1) {
                    for (char flag : arg.toCharArray()) {
                        this.flags.add(flag);
                    }
                } else if (argWithValue.length == 2) {
                    if (argWithValue[0].isEmpty()) {
                        throw new ArgumentParseException("Error while parsing option, no identifier found", arg.indexOf('='));
                    }

                    String value = argWithValue[1];
                    if (value.isEmpty()) {
                        throw new ArgumentParseException("Error while parsing option, no value found", arg.indexOf('='));
                    }

                    char[] flags = argWithValue[0].toCharArray();
                    char optionName = flags[flags.length - 1];
                    for (int f = 0; f < flags.length - 1; f++) {
                        this.flags.add(flags[f]);
                    }

                    this.options.put(String.valueOf(optionName), new CmdOption(sender, value));
                } else {
                    throw new ArgumentParseException("Error while parsing options, to many values", arg.lastIndexOf('='));
                }
            } else {
                this.args.addLast(this.createArgument(sender, arg));
            }
        }
    }

    public abstract Argument createArgument(Sender sender, String arg);

    public Set<Character> getFlags() {
        return this.flags;
    }

    public boolean containsFlag(Character flag) {
        return this.flags.contains(flag);
    }

    public Map<String, CmdOption> getOptions() {
        return this.options;
    }

    public Optional<CmdOption> getOption(String name) {
        return Optional.ofNullable(this.options.get(name.toLowerCase()));
    }

    public CmdOption getOptionOrElse(String name, CmdOption fallback) {
        return Optional.ofNullable(this.options.get(name.toLowerCase())).orElse(fallback);
    }

    public Iterator<Argument> iterator() {
        return this.args.iterator();
    }

    public Sender getSender() {
        return this.sender;
    }

    public LinkedList<Argument> getAll() {
        return this.args;
    }

    public Argument get(int index) {
        if (this.args.size() > index) {
            return this.args.get(index);
        }
        return null;
    }

    public int getLength() {
        return this.args.size();
    }

    public int size() {
        return this.args.size();
    }

    public int length() {
        return this.args.size();
    }

    public String getString(int index) {
        if (this.args.size() > index) {
            return this.args.get(index).getString();
        }
        return null;
    }

    public Argument getFirst() {
        if (!this.args.isEmpty()) {
            return this.args.get(0);
        }
        return null;
    }

    public Argument getLast() {
        if (!this.args.isEmpty()) {
            return this.args.get(this.args.size() - 1);
        }
        return null;
    }

}
