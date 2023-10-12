package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Attendance;

/**
 * Marks the attendance of an existing student in the taa.
 */
public class MarkAttendanceCommand extends Command {
    public static final String COMMAND_WORD = "markAtd";
    public static final String ATTENDANCE_MARK_SUCCESS = "Attendance marked successfully!";
    public static final String ATTENDANCE_MARK_FAIL = "Attendance failed to mark!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the student identified\n"
            + "by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer), "
            + "[" + PREFIX_WEEK + "WeekToMark] \n"
            + "Example: " + COMMAND_WORD + " 1 w/1 ";
    private final Index index;
    private final Index week;

    /**
     * Constructs a new MarkAttendanceCommand to mark attendance for a student on a specific week.
     *
     * @param index The index of the student to mark attendance for.
     * @param week The index of the week to mark attendance on.
     */
    public MarkAttendanceCommand(Index index, Index week) {
        this.index = index;
        this.week = week;
    }

    /**
     * Executes the MarkAttendanceCommand to mark attendance for a student on a specific week.
     *
     * @param model The model that the command operates on.
     * @return A CommandResult indicating the outcome of the execution.
     * @throws CommandException If there is an error in executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person studentToEdit = lastShownList.get(index.getZeroBased());
        Person editedStudent = new Person(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), studentToEdit.getAttendance(), studentToEdit.getTags(),
                studentToEdit.getComments());
        Attendance studentAtd = studentToEdit.getAttendance();
        if (studentAtd.isMarkedWeek(this.week.getZeroBased())) {
            return new CommandResult(Messages.MESSAGE_DUPLICATE_MARKINGS);
        }
        studentAtd.markAttendance(this.week.getZeroBased());

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether
     * the attendance is marked.
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = personToEdit.getAttendance().isMarkedWeek(this.week.getZeroBased())
                ? ATTENDANCE_MARK_SUCCESS
                : ATTENDANCE_MARK_FAIL;
        return String.format(message, personToEdit);
    }
}
