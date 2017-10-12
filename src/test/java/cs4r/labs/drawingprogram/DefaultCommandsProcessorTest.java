package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.CommandNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link DefaultCommandsProcessor}
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultCommandsProcessorTest {


    @Test
    public void processCommandWhenCommandImplementationIsFound() throws Exception {

        //Given
        DrawingContext drawingContext = mock(DrawingContext.class);
        Command command = mock(Command.class);
        CommandImplementation commandImplementation = mock(CommandImplementation.class);
        CommandImplementationRegistry commandImplementationRegistry = mock(CommandImplementationRegistry.class);
        when(commandImplementationRegistry.findImplementation(command)).thenReturn(Optional.of(commandImplementation));

        DefaultCommandsProcessor defaultCommandsProcessor = new DefaultCommandsProcessor(commandImplementationRegistry);

        // When
        defaultCommandsProcessor.process(command, drawingContext);

        // Then
        verify(commandImplementationRegistry).findImplementation(command);
        verify(commandImplementation).execute(drawingContext);
    }

    @Test
    public void throwCommandNotFoundExceptionWhenCommandImplementationNotFound() throws Exception {

        DrawingContext drawingContext = mock(DrawingContext.class);
        Command command = mock(Command.class);
        when(command.getName()).thenReturn("misspelled-command-name");
        CommandImplementationRegistry commandImplementationRegistry = mock(CommandImplementationRegistry.class);
        when(commandImplementationRegistry.findImplementation(command)).thenReturn(Optional.empty());

        DefaultCommandsProcessor defaultCommandsProcessor = new DefaultCommandsProcessor(commandImplementationRegistry);

        // When
        assertThatThrownBy(() -> defaultCommandsProcessor.process(command, drawingContext))
                .isInstanceOf(CommandNotFoundException.class)
                .hasMessage("Command \"misspelled-command-name\" not found");

        // Then
        verify(commandImplementationRegistry).findImplementation(command);
    }
}