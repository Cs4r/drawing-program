package cs4r.labs.drawingprogram;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link NaiveExceptionHandler}
 */
public class NaiveExceptionHandlerTest {

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

        Assertions.assertThatThrownBy(() -> exceptionHandler.handle(null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }

    @Test
    public void printExceptionMessage() throws Exception {

        // Given
        NaiveExceptionHandler exceptionHandler = new NaiveExceptionHandler();
        DrawingContext context = mock(DrawingContext.class);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        when(context.getOutput()).thenReturn(output);

        RuntimeException exception = Mockito.mock(RuntimeException.class);
        when(exception.getMessage()).thenReturn("::exception message::");

        // When
        exceptionHandler.handle(exception, context);

        // Then
        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEqualTo("::exception message::" + "\n");

        output.close();
    }

    @Test
    public void printExceptionMessageEvenWhenNull() throws Exception {
        // Given
        NaiveExceptionHandler exceptionHandler = new NaiveExceptionHandler();
        DrawingContext context = mock(DrawingContext.class);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        when(context.getOutput()).thenReturn(output);

        RuntimeException exception = Mockito.mock(RuntimeException.class);
        when(exception.getMessage()).thenReturn(null);

        // When
        exceptionHandler.handle(exception, context);

        // Then
        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEqualTo("Oops! An error occurred but there are no details\n");

        output.close();
    }

    private String getOutputAsString(DrawingContext context) {
        ByteArrayOutputStream output = (ByteArrayOutputStream) context.getOutput();
        return new String(output.toByteArray());
    }
}