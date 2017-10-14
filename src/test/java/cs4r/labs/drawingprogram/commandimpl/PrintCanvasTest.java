package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;
import cs4r.labs.drawingprogram.exception.CorruptedOutputException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link PrintCanvas}
 */
@RunWith(MockitoJUnitRunner.class)
public class PrintCanvasTest {

    private static final String CANVAS_CONTENT = "::Canvas Content::";
    private ByteArrayOutputStream output;

    @Mock
    private DrawingContext context;
    @Mock
    private Canvas canvas;

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

        PrintCanvas printCanvas = new PrintCanvas();

        DrawingContext context = mock(DrawingContext.class);

        assertThatThrownBy(() -> printCanvas.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }


    @Test
    public void printCanvasIfContextIsActive() throws Exception {

        // Given
        PrintCanvas printCanvas = new PrintCanvas();

        activeContextWithCanvas(true);

        // When
        printCanvas.execute(null, context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(canvas).toText();

        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEqualTo(CANVAS_CONTENT);
    }


    @Test
    public void doNotPrintCanvasIfContextIsNotActive() throws Exception {

        // Given
        PrintCanvas printCanvas = new PrintCanvas();

        activeContextWithCanvas(false);

        // When
        printCanvas.execute(null, context);

        // Then
        verify(context).isActive();
        verify(context, never()).getCanvas();
        verify(canvas, never()).toText();

        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEmpty();
    }

    @Test
    public void throwCanvasNotFoundExceptionIfContextIsActiveButNoCanvas() throws Exception {
        // Given
        PrintCanvas printCanvas = new PrintCanvas();

        activeContextWithNoCanvas();

        // When
        assertThatThrownBy(() -> printCanvas.execute(null, context))
                .isInstanceOf(CanvasNotFoundException.class)
                .hasMessage("no canvas to draw on");

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(canvas, never()).toText();

        String outputAsString = getOutputAsString(context);
        assertThat(outputAsString).isEmpty();
    }

    @Test
    public void throwCorruptedOutputExceptionIfOutputIsCorrupted() throws Exception {
        PrintCanvas printCanvas = new PrintCanvas();

        activeContextWithBrokenOutput();

        assertThatThrownBy(() ->
                printCanvas.execute(null, context))
                .isInstanceOf(CorruptedOutputException.class)
                .hasMessage("output is corrupted");

        verify(context).isActive();
    }

    private void activeContextWithBrokenOutput() throws IOException {
        activeContextWithCanvas(true);
        OutputStream corruptedOutput = mock(OutputStream.class);
        doThrow(IOException.class).when(corruptedOutput).write(any());
        when(context.getOutput()).thenReturn(corruptedOutput);
    }

    private void activeContextWithNoCanvas() {
        activeContextWithCanvas(true);
        when(context.getCanvas()).thenReturn(empty());
    }

    private String getOutputAsString(DrawingContext context) {
        ByteArrayOutputStream output = (ByteArrayOutputStream) context.getOutput();
        return new String(output.toByteArray());
    }

    private void activeContextWithCanvas(boolean isActive) {
        when(context.isActive()).thenReturn(isActive);

        when(context.getOutput()).thenReturn(output);

        when(canvas.toText()).thenReturn(CANVAS_CONTENT);

        when(context.getCanvas()).thenReturn(Optional.of(canvas));
    }
}