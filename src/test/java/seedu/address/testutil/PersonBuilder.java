package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "amyTelegram";
    public static final String DEFAULT_ATTENDANCE = "0,0,0,0,0,0,0,0,0,0,0,0";
    public static final String DEFAULT_GROUP = "tut33";

    private Name name;
    private Phone phone;
    private Email email;
    private TelegramHandle telegramHandle;
    private Attendance attendance;
    private Set<Tag> tags;
    private Set<Comment> comments;
    private Set<Assignment> assignments;
    private Group group;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM);
        attendance = new Attendance(DEFAULT_ATTENDANCE);
        tags = new HashSet<>();
        comments = new HashSet<>();
        assignments = new HashSet<>();
        group = new Group(DEFAULT_GROUP);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        telegramHandle = personToCopy.getTelegramHandle();
        attendance = personToCopy.getAttendance();
        tags = new HashSet<>(personToCopy.getTags());
        comments = new HashSet<>(personToCopy.getComments());
        assignments = new HashSet<>(personToCopy.getAssignments());
        group = personToCopy.getGroup();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code comments} into a {@code Set<Comment>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withComments(String ... comments) {
        this.comments = SampleDataUtil.getCommentSet(comments);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegram(String telegram) {
        this.telegramHandle = new TelegramHandle(telegram);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendance(String attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    /**
     * Sets the {@code Assignment} of the {@code Person} that we are building.
     */
    public PersonBuilder withAssignments(String... assignmentNames) {
        this.assignments = SampleDataUtil.getAssignmentSet(assignmentNames);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Group} of the {@code Person} that we are building.
     */
    public PersonBuilder withGroup(String group) {
        this.group = new Group(group);
        return this;
    }

    /**
     * Builds a person with the given parameters.
     */
    public Person build() {
        return new Person(name, Optional.of(phone), Optional.of(email), Optional.of(telegramHandle),
                Optional.of(attendance), tags, comments, assignments, Optional.of(group));
    }

}
