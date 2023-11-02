package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyNpcTrack;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of NpcTrack data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private NpcTrackStorage npcTrackStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code NpcTrackStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(NpcTrackStorage npcTrackStorage, UserPrefsStorage userPrefsStorage) {
        this.npcTrackStorage = npcTrackStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ NpcTrack methods ==============================

    @Override
    public Path getNpcTrackFilePath() {
        return npcTrackStorage.getNpcTrackFilePath();
    }

    @Override
    public Optional<ReadOnlyNpcTrack> readNpcTrack() throws DataLoadingException {
        return readNpcTrack(npcTrackStorage.getNpcTrackFilePath());
    }

    @Override
    public Optional<ReadOnlyNpcTrack> readNpcTrack(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return npcTrackStorage.readNpcTrack(filePath);
    }

    @Override
    public void saveNpcTrack(ReadOnlyNpcTrack npcTrack) throws IOException {
        saveNpcTrack(npcTrack, npcTrackStorage.getNpcTrackFilePath());
    }

    @Override
    public void saveNpcTrack(ReadOnlyNpcTrack npcTrack, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        npcTrackStorage.saveNpcTrack(npcTrack, filePath);
    }

}
