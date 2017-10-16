package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.command.*;
import cs4r.labs.drawingprogram.command.commandimpl.*;
import cs4r.labs.drawingprogram.command.exception.ExceptionHandler;
import cs4r.labs.drawingprogram.command.exception.NaiveExceptionHandler;

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
    private final DrawingCommandsInterpreter drawingCommandsInterpreter;
    private final ExceptionHandler exceptionHandler;

    public DrawingProgram(InputStream input, OutputStream output) {
        context = new DrawingContext(input, output);
        CommandImplementationRegistry registry = buildRegistry(new ArgumentParser());
        commandsProcessor = new CommandsProcessor(registry);
        commandsReader = new CommandsReader(context);
        exceptionHandler = new NaiveExceptionHandler();
        drawingCommandsInterpreter = new DrawingCommandsInterpreter(commandsReader, commandsProcessor, exceptionHandler);
    }

    public void run() {
        drawingCommandsInterpreter.interpretCommands(context);
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

    public DrawingCommandsInterpreter getDrawingCommandsInterpreter() {
        return drawingCommandsInterpreter;
    }

    private InMemoryCommandImplementationRegistry buildRegistry(ArgumentParser argumentParser) {
        return new InMemoryCommandImplementationRegistry(new HashMap<String, CommandImplementation>() {{

            put(SupportedCommands.PRINT_CANVAS_COMMAND.getName(), new PrintCanvas());
            put(SupportedCommands.PRINT_PROMPT_COMMAND.getName(), new PrintPrompt());
            put(SupportedCommands.CREATE_CANVAS_COMMAND, new CreateCanvas(argumentParser));
            put(SupportedCommands.DRAW_LINE_COMMAND, new DrawLine(argumentParser));
            put(SupportedCommands.DRAW_RECTANGLE_COMMAND, new DrawRectangle(argumentParser));
            put(SupportedCommands.FILL_AREA_COMMAND, new FillArea(argumentParser));
            put(SupportedCommands.QUIT_COMMAND, new Terminate());
        }});
    }

    public ExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public static void main(String[] args) {

        new DrawingProgram(System.in, System.out).run();
    }
}
