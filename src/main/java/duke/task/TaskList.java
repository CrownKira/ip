package duke.task;

import duke.Storage;

import java.io.IOException;
import java.util.ArrayList;


public class TaskList extends ArrayList<Task> {
    /**
     * Constructs a empty TaskList.
     */
    public TaskList() {
        super();
    }

    /**
     * Constructs a TaskList with the specified tasks.
     *
     * @param tasks Tasks to be contained in the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.addAll(tasks);
    }

    /**
     * Adds task to the TaskList and save it to db.
     *
     * @param task    Task to be added.
     * @param storage Storage used to save the task.
     * @return true (as specified by Collection.add).
     */
    public boolean add(Task task, Storage storage) {
        boolean result = super.add(task);
        try {
            storage.save(task);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param index   The index of the task to be removed.
     * @param storage Storage used to save the new tasks after removing the specified task.
     * @return The Task that was removed from the list
     */
    public Task remove(int index, Storage storage) {
        // TODO: find and remove from file O(N)
        Task task = super.remove(index);
        try {
            storage.save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return task;
    }

    /**
     * Marks the task as done and save the new list to db.
     *
     * @param task    Task to be marked as done.
     * @param storage Storage used to save the new tasks after marking the specified task as done.
     */
    public void markAsDone(Task task, Storage storage) {
        task.markAsDone();
        try {
            storage.save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
