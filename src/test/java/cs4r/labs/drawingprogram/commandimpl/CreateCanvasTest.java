package cs4r.labs.drawingprogram.commandimpl;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link CreateCanvas}.
 */
public class CreateCanvasTest {

    @Test
    public void requireNonNullContext() throws Exception {

        CreateCanvas createCanvas = new CreateCanvas();

        assertThatThrownBy(() -> createCanvas.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }
}