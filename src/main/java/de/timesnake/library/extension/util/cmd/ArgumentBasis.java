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

import de.timesnake.database.util.Database;
import de.timesnake.database.util.user.DbUser;
import de.timesnake.library.basic.util.Status;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class ArgumentBasis {

    protected final Sender sender;
    protected String string;

    public ArgumentBasis(Sender sender, String string) {
        this.sender = sender;
        this.string = string;
    }

    public Sender getSender() {
        return sender;
    }

    public String getString() {
        return this.string;
    }

    public Component getComponent() {
        return Component.text(this.string);
    }

    @Override
    public String toString() {
        return this.string;
    }

    public boolean isInt(boolean sendMessage) {
        try {
            Integer.parseInt(this.string);
        } catch (NumberFormatException e) {
            if (sendMessage) {
                sender.sendMessageNoInteger(this.string);
            }
            return false;
        }
        return true;
    }

    public boolean isFloat(boolean sendMessage) {
        try {
            Float.parseFloat(this.string);
        } catch (NumberFormatException e) {
            if (sendMessage) {
                sender.sendMessageNoFloat(this.string);
            }
            return false;
        }
        return true;
    }

    public boolean isDouble(boolean sendMessage) {
        try {
            Double.parseDouble(this.string);
        } catch (NumberFormatException e) {
            if (sendMessage) {
                sender.sendMessageNoDouble(this.string);
            }
            return false;
        }
        return true;
    }

    public boolean isUUID(boolean sendMessage) {
        try {
            UUID.fromString(this.string);
        } catch (IllegalArgumentException e) {
            if (sendMessage) {
                sender.sendMessageNoUuid(this.string);
            }
            return false;
        }
        return true;
    }

    public boolean isPlayerDatabaseName(boolean sendMessage) {
        if (Database.getUsers().containsUser(this.string.toLowerCase())) {
            return true;
        }
        try {
            if (Database.getUsers().containsUser(UUID.fromString(this.string))) {
                return true;
            }
        } catch (IllegalArgumentException ignored) {
        }
        if (sendMessage) {
            sender.sendMessagePlayerNotExist(this.string);
        }
        return false;
    }

    public boolean isTime(boolean sendMessage) {
        String[] time = null;
        if (this.string.contains(":")) {
            time = this.string.split(":");
        } else if (this.string.contains(".")) {
            time = this.string.split(".");
        } else if (this.string.contains(";")) {
            time = this.string.split(";");
        }
        if (time != null) {
            if (time.length >= 2) {
                if (new ArgumentTypeCheck(this.sender, time[0]).isHour(false) && new ArgumentTypeCheck(this.sender,
                        time[1]).isMinute(false)) {
                    if (time.length == 3) {
                        if (new ArgumentTypeCheck(this.sender, time[2]).isSecond(false)) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        if (sendMessage) {
            sender.sendMessageNoTime(this.string);
        }
        return false;
    }

    public boolean isHour(boolean sendMessage) {
        if (this.isInt(false)) {
            if (Integer.parseInt(this.string) < 24 && Integer.parseInt(this.string) >= 0) {
                return true;
            }
        }
        if (sendMessage) {
            sender.sendMessageNoHour(this.string);
        }
        return false;
    }

    public boolean isMinute(boolean sendMessage) {
        if (this.isInt(false)) {
            if (Integer.parseInt(this.string) < 60 && Integer.parseInt(this.string) >= 0) {
                return true;
            }
        }
        if (sendMessage) {
            sender.sendMessageNoMinute(this.string);
        }
        return false;
    }

    public boolean isSecond(boolean sendMessage) {
        if (this.isInt(false)) {
            if (Integer.parseInt(this.string) < 60 && Integer.parseInt(this.string) >= 0) {
                return true;
            }
        }
        if (sendMessage) {
            sender.sendMessageNoSecond(this.string);
        }
        return false;
    }

    public boolean isColor(boolean sendMessage) {
        switch (this.string.toUpperCase()) {
            case "AQUA":
            case "BLACK":
            case "YELLOW":
            case "WHITE":
            case "TEAL":
            case "SILVER":
            case "RED":
            case "PURBLE":
            case "ORANGE":
            case "OLIVE":
            case "NAVY":
            case "MAROON":
            case "LIME":
            case "GREEN":
            case "GRAY":
            case "FUCHSIA":
            case "BLUE":
                return true;
        }
        if (sendMessage) {
            sender.sendMessageNoColor(this.string);
        }
        return false;
    }

    public boolean isBoolean(boolean sendMessage) {
        if (this.string.equalsIgnoreCase("true") || this.string.equalsIgnoreCase("false")) {
            return true;
        }
        if (sendMessage) {
            this.sender.sendMessageNoBoolean(this.string);
        }
        return false;
    }

    public boolean isServerStatus(boolean sendMessage) {
        if (Status.Server.parseValue(this.string.toLowerCase()) != null) {
            return true;
        }
        try {
            if (Status.Server.parseValue(this.string) != null) {
                return true;
            }
        } catch (IllegalArgumentException ignored) {
        }
        if (sendMessage) {
            sender.sendMessageServerStatusNotExist(this.string);
        }
        return false;
    }

    public boolean isPermissionStatus(boolean sendMessage) {
        if (Status.Permission.parseValue(this.string.toLowerCase()) != null) {
            return true;
        }
        try {
            if (Status.Permission.parseValue(this.string) != null) {
                return true;
            }
        } catch (IllegalArgumentException ignored) {
        }
        if (sendMessage) {
            sender.sendMessagePermissionStatusNotExist(this.string);
        }
        return false;
    }

    public boolean isTicketStatus(boolean sendMessage) {
        if (Status.Ticket.parseValue(this.string.toLowerCase()) != null) {
            return true;
        }
        try {
            if (Status.Ticket.parseValue(this.string) != null) {
                return true;
            }
        } catch (IllegalArgumentException ignored) {
        }
        if (sendMessage) {
            sender.sendMessageTicketStatusNotExist(this.string);
        }
        return false;
    }

    public Integer toInt() {
        try {
            return Integer.valueOf(this.string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Float toFloat() {
        try {
            return Float.valueOf(this.string);
        } catch (NumberFormatException e) {
            return null;
        }

    }

    public Double toDouble() {
        try {
            return Double.valueOf(this.string);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int toTicks() {
        String[] time = null;
        if (this.string.contains(":")) {
            time = string.split(":");
        } else if (this.string.contains(";")) {
            time = string.split(";");
        } else if (this.string.contains(".")) {
            time = string.split(".");
        }
        if (time != null) {
            if (time.length == 2) {
                Argument arg0 = new ArgumentTypeCheck(sender, time[0]);
                Argument arg1 = new ArgumentTypeCheck(sender, time[1]);
                if (arg0.isInt(false) && arg1.isInt(false)) {
                    int hours = arg0.toInt();
                    int minutes = arg1.toInt();
                    if (hours < 24 && hours >= 0) {
                        if (minutes < 60 && minutes >= 0) {
                            int ticks = (int) ((hours * 1000 + minutes / 0.06) - 6000);
                            if (ticks < 0) {
                                ticks += 24000;
                            }
                            return ticks;
                        } else sender.sendMessageNoMinute(arg1.getString());
                    } else sender.sendMessageNoHour(arg0.getString());
                }
            }
        } else sender.sendMessageNoTime(string);
        return 0;
    }

    public DbUser toDbUser() {
        return Database.getUsers().getUser(this.string);
    }

    public String toTime() {
        if (this.isInt(false)) {
            int hours = this.toInt() / 1000 + 6;
            if (hours >= 24) {
                hours = hours - 24;
            }
            int minutes = (int) ((double) this.toInt() % 1000 / 1000 * 60);

            String time = "";

            if (hours < 10) time += "0" + hours;
            else time += hours;

            if (minutes < 10) time += ":0" + minutes;
            else time += ":" + minutes;

            return time;
        }
        return null;
    }

    public boolean toBoolean() {
        return this.string.equalsIgnoreCase("true");
    }

    public Status.Permission toPermissionStatus() {
        return Status.Permission.parseValue(this.string.toLowerCase()) != null ?
                Status.Permission.parseValue(this.string.toLowerCase()) : Status.Permission.parseValue(this.string);
    }

    public Status.Server toServerStatus() {
        return Status.Server.parseValue(this.string.toLowerCase()) != null ?
                Status.Server.parseValue(this.string.toLowerCase()) : Status.Server.parseValue(this.string);
    }

    public Status.Ticket toTicketStatus() {
        return Status.Ticket.parseValue(this.string.toLowerCase()) != null ?
                Status.Ticket.parseValue(this.string.toLowerCase()) : Status.Ticket.parseValue(this.string);
    }

    public Integer toIntOrExit(boolean sendMessage) {
        try {
            return Integer.parseInt(this.string);
        } catch (NumberFormatException e) {
            if (sendMessage) {
                sender.sendMessageNoInteger(this.string);
            }
            throw new CommandExitException();
        }
    }

    public Float toFloatOrExit(boolean sendMessage) {
        try {
            return Float.parseFloat(this.string);
        } catch (NumberFormatException e) {
            if (sendMessage) {
                sender.sendMessageNoFloat(this.string);
            }
            throw new CommandExitException();
        }
    }

    public Double toDoubleOrExit(boolean sendMessage) {
        try {
            return Double.parseDouble(this.string);
        } catch (NumberFormatException e) {
            if (sendMessage) {
                sender.sendMessageNoDouble(this.string);
            }
            throw new CommandExitException();
        }
    }

    public UUID toUUIDOrExit(boolean sendMessage) {
        try {
            return UUID.fromString(this.string);
        } catch (IllegalArgumentException e) {
            if (sendMessage) {
                sender.sendMessageNoUuid(this.string);
            }
            throw new CommandExitException();
        }
    }

    public boolean isBooleanOrExit(boolean sendMessage) {
        if (this.string.equalsIgnoreCase("true")) {
            return true;
        } else if (this.string.equalsIgnoreCase("false")) {
            return false;
        }
        if (sendMessage) {
            this.sender.sendMessageNoBoolean(this.string);
        }
        throw new CommandExitException();
    }

    public Status.Server toServerStatusOrExit(boolean sendMessage) {
        Status.Server status = Status.Server.parseValue(this.string.toLowerCase());
        if (status != null) {
            return status;
        }

        if (sendMessage) {
            sender.sendMessageServerStatusNotExist(this.string);
        }
        throw new CommandExitException();
    }

    public Status.Permission toPermissionStatusOrExit(boolean sendMessage) {
        Status.Permission status = Status.Permission.parseValue(this.string.toLowerCase());
        if (status != null) {
            return status;
        }
        if (sendMessage) {
            sender.sendMessagePermissionStatusNotExist(this.string);
        }
        throw new CommandExitException();
    }

    public Status.Ticket toTicketStatusOrExit(boolean sendMessage) {
        Status.Ticket status = Status.Ticket.parseValue(this.string.toLowerCase());
        if (status != null) {
            return status;
        }
        if (sendMessage) {
            sender.sendMessageTicketStatusNotExist(this.string);
        }
        throw new CommandExitException();
    }

    public boolean equals(String... string) {
        for (String s : string) {
            if (this.string.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean equalsIgnoreCase(String... string) {
        for (String s : string) {
            if (this.string.equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String string) {
        return this.string.contains(string);
    }

    public String toLowerCase() {
        return this.string.toLowerCase();
    }

    public String toUpperCase() {
        return this.string.toUpperCase();
    }
}