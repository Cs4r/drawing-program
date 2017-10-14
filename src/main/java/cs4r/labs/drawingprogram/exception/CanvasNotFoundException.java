package cs4r.labs.drawingprogram.exception;

/**
 * An exception to be thrown when the canvas is required but not found.
 */
public class CanvasNotFoundException extends RuntimeException {

    public CanvasNotFoundException() {
        super("no canvas to draw on");
    }
}
