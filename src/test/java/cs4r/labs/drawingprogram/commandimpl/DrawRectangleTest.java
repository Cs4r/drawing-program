package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link DrawRectangle}.
 */
@RunWith(MockitoJUnitRunner.class)
public class DrawRectangleTest {

    private DrawingContext context;

    @Mock
    private ArgumentParser argumentParser;
    @Mock
    private Canvas canvas;

    @Test
    public void requireNonNullArguments() throws Exception {

        DrawRectangle drawRectangle = new DrawRectangle(argumentParser);

        assertThatThrownBy(() -> drawRectangle.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> drawRectangle.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }

    @Test
    public void drawRectangle() throws Exception {

        // Given
        context = TestUtils.activeContextWithCanvas(canvas);
        String arguments = "1 1 40 40";
        argumentParser = TestUtils.validArguments(arguments, 1, 1, 40, 40);
        DrawRectangle drawRectangle = new DrawRectangle(this.argumentParser);

        // When
        drawRectangle.execute(arguments, context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(this.argumentParser).getPositionalArgument(arguments, 0, Integer.class);
        verify(this.argumentParser).getPositionalArgument(arguments, 1, Integer.class);
        verify(this.argumentParser).getPositionalArgument(arguments, 2, Integer.class);
        verify(this.argumentParser).getPositionalArgument(arguments, 3, Integer.class);
        verify(canvas).drawRectangle(0, 0, 39, 39);
    }


    @Test
    public void doNotDrawRectangleIfContextIsNotActive() throws Exception {
        context = TestUtils.inactiveContext();
        DrawRectangle drawRectangle = new DrawRectangle(argumentParser);

        // When
        drawRectangle.execute("Arguments do not matter because the context is inactive", context);

        // Then
        verify(context).isActive();
        verify(context, never()).getCanvas();
        verify(canvas, never()).drawRectangle(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void throwCanvasNotFoundExceptionIfNoCanvas() throws Exception {
        context = TestUtils.activeContextWithoutCanvas(canvas);
        DrawRectangle drawRectangle = new DrawRectangle(argumentParser);

        // When
        assertThatThrownBy(() -> drawRectangle.execute("Arguments do not matter because there is no canvas to draw on", context))
                .isInstanceOf(CanvasNotFoundException.class)
                .hasMessage("no canvas to draw on");

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(canvas, never()).drawRectangle(anyInt(), anyInt(), anyInt(), anyInt());
    }

    @Test
    public void cannotBeConstructedWithNullArgumentParser() throws Exception {

        assertThatThrownBy(() -> new DrawRectangle(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("argumentParser cannot be null");
    }
}