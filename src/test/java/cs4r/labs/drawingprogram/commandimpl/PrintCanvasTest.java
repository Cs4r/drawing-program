package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for {@link PrintCanvas}
 */
public class PrintCanvasTest {

    @Test
    public void requiresNonNullContext() throws Exception {

        PrintCanvas printCanvas = new PrintCanvas();

        DrawingContext context = mock(DrawingContext.class);

        assertThatThrownBy(() -> printCanvas.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }
}