/*
 * Copyright (C) 2023 timesnake
 */

package de.timesnake.library.extension.util.cmd;

import de.timesnake.library.chat.ExTextColor;
import de.timesnake.library.extension.util.cmd.IncCommandSelection.Builder;
import java.util.Collection;

public abstract class IncCommandListener<S extends Sender, A extends Argument, C extends IncCommandContext>
        implements CommandListenerBasis<S, A> {

    public abstract C onCommand(S sender, ExCommand<S, A> cmd);

    /**
     * @param sender
     * @param context
     * @param option
     * @param value
     * @return Returns true, if command is completed
     */
    public abstract <V> boolean onUpdate(S sender, C context, IncCommandOption<V> option, V value);

    public abstract Collection<IncCommandOption<?>> getOptions();

    public abstract String getCommand();

    public IncCommandSelection.Builder createSelection(IncCommandOption<?> option) {
        return new Builder().command(this.getCommand()).option(option);
    }

    public void sendSelectionTo(S sender, IncCommandSelection.Builder selection) {
        sender.sendPluginMessage(selection.buildToMessage(ExTextColor.PERSONAL));
    }
}
