package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.fields.Comment;
import seedu.address.model.fields.Tag;

/**
 * Represents a Person in the npc_track.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Optional<Phone> phone;
    private final Optional<Email> email;

    // Data fields
    private final Optional<TelegramHandle> telegramHandle;
    private final Optional<Attendance> attendance;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Comment> comments = new HashSet<>();
    private final Set<Assignment> assignments = new HashSet<>();
    private final Optional<Group> group;

    /**
     * Only name field must be present and not null.
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.phone = Optional.empty();
        this.email = Optional.empty();
        this.telegramHandle = Optional.empty();
        this.attendance = Optional.empty();
        this.group = Optional.empty();
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Optional<Phone> phone, Optional<Email> email, Optional<TelegramHandle> telegramHandle,
                  Optional<Attendance> attendance,
                  Set<Tag> tags, Set<Comment> comments, Set<Assignment> assignments, Optional<Group> group) {
        requireAllNonNull(name, phone, email, telegramHandle, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.attendance = attendance;
        this.telegramHandle = telegramHandle;
        this.tags.addAll(tags);
        this.comments.addAll(comments);
        this.assignments.addAll(assignments);
        this.group = group;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone.orElse(null);
    }

    public Email getEmail() {
        return email.orElse(null);
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle.orElse(null);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable comment set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(comments);
    }

    /**
     * Returns an immutable assignment set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Assignment> getAssignments() {
        return Collections.unmodifiableSet(assignments);
    }

    /**
     * Gets the student's attendance record.
     *
     * @return The `Attendance` object representing the student's attendance for a specific period.
     */
    public Attendance getAttendance() {
        return this.attendance.orElse(null);
    }

    /**
     * Retrieves the number of weeks the student was present based on their attendance record.
     *
     * @return The count of weeks for which the student was marked as present.
     */
    public int getWeeksPresent() {
        Attendance stdatd = this.attendance.orElse(null);
        if (stdatd != null) {
            return stdatd.getWeeksPresent();
        }
        return 0;
    }

    /**
     * Retrieves the total number of weeks in the attendance record period.
     *
     * @return The total number of weeks in the period, typically 12 weeks.
     */
    public int getTotalWeeks() {
        Attendance stdatd = this.attendance.orElse(null);
        if (stdatd != null) {
            return stdatd.getTotalWeeks();
        }
        return 0;
    }

    /**
     * Retrieves the total number of participation points.
     *
     * @return The total number of participation points.
     */
    public int getTotalPart() {
        Attendance stdatd = this.attendance.orElse(null);
        if (stdatd != null) {
            return stdatd.getTotalPart();
        }
        return 0;
    }

    public Group getGroup() {
        return group.orElse(null);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && telegramHandle.equals(otherPerson.telegramHandle)
                && attendance.equals(otherPerson.attendance)
                && tags.equals(otherPerson.tags)
                && comments.equals(otherPerson.comments)
                && assignments.equals(otherPerson.assignments)
                && group.equals(otherPerson.group);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, telegramHandle, attendance, tags, comments, assignments, group);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone.orElse(null))
                .add("email", email.orElse(null))
                .add("telegram", telegramHandle.orElse(null))
                .add("attendance", attendance.orElse(null))
                .add("tags", tags)
                .add("comments", comments)
                .add("assignments", assignments)
                .add("group", group.orElse(null))
                .toString();
    }

}
