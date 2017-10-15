package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

/**
 * Unit tests for {@link FillArea}.
 */
public class FillAreaTest {

    @Test
    public void requireNonNullArguments() throws Exception {

        FillArea fillArea = new FillArea();

        assertThatThrownBy(() -> fillArea.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> fillArea.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }

}