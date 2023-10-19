package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;

/**
 * Parses input arguments and creates a new {@code UnMarkAttendanceCommand} object
 */
public class UnmarkAttendanceParser implements Parser<UnmarkAttendanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code UnMarkAttendanceCommand}
     * and returns a {@code UnMarkAttendanceCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL);

        Index index;
        int week = 0;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkAttendanceCommand.MESSAGE_USAGE), ive);
        }

        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            week = ParserUtil.parseTutorial(argMultimap.getValue(PREFIX_TUTORIAL).get());
        }

        if (week == 0) {
            throw new ParseException(Attendance.TUTORIAL_ERROR_MSG);
        }

        return new UnmarkAttendanceCommand(index, Index.fromOneBased(week));
    }
}
