package cs4r.labs.drawingprogram;


public interface CommandsReader {

    /**
     * @param drawingContext
     * @return
     * @throw {@link cs4r.labs.drawingprogram.exception.InvalidCommandException} when the next element in the input
     * cannot be interpret as a command
     */
    Command nextCommand(DrawingContext drawingContext);
}

