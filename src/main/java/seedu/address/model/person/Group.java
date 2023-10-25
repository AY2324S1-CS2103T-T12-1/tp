package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Person's group in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGroup(String)}
 */
public class Group {
    public static final Group DEFAULT_GROUP = new Group("Default");
    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric characters and"
            + " spaces, and it should not be blank";
    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    /**
     * Constructs a {@code Group}.
     *
     * @param groupName A valid group name.
     */
    public Group(String groupName) {
        requireNonNull(groupName);
        checkArgument(isValidGroup(groupName), MESSAGE_CONSTRAINTS);
        this.value = groupName.trim();
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Returns true if a given string is a valid group name.
     */
    public static boolean isValidGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Group)) {
            return false;
        }

        Group otherGroup = (Group) other;
        return value.equals(otherGroup.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
