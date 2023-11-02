package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AssignmentCommand;
import seedu.address.logic.commands.AssignmentGroupCommand;
import seedu.address.logic.commands.AssignmentIndivCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ConfirmClearCommand;
import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.commands.DeassignmentCommand;
import seedu.address.logic.commands.DeassignmentIndivCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindGroupCommand;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.commands.GradeGroupCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.InputGroupParticipationCommand;
import seedu.address.logic.commands.InputParticipationCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListParticipationCommand;
import seedu.address.logic.commands.MarkAttendanceCommand;
import seedu.address.logic.commands.MarkGroupAttendanceCommand;
import seedu.address.logic.commands.UnmarkAttendanceCommand;
import seedu.address.logic.commands.UnmarkGroupAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class NpcTrackParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(NpcTrackParser.class);
    private Command previousCommand = null;

    /**
     * Parses the user input to determine and retrieve the appropriate command for execution.
     * It updates the previous command statefully.
     *
     * @param userInput The full user input string.
     * @return The parsed command based on the user input.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        Command command;
        try {
            command = getCommand(userInput);
        } catch (ParseException e) {
            previousCommand = null;
            throw e;
        }

        previousCommand = command;

        return command;
    }

    /**
     * Parses user input to extract the command for execution.
     *
     * @param userInput The full user input string.
     * @return The appropriate command based on the user input.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    public Command getCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case AssignmentCommand.COMMAND_WORD:
            return new AssignmentCommandParser().parse(arguments);

        case AssignmentIndivCommand.COMMAND_WORD:
            return new AssignmentIndivCommandParser().parse(arguments);

        case DeassignmentCommand.COMMAND_WORD:
            return new DeassignmentCommandParser().parse(arguments);

        case DeassignmentIndivCommand.COMMAND_WORD:
            return new DeassignmentIndivCommandParser().parse(arguments);

        case AssignmentGroupCommand.COMMAND_WORD:
            return new AssignmentGroupCommandParser().parse(arguments);

        case CreateGroupCommand.COMMAND_WORD:
            return new CreateGroupCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ConfirmClearCommand.COMMAND_WORD:
            if (previousCommand instanceof ClearCommand) {
                return new ConfirmClearCommand();
            } else {
                logger.finer("This user input caused a ParseException: " + userInput);
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindGroupCommand.COMMAND_WORD:
            return new FindGroupCommandParser().parse(arguments);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case GradeGroupCommand.COMMAND_WORD:
            return new GradeGroupCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListParticipationCommand.COMMAND_WORD:
            return new ListParticipationParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case InputGroupParticipationCommand.COMMAND_WORD:
            return new InputGroupParticipationParser().parse(arguments);

        case InputParticipationCommand.COMMAND_WORD:
            return new InputParticipationParser().parse(arguments);

        case MarkAttendanceCommand.COMMAND_WORD:
            return new MarkAttendanceParser().parse(arguments);

        case UnmarkAttendanceCommand.COMMAND_WORD:
            return new UnmarkAttendanceParser().parse(arguments);

        case MarkGroupAttendanceCommand.COMMAND_WORD:
            return new MarkGroupAttendanceParser().parse(arguments);

        case UnmarkGroupAttendanceCommand.COMMAND_WORD:
            return new UnmarkGroupAttendanceParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
