package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command implementation that draws a rectangle.
 */
public class DrawRectangle implements CommandImplementation {

    private final ArgumentParser argumentParser;

    public DrawRectangle(ArgumentParser argumentParser) {

        this.argumentParser = argumentParser;
    }

    @Override
    public void execute(String arguments, DrawingContext context) {
        Checks.failIfAnyArgumentIsNull(arguments, context);

        if (context.isActive()) {
            Canvas canvas = context.getCanvas().orElseThrow(CanvasNotFoundException::new);

            Integer x1 = argumentParser.getPositionalArgument(arguments, 0, Integer.class) - 1;
            Integer y1 = argumentParser.getPositionalArgument(arguments, 1, Integer.class) - 1;
            Integer x2 = argumentParser.getPositionalArgument(arguments, 2, Integer.class) - 1;
            Integer y2 = argumentParser.getPositionalArgument(arguments, 3, Integer.class) - 1;

            canvas.drawRectangle(x1, y1, x2, y2);
        }
    }
}
