package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;

import java.util.Objects;

/**
 * A command implementation that draws a line in the canvas.
 */
public class DrawLine implements CommandImplementation {

    private final ArgumentParser argumentParser;

    public DrawLine(ArgumentParser argumentParser) {

        this.argumentParser = argumentParser;
    }

    @Override
    public void execute(String arguments, DrawingContext drawingContext) {

        failIfAnyArgumentIsNull(arguments, drawingContext);

        drawingContext.isActive();
        Canvas canvas = drawingContext.getCanvas().get();

        Integer x1 = argumentParser.getPositionalArgument(0, Integer.class).get() - 1;
        Integer y1 = argumentParser.getPositionalArgument(1, Integer.class).get() - 1;
        Integer x2 = argumentParser.getPositionalArgument(2, Integer.class).get() - 1;
        Integer y2 = argumentParser.getPositionalArgument(3, Integer.class).get() - 1;

        canvas.drawLine(x1, y1, x2, y2);
    }

    private void failIfAnyArgumentIsNull(Object... arguments) {
        for (Object arg : arguments) {
            if (Objects.isNull(arg)) {
                throw new IllegalArgumentException("arguments cannot be null");
            }
        }
    }
}
