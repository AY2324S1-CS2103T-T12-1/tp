package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalNpcTrack;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class DeassignmentIndivCommandTest {

    private Model model = new ModelManager(getTypicalNpcTrack(), new UserPrefs());
    private Name assignmentName = new Name("Lab1");
    private final Model emptyModel = new ModelManager();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personWithAssignmentToDeassign = new PersonBuilder()
                .withAssignments(assignmentName.toString())
                .build();

        emptyModel.addPerson(personWithAssignmentToDeassign);
        DeassignmentIndivCommand deassignmentIndivCommand = new DeassignmentIndivCommand(INDEX_FIRST_PERSON,
                assignmentName);

        String expectedMessage = String.format(DeassignmentIndivCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(deassignmentIndivCommand, emptyModel, expectedMessage, emptyModel);
    }

    @Test
    public void equals() {
        Name assignmentName1 = new Name("Assignment1");
        Name assignmentName2 = new Name("Assignment2");

        DeassignmentIndivCommand deassignmentIndivCommand1 = new DeassignmentIndivCommand(INDEX_FIRST_PERSON,
                assignmentName1);
        DeassignmentIndivCommand deassignmentIndivCommand2 = new DeassignmentIndivCommand(INDEX_FIRST_PERSON,
                assignmentName1);
        DeassignmentIndivCommand deassignmentIndivCommand3 = new DeassignmentIndivCommand(INDEX_FIRST_PERSON,
                assignmentName2);

        // Check for equality
        assertTrue(deassignmentIndivCommand1.equals(deassignmentIndivCommand2));

        // Check for inequality
        assertFalse(deassignmentIndivCommand1.equals(deassignmentIndivCommand3));
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeassignmentIndivCommand deassignmentIndexCommand = new DeassignmentIndivCommand(outOfBoundIndex,
                assignmentName);

        assertCommandFailure(deassignmentIndexCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_noSuchAssignmentName_failure() {
        Name testName = new Name("NoSuchAssignment");
        Person person = new PersonBuilder().withAssignments("Lab1").build();

        // Add the sample person to the model
        emptyModel.addPerson(person);
        DeassignmentIndivCommand deassignmentIndivCommand = new DeassignmentIndivCommand(INDEX_FIRST_PERSON, testName);


        assertCommandFailure(deassignmentIndivCommand, emptyModel, DeassignmentIndivCommand.MESSAGE_FAIL);
    }

    @Test
    public void execute_specifiedUnfilteredList_success() {
        Person person = new PersonBuilder().withAssignments("Lab1").build();

        emptyModel.addPerson(person);
        Index index = Index.fromZeroBased(0);

        DeassignmentIndivCommand deassignmentIndexCommand = new DeassignmentIndivCommand(index, new Name(
                "Lab1"));

        String expectedMessage = String.format(DeassignmentIndivCommand.MESSAGE_SUCCESS);

        assertCommandSuccess(deassignmentIndexCommand, emptyModel, expectedMessage, emptyModel);
    }


}
