package cs4r.labs.drawingprogram.commandimpl;


import cs4r.labs.drawingprogram.exception.InvalidArgumentException;
import cs4r.labs.drawingprogram.util.Checks;

import static java.lang.String.format;

/**
 * Parses command line arguments and transforms them into their expected types.
 */
public class ArgumentParser {

    /**
     * Parses the argument at the given position and transforms it into an Integer value.
     *
     * @param rawArguments a non-null string with the arguments. Arguments must be separated by spaces.
     * @param position     the position of the argument.
     * @throw {@link InvalidArgumentException} if the argument at the given position cannot be transformed into an Integer.
     * (Independently of the reason why it couldn't be transformed or parsed).
     */
    Integer getIntArgument(String rawArguments, int position) {
        Checks.failIfNullArgument(rawArguments, "rawArguments");
        try {
            String[] arguments = rawArguments.trim().split("\\s+");
            return Integer.valueOf(arguments[position]);
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException(format("argument at position %d has an unexpected type", position));
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new InvalidArgumentException(format("no argument at position %d", position));
        }
    }

    /**
     * Parses the argument at the given position and transforms it into an Character value.
     *
     * @param rawArguments a non-null string with the arguments. Arguments must be separated by spaces.
     * @param position     the position of the argument.
     * @throw {@link InvalidArgumentException} if the argument at the given position cannot be transformed into a Character.
     * (Independently of the reason why it couldn't be transformed or parsed).
     */
    Character getCharArgument(String rawArguments, int position) {
        throw new UnsupportedOperationException();
    }
}
