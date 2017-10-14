package cs4r.labs.drawingprogram;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

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
}