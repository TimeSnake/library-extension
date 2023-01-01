/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.player;

import de.timesnake.database.util.user.DbUser;

import java.util.UUID;

public interface User {

    String getChatName();

    UUID getUniqueId();

    DbUser getDatabase();

}
