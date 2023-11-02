package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NpcTrack;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class GradeGroupCommandTest {
    private final Model model = new ModelManager();
    private GradeGroupCommand gradeGroupCommand;

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GradeGroupCommand(null, null, 0));
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        Group group = new Group("G01");
        // Create a sample person with no assignment
        Person person = new PersonBuilder().withAssignments("Lab1").withGroup("G01").build();

        // Add the sample person to the model
        model.addPerson(person);

        // Index 1 corresponds to the sample person

        Assignment sampleAssignment = person.getAssignments().stream().findFirst().orElse(null);
        int score = 90;

        // Create a new GradeGroupCommand
        gradeGroupCommand = new GradeGroupCommand(group, sampleAssignment.name, score);

        String expectedMessage = String.format(GradeCommand.ASSIGNMENT_GRADE_SUCCESS);
        Model expectedModel = new ModelManager(new NpcTrack(model.getNpcTrack()), new UserPrefs());

        // Check if the person's assignment grade is edited
        assertCommandSuccess(gradeGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validAssignmentScore_success() {
        Name testName = new Name("testAssignment");
        Person person = new PersonBuilder().withAssignments("testAssignment").withGroup("G01").build();

        // Add the sample person to the model
        model.addPerson(person);
        Group group = new Group("G01");

        GradeGroupCommand gradeGroupCommand = new GradeGroupCommand(group, testName, 90);

        String expectedMessage = String.format(GradeGroupCommand.ASSIGNMENT_GRADE_SUCCESS);
        Model expectedModel = new ModelManager(new NpcTrack(model.getNpcTrack()), new UserPrefs());

        // Check if the person's assignment grade is edited
        assertCommandSuccess(gradeGroupCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals_differentCommand_returnsTrue() {
        Name name = new Name("Lab1");
        // Create a MarkAttendanceCommand with the same index and tutorial
        Group group = new Group("G01");
        Group group2 = new Group("G02");
        GradeGroupCommand command1 = new GradeGroupCommand(group, name, 0);
        GradeGroupCommand command2 = new GradeGroupCommand(group2, name, 0);

        // They should be equal
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_sameCommands_returnsTrue() {
        Name name = new Name("Lab1");
        // Create two different MarkAttendanceCommands
        Group group = new Group("G01");
        GradeGroupCommand command1 = new GradeGroupCommand(group, name, 0);
        GradeGroupCommand command2 = new GradeGroupCommand(group, name, 0);

        // They should not be equal
        assertTrue(command1.equals(command2));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of npc_track
     */
    @Test
    public void execute_invalidGroupFilteredList_failure() {
        Name name = new Name("Lab1");
        Group group = new Group("G01");
        GradeGroupCommand gradeGroupCommand = new GradeGroupCommand(group, name, 10);

        assertCommandFailure(gradeGroupCommand, model, Messages.INVALID_GROUP);
    }

    @Test
    public void execute_noSuchAssignmentName_failure() {
        Name testName = new Name("NoSuchAssignment");
        Person person = new PersonBuilder().withAssignments("Lab1").withGroup("G01").build();
        Group group = new Group("G01");
        // Add the sample person to the model
        model.addPerson(person);
        GradeGroupCommand gradeCommand = new GradeGroupCommand(group, testName, -1);

        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_NAME);
    }

    @Test
    public void execute_invalidAssignmentScoreBelowZero_failure() {
        Name testName = new Name("Lab1");
        Group group = new Group("G01");
        Person person = new PersonBuilder().withAssignments("Lab1").withGroup("G01").build();
        // Add the sample person to the model
        model.addPerson(person);
        GradeGroupCommand gradeGroupCommand = new GradeGroupCommand(group, testName, -1);
        assertCommandFailure(gradeGroupCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE);
    }

    @Test
    public void execute_invalidAssignmentScoreAboveMaxScore_failure() {
        Name testName = new Name("Lab1");
        Person person = new PersonBuilder().withAssignments("Lab1").withGroup("G01").build();
        Group group = new Group("G01");
        // Add the sample person to the model
        model.addPerson(person);

        GradeGroupCommand gradeCommand = new GradeGroupCommand(group, testName, 101);
        assertCommandFailure(gradeCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_SCORE);
    }
}
