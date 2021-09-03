package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

/**
 * EventCommand class encapsulates command to add a new event.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs an EventCommand with the specified description.
     *
     * @param description Description of the command.
     */
    public EventCommand(String description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean shouldPrintMessage) throws DukeException {
        String[] line = description.split(" /at ");
        if (line.length != 2) {
            throw new DukeIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong format for an event task. "
                    + "Try this instead: event <description> /at <date>"
            );
        }
        Task task = new Event(line[0], line[1]);
        tasks.add(task, storage);

        String message = "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.toArray().length
            + " task(s) in the list.";
        if (shouldPrintMessage) {
            ui.showMessage(message);
        }
        return message;
    }
}
