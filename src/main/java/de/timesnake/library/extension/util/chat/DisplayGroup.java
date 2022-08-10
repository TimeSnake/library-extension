package de.timesnake.library.extension.util.chat;

import de.timesnake.database.util.group.DbDisplayGroup;

import java.util.HashSet;
import java.util.Set;

public abstract class DisplayGroup<ChatColor, User> {

    public static final int MAX_PREFIX_LENGTH = 2;

    protected final DbDisplayGroup database;

    protected final String name;
    protected final Integer rank;
    protected final Set<User> users = new HashSet<>();
    protected final boolean showAlways;
    protected String prefix;
    protected ChatColor prefixColor;

    public DisplayGroup(DbDisplayGroup database) {
        this.database = database;
        DbDisplayGroup dbLocal = database.toLocal();

        this.name = dbLocal.getName();
        this.rank = dbLocal.getRank();
        this.prefix = dbLocal.getPrefix();
        this.showAlways = dbLocal.showAlways();

        this.prefixColor = this.loadPrefixColor(database.getChatColorName());
    }

    public abstract ChatColor loadPrefixColor(String chatColorName);

    public String getName() {
        return this.name;
    }

    public Integer getRank() {
        return this.rank;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.database.setPrefix(prefix);
    }

    public ChatColor getPrefixColor() {
        return this.prefixColor;
    }

    public void setPrefixColor(ChatColor chatColor) {
        this.prefixColor = chatColor;
        this.database.setChatColorName(chatColor.toString());
    }

    public boolean isShowAlways() {
        return showAlways;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public Set<User> getUsers() {
        return this.users;
    }

}

