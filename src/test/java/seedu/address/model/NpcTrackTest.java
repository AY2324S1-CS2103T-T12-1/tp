package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_ALICE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalNpcTrack;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class NpcTrackTest {

    private final NpcTrack npcTrack = new NpcTrack();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), npcTrack.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> npcTrack.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyNpcTrack_replacesData() {
        NpcTrack newData = getTypicalNpcTrack();
        npcTrack.resetData(newData);
        assertEquals(newData, npcTrack);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        NpcTrackStub newData = new NpcTrackStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> npcTrack.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> npcTrack.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInNpcTrack_returnsFalse() {
        assertFalse(npcTrack.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInNpcTrack_returnsTrue() {
        npcTrack.addPerson(ALICE);
        assertTrue(npcTrack.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInNpcTrack_returnsTrue() {
        npcTrack.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withTelegram(VALID_TELEGRAM_ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(npcTrack.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> npcTrack.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = NpcTrack.class.getCanonicalName() + "{persons=" + npcTrack.getPersonList() + "}";
        assertEquals(expected, npcTrack.toString());
    }

    /**
     * A stub ReadOnlyNpcTrack whose persons list can violate interface constraints.
     */
    private static class NpcTrackStub implements ReadOnlyNpcTrack {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        NpcTrackStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

    @Test
    public void equalsMethod() {
        NpcTrack npcTrack = new NpcTrack();
        NpcTrack npcTrack1 = new NpcTrack();
        assertTrue(npcTrack.equals(npcTrack1));
    }

    @Test
    public void equalsMethodSameObject() {
        NpcTrack npcTrack = new NpcTrack();
        assertTrue(npcTrack.equals(npcTrack));
    }

    @Test
    public void equalsMethodDifferentObject() {
        NpcTrack npcTrack = new NpcTrack();
        assertFalse(npcTrack.equals(new Object()));
    }

    @Test
    public void hashCodeMethod() {
        NpcTrack npcTrack = new NpcTrack();
        NpcTrack npcTrack1 = new NpcTrack();
        assertEquals(npcTrack.hashCode(), npcTrack1.hashCode());
    }
}
