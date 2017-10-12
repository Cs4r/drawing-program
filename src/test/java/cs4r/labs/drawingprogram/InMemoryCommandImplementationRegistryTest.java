package cs4r.labs.drawingprogram;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link InMemoryCommandImplementationRegistry}.
 */
public class InMemoryCommandImplementationRegistryTest {

    @Test
    public void doNotFindImplementationItIsNotInTheRegistry() throws Exception {

        // Given
        Command commandWithoutImplementation = mock(Command.class);
        when(commandWithoutImplementation.getName()).thenReturn("A command without implementation");

        HashMap<String, CommandImplementation> registeredCommands = new HashMap<>();
        registeredCommands.put("oneCommand", mock(CommandImplementation.class));

        // When
        InMemoryCommandImplementationRegistry inMemoryRegistry = new InMemoryCommandImplementationRegistry(registeredCommands);

        // Then
        assertThat(inMemoryRegistry.findImplementation(commandWithoutImplementation)).isEqualTo(Optional.empty());
    }

    @Test
    public void findImplementationIfItIsInTheRegistry() throws Exception {

        // Given
        Command commandWithImplementation = mock(Command.class);
        when(commandWithImplementation.getName()).thenReturn("A command with implementation");

        HashMap<String, CommandImplementation> registeredCommands = new HashMap<>();
        CommandImplementation implementation = mock(CommandImplementation.class);
        registeredCommands.put("A command with implementation", implementation);

        // When
        InMemoryCommandImplementationRegistry inMemoryRegistry = new InMemoryCommandImplementationRegistry(registeredCommands);

        // Then
        assertThat(inMemoryRegistry.findImplementation(commandWithImplementation)).isEqualTo(Optional.of(implementation));
    }
}