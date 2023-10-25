package seedu.address.ui;

import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label attendance;
    @FXML
    private Label phone;
    @FXML
    private Label telegramHandle;
    @FXML
    private Label email;
    @FXML
    private Hyperlink telegramLink;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane comments;
    @FXML
    private FlowPane assignments;
    @FXML
    private Label group;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        // Initialize the button visibility based on the presence of the Telegram handle
        if (person.getTelegramHandle() != null) {
            telegramLink.setVisible(true);
        } else {
            telegramLink.setVisible(false);
        }
        name.setText(person.getName().fullName);
        attendance.setText(String.format("Attendance: %d/%d",
                this.person.getWeeksPresent(), this.person.getTotalWeeks()));
        phone.setText("Phone: " + person.getPhone().value);
        telegramHandle.setText("Telegram Handle: " + person.getTelegramHandle().value);
        email.setText("Email: " + person.getEmail().value);
        group.setText("Tutorial Group: " + person.getGroup().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getComments().stream()
                .sorted(Comparator.comparing(comment -> comment.commentName))
                .forEach(comment -> comments.getChildren().add(new Label(comment.commentName)));
        person.getAssignments().stream()
                .sorted(Comparator.comparing(assignment -> assignment.toString()))
                .forEach(assignment -> assignments.getChildren().add(new Label(assignment.toString())));
    }

    /**
     * Opens a link to the telegram handle of {@code Person}.
     */
    @FXML
    public void openLink(ActionEvent event) {
        String telegramHandle = person.getTelegramHandle().value;
        if (telegramHandle != null && !telegramHandle.isEmpty()) {
            UiUtil.open("https://t.me/" + telegramHandle);
        } else {
            // Handle the case where there is no telegram handle
            // You can show an error message or take any other action as needed
            System.out.println("No Telegram handle available.");
        }
    }
}
