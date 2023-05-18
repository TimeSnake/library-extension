/*
 * Copyright (C) 2023 timesnake
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
    if (this == o) {
      return true;
    }
    if (!(o instanceof ExPermission that)) {
      return false;
    }
    return Objects.equals(permission, that.permission) && Objects.equals(mode, that.mode)
        && Objects.equals(server, that.server);
  }

  @Override
  public int hashCode() {
    return Objects.hash(permission, mode, server);
  }
}
