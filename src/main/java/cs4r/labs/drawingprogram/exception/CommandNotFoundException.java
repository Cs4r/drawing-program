package cs4r.labs.drawingprogram.exception;


/**
 * An exception to be thrown when a console command is not found (not recognised by the {@link cs4r.labs.drawingprogram.CommandsProcessor})
 */
public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String message) {
        super(message);
    }
}
