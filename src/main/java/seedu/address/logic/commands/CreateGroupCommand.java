package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;

/**
 * Creates a group in the npc_track.
 */
public class CreateGroupCommand extends Command {

    public static final String COMMAND_WORD = "group";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " PREVIOUS_GROUP_NAME UPDATED_GROUP_NAME\n"
            + "Example: " + COMMAND_WORD + " T01 T02";
    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the npc_track";
    public static final String MESSAGE_SUCCESS = "Group updated: %1$s";
    private static final Logger logger = Logger.getLogger(CreateGroupCommand.class.getName());
    private final String groupName;
    private final String updatedGroupName;
    /**
     * Creates an CreateGroupCommand to add the specified {@code Group}
     */
    public CreateGroupCommand(String groupName, String updatedGroupName) {
        requireNonNull(groupName);
        requireNonNull(updatedGroupName);
        this.groupName = groupName;
        this.updatedGroupName = updatedGroupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGroup(new Group(updatedGroupName))) {
            logger.log(java.util.logging.Level.WARNING, MESSAGE_DUPLICATE_GROUP);
        }
        List<Person> lastShownList = model.getFilteredPersonList();
        // loop through the person in the list and updates everyone without a group to the new group
        boolean hasUpdated = false;
        for (Person p : lastShownList) {
            Group grp = new Group(updatedGroupName);
            if (p.getGroup().equals(new Group(groupName))) {
                Person updatedPerson = new Person(p.getName(), Optional.ofNullable(p.getPhone()),
                        Optional.ofNullable(p.getEmail()), Optional.ofNullable(p.getTelegramHandle()),
                        Optional.ofNullable(p.getAttendance()), p.getTags(), p.getComments(), p.getAssignments(),
                        Optional.ofNullable(grp));
                model.setPerson(p, updatedPerson);
                hasUpdated = true;
            }
        }
        if (!hasUpdated) {
            throw new CommandException("No one in the group: " + groupName + " to update");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, updatedGroupName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateGroupCommand // instanceof handles nulls
                && groupName.equals(((CreateGroupCommand) other).groupName)
                && updatedGroupName.equals(((CreateGroupCommand) other).updatedGroupName));
    }
}
