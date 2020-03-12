package nasa.logic.commands.addcommands;

import nasa.model.activity.Deadline;
import nasa.model.module.ModuleCode;
import nasa.logic.parser.CliSyntax;

public class AddDeadlineCommand extends AddCommand {

    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the module's activity list. "
            + "Parameters:"
            + CliSyntax.PREFIX_MODULE + "MODULE CODE"
            + CliSyntax.PREFIX_DATE + "DATE"
            + CliSyntax.PREFIX_ACTIVITY_NAME + "ACTIVITY NAME"
            + CliSyntax.PREFIX_PRIORITY + "PRIORITY"
            + CliSyntax.PREFIX_NOTE + "NOTE" + "\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_MODULE + "CS3233"
            + CliSyntax.PREFIX_DATE + "2020-03-20"
            + CliSyntax.PREFIX_ACTIVITY_NAME + "SEA Group Programming Assignment"
            + CliSyntax.PREFIX_PRIORITY + "1"
            + CliSyntax.PREFIX_NOTE + "Focus on computational geometry and DP.";

    public AddDeadlineCommand(Deadline deadline, ModuleCode moduleCode) {
       super(deadline, moduleCode);
    }
}
