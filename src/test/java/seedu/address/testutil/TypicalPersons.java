package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PART_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PART_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.NpcTrack;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTelegram("aliceTelegram").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)
            .build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTelegram("bensonTelegram")
            .withEmail("benson@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)
            .build();

    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("carl@example.com").withTelegram("carlTelegram")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART).build();;
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("daniel@example.com").withTelegram("danielTelegram")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("94822242")
            .withEmail("elle@example.com").withTelegram("elleTelegram")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)
            .build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("94824271")
            .withEmail("fiona@example.com").withTelegram("fionaTelegram")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART)
            .build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("94824424")
            .withEmail("george@example.com").withTelegram("georgeTelegram")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("84824249")
            .withEmail("hoon@example.com").withTelegram("hoonTelegram")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART).build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("84821318")
            .withEmail("ida@example.com").withTelegram("idaTelegram")
            .withAttendance(Attendance.ORIGINAL_ATD, Attendance.ORIGINAL_PART).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY).withTags(VALID_TAG_FRIEND)
            .withAttendance(VALID_ATTENDANCE_AMY, VALID_PART_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withAttendance(VALID_ATTENDANCE_BOB, VALID_PART_BOB).build();


    public static final String KEYWORD_MATCHING_MEIER = "Meier";

    private TypicalPersons() {
        // prevents instantiation
    }

    /**
     * Returns an {@code NpcTrack} with all the typical persons.
     */
    public static NpcTrack getTypicalNpcTrack() {
        NpcTrack ab = new NpcTrack();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
