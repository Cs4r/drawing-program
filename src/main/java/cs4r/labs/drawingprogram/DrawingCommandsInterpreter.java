package cs4r.labs.drawingprogram;


public class DrawingCommandsInterpreter {

    private final CommandsReader commandsReader;
    private final CommandsProcessor commandsProcessor;

    public DrawingCommandsInterpreter(CommandsReader commandsReader,
                                      CommandsProcessor commandsProcessor) {

        this.commandsReader = commandsReader;
        this.commandsProcessor = commandsProcessor;
    }

    public void interpretCommands(DrawingContext context) {
        beforeProcessingCommand(context);
        Command command = readCommand(context);
        processCommand(context, command);
        afterProcessingCommand(context);
    }

    private void processCommand(DrawingContext context, Command command) {
        commandsProcessor.process(command, context);
    }

    private Command readCommand(DrawingContext context) {
        return commandsReader.nextCommand(context);
    }

    public void afterProcessingCommand(DrawingContext context) {

    }

    public void beforeProcessingCommand(DrawingContext context) {

    }
}
