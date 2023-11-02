package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' npc_track file path.
     */
    Path getNpcTrackFilePath();

    /**
     * Sets the user prefs' npc_track file path.
     */
    void setNpcTrackFilePath(Path npcTrackFilePath);

    /**
     * Replaces npc_track data with the data in {@code npcTrack}.
     */
    void setNpcTrack(ReadOnlyNpcTrack npcTrack);

    /** Returns the NpcTrack */
    ReadOnlyNpcTrack getNpcTrack();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the npc_track.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the npc_track.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the npc_track.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the npc_track.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the npc_track.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Updates the student list to propagate change to the rest of the model.
     *
     * @param person The student to be refreshed.
     */
    void updatePerson(Person person);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a group with the same identity as {@code group} exists in the npc_track.
     */
    boolean hasGroup(Group group);
}
