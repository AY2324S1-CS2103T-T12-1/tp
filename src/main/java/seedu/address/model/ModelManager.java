package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Group;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the npc_track data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final NpcTrack npcTrack;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given npcTrack and userPrefs.
     */
    public ModelManager(ReadOnlyNpcTrack npcTrack, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(npcTrack, userPrefs);

        logger.fine("Initializing with npc_track: " + npcTrack + " and user prefs " + userPrefs);

        this.npcTrack = new NpcTrack(npcTrack);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.npcTrack.getPersonList());
    }

    public ModelManager() {
        this(new NpcTrack(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getNpcTrackFilePath() {
        return userPrefs.getNpcTrackFilePath();
    }

    @Override
    public void setNpcTrackFilePath(Path npcTrackFilePath) {
        requireNonNull(npcTrackFilePath);
        userPrefs.setNpcTrackFilePath(npcTrackFilePath);
    }

    //=========== NpcTrack ================================================================================

    @Override
    public void setNpcTrack(ReadOnlyNpcTrack npcTrack) {
        this.npcTrack.resetData(npcTrack);
    }

    @Override
    public ReadOnlyNpcTrack getNpcTrack() {
        return npcTrack;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return npcTrack.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        npcTrack.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        npcTrack.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        npcTrack.setPerson(target, editedPerson);
    }

    @Override
    public void updatePerson(Person target) {
        npcTrack.updatePerson(target);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedNpcTrack}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean hasGroup(Group group) {
        return npcTrack.hasGroup(group);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return npcTrack.equals(otherModelManager.npcTrack)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }
}
