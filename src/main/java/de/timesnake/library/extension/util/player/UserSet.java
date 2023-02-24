/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.player;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Consumer;

public class UserSet<User> extends HashSet<User> {

    public static final HashSet<UserSet<?>> LISTS = new HashSet<>();

    private Consumer<User> onAdd;
    private Consumer<User> onRemove;

    public UserSet() {
        LISTS.add(this);
    }

    public UserSet(Collection<? extends User> collection) {
        super(collection);
        LISTS.add(this);
    }

    public void onAdd(Consumer<User> consumer) {
        this.onAdd = consumer;
    }

    public void onRemove(Consumer<User> consumer) {
        this.onRemove = consumer;
    }

    @Override
    public boolean add(User user) {
        boolean result = super.add(user);
        if (result && this.onAdd != null) {
            this.onAdd.accept(user);
        }
        return result;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = super.remove(o);
        if (result && this.onAdd != null) {
            this.onRemove.accept((User) o);
        }
        return result;
    }
}
