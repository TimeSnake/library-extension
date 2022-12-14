/*
 * Copyright (C) 2022 timesnake
 */

package de.timesnake.library.extension.util.player;

import java.util.Collection;
import java.util.LinkedList;

public class UserList<User> extends LinkedList<User> {

    public static final LinkedList<UserList<?>> LISTS = new LinkedList<>();

    public UserList() {
        LISTS.add(this);
    }

    public UserList(Collection<? extends User> collection) {
        super(collection);
        LISTS.add(this);
    }
}
