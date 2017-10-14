package cs4r.labs.drawingprogram;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link NaiveExceptionHandler}
 */
@RunWith(MockitoJUnitRunner.class)
public class NaiveExceptionHandlerTest {

    @Mock
    private DrawingContext context;
    private ByteArrayOutputStream output;

    @After
    public void tearDown() throws Exception {
        if (output != null) {
            output.close();
        }
    }

    @Test
    public void requireNonNullArguments() throws Exception {

        NaiveExceptionHandler exceptionHandler = new NaiveExceptionHandler();

        DrawingContext context = mock(DrawingContext.class);

        assertThatThrownBy(() -> exceptionHandler.handle(null, context))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> exceptionHandler.handle(null, context))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> exceptionHandler.handle(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }

    @Test
    public void printExceptionMessage() throws Exception {

        // Given
        NaiveExceptionHandler exceptionHandler = new NaiveExceptionHandler();
        contextWithValidOutput();
        RuntimeException exception = exceptionWithMessage("::exceptionWithMessage message::");

        // When
        exceptionHandler.handle(exception, context);

        // Then
        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEqualTo("::exceptionWithMessage message::" + "\n");

    }

    @Test
    public void printExceptionMessageEvenWhenNull() throws Exception {
        // Given
        NaiveExceptionHandler exceptionHandler = new NaiveExceptionHandler();
        contextWithValidOutput();
        RuntimeException exception = exceptionWithMessage(null);

        // When
        exceptionHandler.handle(exception, context);

        // Then
        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEqualTo("Oops! An error occurred but there are no details\n");
    }

    @Test
    public void printStackTraceIfCannotWriteToTheOutput() throws Exception {
        // Given
        NaiveExceptionHandler exceptionHandler = new NaiveExceptionHandler();
        RuntimeException exception = exceptionWithMessage("::exception message::");
        IOException ioException = brokenOutput();

        // When
        exceptionHandler.handle(exception, context);

        // Then
        verify(ioException).printStackTrace();
    }

    private IOException brokenOutput() throws IOException {
        OutputStream brokenOutput = mock(OutputStream.class);
        IOException ioException = mock(IOException.class);
        doThrow(ioException).when(brokenOutput).write(any());
        when(context.getOutput()).thenReturn(brokenOutput);
        return ioException;
    }

    private RuntimeException exceptionWithMessage(String exceptionMessage) {
        RuntimeException exception = mock(RuntimeException.class);
        when(exception.getMessage()).thenReturn(exceptionMessage);
        return exception;
    }

    private void contextWithValidOutput() {
        context = mock(DrawingContext.class);
        output = new ByteArrayOutputStream();
        when(context.getOutput()).thenReturn(output);
    }

    private String getOutputAsString(DrawingContext context) {
        ByteArrayOutputStream output = (ByteArrayOutputStream) context.getOutput();
        return new String(output.toByteArray());
    }
}