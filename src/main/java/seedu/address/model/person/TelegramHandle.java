package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address in the npc_track.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegramHandle(String)}
 */
public class TelegramHandle {

    public static final TelegramHandle NULL_TELEGRAM_HANDLE = null;
    public static final String MESSAGE_CONSTRAINTS = "Telegram Handle must "
            + "use a-z, 0-9 and underscores. Minimum length is 5 characters and maximum is 32 characters. It must "
            + "start with a letter and cannot end with an underscore";

    /*
     * The telegram handle uses a-z, 0-9 and underscores.
     * Minimum length is 5 characters and maximum is 32 characters.
     * It must start with a letter and cannot end with an underscore.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z][a-zA-Z0-9_]{3,30}[a-zA-Z0-9]$";

    public final String value;


    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public TelegramHandle(String address) {
        requireNonNull(address);
        checkArgument(isValidTelegramHandle(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TelegramHandle)) {
            return false;
        }

        TelegramHandle otherTelegramHandle = (TelegramHandle) other;
        return value.equals(otherTelegramHandle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
