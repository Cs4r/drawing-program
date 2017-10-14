package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;


/**
 * Unit tests for {@link DrawLine}
 */
public class DrawLineTest {


    private DrawingContext context;
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
        contextWithCanvas();
        validArguments();
        DrawLine drawLine = new DrawLine(argumentParser);

        // When
        drawLine.execute("1 2 3 4", context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(argumentParser).getPositionalArgument(0, Integer.class);
        verify(argumentParser).getPositionalArgument(1, Integer.class);
        verify(argumentParser).getPositionalArgument(2, Integer.class);
        verify(argumentParser).getPositionalArgument(3, Integer.class);
        verify(canvas).drawLine(0, 1, 2, 3);
    }

    private ArgumentParser validArguments() {
        argumentParser = mock(ArgumentParser.class);
        when(argumentParser.getPositionalArgument(0, Integer.class)).thenReturn(Optional.of(1));
        when(argumentParser.getPositionalArgument(1, Integer.class)).thenReturn(Optional.of(2));
        when(argumentParser.getPositionalArgument(2, Integer.class)).thenReturn(Optional.of(3));
        when(argumentParser.getPositionalArgument(3, Integer.class)).thenReturn(Optional.of(4));
        return argumentParser;
    }

    private void contextWithCanvas() {
        context = mock(DrawingContext.class);
        when(context.isActive()).thenReturn(true);
        canvas = mock(Canvas.class);
        when(context.getCanvas()).thenReturn(Optional.of(canvas));
    }
}