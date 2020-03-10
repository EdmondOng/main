package nasa.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import nasa.commons.core.GuiSettings;
import nasa.commons.core.LogsCenter;
import nasa.model.activity.Activity;
import nasa.model.module.Module;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final NasaBook nasaBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Module> filteredModules;

    /**
     * Initializes a ModelManager with the given NasaBook and userPrefs.
     */
    public ModelManager(ReadOnlyNasaBook NasaBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(NasaBook, userPrefs);

        logger.fine("Initializing with address book: " + NasaBook + " and user prefs " + userPrefs);

        this.nasaBook = new NasaBook(NasaBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredModules = new FilteredList<>(this.nasaBook.getModuleList());
    }

    public ModelManager() {
        this(new NasaBook(), new UserPrefs());
    }

    //=========== UserPrefsNasa ==================================================================================

    @Override
    public void setUserPrefsNasa(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getNasaBookFilePath() {
        return userPrefs.getNasaBookFilePath();
    }

    @Override
    public void setNasaBookFilePath(Path nasaBookFilePath) {
        requireNonNull(nasaBookFilePath);
        userPrefs.setNasaBookFilePath(nasaBookFilePath);
    }

    //=========== NasaBook ================================================================================

    @Override
    public void setNasaBook(ReadOnlyNasaBook NasaBook) {
        this.nasaBook.resetData(NasaBook);
    }

    @Override
    public ReadOnlyNasaBook getNasaBook() {
        return nasaBook;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return nasaBook.hasModule(module);
    }

    @Override
    public void deleteModule(Module target) {
        nasaBook.removeModule(target);
    }

    @Override
    public void addModule(Module module) {
        nasaBook.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void setModule(Module target, Module editedModule) {
        requireAllNonNull(target, editedModule);

        nasaBook.setModule(target, editedModule);
    }

    @Override
    public void addActivity(Module target, Activity activity) {
        nasaBook.addActivity(target, activity);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void removeActivity(Module target, Activity activity) {
        nasaBook.removeActivity(target, activity);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public boolean hasActivity(Module target, Activity activity) {
        requireNonNull(activity);
        return nasaBook.hasActivity(target, activity);
    }

    //=========== Filtered Module List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Module} backed by the internal list of
     * {@code versionedNasaBook}
     */
    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return nasaBook.equals(other.nasaBook)
                && userPrefs.equals(other.userPrefs)
                && filteredModules.equals(other.filteredModules);
    }

}
