package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.CommandNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link DefaultCommandsProcessor}
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultCommandsProcessorTest {

    @Mock
    private DrawingContext drawingContext;

    @Mock
    private Command command;

    @Mock
    private CommandImplementation commandImplementation;

    @Mock
    private CommandImplementationRegistry commandImplementationRegistry;

    @InjectMocks
    private DefaultCommandsProcessor defaultCommandsProcessor;


    @Test
    public void processCommandWhenCommandImplementationIsFound() throws Exception {

        //Given
        commandImplementationFound();

        // When
        defaultCommandsProcessor.process(command, drawingContext);

        // Then
        verify(commandImplementationRegistry).findImplementation(command);
        verify(commandImplementation).execute(drawingContext);
    }

    @Test
    public void throwCommandNotFoundExceptionWhenCommandImplementationNotFound() throws Exception {

        // Given
        commandWithName("misspelled-command-name");
        commandImplementationNotFound();

        // When
        assertThatThrownBy(() -> defaultCommandsProcessor.process(command, drawingContext))
                .isInstanceOf(CommandNotFoundException.class)
                .hasMessage("Command \"misspelled-command-name\" not found");

        // Then
        verify(commandImplementationRegistry).findImplementation(command);
    }

    private void commandWithName(String commandName) {
        when(command.getName()).thenReturn(commandName);
    }

    private void commandImplementationNotFound() {
        when(commandImplementationRegistry.findImplementation(command)).thenReturn(Optional.empty());
    }


    private void commandImplementationFound() {
        when(commandImplementationRegistry.findImplementation(command)).thenReturn(Optional.of(commandImplementation));
    }
}