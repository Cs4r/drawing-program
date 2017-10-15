package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;
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
            Canvas canvas = context.getCanvas().orElseThrow(CanvasNotFoundException::new);

            Integer x = argumentParser.getPositionalArgument(arguments, 0, Integer.class);
            Integer y = argumentParser.getPositionalArgument(arguments, 1, Integer.class);
            Character c = argumentParser.getPositionalArgument(arguments, 2, Character.class);

            canvas.fillArea(x - 1, y - 1, c);
        }
    }
}
