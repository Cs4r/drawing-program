package cs4r.labs.drawingprogram.commandimpl;


/**
 * Parses command line arguments and transforms them into their expected types.
 */
public class ArgumentParser {

    /**
     * Parses the argument at the given position and transforms it into an Integer value.
     *
     * @param rawArguments the arguments as a raw string. Arguments must be separated by spaces.
     * @param position     the position of the argument. Must be greater or equal than zero.
     * @throw InvalidArgumentException if the argument at the given position cannot be transformed into an Integer.
     * (Independently of the reason why it couldn't be transformed or parsed).
     */
    Integer getIntArgument(String rawArguments, int position) {

        String[] arguments = rawArguments.trim().split("\\s+");

        return Integer.valueOf(arguments[position]);
    }

    /**
     * Parses the argument at the given position and transforms it into an Character value.
     *
     * @param rawArguments the arguments as a raw string. Arguments must be separated by spaces.
     * @param position     the position of the argument. Must be greater or equal than zero.
     * @throw InvalidArgumentException if the argument at the given position cannot be transformed into a Character.
     * (Independently of the reason why it couldn't be transformed or parsed).
     */
    Character getCharArgument(String rawArguments, int position) {
        throw new UnsupportedOperationException();
    }
}
