package cs4r.labs.drawingprogram;


import java.util.Optional;

public interface CommandImplementationRegistry {

    Optional<CommandImplementation> findImplementation(Command command);
}
