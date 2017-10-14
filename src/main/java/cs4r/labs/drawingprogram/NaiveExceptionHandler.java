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
        String messageToPrint = String.format("%s\n", getMessage(exception));

        try {
            output.write(messageToPrint.getBytes());
            output.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String getMessage(RuntimeException exception) {
        String message = exception.getMessage();
        message = message == null ? "Oops! An error occurred but there are no details" : message;
        return message;
    }

    private void failIfAnyArgumentIsNull(Object... arguments) {
        for (Object arg : arguments) {
            if (Objects.isNull(arg)) {
                throw new IllegalArgumentException("arguments cannot be null");
            }
        }
    }
}
