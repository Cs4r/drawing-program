package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.CommandImplementation;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

/**
 * A command implementation that creates a new canvas.
 */
public class CreateCanvas implements CommandImplementation {
    private final ArgumentParser argumentParser;

    public CreateCanvas(ArgumentParser argumentParser) {
        Checks.failIfNullArgument(argumentParser, "argumentParser");
        this.argumentParser = argumentParser;
    }

    @Override
    public void execute(String arguments, DrawingContext context) {
        Checks.failIfNullArgument(context, "context");

        if (context.isActive()) {
            Integer width = argumentParser.getPositionalArgument(arguments, 0, Integer.class);
            Integer height = argumentParser.getPositionalArgument(arguments, 1, Integer.class);

            context.setCanvas(new Canvas(height, width));
        }
    }
}
