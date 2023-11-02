package seedu.address.testutil;

import seedu.address.model.NpcTrack;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building npc_track objects.
 * Example usage: <br>
 *     {@code NpcTrack ab = new NpcTrackBuilder().withPerson("John", "Doe").build();}
 */
public class NpcTrackBuilder {

    private NpcTrack npcTrack;

    public NpcTrackBuilder() {
        npcTrack = new NpcTrack();
    }

    public NpcTrackBuilder(NpcTrack npcTrack) {
        this.npcTrack = npcTrack;
    }

    /**
     * Adds a new {@code Person} to the {@code NpcTrack} that we are building.
     */
    public NpcTrackBuilder withPerson(Person person) {
        npcTrack.addPerson(person);
        return this;
    }

    public NpcTrack build() {
        return npcTrack;
    }
}
