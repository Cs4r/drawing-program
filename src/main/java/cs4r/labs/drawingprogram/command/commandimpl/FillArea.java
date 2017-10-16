package cs4r.labs.drawingprogram.command.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.command.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command implementation that fills an area with a given color.
 */
public class FillArea implements CommandImplementation {

    private final ArgumentParser argumentParser;

    public FillArea(ArgumentParser argumentParser) {
        Checks.failIfNullArgument(argumentParser, "argumentParser");
        this.argumentParser = argumentParser;
    }

    @Override
    public void execute(String arguments, DrawingContext context) {
        Checks.failIfAnyArgumentIsNull(arguments, context);

        if (context.isActive()) {
            Canvas canvas = context.getCanvas();

            Integer x = argumentParser.getIntArgument(arguments, 0);
            Integer y = argumentParser.getIntArgument(arguments, 1);
            Character c = argumentParser.getCharArgument(arguments, 2);

            canvas.fillArea(x - 1, y - 1, c);
        }
    }
}
