package cs4r.labs.drawingprogram.command;


import cs4r.labs.drawingprogram.command.commandimpl.CommandImplementation;

import java.util.Optional;

/**
 * A registry of {@link CommandImplementation}s.
 */
public interface CommandImplementationRegistry {

    /**
     * Finds the implementation of a command.
     *
     * @param command the {@link Command}.
     * @return an Optional containing the {@link CommandImplementation} for the supplied {@link Command}
     * in case it is found in the registry, {@link Optional#empty()} otherwise.
     */
    Optional<CommandImplementation> findImplementation(Command command);
}
