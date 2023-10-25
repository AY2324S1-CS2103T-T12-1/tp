package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String telegram;
    private final String attendance;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedComment> comments = new ArrayList<>();
    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();
    private final String group;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("telegram") String telegram,
            @JsonProperty("attendance") String attendance, @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("comments") List<JsonAdaptedComment> comments,
                             @JsonProperty("assignments") List<JsonAdaptedAssignment> assignments,
                             @JsonProperty("group") String group) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.attendance = attendance;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (comments != null) {
            this.comments.addAll(comments);
        }
        if (assignments != null) {
            this.assignments.addAll(assignments);
        }
        this.group = group;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        telegram = source.getTelegramHandle().value;
        attendance = source.getAttendance().toString();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        comments.addAll(source.getComments().stream()
                .map(JsonAdaptedComment::new)
                .collect(Collectors.toList()));
        assignments.addAll(source.getAssignments().stream()
                .map(JsonAdaptedAssignment::new)
                .collect(Collectors.toList()));
        group = source.getGroup().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        final List<Comment> personComments = new ArrayList<>();
        for (JsonAdaptedComment comment : comments) {
            personComments.add(comment.toModelType());
        }

        final List<Assignment> personAssignments = new ArrayList<>();
        for (JsonAdaptedAssignment assignment : assignments) {
            personAssignments.add(assignment.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegram == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (!TelegramHandle.isValidTelegramHandle(telegram)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        final TelegramHandle modelTelegramHandle = new TelegramHandle(telegram);

        if (attendance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Attendance.class.getSimpleName()));
        }

        if (group == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Group.class.getSimpleName()));
        }
        if (!Group.isValidGroup(group)) {
            throw new IllegalValueException(Group.MESSAGE_CONSTRAINTS);
        }

        final Attendance modelAttendance = new Attendance(attendance);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Comment> modelComments = new HashSet<>(personComments);
        final Set<Assignment> modelAssignments = new HashSet<>(personAssignments);
        final Group modelGroup = new Group(group);
        return new Person(modelName, Optional.of(modelPhone), Optional.of(modelEmail),
                Optional.of(modelTelegramHandle), Optional.of(modelAttendance),
                modelTags, modelComments, modelAssignments, Optional.of(modelGroup));

    }

}
