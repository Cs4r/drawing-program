package cs4r.labs.drawingprogram;

import java.util.Optional;

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

        commandImplementation.get().execute(context);
    }

    @Override
    public boolean canHandle(Command command) {
        return false;
    }
}
