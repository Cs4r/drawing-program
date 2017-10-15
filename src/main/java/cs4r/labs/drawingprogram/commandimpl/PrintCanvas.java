package cs4r.labs.drawingprogram.commandimpl;


import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CorruptedOutputException;
import cs4r.labs.drawingprogram.util.Checks;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A command implementation that prints the canvas.
 */
public class PrintCanvas implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext context) {
        Checks.failIfNullArgument(context, "context");

        if (context.isActive()) {
            OutputStream output = context.getOutput();
            Canvas canvas = context.getCanvas();
            byte[] canvasAsBytes = canvas.toText().getBytes();

            try {
                output.write(canvasAsBytes);
                output.flush();
            } catch (IOException e) {
                throw new CorruptedOutputException();
            }
        }
    }
}
