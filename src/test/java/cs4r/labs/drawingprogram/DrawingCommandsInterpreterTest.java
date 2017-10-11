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

    @Test
    public void interpretOneSingleCommand() throws Exception {

        // Given
        Command command = mock(Command.class);

        when(commandsReader.nextCommand(drawingContext)).thenReturn(command);

        // When
        DrawingCommandsInterpreter commandsInterpreter =
                Mockito.spy(new DrawingCommandsInterpreter(commandsReader,
                        commandsProcessor));

        commandsInterpreter.interpretCommands(drawingContext);

        // Then
        InOrder inOrder = inOrder(commandsReader, commandsProcessor, commandsInterpreter);

        inOrder.verify(commandsInterpreter).beforeProcessingCommand(drawingContext);

        inOrder.verify(commandsReader).nextCommand(drawingContext);

        inOrder.verify(commandsProcessor).process(command, drawingContext);

        inOrder.verify(commandsInterpreter).afterProcessingCommand(drawingContext);
    }
}
