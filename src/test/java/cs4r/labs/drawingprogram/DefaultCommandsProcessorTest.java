package cs4r.labs.drawingprogram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
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
}