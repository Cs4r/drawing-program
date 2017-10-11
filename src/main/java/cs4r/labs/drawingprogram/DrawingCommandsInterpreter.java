package cs4r.labs.drawingprogram;


public class DrawingCommandsInterpreter {

    private final CommandsReader commandsReader;
    private final CommandsProcessor commandsProcessor;
    private final ExceptionHandler exceptionHandler;

    public DrawingCommandsInterpreter(CommandsReader commandsReader,
                                      CommandsProcessor commandsProcessor, ExceptionHandler exceptionHandler) {

        this.commandsReader = commandsReader;
        this.commandsProcessor = commandsProcessor;
        this.exceptionHandler = exceptionHandler;
    }

    public void interpretCommands(DrawingContext context) {
        try {
            beforeProcessingCommand(context);
            Command command = readCommand(context);
            processCommand(context, command);
            afterProcessingCommand(context);
        } catch (RuntimeException runtimeException) {
            exceptionHandler.handle(runtimeException, context);
        }
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
