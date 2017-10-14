package cs4r.labs.drawingprogram.commandimpl;


import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;

import java.util.Objects;

/**
 * A command implementation that prints the canvas.
 */
public class PrintCanvas implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext context) {
        failIfContextIsNull(context);
    }

    private void failIfContextIsNull(DrawingContext context) {
        if (Objects.isNull(context)) {
            throw new IllegalArgumentException("context cannot be null");
        }
    }
}
