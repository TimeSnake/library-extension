package de.timesnake.library.extension.util.chat;

import java.util.HashMap;
import java.util.Map;

public class Plugin {

    public static final Plugin SYSTEM = new Plugin("System", "LES");
    public static final Plugin CONSOLE = new Plugin("Console", "LEC");
    public static final Plugin BUKKIT = new Plugin("Bukkit", "LEB");
    public static final Plugin PROXY = new Plugin("Proxy", "LEP");
    public static final Plugin INFO = new Plugin("Info", "LEI");
    public static final Plugin NETWORK = new Plugin("Network", "LEN");
    public static final Plugin TIME_COINS = new Plugin("Coins", "LET");
    public static final Plugin PRIVATE_MESSAGES = new Plugin("Msg", "LEM");
    public static final Plugin MAILS = new Plugin("Mails", "LEN");

    private final String name;
    private final String code;

    private final Map<String, Code> codeByString = new HashMap<>();
    private int shortCodeCounter = 1;

    protected Plugin(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
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
