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
 * Unit tests for {@link DrawRectangle}.
 */
@RunWith(MockitoJUnitRunner.class)
public class DrawRectangleTest {

    @Mock
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
        activeContextWithCanvas();
        String arguments = "1 1 40 40";
        validArguments(arguments, 1, 1, 40, 40);
        DrawRectangle drawRectangle = new DrawRectangle(argumentParser);

        // When
        drawRectangle.execute(arguments, context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(argumentParser).getPositionalArgument(arguments, 0, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 1, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 2, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 3, Integer.class);
        verify(canvas).drawRectangle(0, 0, 39, 39);
    }


    @Test
    public void doNotDrawRectangleIfContextIsNotActive() throws Exception {
        inactiveContext();
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
        activeContextWithoutCanvas();
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

    private void activeContextWithoutCanvas() {
        when(context.isActive()).thenReturn(true);
        when(context.getCanvas()).thenReturn(Optional.empty());
    }

    private void inactiveContext() {
        when(context.isActive()).thenReturn(false);
    }

    private void validArguments(String arguments, int x1, int y1, int x2, int y2) {
        argumentParser = mock(ArgumentParser.class);
        when(argumentParser.getPositionalArgument(arguments, 0, Integer.class)).thenReturn(x1);
        when(argumentParser.getPositionalArgument(arguments, 1, Integer.class)).thenReturn(y1);
        when(argumentParser.getPositionalArgument(arguments, 2, Integer.class)).thenReturn(x2);
        when(argumentParser.getPositionalArgument(arguments, 3, Integer.class)).thenReturn(y2);
    }

    private void activeContextWithCanvas() {
        when(context.isActive()).thenReturn(true);
        when(context.getCanvas()).thenReturn(Optional.of(canvas));
    }
}