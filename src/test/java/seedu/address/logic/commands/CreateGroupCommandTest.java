package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CreateGroupCommandTest {

    @Test
    public void equals() {
        CreateGroupCommand command = new CreateGroupCommand("tut33", "tut34");
        assertTrue(command.equals(new CreateGroupCommand("tut33", "tut34")));
    }

    @Test
    public void equalsSameObjectTest() {
        CreateGroupCommand command = new CreateGroupCommand("tut33", "tut34");
        assertTrue(command.equals(command));
    }

    @Test
    public void equalsNullTest() {
        CreateGroupCommand command = new CreateGroupCommand("tut33", "tut34");
        assertTrue(!command.equals(null));
    }

    @Test
    public void equalsDifferentObjectTest() {
        CreateGroupCommand command = new CreateGroupCommand("tut33", "tut34");
        assertTrue(!command.equals(new ClearCommand()));
    }
}
