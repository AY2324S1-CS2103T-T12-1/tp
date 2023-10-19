package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Comment(null));
    }

    @Test
    public void constructor_invalidCommentName_throwsIllegalArgumentException() {
        String invalidCommentName = "";
        assertThrows(IllegalArgumentException.class, () -> new Comment(invalidCommentName));
    }

    @Test
    public void isValidCommentName() {
        // null comment name
        assertThrows(NullPointerException.class, () -> Comment.isValidCommentName(null));
    }

    @Test
    public void equals() {
        // same object -> returns true
        Comment comment = new Comment("test");
        assertTrue(comment.equals(comment));

        // same values -> returns true
        Comment commentCopy = new Comment("test");
        assertTrue(comment.equals(commentCopy));

        // different types -> returns false
        assertFalse(comment.equals(1));

        // null -> returns false
        assertFalse(comment.equals(null));

        // different comment -> returns false
        Comment differentComment = new Comment("different");
        assertFalse(comment.equals(differentComment));
    }

    @Test
    public void hashCodeTest() {
        Comment comment = new Comment("test");
        assertEquals(comment.hashCode(), comment.hashCode());
    }

    @Test
    public void toStringTest() {
        Comment comment = new Comment("test");
        assertEquals(comment.toString(), "[test]");
    }
}
