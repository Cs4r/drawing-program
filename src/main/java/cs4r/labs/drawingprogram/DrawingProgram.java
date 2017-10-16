package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.commandimpl.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

/*
 * The drawing program. Main entry point of this piece of software.
 */
public class DrawingProgram {


    private final DrawingContext context;
    private final CommandsProcessor commandsProcessor;
    private final CommandsReader commandsReader;

    public DrawingProgram(InputStream input, OutputStream output) {
        context = new DrawingContext(input, output);
        CommandImplementationRegistry registry = buildRegistry(new ArgumentParser());
        commandsProcessor = new CommandsProcessor(registry);
        commandsReader = new CommandsReader(context);
    }

    public void run() {

    }

    public DrawingContext getContext() {
        return context;
    }

    public CommandsProcessor getCommandsProcessor() {
        return commandsProcessor;
    }

    public CommandsReader getCommandsReader() {
        return commandsReader;
    }

    private InMemoryCommandImplementationRegistry buildRegistry(ArgumentParser argumentParser) {
        return new InMemoryCommandImplementationRegistry(new HashMap<String, CommandImplementation>() {{

            put(Command.PRINT_CANVAS_COMMAND.getName(), new PrintCanvas());
            put(Command.PRINT_PROMPT_COMMAND.getName(), new PrintPrompt());
            put("C", new CreateCanvas(argumentParser));
            put("L", new DrawLine(argumentParser));
            put("R", new DrawRectangle(argumentParser));
            put("B", new FillArea(argumentParser));
            put("Q", new Terminate());
        }});
    }
}
