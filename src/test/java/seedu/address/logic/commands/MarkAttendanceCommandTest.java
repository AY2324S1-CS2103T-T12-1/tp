package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class MarkAttendanceCommandTest {
    private Model model;
    private MarkAttendanceCommand markAttendanceCommand;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkAttendanceCommand(null, null));
    }

    @Test
    public void execute_validIndexAndTutorial_markAttendanceSuccessful() {
        // Create a sample person with no attendance marked
        Person person = new PersonBuilder().build();

        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to the sample person
        int index = 1;

        // Index 1 corresponds to week 1
        int week = 1;

        // Create a new MarkAttendanceCommand
        markAttendanceCommand = new MarkAttendanceCommand(Index.fromOneBased(index), Index.fromOneBased(week));

        // Execute the command
        try {
            markAttendanceCommand.execute(model);
        } catch (CommandException e) {
            e.printStackTrace();
        }

        // Check if the person's attendance has been marked for week 1
        assertTrue(person.getAttendance().isMarkedWeek(0));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        // Index 1 is invalid in an empty model
        int index = 1;

        // Index 1 corresponds to week 1
        int week = 1;

        // Create a new MarkAttendanceCommand
        markAttendanceCommand = new MarkAttendanceCommand(Index.fromOneBased(index), Index.fromOneBased(week));

        // Execute the command and expect a CommandException
        assertThrows(CommandException.class, () -> markAttendanceCommand.execute(model));
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        // Create a MarkAttendanceCommand with the same index and tutorial
        MarkAttendanceCommand command1 = new MarkAttendanceCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        MarkAttendanceCommand command2 = new MarkAttendanceCommand(Index.fromOneBased(1), Index.fromOneBased(1));

        // They should be equal
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        // Create two different MarkAttendanceCommands
        MarkAttendanceCommand command1 = new MarkAttendanceCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        MarkAttendanceCommand command2 = new MarkAttendanceCommand(Index.fromOneBased(2), Index.fromOneBased(2));

        // They should not be equal
        assertFalse(command1.equals(command2));
    }
}
