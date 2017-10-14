package cs4r.labs.drawingprogram;

import java.util.Objects;

/**
 * A naive implementation of {@link ExceptionHandler}.
 */
public class NaiveExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(RuntimeException exception, DrawingContext context) {
        failIfAnyArgumentIsNull(exception, context);
    }

    private void failIfAnyArgumentIsNull(Object... arguments) {
        for (Object arg : arguments) {
            if (Objects.isNull(arg)) {
                throw new IllegalArgumentException("arguments cannot be null");
            }
        }
    }
}
