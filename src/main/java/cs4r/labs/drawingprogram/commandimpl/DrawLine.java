package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command implementation that draws a line in the canvas.
 */
public class DrawLine implements CommandImplementation {

    private final ArgumentParser argumentParser;

    public DrawLine(ArgumentParser argumentParser) {
        Checks.failIfNullArgument(argumentParser, "argumentParser");
        this.argumentParser = argumentParser;
    }

    @Override
    public void execute(String arguments, DrawingContext drawingContext) {
        Checks.failIfAnyArgumentIsNull(arguments, drawingContext);

        if (drawingContext.isActive()) {
            Canvas canvas = drawingContext.getCanvas();

            Integer x1 = argumentParser.getIntArgument(arguments, 0) - 1;
            Integer y1 = argumentParser.getIntArgument(arguments, 1) - 1;
            Integer x2 = argumentParser.getIntArgument(arguments, 2) - 1;
            Integer y2 = argumentParser.getIntArgument(arguments, 3) - 1;

            canvas.drawLine(x1, y1, x2, y2);
        }
    }
}
