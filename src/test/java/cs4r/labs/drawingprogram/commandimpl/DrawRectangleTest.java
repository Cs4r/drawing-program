package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for {@link DrawRectangle}.
 */
public class DrawRectangleTest {

    @Test
    public void requireNonNullArguments() throws Exception {

        DrawRectangle drawRectangle = new DrawRectangle();

        assertThatThrownBy(() -> drawRectangle.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> drawRectangle.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }
}