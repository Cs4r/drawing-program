package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CorruptedOutputException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
    public void requireNonNullContext() throws Exception {

        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = mock(DrawingContext.class);

        assertThatThrownBy(() -> printPrompt.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }

    @Test
    public void printPromptIfContextIsActive() throws Exception {

        // Given
        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = activeContext(true);

        // When
        printPrompt.execute(null, context);

        // Then
        verify(context).isActive();
        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEqualTo("enter command:");
    }

    private String getOutputAsString(DrawingContext context) {
        ByteArrayOutputStream output = (ByteArrayOutputStream) context.getOutput();
        return new String(output.toByteArray());
    }

    @Test
    public void doNotPrintPromptIfContextIsNotActive() throws Exception {
        // Given
        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = activeContext(false);

        // When
        printPrompt.execute(null, context);

        // Then
        verify(context).isActive();
        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEmpty();
    }

    @Test
    public void throwCorruptedOutputExceptionIfOutputIsCorrupted() throws Exception {
        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = activeContextWithBrokenOutput();

        assertThatThrownBy(() ->
                printPrompt.execute(null, context))
                .isInstanceOf(CorruptedOutputException.class)
                .hasMessage("output is corrupted");

        verify(context).isActive();
    }

    private DrawingContext activeContextWithBrokenOutput() throws IOException {
        DrawingContext context = activeContext(true);

        OutputStream brokenOutput = mock(OutputStream.class);
        doThrow(IOException.class).when(brokenOutput).write(any());
        when(context.getOutput()).thenReturn(brokenOutput);
        return context;
    }

    private DrawingContext activeContext(boolean isActive) {

        DrawingContext drawingContext = mock(DrawingContext.class);
        when(drawingContext.isActive()).thenReturn(isActive);
        when(drawingContext.getOutput()).thenReturn(output);

        return drawingContext;
    }
}