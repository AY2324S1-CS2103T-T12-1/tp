package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.NpcTrack;
import seedu.address.model.person.Person;

public class SampleDataUtilTest {

    private Person[] testerSample = SampleDataUtil.getSamplePersons();

    @Test
    public void getSamplePersons() {
        assertNotEquals(SampleDataUtil.getSamplePersons(), testerSample);
    }

    @Test
    public void getSampleNpcTrack() {
        NpcTrack addr = new NpcTrack();
        for (Person p : testerSample) {
            addr.addPerson(p);
        }
        assertEquals(SampleDataUtil.getSampleNpcTrack(), addr);
    }

    @Test
    public void getCommentSet() {
        assertEquals(SampleDataUtil.getCommentSet(), SampleDataUtil.getCommentSet());
    }
}
