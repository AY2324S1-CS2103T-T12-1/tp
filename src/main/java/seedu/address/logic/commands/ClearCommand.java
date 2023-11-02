package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Send warning to clear the npc_track.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Are you sure you want to clear? Enter 'yes' if you want to clear.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Confirmation message to clear. "
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
