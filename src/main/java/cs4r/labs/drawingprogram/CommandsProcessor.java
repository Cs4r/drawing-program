package cs4r.labs.drawingprogram;


public interface CommandsProcessor {

    void process(Command command, DrawingContext context);

    boolean canHandle(Command command);
}
