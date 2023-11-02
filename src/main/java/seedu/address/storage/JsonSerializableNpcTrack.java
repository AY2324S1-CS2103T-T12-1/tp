package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.NpcTrack;
import seedu.address.model.ReadOnlyNpcTrack;
import seedu.address.model.person.Person;

/**
 * An Immutable NpcTrack that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableNpcTrack {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNpcTrack} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNpcTrack(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyNpcTrack} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNpcTrack}.
     */
    public JsonSerializableNpcTrack(ReadOnlyNpcTrack source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this npc_track into the model's {@code NpcTrack} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public NpcTrack toModelType() throws IllegalValueException {
        NpcTrack npcTrack = new NpcTrack();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (npcTrack.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            npcTrack.addPerson(person);
        }
        return npcTrack;
    }

}
