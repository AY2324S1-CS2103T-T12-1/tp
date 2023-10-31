package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Marks the attendance of an existing student in the taa.
 */
public class DeassignmentCommand extends Command {
    public static final String COMMAND_WORD = "deassign";
    public static final String MESSAGE_SUCCESS = "Assignment deleted successfully!";
    public static final String MESSAGE_FAIL = "Assignment not found.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes an assignment for all students.\n"
        + "Compulsory Parameters: "
        + PREFIX_NAME + "ASSIGNMENT_NAME "
        + "\nExample: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Tutorial1";
    private final Name name;

    /**
     * Constructs an DeassignmentCommand with the specified name and maximum score.
     *
     * @param name The name of the assignment. Must not be null.
     * @throws NullPointerException If the provided name is null.
     */
    public DeassignmentCommand(Name name) {
        requireNonNull(name);

        this.name = name;
    }

    /**
     * Executes the command to create a new assignment for all students in the last shown list.
     *
     * @param model Model representing the current state of the address book.
     * @return CommandResult representing the result of the execution.
     * @throws CommandException If there is an error during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Assignment deletedAssignment = new Assignment(name);

        // If nobody has the assignment, this is an error.
        if (!lastShownList.stream().filter(person ->
                person.getAssignments().contains(deletedAssignment))
                .findAny().isPresent()) {
            throw new CommandException(MESSAGE_FAIL);
        }

        // Loop through the list and update each person's assignments
        for (Person studentToEdit : lastShownList) {
            Set<Assignment> updatedAssignments = new HashSet<>();

            updatedAssignments.addAll(studentToEdit.getAssignments());
            updatedAssignments.remove(deletedAssignment);

            // Create a new student with the updated assignments
            Person editedStudent = new Person(
                    studentToEdit.getName(), Optional.of(studentToEdit.getPhone()),
                    Optional.of(studentToEdit.getEmail()),
                    Optional.of(studentToEdit.getTelegramHandle()), Optional.of(studentToEdit.getAttendance()),
                    studentToEdit.getTags(),
                    studentToEdit.getComments(), updatedAssignments, Optional.of(studentToEdit.getGroup()));

            // Set the updated student in the model
            model.setPerson(studentToEdit, editedStudent);
        }

        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeassignmentCommand)) {
            return false;
        }

        DeassignmentCommand otherDeassignmentCommand = (DeassignmentCommand) other;
        return this.name.equals(otherDeassignmentCommand.name);
    }
}
