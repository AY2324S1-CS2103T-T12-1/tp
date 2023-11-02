package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setNpcTrackFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setNpcTrackFilePath(null));
    }

    @Test
    public void equalsTest() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.equals(userPrefs);
    }

    @Test
    public void equalsDifferentObjectTest() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.equals(new Object());
    }
    @Test
    public void hashCodeTest() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.hashCode();
    }

}
