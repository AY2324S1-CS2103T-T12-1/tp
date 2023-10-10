package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null address
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid addresses
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidTelegramHandle("1234")); // less than 5
        assertFalse(TelegramHandle.isValidTelegramHandle("%&@#&@")); // contains invalid symbols

        // valid addresses
        assertTrue(TelegramHandle.isValidTelegramHandle("mamamia"));
        assertTrue(TelegramHandle.isValidTelegramHandle("12345")); // 5 digits
        assertTrue(TelegramHandle.isValidTelegramHandle("longtelegramhandle")); // long telegram handle
    }

    @Test
    public void equals() {
        TelegramHandle telegramHandle = new TelegramHandle("validtelegramhandle");

        // same values -> returns true
        assertTrue(telegramHandle.equals(new TelegramHandle("validtelegramhandle")));

        // same object -> returns true
        assertTrue(telegramHandle.equals(telegramHandle));

        // null -> returns false
        assertFalse(telegramHandle.equals(null));

        // different types -> returns false
        assertFalse(telegramHandle.equals(5.0f));

        // different values -> returns false
        assertFalse(telegramHandle.equals(new TelegramHandle("othervalidtelegramHandle")));
    }
}
