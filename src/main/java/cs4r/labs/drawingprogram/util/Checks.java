package cs4r.labs.drawingprogram.util;

import java.util.Objects;

import static java.util.Objects.isNull;

/**
 * An utility class for validating arguments
 */
public class Checks {


    public static void failIfAnyArgumentIsNull(Object... arguments) {
        for (Object arg : arguments) {
            if (Objects.isNull(arg)) {
                throw new IllegalArgumentException("arguments cannot be null");
            }
        }
    }

    public static <T> void failIfNullArgument(T object, String argumentName) {
        if (isNull(object)) {
            throw new IllegalArgumentException(argumentName + " cannot be null");
        }
    }
}
