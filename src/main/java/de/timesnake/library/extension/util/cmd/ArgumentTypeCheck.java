package de.timesnake.library.extension.util.cmd;

public class ArgumentTypeCheck extends Argument {


    public ArgumentTypeCheck(Sender sender, String string) {
        super(sender, string);
    }

    public boolean isPlayerName(boolean sendMessage) {
        return false;
    }

    public boolean isChatColor(boolean sendMessage) {
        return false;
    }

    public <Player> Player toPlayer() {
        return null;
    }

    public Object toChatColor(boolean sendMessage) {
        return null;
    }
}
