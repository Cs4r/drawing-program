package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.util.Checks;

import static java.util.Objects.isNull;

public class DrawingCommandsInterpreter {

    private final CommandsReader commandsReader;
    private final CommandsProcessor commandsProcessor;
    private final ExceptionHandler exceptionHandler;

    public DrawingCommandsInterpreter(CommandsReader commandsReader,
                                      CommandsProcessor commandsProcessor,
                                      ExceptionHandler exceptionHandler) {
        failIfNullArguments(commandsReader, commandsProcessor, exceptionHandler);
        failIfCannotHandlePrintPromptCommand(commandsProcessor);
        failIfCannotHandlePrintCanvasCommand(commandsProcessor);
        this.commandsReader = commandsReader;
        this.commandsProcessor = commandsProcessor;
        this.exceptionHandler = exceptionHandler;
    }

    public void interpretCommands(DrawingContext context) {

        failIfContextIsNull(context);

        while (context.isActive()) {
            try {
                beforeProcessingCommand(context);
                Command command = readCommand(context);
                processCommand(context, command);
                afterProcessingCommand(context);
            } catch (RuntimeException runtimeException) {
                exceptionHandler.handle(runtimeException, context);
            }
        }
    }

    public void afterProcessingCommand(DrawingContext context) {
        commandsProcessor.process(SupportedCommands.PRINT_CANVAS_COMMAND, context);
    }

    public void beforeProcessingCommand(DrawingContext context) {
        commandsProcessor.process(SupportedCommands.PRINT_PROMPT_COMMAND, context);
    }

    private void failIfContextIsNull(DrawingContext context) {
        if (isNull(context)) {
            throw new IllegalArgumentException("context cannot be null");
        }
    }

    private void processCommand(DrawingContext context, Command command) {
        commandsProcessor.process(command, context);
    }

    private Command readCommand(DrawingContext context) {
        return commandsReader.nextCommand();
    }


    private void failIfNullArguments(CommandsReader commandsReader,
                                     CommandsProcessor commandsProcessor,
                                     ExceptionHandler exceptionHandler) {
        Checks.failIfNullArgument(commandsReader, "CommandsReader");
        Checks.failIfNullArgument(commandsProcessor, "CommandsProcessor");
        Checks.failIfNullArgument(exceptionHandler, "ExceptionHandler");
    }

    private void failIfCannotHandlePrintCanvasCommand(CommandsProcessor commandsProcessor) {
        if (!commandsProcessor.canHandle(SupportedCommands.PRINT_CANVAS_COMMAND)) {
            throw new IllegalArgumentException("CommandsProcessor must handle PrintCanvasCommand");
        }
    }

    private void failIfCannotHandlePrintPromptCommand(CommandsProcessor commandsProcessor) {
        if (!commandsProcessor.canHandle(SupportedCommands.PRINT_PROMPT_COMMAND)) {
            throw new IllegalArgumentException("CommandsProcessor must handle PrintPromptCommand");
        }
    }
}
