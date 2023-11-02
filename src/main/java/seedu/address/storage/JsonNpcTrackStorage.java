package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyNpcTrack;

/**
 * A class to access NpcTrack data stored as a json file on the hard disk.
 */
public class JsonNpcTrackStorage implements NpcTrackStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNpcTrackStorage.class);

    private Path filePath;

    public JsonNpcTrackStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getNpcTrackFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNpcTrack> readNpcTrack() throws DataLoadingException {
        return readNpcTrack(filePath);
    }

    /**
     * Similar to {@link #readNpcTrack()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyNpcTrack> readNpcTrack(Path filePath) throws DataLoadingException {
        logger.info("Attempting to read data from file: " + filePath);
        requireNonNull(filePath);
        logger.info("success");
        Optional<JsonSerializableNpcTrack> jsonNpcTrack = JsonUtil.readJsonFile(
                filePath, JsonSerializableNpcTrack.class);
        if (!jsonNpcTrack.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNpcTrack.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveNpcTrack(ReadOnlyNpcTrack npcTrack) throws IOException {
        saveNpcTrack(npcTrack, filePath);
    }

    /**
     * Similar to {@link #saveNpcTrack(ReadOnlyNpcTrack)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveNpcTrack(ReadOnlyNpcTrack npcTrack, Path filePath) throws IOException {
        requireNonNull(npcTrack);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNpcTrack(npcTrack), filePath);
    }

}
