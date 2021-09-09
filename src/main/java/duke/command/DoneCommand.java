package duke.command;

import duke.Duke;
import duke.Storage;
import duke.exception.DukeIndexOutOfRangeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DoneCommand class encapsulates command to mark a task as done.
 */
public class DoneCommand extends Command {
    /**
     * Index of the task to be marked done.
     */
    private int index;

    /**
     * Constructs an DoneCommand with the specified index.
     *
     * @param index Index of the task to be marked as done.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Duke duke, TaskList tasks, Storage storage)
        throws DukeIndexOutOfRangeException {
        if (index < 1 || index > tasks.toArray().length) {
            throw new DukeIndexOutOfRangeException(
                tasks.toArray().length > 0 ? "OOPS!!! I'm sorry, index is out of range! "
                    + "Please try with a number from 1 to " + tasks
                    .toArray().length : "OOPS!!! I'm sorry, there is nothing in the list yet.");
        } else {
            Task task = tasks.get(index - 1);
            tasks.markAsDone(task, storage);

            String message = "Nice! I've marked this task as done:\n  " + task;
            duke.setResponse(message);
        }
    }
}
