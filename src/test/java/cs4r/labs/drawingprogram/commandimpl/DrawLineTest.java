package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.DrawingContext;
import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


/**
 * Unit tests for {@link DrawLine}
 */
@RunWith(MockitoJUnitRunner.class)
public class DrawLineTest {


    @Mock
    private DrawingContext context;
    @Mock
    private Canvas canvas;
    private ArgumentParser argumentParser;

    @Test
    public void requireNonNullArguments() throws Exception {

        DrawLine drawLine = new DrawLine(null);

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
        activeContextWithCanvas();
        String arguments = "1 2 3 4";
        validArguments(arguments, 1, 2, 3, 4);
        DrawLine drawLine = new DrawLine(argumentParser);

        // When
        drawLine.execute(arguments, context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(argumentParser).getPositionalArgument(arguments, 0, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 1, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 2, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 3, Integer.class);
        verify(canvas).drawLine(0, 1, 2, 3);
    }

    @Test
    public void doNotDrawALineIfContextIsNotActive() throws Exception {
        inactiveContext();
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
        activeContextWithoutCanvas();
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

    private void activeContextWithoutCanvas() {
        when(context.isActive()).thenReturn(true);
        when(context.getCanvas()).thenReturn(Optional.empty());
    }

    private void inactiveContext() {
        when(context.isActive()).thenReturn(false);
    }

    private ArgumentParser validArguments(String arguments, int x1, int y1, int x2, int y2) {
        argumentParser = mock(ArgumentParser.class);
        when(argumentParser.getPositionalArgument(arguments, 0, Integer.class)).thenReturn(x1);
        when(argumentParser.getPositionalArgument(arguments, 1, Integer.class)).thenReturn(y1);
        when(argumentParser.getPositionalArgument(arguments, 2, Integer.class)).thenReturn(x2);
        when(argumentParser.getPositionalArgument(arguments, 3, Integer.class)).thenReturn(y2);
        return argumentParser;
    }

    private void activeContextWithCanvas() {
        when(context.isActive()).thenReturn(true);
        when(context.getCanvas()).thenReturn(Optional.of(canvas));
    }
}