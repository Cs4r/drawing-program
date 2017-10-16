package cs4r.labs.drawingprogram.command.commandimpl;

import cs4r.labs.drawingprogram.command.DrawingContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for {@link Terminate}.
 */
public class TerminateTest {

    @Test
    public void requireNonNullContext() throws Exception {

        Terminate terminate = new Terminate();

        assertThatThrownBy(() -> terminate.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }

    @Test
    public void deactivateContext() throws Exception {

        // Given
        Terminate terminate = new Terminate();
        DrawingContext context = mock(DrawingContext.class);

        // When
        terminate.execute(null, context);

        // Then
        verify(context).deactivate();
    }
}