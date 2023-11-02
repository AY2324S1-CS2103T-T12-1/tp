package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.NpcTrack;


/**
 * Clears the npc_track.
 */
public class ConfirmClearCommand extends Command {
    public static final String COMMAND_WORD = "yes";
    public static final String MESSAGE_SUCCESS = "npc_track has been cleared!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the npc_track. "
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setNpcTrack(new NpcTrack());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
