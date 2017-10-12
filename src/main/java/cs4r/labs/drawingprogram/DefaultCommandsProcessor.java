package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.CommandNotFoundException;

import java.util.Optional;

import static java.lang.String.format;
import static java.util.Objects.isNull;

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

        failIfCommandIsNull(command);

        return commandImplementationRegistry.findImplementation(command).isPresent();
    }

    private void failIfCommandIsNull(Command command) {

        if (isNull(command)) {
            throw new IllegalArgumentException("command cannot be null");
        }
    }
}
