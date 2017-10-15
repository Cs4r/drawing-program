package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command implementation that fills an area with a given color.
 */
public class FillArea implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext drawingContext) {
        Checks.failIfAnyArgumentIsNull(arguments, drawingContext);
    }
}
