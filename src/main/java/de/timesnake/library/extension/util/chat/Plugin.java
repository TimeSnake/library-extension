/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.chat;

import de.timesnake.library.basic.util.LogHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plugin {

    public static final Plugin SYSTEM = new Plugin("System", "LES", LogHelper.getLogger("System", Level.INFO));
    public static final Plugin CONSOLE = new Plugin("Console", "LEC", LogHelper.getLogger("Console", Level.INFO));
    public static final Plugin BUKKIT = new Plugin("Bukkit", "LEB", LogHelper.getLogger("Bukkit", Level.INFO));
    public static final Plugin PROXY = new Plugin("Proxy", "LEP", LogHelper.getLogger("Proxy", Level.INFO));
    public static final Plugin INFO = new Plugin("Info", "LEI", LogHelper.getLogger("Info", Level.INFO));
    public static final Plugin NETWORK = new Plugin("Network", "LEN", LogHelper.getLogger("Network", Level.INFO));
    public static final Plugin TIME_COINS = new Plugin("Coins", "LET", LogHelper.getLogger("Coins", Level.INFO));
    public static final Plugin PRIVATE_MESSAGES = new Plugin("Msg", "LEM", LogHelper.getLogger("Msg", Level.INFO));
    public static final Plugin MAILS = new Plugin("Mails", "LEN", LogHelper.getLogger("Mails", Level.INFO));
    public static final Plugin CHATS = new Plugin("Chats", "CHT", LogHelper.getLogger("Chats", Level.INFO));

    // only for console
    public static final Plugin SCOREBOARD = new Plugin("Scoreboard", "SCB", LogHelper.getLogger("Scoreboard"));
    public static final Plugin GROUPS = new Plugin("Groups", "GRP", LogHelper.getLogger("Groups"));
    public static final Plugin PACKETS = new Plugin("Packets", "PKT", LogHelper.getLogger("Packets"));
    public static final Plugin WORLDS = new Plugin("Worlds", "WLD", LogHelper.getLogger("Worlds"));
    public static final Plugin PERMISSIONS = new Plugin("Permissions", "PRM", LogHelper.getLogger("Permissions"));


    private final String name;
    private final String code;
    private final Logger logger;

    private final Map<String, Code> codeByString = new HashMap<>();
    private int shortCodeCounter = 1;

    protected Plugin(String name, String code, Logger logger) {
        this.name = name;
        this.code = code;
        this.logger = logger;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Logger getLogger() {
        return logger;
    }

    /**
     * @param name       should be 2 characters long
     * @param desciption
     * @return
     */
    public Code.Help createHelpCode(String name, String desciption) {
        Code.Help code = new Code.Help(this, name, this.createShortCode(), desciption);
        this.codeByString.put(code.asStringCode(), code);
        return code;
    }

    /**
     * @param name       should be 2 characters long
     * @param permission
     * @return
     */
    public Code.Permission createPermssionCode(String name, String permission) {
        Code.Permission code = new Code.Permission(this, name, this.createShortCode(), permission);
        this.codeByString.put(code.asStringCode(), code);
        return code;
    }

    /**
     * @param name       should be 2 characters long
     * @param permission
     * @return
     */
    public Code.Permission createPermssionCode(String name, String permission, String description) {
        Code.Permission code = new Code.Permission(this, name, this.createShortCode(), permission, description);
        this.codeByString.put(code.asStringCode(), code);
        return code;
    }

    /**
     * @param name        should be 2 characters long
     * @param description
     * @return
     */
    public Code.Error createErrorCode(String name, String description) {
        Code.Error code = new Code.Error(this, name, this.createShortCode(), description);
        this.codeByString.put(code.asStringCode(), code);
        return code;
    }

    public Code addCode(Code code) {
        this.codeByString.put(code.asStringCode(), code);
        return code;
    }

    private int createShortCode() {
        return this.shortCodeCounter++;
    }

    public Map<String, Code> getCodeByString() {
        return codeByString;
    }
}
