package de.timesnake.library.extension.util.chat;

import de.timesnake.database.util.group.DbDisplayGroup;
import de.timesnake.library.basic.util.Nullable;
import de.timesnake.library.basic.util.chat.ExTextColor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DisplayGroup<User> implements Comparable<DisplayGroup<?>> {

    public static final int MAX_PREFIX_LENGTH = 2;

    protected final DbDisplayGroup database;

    protected final String name;
    protected final Integer rank;
    protected final Set<User> users = new HashSet<>();
    protected final boolean showAlways;
    protected String prefix;
    protected ExTextColor prefixColor;

    public DisplayGroup(DbDisplayGroup database) {
        this.database = database;
        DbDisplayGroup dbLocal = database.toLocal();

        this.name = dbLocal.getName();
        this.rank = dbLocal.getRank();
        this.prefix = dbLocal.getPrefix();
        this.showAlways = dbLocal.showAlways();

        this.prefixColor = database.getChatColor();
        if (this.prefixColor == null) {
            this.prefixColor = ExTextColor.WHITE;
        }
    }

    public String getName() {
        return this.name;
    }

    public Integer getRank() {
        return this.rank;
    }

    @Nullable
    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        this.database.setPrefix(prefix);
    }

    @Nullable
    public ExTextColor getPrefixColor() {
        return this.prefixColor;
    }

    public void setPrefixColor(ExTextColor chatColor) {
        if (chatColor == null) {
            chatColor = ExTextColor.WHITE;
        }
        this.prefixColor = chatColor;
        this.database.setChatColor(chatColor);
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

    @Override
    public int compareTo(DisplayGroup<?> o) {
        return Integer.compare(this.getRank(), o.getRank());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DisplayGroup<?> displayGroup)) return false;
        return Objects.equals(name, displayGroup.name);
    }
}

