package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;

import static java.util.Objects.isNull;

/**
 * A command that signals that the program must terminate.
 */
public class Terminate implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext context) {
        failIfContextIsNull(context);
    }

    private void failIfContextIsNull(DrawingContext context) {

        if (isNull(context)) {
            throw new IllegalArgumentException("context cannot be null");
        }
    }
}
