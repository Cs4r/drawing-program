package cs4r.labs.drawingprogram;

import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

/**
 * A implementation of {@link CommandImplementationRegistry} that holds the {@link CommandImplementation}s
 * in memory.
 */
public class InMemoryCommandImplementationRegistry implements CommandImplementationRegistry {

    private final Map<String, CommandImplementation> registry;

    public InMemoryCommandImplementationRegistry(Map<String, CommandImplementation> registry) {

        failIfRegistryIsNull(registry);

        this.registry = registry;
    }

    @Override
    public Optional<CommandImplementation> findImplementation(Command command) {

        failIfNullCommand(command);

        return Optional.ofNullable(registry.get(command.getName()));
    }

    private void failIfNullCommand(Command command) {

        if (isNull(command)) {
            throw new IllegalArgumentException("command cannot be null");
        }
    }

    private void failIfRegistryIsNull(Map<String, CommandImplementation> registry) {

        if (isNull(registry)) {
            throw new IllegalArgumentException("registry cannot be null");
        }
    }
}