package cs4r.labs.drawingprogram.command.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.TestUtils;
import cs4r.labs.drawingprogram.command.DrawingContext;
import cs4r.labs.drawingprogram.command.exception.CanvasNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


/**
 * Unit tests for {@link DrawLine}
 */
@RunWith(MockitoJUnitRunner.class)
public class DrawLineTest {


    private DrawingContext context;
    @Mock
    private Canvas canvas;
    @Mock
    private ArgumentParser argumentParser;

    @Test
    public void requireNonNullArguments() throws Exception {

        DrawLine drawLine = new DrawLine(argumentParser);

        assertThatThrownBy(() -> drawLine.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> drawLine.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }

    @Test
    public void drawLine() throws Exception {

        // Given
        context = TestUtils.activeContextWithCanvas(canvas);
        String arguments = "1 2 3 4";
        argumentParser = TestUtils.validIntArguments(arguments, 1, 2, 3, 4);
        DrawLine drawLine = new DrawLine(argumentParser);

        // When
        drawLine.execute(arguments, context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(argumentParser).getIntArgument(arguments, 0);
        verify(argumentParser).getIntArgument(arguments, 1);
        verify(argumentParser).getIntArgument(arguments, 2);
        verify(argumentParser).getIntArgument(arguments, 3);
        verify(canvas).drawLine(0, 1, 2, 3);
    }

    @Test
    public void doNotDrawALineIfContextIsNotActive() throws Exception {
        context = TestUtils.inactiveContext();
        DrawLine drawLine = new DrawLine(argumentParser);

        // When
        drawLine.execute("Arguments do not matter because the context is inactive", context);

        // Then
        verify(context).isActive();
        verify(context, never()).getCanvas();
        verify(canvas, never()).drawLine(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void throwCanvasNotFoundExceptionIfNoCanvas() throws Exception {
        context = TestUtils.activeContextWithoutCanvas(canvas);
        DrawLine drawLine = new DrawLine(argumentParser);

        // When
        assertThatThrownBy(() -> drawLine.execute("Arguments do not matter because there is no canvas to draw on", context))
                .isInstanceOf(CanvasNotFoundException.class)
                .hasMessage("no canvas to draw on");

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(canvas, never()).drawLine(anyInt(), anyInt(), anyInt(), anyInt());
    }


    @Test
    public void cannotBeConstructedWithNullArgumentParser() throws Exception {

        assertThatThrownBy(() -> new DrawLine(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("argumentParser cannot be null");
    }
}