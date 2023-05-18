/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.player;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class UserMap<User, Value> extends HashMap<User, Value> {

  public static final LinkedList<UserMap<?, ?>> MAPS = new LinkedList<>();

  public UserMap() {
    MAPS.add(this);
  }

  public UserMap(Map<? extends User, Value> map) {
    super(map);
    MAPS.add(this);
  }
}
