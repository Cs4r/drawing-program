package cs4r.labs.drawingprogram;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * A naive implementation of {@link ExceptionHandler}.
 */
public class NaiveExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(RuntimeException exception, DrawingContext context) {
        failIfAnyArgumentIsNull(exception, context);

        OutputStream output = context.getOutput();
        String exceptionMessage = String.format("%s\n", exception.getMessage());

        try {
            output.write(exceptionMessage.getBytes());
            output.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void failIfAnyArgumentIsNull(Object... arguments) {
        for (Object arg : arguments) {
            if (Objects.isNull(arg)) {
                throw new IllegalArgumentException("arguments cannot be null");
            }
        }
    }
}
