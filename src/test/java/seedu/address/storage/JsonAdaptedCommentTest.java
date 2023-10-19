package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.fields.Comment;

public class JsonAdaptedCommentTest {
    @Test
    public void getCommentNameTest() {
        JsonAdaptedComment comment = new JsonAdaptedComment("test");
        assertEquals(comment.getCommentName(), "test");
    }

    @Test
    public void toModelTypeTest() throws IllegalValueException {
        JsonAdaptedComment comment = new JsonAdaptedComment("test");
        Comment commentModel = comment.toModelType();
        assertEquals(commentModel.commentName, "test");
    }

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
    public void constructor_validCommentName() {
        Comment comment = new Comment("test");
        JsonAdaptedComment jsonAdaptedComment = new JsonAdaptedComment(comment);
        assertEquals(jsonAdaptedComment.getCommentName(), "test");
    }

    @Test
    public void toModelType_illegalValueException() {
        JsonAdaptedComment comment = new JsonAdaptedComment("");
        assertThrows(IllegalValueException.class, () -> comment.toModelType());
    }
}
