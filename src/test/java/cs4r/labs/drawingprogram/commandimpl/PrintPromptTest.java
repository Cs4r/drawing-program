package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * Unit tests for {@link PrintPrompt}.
 */
public class PrintPromptTest {

    private ByteArrayOutputStream output;

    @Before
    public void setUp() throws Exception {
        output = new ByteArrayOutputStream();
    }

    @After
    public void tearDown() throws Exception {
        output.close();
    }

    @Test
    public void requiresNonNullContext() throws Exception {

        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = Mockito.mock(DrawingContext.class);

        assertThatThrownBy(() -> printPrompt.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }

    @Test
    public void printsPromptIfContextIsActive() throws Exception {

        // Given
        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = activeContext();

        // When
        printPrompt.execute(null, context);

        // Then
        verify(context).isActive();
        ByteArrayOutputStream output = (ByteArrayOutputStream) context.getOutput();
        String outputAsString = new String(output.toByteArray());
        assertThat(outputAsString).isEqualTo("enter command:");
    }

    private DrawingContext activeContext() {

        DrawingContext drawingContext = Mockito.mock(DrawingContext.class);
        when(drawingContext.isActive()).thenReturn(true);
        when(drawingContext.getOutput()).thenReturn(output);

        return drawingContext;
    }
}