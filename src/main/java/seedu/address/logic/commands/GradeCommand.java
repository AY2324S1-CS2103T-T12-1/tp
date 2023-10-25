package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
 * Grades the assignment of an existing student in the list.
 */
public class GradeCommand extends Command {
    public static final String COMMAND_WORD = "grade";
    public static final String ASSIGNMENT_GRADE_SUCCESS = "Assignment graded successfully!";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the specific assignment of the student identified "
            + "by the index number used in the displayed student list.\n"
            + "Compulsory Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "ASSIGNMENT_NAME " + PREFIX_GRADE + "SCORE\n "
            + "Example: " + COMMAND_WORD + " 1 n/Tutorial1 g/96 ";
    private final Index index;
    private final Name name;
    private final int score;

    /**
     * Constructs a new GradeCommand to grade student's assignment.
     *
     * @param index The index of the student to mark attendance for.
     * @param name The name of the assignment to grade.
     * @param score The score to be given to the assignment.
     */
    public GradeCommand(Index index, Name name, int score) {
        requireNonNull(index);
        requireNonNull(name);
        this.index = index;
        this.name = name;
        this.score = score;
    }

    /**
     * Executes the GradeCommand to grade student's assignment.
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

        // Get the student to be graded
        Person studentToGrade = lastShownList.get(index.getZeroBased());

        // From the specific students' assignments, get that specific named assignment
        Assignment studentAssignment = new Assignment(this.name);
        Set<Assignment> studentAssignments = studentToGrade.getAssignments();

        // Throw error if assignment is not found
        if (!studentAssignments.contains(studentAssignment)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_NAME);
        }

        for (Assignment assignment : studentAssignments) {
            if (assignment.equals(studentAssignment)) {
                if (score > assignment.getMaxScore() || score < 0) {
                    throw new CommandException(Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE);
                }
                assignment.setScore(score);
            }
        }
        Person editedStudent = new Person(
                studentToGrade.getName(),
                Optional.of(studentToGrade.getPhone()), Optional.of(studentToGrade.getEmail()),
                Optional.of(studentToGrade.getTelegramHandle()), Optional.of(studentToGrade.getAttendance()),
                studentToGrade.getTags(),
                studentToGrade.getComments(), studentToGrade.getAssignments(), Optional.of(studentToGrade.getGroup()));
        model.setPerson(studentToGrade, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(ASSIGNMENT_GRADE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GradeCommand)) {
            return false;
        }

        GradeCommand otherGradeCommand = (GradeCommand) other;
        return this.name.equals(otherGradeCommand.name)
                && this.score == otherGradeCommand.score;
    }

}
