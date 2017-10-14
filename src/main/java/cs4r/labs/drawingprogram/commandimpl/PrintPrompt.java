package cs4r.labs.drawingprogram.commandimpl;


import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;

import java.util.Objects;

public class PrintPrompt implements CommandImplementation {

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
