package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyNpcTrack;

/**
 * Represents a storage for {@link seedu.address.model.NpcTrack}.
 */
public interface NpcTrackStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getNpcTrackFilePath();

    /**
     * Returns NpcTrack data as a {@link ReadOnlyNpcTrack}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyNpcTrack> readNpcTrack() throws DataLoadingException;

    /**
     * @see #getNpcTrackFilePath()
     */
    Optional<ReadOnlyNpcTrack> readNpcTrack(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyNpcTrack} to the storage.
     * @param npcTrack cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNpcTrack(ReadOnlyNpcTrack npcTrack) throws IOException;

    /**
     * @see #saveNpcTrack(ReadOnlyNpcTrack)
     */
    void saveNpcTrack(ReadOnlyNpcTrack npcTrack, Path filePath) throws IOException;

}
