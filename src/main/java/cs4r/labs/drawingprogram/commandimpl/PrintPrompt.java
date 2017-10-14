package cs4r.labs.drawingprogram.commandimpl;


import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CorruptedOutputException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * A command implementation that prints the console prompt.
 */
public class PrintPrompt implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext context) {
        // Note that we don't validate the first argument as it is not used.
        failIfContextIsNull(context);

        if (context.isActive()) {
            OutputStream output = context.getOutput();
            try {
                output.write("enter command:".getBytes());
                output.flush();
            } catch (IOException e) {
                throw new CorruptedOutputException();
            }
        }
    }

    private void failIfContextIsNull(DrawingContext context) {

        if (Objects.isNull(context)) {
            throw new IllegalArgumentException("context cannot be null");
        }
    }
}
