/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.library.extension.util.permission;

import de.timesnake.database.util.group.DbPermGroup;
import de.timesnake.library.extension.util.player.UserList;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class PermGroup<User> implements Comparable<PermGroup<?>> {

    protected final DbPermGroup database;

    protected final String name;
    protected final Integer rank;
    protected final Set<ExPermission> permissions = new HashSet<>();
    protected final Collection<User> users;

    public PermGroup(DbPermGroup database) {
        this.database = database;

        DbPermGroup dbLocal = this.database.toLocal();

        this.rank = dbLocal.getRank();
        this.name = dbLocal.getName();
        this.users = new UserList<>();
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public Integer getRank() {
        return rank;
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

    @NotNull
    public Collection<? extends ExPermission> getPermissions() {
        return new HashSet<>(this.permissions);
    }

    public abstract void updatePermissions();

    @Override
    public int compareTo(PermGroup<?> o) {
        return Integer.compare(this.getRank(), o.getRank());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermGroup<?> permGroup)) return false;
        return Objects.equals(name, permGroup.name);
    }
}
