package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.util.Checks;

import java.util.Map;
import java.util.Optional;

/**
 * A implementation of {@link CommandImplementationRegistry} that holds the {@link CommandImplementation}s
 * in memory.
 */
public class InMemoryCommandImplementationRegistry implements CommandImplementationRegistry {

    private final Map<String, CommandImplementation> registry;

    public InMemoryCommandImplementationRegistry(Map<String, CommandImplementation> registry) {

        Checks.failIfNullArgument(registry, "registry");

        this.registry = registry;
    }

    @Override
    public Optional<CommandImplementation> findImplementation(Command command) {

        Checks.failIfNullArgument(command, "command");

        return Optional.ofNullable(registry.get(command.getName()));
    }

}
