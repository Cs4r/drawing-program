package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.DrawingContext;
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