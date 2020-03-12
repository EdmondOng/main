package nasa.logic.parser;

import nasa.logic.commands.AddModuleCommand;
import nasa.logic.parser.exceptions.ParseException;

import nasa.model.module.Module;
import nasa.model.module.ModuleName;
import nasa.model.module.ModuleCode;
import nasa.commons.core.Messages;

import java.util.stream.Stream;

public class AddModuleCommandParser implements Parser<AddModuleCommand> {

    @Override
    public AddModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_MODULE_NAME);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_MODULE, CliSyntax.PREFIX_MODULE_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE));
        }

        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(CliSyntax.PREFIX_MODULE).get());
        ModuleName moduleName = ParserUtil.parseModuleName(argMultimap.getValue(CliSyntax.PREFIX_MODULE_NAME).get());
        Module module = new Module(moduleCode, moduleName);
        return new AddModuleCommand(module);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
