package cs4r.labs.drawingprogram.exception;


/**
 * An exception to be thrown when a console command is not well formed
 */
public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String message) {
        super(message);
    }
}
