package cs4r.labs.drawingprogram;

/**
 * Runs the logic associated with a console command.
 */
public interface CommandImplementation {

    /**
     * Executes the "business" logic of a console command.
     *
     * @param arguments      the console command arguments. Implementations of this interface can decide if can be null or not.
     * @param drawingContext context where the effects of the command should be manifested. Must be non-null.
     */
    void execute(String arguments, DrawingContext drawingContext);
}
