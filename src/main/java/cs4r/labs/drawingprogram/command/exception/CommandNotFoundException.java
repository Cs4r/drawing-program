package cs4r.labs.drawingprogram.command.exception;


import cs4r.labs.drawingprogram.command.CommandsProcessor;

/**
 * An exception to be thrown when a console command is not found (not recognised by the {@link CommandsProcessor})
 */
public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String message) {
        super(message);
    }
}
