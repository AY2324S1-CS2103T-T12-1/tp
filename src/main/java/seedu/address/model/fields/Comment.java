package seedu.address.model.fields;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Comment in the npc_track.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCommentName(String)}
 */
public class Comment {

    public static final String MESSAGE_CONSTRAINTS = "Comments can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String commentName;

    /**
     * Constructs a {@code Comment}.
     *
     * @param commentName A valid tag name.
     */
    public Comment(String commentName) {
        requireNonNull(commentName);
        checkArgument(isValidCommentName(commentName), MESSAGE_CONSTRAINTS);
        this.commentName = commentName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidCommentName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherComment = (Comment) other;
        return commentName.equals(otherComment.commentName);
    }

    @Override
    public int hashCode() {
        return commentName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + commentName + ']';
    }

}
