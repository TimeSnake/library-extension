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

package de.timesnake.library.extension.util.cmd;

public class CommandHelp {

    private final int code;
    private final Contact contact;
    private final String description;

    public CommandHelp(int code, Contact contact, String description) {
        this.code = code;
        this.contact = contact;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public Contact getContact() {
        return contact;
    }

    public String getDescription() {
        return description;
    }

    public static class Contact {

        public static final Contact STAFF = new Contact("staff");
        public static final Contact ADMIN = new Contact("admin");
        private final String name;

        Contact(String name) {
            this.name = name;
        }

        public static Contact getPluginDeveloper(String name) {
            return new Contact(name);
        }
    }
}
