package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fields.Comment;
import seedu.address.model.fields.Tag;
import seedu.address.model.person.Assignment;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Email;
import seedu.address.model.person.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;


/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE,
                        PREFIX_TAG, PREFIX_COMMENT, PREFIX_GROUP);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM_HANDLE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        // optional fields
        Phone phone = Phone.DEFAULT_PHONE;
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        Email email = Email.NULL_EMAIL;
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        TelegramHandle telegram = TelegramHandle.NULL_TELEGRAM_HANDLE;
        if (argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).isPresent()) {
            telegram = ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_TELEGRAM_HANDLE).get());
        }
        Attendance attendance = new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART);
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Comment> commentList = ParserUtil.parseComments(argMultimap.getAllValues(PREFIX_COMMENT));
        Set<Assignment> assignmentList = new HashSet<>();
        Group group = Group.DEFAULT_GROUP;
        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            group = ParserUtil.parseGroup(argMultimap.getValue(PREFIX_GROUP).get());
        }

        Person person = new Person(name, Optional.ofNullable(phone), Optional.ofNullable(email),
                Optional.ofNullable(telegram), Optional.of(attendance), tagList, commentList, assignmentList,
                Optional.of(group));


        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
