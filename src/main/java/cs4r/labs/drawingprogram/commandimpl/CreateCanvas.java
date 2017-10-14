package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command implementation that creates a new canvas.
 */
public class CreateCanvas implements CommandImplementation {
    @Override
    public void execute(String arguments, DrawingContext context) {

        Checks.failIfNullArgument(context, "context");
    }
}
