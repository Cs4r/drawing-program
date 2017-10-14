package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;

import java.util.Objects;

/**
 * A command implementation that draws a line in the canvas.
 */
public class DrawLine implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext drawingContext) {

        failIfAnyArgumentIsNull(arguments, drawingContext);

    }

    private void failIfAnyArgumentIsNull(Object... arguments) {
        for (Object arg : arguments) {
            if (Objects.isNull(arg)) {
                throw new IllegalArgumentException("arguments cannot be null");
            }
        }
    }
}
