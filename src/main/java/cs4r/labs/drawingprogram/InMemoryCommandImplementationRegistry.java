package cs4r.labs.drawingprogram;

import java.util.Map;
import java.util.Optional;

/**
 * A implementation of {@link CommandImplementationRegistry} that holds the {@link CommandImplementation}s
 * in memory.
 */
public class InMemoryCommandImplementationRegistry implements CommandImplementationRegistry {

    private final Map<String, CommandImplementation> registry;

    public InMemoryCommandImplementationRegistry(Map<String, CommandImplementation> registry) {

        this.registry = registry;
    }

    @Override
    public Optional<CommandImplementation> findImplementation(Command command) {
        return Optional.ofNullable(registry.get(command.getName()));
    }
}
