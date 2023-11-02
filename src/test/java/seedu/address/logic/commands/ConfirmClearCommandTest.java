package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalNpcTrack;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.NpcTrack;
import seedu.address.model.UserPrefs;

public class ConfirmClearCommandTest {
    @Test
    public void execute_emptyNpcTrack_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ConfirmClearCommand(), model, ConfirmClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyNpcTrack_success() {
        Model model = new ModelManager(getTypicalNpcTrack(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalNpcTrack(), new UserPrefs());
        expectedModel.setNpcTrack(new NpcTrack());

        assertCommandSuccess(new ConfirmClearCommand(), model, ConfirmClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
