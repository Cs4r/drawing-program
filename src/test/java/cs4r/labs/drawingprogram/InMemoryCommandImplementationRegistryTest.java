package cs4r.labs.drawingprogram;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link InMemoryCommandImplementationRegistry}.
 */
public class InMemoryCommandImplementationRegistryTest {

    private HashMap<String, CommandImplementation> registeredCommands;

    @Before
    public void setUp() throws Exception {
        registeredCommands = new HashMap<>();
    }

    @Test
    public void doNotFindImplementationItIsNotInTheRegistry() throws Exception {

        // Given
        Command commandWithoutImplementation = commandWithName("A command without implementation");
        registryWithCommandImplementationFor("otherCommand");

        // When
        InMemoryCommandImplementationRegistry inMemoryRegistry = new InMemoryCommandImplementationRegistry(registeredCommands);

        // Then
        assertThat(inMemoryRegistry.findImplementation(commandWithoutImplementation)).isEqualTo(Optional.empty());
    }

    @Test
    public void findImplementationIfItIsInTheRegistry() throws Exception {

        // Given
        Command commandWithImplementation = commandWithName("A command with implementation");
        CommandImplementation implementation = registryWithCommandImplementationFor("A command with implementation");

        // When
        InMemoryCommandImplementationRegistry inMemoryRegistry = new InMemoryCommandImplementationRegistry(registeredCommands);

        // Then
        assertThat(inMemoryRegistry.findImplementation(commandWithImplementation)).isEqualTo(Optional.of(implementation));
    }

    private CommandImplementation registryWithCommandImplementationFor(String commandName) {
        CommandImplementation commandImplementation = mock(CommandImplementation.class);
        registeredCommands.put(commandName, commandImplementation);
        return commandImplementation;
    }

    private Command commandWithName(String value) {
        Command commandWithoutImplementation = mock(Command.class);
        when(commandWithoutImplementation.getName()).thenReturn(value);
        return commandWithoutImplementation;
    }
}