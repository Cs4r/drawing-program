package cs4r.labs.drawingprogram.exception;

/**
 * An exception to be thrown when it is not possible to write in the output because it is corrupted.
 */
public class CorruptedOutputException extends RuntimeException {

    public CorruptedOutputException(String message) {
        super(message);
    }
}
