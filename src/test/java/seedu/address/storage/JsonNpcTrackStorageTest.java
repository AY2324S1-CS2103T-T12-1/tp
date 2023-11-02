package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalNpcTrack;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.NpcTrack;
import seedu.address.model.ReadOnlyNpcTrack;

public class JsonNpcTrackStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonNpcTrackStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readNpcTrack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readNpcTrack(null));
    }

    private java.util.Optional<ReadOnlyNpcTrack> readNpcTrack(String filePath) throws Exception {
        return new JsonNpcTrackStorage(Paths.get(filePath)).readNpcTrack(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readNpcTrack("NonExistentFile.json").isPresent());
    }

    @Test
    public void readNpcTrack_invalidPersonNpcTrack_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readNpcTrack("invalidPersonNpcTrack.json"));
    }

    @Test
    public void readNpcTrack_invalidAndValidPersonNpcTrack_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readNpcTrack("invalidAndValidPersonNpcTrack.json"));
    }

    @Test
    public void readAndSaveNpcTrack_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempNpcTrack.json");
        NpcTrack original = getTypicalNpcTrack();
        JsonNpcTrackStorage jsonNpcTrackStorage = new JsonNpcTrackStorage(filePath);

        // Save in new file and read back
        jsonNpcTrackStorage.saveNpcTrack(original, filePath);
        ReadOnlyNpcTrack readBack = jsonNpcTrackStorage.readNpcTrack(filePath).get();
        assertEquals(original, new NpcTrack(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonNpcTrackStorage.saveNpcTrack(original, filePath);
        readBack = jsonNpcTrackStorage.readNpcTrack(filePath).get();
        assertEquals(original, new NpcTrack(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonNpcTrackStorage.saveNpcTrack(original); // file path not specified
        readBack = jsonNpcTrackStorage.readNpcTrack().get(); // file path not specified
        assertEquals(original, new NpcTrack(readBack));

    }

    @Test
    public void saveNpcTrack_nullNpcTrack_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNpcTrack(null, "SomeFile.json"));
    }

    /**
     * Saves {@code npcTrack} at the specified {@code filePath}.
     */
    private void saveNpcTrack(ReadOnlyNpcTrack npcTrack, String filePath) {
        try {
            new JsonNpcTrackStorage(Paths.get(filePath))
                    .saveNpcTrack(npcTrack, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveNpcTrack_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveNpcTrack(new NpcTrack(), null));
    }
}
