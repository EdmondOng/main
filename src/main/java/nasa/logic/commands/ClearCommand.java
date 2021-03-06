package nasa.logic.commands;

import static java.util.Objects.requireNonNull;

import nasa.model.Model;
import nasa.model.NasaBook;

/**
 * Clears the nasa book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Nasa book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setNasaBook(new NasaBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
