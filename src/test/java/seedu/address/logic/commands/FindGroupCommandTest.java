package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalNpcTrack;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.GroupContainsKeywordsPredicate;

public class FindGroupCommandTest {
    private Model model = new ModelManager(getTypicalNpcTrack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalNpcTrack(), new UserPrefs());

    @Test
    public void equals() {
        GroupContainsKeywordsPredicate firstPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList("first"));
        GroupContainsKeywordsPredicate secondPredicate =
                new GroupContainsKeywordsPredicate(Collections.singletonList("second"));

        FindGroupCommand findFirstCommand = new FindGroupCommand(firstPredicate);
        FindGroupCommand findSecondCommand = new FindGroupCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindGroupCommand findFirstCommandCopy = new FindGroupCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        GroupContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindGroupCommand command = new FindGroupCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        GroupContainsKeywordsPredicate predicate = new GroupContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindGroupCommand findGroupCommand = new FindGroupCommand(predicate);
        String expected = FindGroupCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findGroupCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code GroupContainsKeywordsPredicate}.
     */
    private GroupContainsKeywordsPredicate preparePredicate(String userInput) {
        return new GroupContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
