package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Unmarks the attendance of an existing student in the list.
 */
public class DeassignmentIndivCommand extends Command {
    public static final String COMMAND_WORD = "deassignIndiv";
    public static final String MESSAGE_SUCCESS = "Assignment deassigned successfully!";
    public static final String MESSAGE_FAIL = "Assignment not found.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deassigns an assignment for one student.\n"
            + "Compulsory Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_NAME + "ASSIGNMENT_NAME "
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Tutorial1 ";
    private final Name name;

    private final Index index;

    /**
     * Constructs an DeassignmentIndivCommand with the specified name and maximum score.
     *
     * @param index of the person in the filtered person list to edit
     * @param name The name of the assignment. Must not be null.
     * @throws NullPointerException If the provided name is null.
     */
    public DeassignmentIndivCommand(Index index, Name name) {
        requireNonNull(index);
        requireNonNull(name);

        this.index = index;
        this.name = name;
    }

    /**
     * Executes the command to delete an assignment for a particular student.
     *
     * @param model Model representing the current state of the npc_track.
     * @return CommandResult representing the result of the execution.
     * @throws CommandException If there is an error during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        Assignment deletedAssignment = new Assignment(name);

        // defensive programming to avoid index exceeding the list number
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person studentToEdit = lastShownList.get(index.getZeroBased());

        Set<Assignment> updatedAssignments = new HashSet<>();

        updatedAssignments.addAll(studentToEdit.getAssignments());
        if (!updatedAssignments.contains(deletedAssignment)) {
            throw new CommandException(MESSAGE_FAIL);
        }

        updatedAssignments.remove(deletedAssignment);

        // Create a new student with the updated assignments
        Person editedStudent = new Person(
                studentToEdit.getName(), Optional.ofNullable(studentToEdit.getPhone()),
                Optional.ofNullable(studentToEdit.getEmail()),
                Optional.ofNullable(studentToEdit.getTelegramHandle()),
                Optional.ofNullable(studentToEdit.getAttendance()),
                studentToEdit.getTags(),
                studentToEdit.getComments(), updatedAssignments, Optional.ofNullable(studentToEdit.getGroup()));

        // Set the updated student in the model
        model.setPerson(studentToEdit, editedStudent);

        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeassignmentIndivCommand)) {
            return false;
        }

        DeassignmentIndivCommand otherDeassignmentIndivCommand = (DeassignmentIndivCommand) other;
        return this.name.equals(otherDeassignmentIndivCommand.name)
                && this.index.equals(otherDeassignmentIndivCommand.index);
    }
}
