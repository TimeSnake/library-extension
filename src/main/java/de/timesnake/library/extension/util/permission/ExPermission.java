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

package de.timesnake.library.extension.util.permission;

import de.timesnake.library.basic.util.Status;

import java.util.Collection;
import java.util.Objects;

public class ExPermission {

    private String permission;
    private Status.Permission mode;
    private Collection<String> server;

    public ExPermission(String permission, Status.Permission mode, Collection<String> server) {
        this.setPermission(permission);
        this.setMode(mode);
        this.setServer(server);
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Status.Permission getMode() {
        return mode;
    }

    public void setMode(Status.Permission mode) {
        this.mode = mode;
    }

    public Collection<String> getServer() {
        return server;
    }

    public void setServer(Collection<String> server) {
        this.server = server;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExPermission that)) return false;
        return Objects.equals(permission, that.permission) && Objects.equals(mode, that.mode)
                && Objects.equals(server, that.server);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permission, mode, server);
    }
}
