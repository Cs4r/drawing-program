package cs4r.labs.drawingprogram.exception;

/**
 * An exception to be thrown when {@link cs4r.labs.drawingprogram.commandimpl.ArgumentParser}
 * tries to parse and transform an argument but it fails.
 */
public class InvalidArgumentException extends RuntimeException {

    public InvalidArgumentException(String message) {
        super(message);
    }
}
