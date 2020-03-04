package seedu.address.model.activity;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Activity's TaskName in NASA.
 * Guarantees: immutable; is valid as declared in {@link #isValidTask(String)}
 */
public class TaskName {

    public static final String MESSAGE_CONSTRAINTS = "Task can take any values, and it should not be blank";

    /*
     * The first character of the task must not be a whitespace,
     * in between words cannot have more than one whitespace
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[^\\s]*(\\w+\\s)*\\w+\\s*$";

    public final String task;

    /**
     * Constructs an {@code TaskName}.
     *
     * @param task A valid task name.
     */
    public TaskName(String task) {
        requireNonNull(task);
        this.task = task;
    }

    /**
     * Returns true if a given string is a valid task.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return task;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskName // instanceof handles nulls
                && task.equals(((TaskName) other).task)); // state check
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }

}
