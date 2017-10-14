package cs4r.labs.drawingprogram.commandimpl;


import java.util.Optional;

public interface ArgumentParser {

    <T> Optional<T> getPositionalArgument(int position, Class<T> type);
}
