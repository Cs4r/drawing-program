package cs4r.labs.drawingprogram.command.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.TestUtils;
import cs4r.labs.drawingprogram.command.DrawingContext;
import cs4r.labs.drawingprogram.command.exception.CanvasNotFoundException;
import cs4r.labs.drawingprogram.command.exception.CorruptedOutputException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;

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

        assertThatThrownBy(() -> printCanvas.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }


    @Test
    public void printCanvasIfContextIsActive() throws Exception {

        // Given
        PrintCanvas printCanvas = new PrintCanvas();

        when(canvas.toText()).thenReturn(CANVAS_CONTENT);
        context = TestUtils.activeContextWith(canvas, output);

        // When
        printCanvas.execute(null, context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(canvas).toText();

        String outputAsString = TestUtils.getOutputAsString(context);
        assertThat(outputAsString).isEqualTo("\n" + CANVAS_CONTENT);
    }


    @Test
    public void doNotPrintCanvasIfContextIsNotActive() throws Exception {

        // Given
        PrintCanvas printCanvas = new PrintCanvas();

        context = TestUtils.inactiveContextWithOutput(output);

        // When
        printCanvas.execute(null, context);

        // Then
        verify(context).isActive();
        verify(context, never()).getCanvas();
        verify(canvas, never()).toText();

        String outputAsString = TestUtils.getOutputAsString(context);
        assertThat(outputAsString).isEmpty();
    }

    @Test
    public void throwCanvasNotFoundExceptionIfContextIsActiveButNoCanvas() throws Exception {
        // Given
        PrintCanvas printCanvas = new PrintCanvas();

        context = TestUtils.activeContextWith(null, output);

        // When
        assertThatThrownBy(() -> printCanvas.execute(null, context))
                .isInstanceOf(CanvasNotFoundException.class)
                .hasMessage("no canvas to draw on");

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(canvas, never()).toText();

        String outputAsString = TestUtils.getOutputAsString(context);
        assertThat(outputAsString).isEmpty();
    }

    @Test
    public void throwCorruptedOutputExceptionIfOutputIsCorrupted() throws Exception {
        PrintCanvas printCanvas = new PrintCanvas();

        when(canvas.toText()).thenReturn(CANVAS_CONTENT);
        context = TestUtils.activeContextWithBrokenOutput(canvas);

        assertThatThrownBy(() ->
                printCanvas.execute(null, context))
                .isInstanceOf(CorruptedOutputException.class)
                .hasMessage("output is corrupted");

        verify(context).isActive();
    }
}