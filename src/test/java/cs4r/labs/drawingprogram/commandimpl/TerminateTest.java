package cs4r.labs.drawingprogram.commandimpl;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link Terminate}
 */
public class TerminateTest {


    @Test
    public void requireNonNullContext() throws Exception {

        Terminate terminate = new Terminate();

        assertThatThrownBy(() -> terminate.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }
}