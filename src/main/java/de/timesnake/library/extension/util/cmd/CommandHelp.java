package de.timesnake.library.extension.util.cmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHelp {

    public static final CommandHelp NO_CMD_HELP = new CommandHelp("", "This command has no help text", "", List.of());

    private String cmd;
    private List<String> aliases = new ArrayList<>();

    private String description;
    private String syntax;

    private Map<String, CommandHelp> subCmdHelps = new HashMap<>();

    public CommandHelp(String cmd, String description, String syntax, List<String> aliases, CommandHelp... subCmdHelps) {
        this.cmd = cmd;
        this.description = description;
        this.syntax = syntax;
        this.aliases = aliases;
        for (CommandHelp cmdHelp : subCmdHelps) {
            this.subCmdHelps.put(cmdHelp.getCmd(), cmdHelp);
        }
    }

    public CommandHelp(String cmd) {
        this.cmd = cmd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public void setCmdHelps(CommandHelp... subCmdHelps) {
        for (CommandHelp cmdHelp : subCmdHelps) {
            this.subCmdHelps.put(cmdHelp.getCmd(), cmdHelp);
        }
    }

    public String getCmd() {
        return cmd;
    }

    public List<String> getAlias() {
        return aliases;
    }

    public void setAlias(List<String> aliases) {
        this.aliases = aliases;
    }

    public void setAlias(String... aliases) {
        this.aliases = List.of(aliases);
    }

    public Map<String, CommandHelp> getSubCmdHelps() {
        return subCmdHelps;
    }

    public void setSubCmdHelps(Map<String, CommandHelp> subCmdHelps) {
        this.subCmdHelps = subCmdHelps;
    }

    public CommandHelp getSubCmdHelp(String subCmd) {
        CommandHelp help = this.subCmdHelps.get(subCmd);
        if (help == null) {
            for (CommandHelp subHelp : this.subCmdHelps.values()) {
                if (subHelp.getAlias().contains(subCmd)) {
                    return subHelp;
                }
            }
        } else {
            return help;
        }
        return NO_CMD_HELP;
    }


}
