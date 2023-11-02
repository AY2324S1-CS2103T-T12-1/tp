package seedu.address.model.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.NpcTrack;
import seedu.address.model.ReadOnlyNpcTrack;
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
 * Contains utility methods for populating {@code NpcTrack} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), Optional.of(new Phone("87438807")),
                    Optional.of(new Email("alexyeoh@example.com")),
                    Optional.of(new TelegramHandle("alexTelegram")),
                    Optional.of(new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)),
                    getTagSet("CS2103T"),
                    getCommentSet("Is always cheerful"),
                    getAssignmentSet("lab1"), Optional.of(new Group("tut33"))),
            new Person(new Name("Bernice Yu"), Optional.of(new Phone("99272758")),
                    Optional.of(new Email("berniceyu@example.com")),
                    Optional.of(new TelegramHandle("berniceTelegram")),
                    Optional.of(new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)),
                    getTagSet("CS2103T"),
                    getCommentSet("Loves candy"),
                    getAssignmentSet("lab1"), Optional.of(new Group("tut33"))),
            new Person(new Name("Charlotte Oliveiro"), Optional.of(new Phone("93210283")),
                    Optional.of(new Email("charlotte@example.com")),
                    Optional.of(new TelegramHandle("charlotteTelegram")),
                    Optional.of(new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)),
                    getTagSet("CS2103T"),
                    getCommentSet("Never comes on time"),
                    getAssignmentSet("lab1"), Optional.of(new Group("tut33"))),
            new Person(new Name("David Li"), Optional.of(new Phone("91031282")),
                    Optional.of(new Email("davidli@example.com")),
                    Optional.of(new TelegramHandle("davidTelegram")),
                    Optional.of(new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)),
                    getTagSet("CS2103T"),
                    getCommentSet("Needs constant attention"),
                    getAssignmentSet("lab1"), Optional.of(new Group("tut33"))),
            new Person(new Name("Evan Low"), Optional.of(new Phone("92492021")),
                    Optional.of(new Email("evan@example.com")),
                    Optional.of(new TelegramHandle("evanTelegram")),
                    Optional.of(new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)),
                    getTagSet("CS2103T"),
                    getCommentSet("Pretty average"),
                    getAssignmentSet("lab1"), Optional.of(new Group("tut33"))),
            new Person(new Name("Fiora Laurent"), Optional.of(new Phone("92624417")),
                    Optional.of(new Email("fiora@example.com")),
                    Optional.of(new TelegramHandle("fioraTelegram")),
                    Optional.of(new Attendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)),
                    getTagSet("CS2103T"),
                    getCommentSet("Happy-go-lucky attitude"),
                    getAssignmentSet("lab1"), Optional.of(new Group("tut33")))
        };
    }

    public static ReadOnlyNpcTrack getSampleNpcTrack() {
        NpcTrack sampleAb = new NpcTrack();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a comment set containing the list of strings given.
     */
    public static Set<Comment> getCommentSet(String... strings) {
        return Arrays.stream(strings)
                .map(Comment::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a assignment set containing the list of strings given.
     */
    public static Set<Assignment> getAssignmentSet(String... strings) {
        return Arrays.stream(strings)
                .map(Name::new)
                .map(Assignment::new)
                .collect(Collectors.toSet());
    }
}

