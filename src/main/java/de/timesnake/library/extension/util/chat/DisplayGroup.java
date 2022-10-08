/*
 * library-extension.main
 * Copyright (C) 2022 timesnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package de.timesnake.library.extension.util.chat;

import de.timesnake.database.util.group.DbDisplayGroup;
import de.timesnake.library.basic.util.chat.ExTextColor;
import de.timesnake.library.extension.util.player.UserList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Objects;

public class DisplayGroup<User> implements Comparable<DisplayGroup<?>> {

    public static final int MAX_PREFIX_LENGTH = 2;

    protected final DbDisplayGroup database;

    protected final String name;
    protected final Integer rank;
    protected final boolean showAlways;
    protected final Collection<User> users;
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

        this.users = new UserList<>();
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
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

    @NotNull
    public Collection<User> getUsers() {
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

