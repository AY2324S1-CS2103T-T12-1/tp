package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CreateGroupCommand object
 */
public class CreateGroupCommandParser implements Parser<CreateGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateGroupCommand
     * and returns a CreateGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateGroupCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE));
        }
        String[] nameKeywords = trimmedArgs.split("\\s+");
        if (nameKeywords.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE));
        }
        String groupName = nameKeywords[0];
        String updatedGroupName = nameKeywords[1];
        return new CreateGroupCommand(groupName, updatedGroupName);
    }
}
