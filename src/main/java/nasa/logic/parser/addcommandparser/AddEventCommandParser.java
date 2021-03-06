<<<<<<< HEAD:src/main/java/NASA/logic/parser/addcommandparser/AddEventCommandParser.java
package NASA.logic.parser.addcommandparser;

import nasa.logic.commands.addcommands.AddEventCommand;
import nasa.logic.parser.ArgumentMultimap;
import nasa.logic.parser.ArgumentTokenizer;
import nasa.logic.parser.ParserUtil;
import nasa.logic.parser.exceptions.ParseException;
import nasa.model.activity.Date;
import nasa.model.activity.Event;
import nasa.model.activity.Name;
import nasa.model.activity.Note;
import nasa.model.module.ModuleCode;

import static nasa.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nasa.logic.parser.CliSyntax.*;
=======
package nasa.logic.parser.addcommandparser;

import static nasa.logic.parser.CliSyntax.PREFIX_ACTIVITY_NAME;
import static nasa.logic.parser.CliSyntax.PREFIX_MODULE;
import static nasa.logic.parser.CliSyntax.PREFIX_NOTE;
import static nasa.logic.parser.CliSyntax.PREFIX_PRIORITY;
>>>>>>> aa5ea9c0f8c25c10f28b4f7a79851f3c35f2fc0f:src/main/java/nasa/logic/parser/addcommandparser/AddEventCommandParser.java

public class AddEventCommandParser extends AddCommandParser {

    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_FROM_DATE, PREFIX_TO_DATE, PREFIX_ACTIVITY_NAME,
                        PREFIX_PRIORITY, PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE, PREFIX_TO_DATE, PREFIX_FROM_DATE, PREFIX_ACTIVITY_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        Date toDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_TO_DATE).get());
        Date fromDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_FROM_DATE).get());
        Name activityName = ParserUtil.parseActivityName(argMultimap.getValue(PREFIX_ACTIVITY_NAME).get());
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE).get());
        Note note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
        Event event = new Event(activityName, fromDate, toDate, note);

        return new AddEventCommand(event, moduleCode);
    }
}
