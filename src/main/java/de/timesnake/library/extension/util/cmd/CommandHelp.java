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
