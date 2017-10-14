package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


/**
 * Unit tests for {@link PrintPrompt}.
 */
public class PrintPromptTest {


    @Test
    public void requiresNonNullContext() throws Exception {

        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = Mockito.mock(DrawingContext.class);

        assertThatThrownBy(() -> printPrompt.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }
}