package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.CommandNotFoundException;

import java.util.Optional;

import static java.lang.String.format;
import static java.util.Objects.isNull;

/**
 * Processes {@link Command}s.
 */
public class CommandsProcessor {

    private CommandImplementationRegistry commandImplementationRegistry;

    /**
     * Constructs a {@link CommandsProcessor}.
     *
     * @param commandImplementationRegistry a registry of {@link CommandImplementation}s. Must be non-null.
     */
    public CommandsProcessor(CommandImplementationRegistry commandImplementationRegistry) {

        failIfRegistryIsNull(commandImplementationRegistry);

        this.commandImplementationRegistry = commandImplementationRegistry;
    }

    /**
     * Process a {@link Command}. The command effects take action in a {@link DrawingContext}.
     *
     * @param command Command to process. Must be non-null.
     * @param context Context where the effects take action. Must be non-null.
     * @throws CommandNotFoundException in case the command is not recognised by this {@link CommandsProcessor}.
     */
    public void process(Command command, DrawingContext context) {

        failIfAnyArgumentIsNull(command, context);

        Optional<CommandImplementation> commandImplementation = commandImplementationRegistry.findImplementation(command);

        if (commandImplementation.isPresent()) {
            commandImplementation.get().execute(context);
        } else {
            String commandName = command.getName();
            throw new CommandNotFoundException(format("Command \"%s\" not found", commandName));
        }
    }

    /**
     * Checks if a command can be handled by this {@link CommandsProcessor}.
     *
     * @param command the command to check. Must be non-null.
     * @return true if this {@link CommandsProcessor} can handle the given {@link Command}, false otherwise.
     */
    public boolean canHandle(Command command) {

        failIfCommandIsNull(command);

        return commandImplementationRegistry.findImplementation(command).isPresent();
    }


    private <T> void throwIllegalArgumentExceptionIfNullArgument(T object, String argumentName) {
        if (isNull(object)) {
            throw new IllegalArgumentException(argumentName + " cannot be null");
        }
    }

    private void failIfRegistryIsNull(CommandImplementationRegistry registry) {

        throwIllegalArgumentExceptionIfNullArgument(registry, "CommandImplementationRegistry");

    }

    private void failIfAnyArgumentIsNull(Command command, DrawingContext context) {

        failIfCommandIsNull(command);
        failIfContextIsNull(context);
    }

    private void failIfContextIsNull(DrawingContext context) {

        throwIllegalArgumentExceptionIfNullArgument(context, "context");
    }

    private void failIfCommandIsNull(Command command) {

        throwIllegalArgumentExceptionIfNullArgument(command, "command");
    }
}
