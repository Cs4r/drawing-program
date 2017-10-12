package cs4r.labs.drawingprogram;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


/**
 * Unit tests for {@link DrawingCommandsInterpreter}
 */
@RunWith(MockitoJUnitRunner.class)
public class DrawingCommandsInterpreterTest {

    @Mock
    private CommandsReader commandsReader;
    @Mock
    private CommandsProcessor commandsProcessor;
    @Mock
    private DrawingContext drawingContext;
    @Mock
    private ExceptionHandler exceptionHandler;

    private DrawingCommandsInterpreter commandsInterpreter;

    @Before
    public void setUp() throws Exception {
        commandsProcessorCanHandlePrintPromptCommand();
        commandsProcessorCanHandlePrintCanvasCommand();

        commandsInterpreter = Mockito.spy(new DrawingCommandsInterpreter(commandsReader,
                commandsProcessor, exceptionHandler));
    }


    @Test
    public void interpretOneSingleCommand() throws Exception {

        // Given
        contextBecomeInactiveAfterProcessing(drawingContext, 1);

        Command command = commandsReaderOutput();

        // When
        commandsInterpreter.interpretCommands(drawingContext);

        // Then
        InOrder inOrder = inOrder(commandsReader, commandsProcessor, commandsInterpreter);

        inOrder.verify(commandsInterpreter).beforeProcessingCommand(drawingContext);

        inOrder.verify(commandsReader).nextCommand(drawingContext);

        inOrder.verify(commandsProcessor).process(command, drawingContext);

        inOrder.verify(commandsInterpreter).afterProcessingCommand(drawingContext);
    }


    @Test
    public void exceptionsAreHandledWhenCommandReadingFails() throws Exception {

        // Given
        contextBecomeInactiveAfterProcessing(drawingContext, 1);

        RuntimeException runtimeException = new RuntimeException("Oops!");

        when(commandsReader.nextCommand(drawingContext)).thenThrow(runtimeException);

        // When
        DrawingCommandsInterpreter commandsInterpreter = new DrawingCommandsInterpreter(commandsReader,
                commandsProcessor, exceptionHandler);

        commandsInterpreter.interpretCommands(drawingContext);

        // Then
        verify(exceptionHandler).handle(runtimeException, drawingContext);
    }

    @Test
    public void exceptionsAreHandledWhenCommandProcessingFails() throws Exception {

        // Given
        contextBecomeInactiveAfterProcessing(drawingContext, 1);

        Command command = commandsReaderOutput();

        RuntimeException runtimeException = new RuntimeException("Oops!");

        doThrow(runtimeException).when(commandsProcessor).process(command, drawingContext);

        commandsInterpreter.interpretCommands(drawingContext);

        // Then
        verify(commandsReader).nextCommand(drawingContext);
        verify(exceptionHandler).handle(runtimeException, drawingContext);
    }

    @Test
    public void interpretSeveralCommands() throws Exception {

        // Given
        int numberOfCommands = 5;

        contextBecomeInactiveAfterProcessing(drawingContext, numberOfCommands);

        Command command = commandsReaderOutput();

        // When
        commandsInterpreter.interpretCommands(drawingContext);

        // Then
        InOrder inOrder = inOrder(commandsReader, commandsProcessor, commandsInterpreter);

        for (int times = 0; times < numberOfCommands; ++times) {

            inOrder.verify(commandsInterpreter).beforeProcessingCommand(drawingContext);

            inOrder.verify(commandsReader).nextCommand(drawingContext);

            inOrder.verify(commandsProcessor).process(command, drawingContext);

            inOrder.verify(commandsInterpreter).afterProcessingCommand(drawingContext);
        }
    }

    @Test
    public void interpretCommandsRequiresANonNullContext() throws Exception {

        assertThatThrownBy(() -> commandsInterpreter.interpretCommands(null))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("context cannot be null");
    }

    @Test
    public void doesNotInterpretCommandsIfContextIsInactive() throws Exception {

        when(drawingContext.isActive()).thenReturn(false);

        commandsInterpreter.interpretCommands(drawingContext);

        verify(commandsProcessor).canHandle(Command.PRINT_PROMPT_COMMAND);
        verify(commandsProcessor).canHandle(Command.PRINT_CANVAS_COMMAND);
        verifyNoMoreInteractions(commandsReader, commandsProcessor, exceptionHandler);
    }

    @Test
    public void ensuresThatCommandsProcessorHandlesPrintPromptCommand() {

        commandsProcessorCannotHandlePrintPromptCommand();

        assertThatThrownBy(() -> new DrawingCommandsInterpreter(commandsReader, commandsProcessor, exceptionHandler))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CommandsProcessor must handle PrintPromptCommand");
    }

    @Test
    public void ensuresThatCommandsProcessorHandlesPrintCanvasCommand() throws Exception {

        commandsProcessorCannotHandlePrintCanvasCommand();

        assertThatThrownBy(() -> new DrawingCommandsInterpreter(commandsReader, commandsProcessor, exceptionHandler))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CommandsProcessor must handle PrintCanvasCommand");
    }

    private void commandsProcessorCannotHandlePrintCanvasCommand() {
        Command printCanvas = Command.PRINT_CANVAS_COMMAND;
        when(commandsProcessor.canHandle(printCanvas)).thenReturn(false);
    }

    private void commandsProcessorCannotHandlePrintPromptCommand() {
        Command printPrompt = Command.PRINT_PROMPT_COMMAND;
        when(commandsProcessor.canHandle(printPrompt)).thenReturn(false);
    }

    private Command commandsReaderOutput() {
        Command command = mock(Command.class);

        when(commandsReader.nextCommand(drawingContext)).thenReturn(command);

        return command;
    }

    private void commandsProcessorCanHandlePrintPromptCommand() {
        when(commandsProcessor.canHandle(Command.PRINT_PROMPT_COMMAND)).thenReturn(true);
    }

    private void commandsProcessorCanHandlePrintCanvasCommand() {
        when(commandsProcessor.canHandle(Command.PRINT_CANVAS_COMMAND)).thenReturn(true);

    }

    private void contextBecomeInactiveAfterProcessing(DrawingContext drawingContext, int numberOfCommands) {
        final int count[] = new int[]{0};

        when(drawingContext.isActive()).then(answer -> count[0]++ != numberOfCommands);
    }
}
