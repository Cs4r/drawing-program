package cs4r.labs.drawingprogram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

    @Test
    public void interpretOneSingleCommand() throws Exception {

        // Given
        contextBecomeInactiveAfterProcessing(drawingContext, 1);

        Command command = mock(Command.class);

        when(commandsReader.nextCommand(drawingContext)).thenReturn(command);

        // When
        DrawingCommandsInterpreter commandsInterpreter =
                Mockito.spy(new DrawingCommandsInterpreter(commandsReader,
                        commandsProcessor, exceptionHandler));

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

        Command command = mock(Command.class);

        when(commandsReader.nextCommand(drawingContext)).thenReturn(command);

        RuntimeException runtimeException = new RuntimeException("Oops!");

        doThrow(runtimeException).when(commandsProcessor).process(command, drawingContext);

        // When
        DrawingCommandsInterpreter commandsInterpreter = new DrawingCommandsInterpreter(commandsReader,
                commandsProcessor, exceptionHandler);

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

        Command command = mock(Command.class);

        when(commandsReader.nextCommand(drawingContext)).thenReturn(command);

        // When
        DrawingCommandsInterpreter commandsInterpreter =
                Mockito.spy(new DrawingCommandsInterpreter(commandsReader,
                        commandsProcessor, exceptionHandler));

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

    private void contextBecomeInactiveAfterProcessing(DrawingContext drawingContext, int numberOfCommands) {
        final int count[] = new int[]{0};

        Mockito.when(drawingContext.isActive()).then(answer -> {

            if (count[0]++ == numberOfCommands) {
                return false;
            } else {
                return true;
            }
        });
    }
}
