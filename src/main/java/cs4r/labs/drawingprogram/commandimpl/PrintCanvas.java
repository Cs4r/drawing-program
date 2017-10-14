package cs4r.labs.drawingprogram.commandimpl;


import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;
import cs4r.labs.drawingprogram.exception.CorruptedOutputException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * A command implementation that prints the canvas.
 */
public class PrintCanvas implements CommandImplementation {

    @Override
    public void execute(String arguments, DrawingContext context) {
        failIfContextIsNull(context);

        if (context.isActive()) {
            OutputStream output = context.getOutput();
            Canvas canvas = getCanvasOrThrow(context);
            byte[] canvasAsBytes = canvas.toText().getBytes();

            try {
                output.write(canvasAsBytes);
                output.flush();
            } catch (IOException e) {
                throw new CorruptedOutputException("output is corrupted");
            }
        }
    }

    private Canvas getCanvasOrThrow(DrawingContext context) {
        return context.getCanvas()
                .orElseThrow(() -> new CanvasNotFoundException("no canvas to draw on"));
    }

    private void failIfContextIsNull(DrawingContext context) {
        if (Objects.isNull(context)) {
            throw new IllegalArgumentException("context cannot be null");
        }
    }
}
