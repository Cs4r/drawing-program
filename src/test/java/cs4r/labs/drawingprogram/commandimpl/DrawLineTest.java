package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * Unit tests for {@link DrawLine}
 */
public class DrawLineTest {


    @Test
    public void requireNonNullArguments() throws Exception {

        DrawLine drawLine = new DrawLine();

        assertThatThrownBy(() -> drawLine.execute(null, Mockito.mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> drawLine.execute(null, Mockito.mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }
}