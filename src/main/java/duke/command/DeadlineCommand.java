package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.DukeIllegalFormatException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DeadlineCommand class encapsulates command to add a new deadline.
 */
public class DeadlineCommand extends AddCommand {
    /**
     * Constructs an DeadlineCommand with the specified description.
     *
     * @param description Description of the command.
     */
    public DeadlineCommand(String description) {
        super(description);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean shouldPrintMessage) throws DukeException {
        String[] line = description.split(" /by ");
        if (line.length != 2) {
            throw new DukeIllegalFormatException(
                "☹ OOPS!!! Seems like you have entered a wrong format for a deadline task. "
                    + "Try this instead: deadline <description> /by <date>"
            );
        }
        Task task = new Deadline(line[0], line[1]);
        tasks.add(task, storage);

        String message = "Got it. I've added this task:\n  "
            + task
            + "\nNow you have " + tasks.toArray().length + " task(s) in the list.";
        if (shouldPrintMessage) {
            ui.showMessage(message);
        }
        return message;
    }
}
