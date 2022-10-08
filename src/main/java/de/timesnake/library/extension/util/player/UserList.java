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
