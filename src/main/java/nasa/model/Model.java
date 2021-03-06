package nasa.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import nasa.commons.core.index.Index;
import javafx.collections.ObservableList;

import nasa.commons.core.GuiSettings;
import nasa.model.activity.Activity;
import nasa.model.module.Module;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Module> PREDICATE_SHOW_ALL_MODULES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefsNasa(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getNasaBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setNasaBookFilePath(Path nasaBookFilePath);

    /**
     * Replaces address book data with the data in {@code nasaBook}.
     */
    void setNasaBook(ReadOnlyNasaBook nasaBook);

    /** Returns the NasaBook */
    ReadOnlyNasaBook getNasaBook();

    /**
     * Returns true if a module with the same identity as {@code module} exists in the address book.
     */
    boolean hasModule(Module module);

    /**
     * Deletes the given module.
     * The module must exist in the nasa book.
     */
    void deleteModule(Module target);

    /**
     * Adds the given module.
     * {@code module} must not already exist in the nasa book.
     */
    void addModule(Module module);

    /**
     * Replaces the given module {@code target} with {@code editedModule}.
     * {@code target} must exist in the nasa book.
     * The module identity of {@code editedModule} must not be the same as another existing module in the address book.
     */
    void setModule(Module target, Module editedModule);

    /**
     * Adds the given activity.
     * {@code activity} must not already exist in the nasa book.
     */
    void addActivity(Module target, Activity activity);

    /**
     * Remove the given activity.
     * {@code activity} must not already exist in the nasa book.
     */
    void removeActivity(Module target, Activity activity);

    /**
     * Returns true if a module {@code target} has {@code activity} exists in the nasa book.
     */
    boolean hasActivity(Module target, Activity activity);

    /**
     * Sets activity through it's index.
     */
    void setActivityByIndex(Module module, Index index, Activity activity);

    /**
     * Edit activity attributes through it's index.
     */
    void editActivityByIndex(Module module, Index index, Object... args);

    /** Returns an unmodifiable view of the filtered module list */
    ObservableList<Module> getFilteredModuleList();

    /**
     * Updates the filter of the filtered module list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredModuleList(Predicate<Module> predicate);

    ObservableList<Activity> getFilteredActivityList(Index index);

    void updateFilteredActivityList(Index index, Predicate<Activity> predicate);

    void removeModuleByIndex(Index index);
}


