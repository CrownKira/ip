package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        this.duke = d;
        // get status response about data loading
        String response = duke.getResponse();
        this.showDukeDialog(response);
        this.showWelcomeDialog();
    }

    private void showDukeDialog(String dukeResponse) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeResponse, dukeImage));
    }

    private void showUserDialog(String userInput) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userInput, userImage));
    }

    private void showWelcomeDialog() {
        String response = duke.getWelcomeResponse();
        this.showDukeDialog(response);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    // TODO: make UI class handle user interaction
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        this.showUserDialog(input);
        this.showDukeDialog(response);
        userInput.clear();
    }
}
