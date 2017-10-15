package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command implementation that draws a rectangle.
 */
public class DrawRectangle implements CommandImplementation {

    private final ArgumentParser argumentParser;

    public DrawRectangle(ArgumentParser argumentParser) {
        Checks.failIfNullArgument(argumentParser, "argumentParser");

        this.argumentParser = argumentParser;
    }

    @Override
    public void execute(String arguments, DrawingContext context) {
        Checks.failIfAnyArgumentIsNull(arguments, context);

        if (context.isActive()) {
            Canvas canvas = context.getCanvas();

            Integer x1 = argumentParser.getIntArgument(arguments, 0) - 1;
            Integer y1 = argumentParser.getIntArgument(arguments, 1) - 1;
            Integer x2 = argumentParser.getIntArgument(arguments, 2) - 1;
            Integer y2 = argumentParser.getIntArgument(arguments, 3) - 1;

            canvas.drawRectangle(x1, y1, x2, y2);
        }
    }
}
