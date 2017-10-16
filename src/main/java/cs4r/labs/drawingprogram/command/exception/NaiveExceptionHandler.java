package cs4r.labs.drawingprogram.command.exception;

import cs4r.labs.drawingprogram.command.DrawingContext;
import cs4r.labs.drawingprogram.util.Checks;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A naive implementation of {@link ExceptionHandler} that just prints the exception message in the console.
 */
public class NaiveExceptionHandler implements ExceptionHandler {

    @Override
    public void handle(RuntimeException exception, DrawingContext context) {
        Checks.failIfAnyArgumentIsNull(exception, context);
        OutputStream output = context.getOutput();
        String messageToPrint = String.format("%s\n", getMessage(exception));

        try {
            output.write('\n');
            output.write(messageToPrint.getBytes());
            output.flush();
        } catch (IOException ioException) {
            // If the exception is such that we cannot even write in the output
            // it's fine to print the stack trace. In a real scenario I would have used
            // a logger instead of java.lang.Exception#printStackTrace
            ioException.printStackTrace();
        }
    }

    private String getMessage(RuntimeException exception) {
        String message = exception.getMessage();
        message = message == null ? "Oops! An error occurred but there are no details" : message;
        return message;
    }
}
