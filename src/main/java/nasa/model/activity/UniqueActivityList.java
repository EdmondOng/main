package nasa.model.activity;

import static java.util.Objects.requireNonNull;
import static nasa.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import nasa.commons.core.index.Index;
import nasa.model.activity.exceptions.ActivityNotFoundException;
import nasa.model.activity.exceptions.DuplicateActivityException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A list of activities that enforces uniqueness between its elements and does not allow nulls.
 * A activity is considered unique by comparing using {@code Activity#isSameActivity(Activity)}.
 * As such, adding and updating of activity uses Activity#isSameActivity(Activity)
 * for equality so as to ensure that the activity being added or updated is
 * unique in terms of identity in the UniqueActivityList. However, the removal of a activity uses
 * Activity#equals(Object) so as to ensure that the activity with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Activity#isSameActivity(Activity)
 */
public class UniqueActivityList implements Iterable<Activity> {

    private final ObservableList<Activity> internalList = FXCollections.observableArrayList();
    private final ObservableList<Activity> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent activity as the given argument.
     */
    public boolean contains(Activity toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameActivity);
    }

    /**
     * Adds a activity to the list.
     * The activity must not already exist in the list.
     */
    public void add(Activity toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateActivityException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the activity {@code target} in the list with {@code editedActivity}.
     * {@code target} must exist in the list.
     * The activity identity of {@code editedActivity} must not be the same as another existing activity in the list.
     */
    public void setActivity(Activity target, Activity editedActivity) {
        requireAllNonNull(target, editedActivity);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ActivityNotFoundException();
        }

        if (!target.isSameActivity(editedActivity) && contains(editedActivity)) {
            throw new DuplicateActivityException();
        }

        internalList.set(index, editedActivity);
    }

    public ObservableList<Activity> getActivityList() {
        return this.internalList;
    }

    public Activity getActivityByIndex(Index index) {
        return internalList.get(index.getZeroBased());
    }

    public void setActivityByIndex(Index index, Activity activity) {
        requireNonNull(activity);

        internalList.set(index.getZeroBased(), activity);
    }

    public void editActivityByIndex(Index index, Object... args) {
        requireAllNonNull(args);

        Activity activity = internalList.get(index.getZeroBased());

        for (Object object : args) {
            if (object instanceof Note) {
                Note note = (Note) object;
                activity.setNote(note);
            }

            if (object instanceof Name) {
                Name name = (Name) object;
                activity.setName(name);
            }

            if (activity instanceof Deadline
                    && object instanceof Date) {
                Date extendDateLine = (Date) object;
                activity.setDate(extendDateLine);
            }
        }

        internalList.set(index.getZeroBased(), activity);
    }

    /**
     * Removes the equivalent activity from the list.
     * The activity must exist in the list.
     */
    public void remove(Activity toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ActivityNotFoundException();
        }
    }

    /**
     * Empty all the activity inside the list.
     */
    public void removeAll() {
       internalList.clear();
    }

    public void setActivities(UniqueActivityList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code activities}.
     * {@code activities} must not contain duplicate activities.
     */
    public void setActivities(List<Activity> activities) {
        requireAllNonNull(activities);
        if (!activitiesAreUnique(activities)) {
            throw new DuplicateActivityException();
        }

        internalList.setAll(activities);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Activity> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Activity> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueActivityList // instanceof handles nulls
                        && internalList.equals(((UniqueActivityList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code activities} contains only unique activities.
     */
    private boolean activitiesAreUnique(List<Activity> activities) {
        for (int i = 0; i < activities.size() - 1; i++) {
            for (int j = i + 1; j < activities.size(); j++) {
                if (activities.get(i).isSameActivity(activities.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
