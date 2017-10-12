package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.CommandNotFoundException;

import java.util.Optional;

import static java.lang.String.format;

/**
 * Default implementation of {@link CommandsProcessor}.
 */
public class DefaultCommandsProcessor implements CommandsProcessor {

    private CommandImplementationRegistry commandImplementationRegistry;

    public DefaultCommandsProcessor(CommandImplementationRegistry commandImplementationRegistry) {

        this.commandImplementationRegistry = commandImplementationRegistry;
    }

    @Override
    public void process(Command command, DrawingContext context) {

        Optional<CommandImplementation> commandImplementation = commandImplementationRegistry.findImplementation(command);

        if (commandImplementation.isPresent()) {
            commandImplementation.get().execute(context);
        } else {
            String commandName = command.getName();
            throw new CommandNotFoundException(format("Command \"%s\" not found", commandName));
        }
    }

    @Override
    public boolean canHandle(Command command) {
        return commandImplementationRegistry.findImplementation(command).isPresent();
    }
}
