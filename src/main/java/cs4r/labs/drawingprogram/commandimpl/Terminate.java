package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command that signals that the program must terminate.
 */
public class Terminate implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext context) {
        Checks.failIfNullArgument(context, "context");

        context.deactivate();
    }
}
