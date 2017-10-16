package cs4r.labs.drawingprogram;


/**
 * Determines what happens when a runtime exception is thrown during the execution of
 * {@link DrawingCommandsInterpreter#interpretCommands(DrawingContext)}.
 * <p>
 * Implementations of this interface have freedom to handle the exceptions:
 * <ul>
 * <li>They can halt the program.</li>
 * <li>They can recover from the exception.</li>
 * <li>They can just log the exception.</li>
 * </ul>
 */
public interface ExceptionHandler {

    /**
     * Handles
     *
     * @param exception the exception to be handle.
     * @param context   the context where the exception occurred.
     */
    void handle(RuntimeException exception, DrawingContext context);
}
