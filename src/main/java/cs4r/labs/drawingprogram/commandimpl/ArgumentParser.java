package cs4r.labs.drawingprogram.commandimpl;


/**
 * Parses command line arguments and transforms them into their expected types.
 */
public interface ArgumentParser {

    /**
     * Parses the argument at the given position and transforms it into an instance of the given type.
     *
     * @param rawArguments the arguments as a raw string. Arguments must be separated by spaces.
     * @param position     the position of the argument. Must be greater or equal than zero.
     * @param type         expected type of the argument
     * @throw InvalidArgumentException if the argument at the given position cannot be transformed into the type given type.
     * (Independently of the reason why it couldn't be transformed or parsed).
     */
    <T> T getPositionalArgument(String rawArguments, int position, Class<T> type);
}
