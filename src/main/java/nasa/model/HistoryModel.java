package nasa.model;

/**
 * API of History interface.
 */
public interface HistoryModel<T> {

    boolean undo();

    boolean redo();

    void add(T list);
}
