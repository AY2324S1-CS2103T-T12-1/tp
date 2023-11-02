package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NpcTrack;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class GradeCommandTest {
    private final Model model = new ModelManager();
    private GradeCommand gradeCommand;



    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradeCommand(null, null, 0));
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        // Create a sample person with no assignment
        Person person = new PersonBuilder().withAssignments("Lab1").build();

        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to the sample person
        int index = 1;
        Assignment sampleAssignment = person.getAssignments().stream().findFirst().orElse(null);
        int score = 90;

        // Create a new GradeCommand
        gradeCommand = new GradeCommand(Index.fromOneBased(index), sampleAssignment.name, score);

        String expectedMessage = String.format(GradeCommand.ASSIGNMENT_GRADE_SUCCESS);
        Model expectedModel = new ModelManager(new NpcTrack(model.getNpcTrack()), new UserPrefs());

        // Check if the person's assignment grade is edited
        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAssignmentScore_success() {
        Name testName = new Name("testAssignment");
        Person person = new PersonBuilder().withAssignments("testAssignment").build();

        // Add the sample person to the model
        model.addPerson(person);

        GradeCommand gradeCommand = new GradeCommand(Index.fromOneBased(1), testName, 90);


        String expectedMessage = String.format(GradeCommand.ASSIGNMENT_GRADE_SUCCESS);
        Model expectedModel = new ModelManager(new NpcTrack(model.getNpcTrack()), new UserPrefs());

        // Check if the person's assignment grade is edited
        assertCommandSuccess(gradeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_sameCommand_returnsTrue() {
        Name name = new Name("Lab1");
        // Create a MarkAttendanceCommand with the same index and tutorial
        GradeCommand command1 = new GradeCommand(Index.fromOneBased(1), name, 0);
        GradeCommand command2 = new GradeCommand(Index.fromOneBased(1), name, 0);

        // They should be equal
        assertTrue(command1.equals(command2));
    }

    @Test
    public void equals_differentCommands_returnsFalse() {
        Name name = new Name("Lab1");
        // Create two different MarkAttendanceCommands
        GradeCommand command1 = new GradeCommand(Index.fromOneBased(1), name, 0);
        GradeCommand command2 = new GradeCommand(Index.fromOneBased(1), name, 10);

        // They should not be equal
        assertFalse(command1.equals(command2));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of npc_track
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        // ensures that outOfBoundIndex is still in bounds of npc_track list
        Name name = new Name("Lab1");
        GradeCommand gradeCommand = new GradeCommand(INDEX_SECOND_PERSON, name, 10);

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noSuchAssignmentName_failure() {
        Name testName = new Name("NoSuchAssignment");
        Person person = new PersonBuilder().withAssignments("Lab1").build();

        // Add the sample person to the model
        model.addPerson(person);
        GradeCommand gradeCommand = new GradeCommand(INDEX_FIRST_PERSON, testName, -1);


        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_NAME);
    }

    @Test
    public void execute_invalidAssignmentScoreBelowZero_failure() {
        Name testName = new Name("Lab1");
        Person person = new PersonBuilder().withAssignments("Lab1").build();

        // Add the sample person to the model
        model.addPerson(person);

        GradeCommand gradeCommand = new GradeCommand(INDEX_FIRST_PERSON, testName, -1);

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE);
    }

    @Test
    public void execute_invalidAssignmentScoreAboveMaxScore_failure() {
        Name testName = new Name("Lab1");
        Person person = new PersonBuilder().withAssignments("Lab1").build();

        // Add the sample person to the model
        model.addPerson(person);

        GradeCommand gradeCommand = new GradeCommand(INDEX_FIRST_PERSON, testName, 101);
        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE);
    }
}
