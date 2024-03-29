package cs4r.labs.drawingprogram.command;

import cs4r.labs.drawingprogram.command.commandimpl.CommandImplementation;
import cs4r.labs.drawingprogram.command.exception.CommandNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link CommandsProcessor}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandsProcessorTest {

    @Mock
    private DrawingContext drawingContext;

    @Mock
    private Command command;

    @Mock
    private CommandImplementation commandImplementation;

    @Mock
    private CommandImplementationRegistry commandImplementationRegistry;

    @InjectMocks
    private CommandsProcessor commandsProcessor;


    @Test
    public void processCommandWhenCommandImplementationIsFound() throws Exception {

        //Given
        commandImplementationFound();

        // When
        commandsProcessor.process(command, drawingContext);

        // Then
        verify(commandImplementationRegistry).findImplementation(command);
        verify(commandImplementation).execute(command.getArguments(), drawingContext);
    }

    @Test
    public void throwCommandNotFoundExceptionWhenCommandImplementationNotFound() throws Exception {

        // Given
        commandWithName("misspelled-command-name");
        commandImplementationNotFound();

        // When
        assertThatThrownBy(() -> commandsProcessor.process(command, drawingContext))
                .isInstanceOf(CommandNotFoundException.class)
                .hasMessage("Command \"misspelled-command-name\" not found");

    }

    @Test
    public void canHandleCommandImplementations() {

        // Given
        commandImplementationFound();

        // When
        assertThat(commandsProcessor.canHandle(command)).isTrue();

        // Then
        verify(commandImplementationRegistry).findImplementation(command);


        reset(commandImplementationRegistry);

        // Given
        commandImplementationNotFound();

        // When
        assertThat(commandsProcessor.canHandle(command)).isFalse();

        // Then
        verify(commandImplementationRegistry).findImplementation(command);


        reset(commandImplementationRegistry);

        // Given
        commandImplementationNotFound();

        // When
        assertThat(commandsProcessor.canHandle(command)).isFalse();

        // Then
        verify(commandImplementationRegistry).findImplementation(command);
    }

    @Test
    public void canHandleRequiresANonNullContext() throws Exception {

        assertThatThrownBy(() -> commandsProcessor.canHandle(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("command cannot be null");
    }

    @Test
    public void processRequiresNonNullArguments() throws Exception {

        assertThatThrownBy(() -> commandsProcessor.process(null, drawingContext))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("command cannot be null");

        assertThatThrownBy(() -> commandsProcessor.process(command, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }

    @Test
    public void cannotConstructWithNullCommandImplementationRegistry() throws Exception {

        assertThatThrownBy(() -> new CommandsProcessor(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("CommandImplementationRegistry cannot be null");
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