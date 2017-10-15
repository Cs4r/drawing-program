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
 * Unit tests for {@link FillArea}.
 */
@RunWith(MockitoJUnitRunner.class)
public class FillAreaTest {

    @Mock
    private Canvas canvas;
    @Mock
    private ArgumentParser argumentParser;
    private DrawingContext context;

    @Test
    public void requireNonNullArguments() throws Exception {

        FillArea fillArea = new FillArea(argumentParser);

        assertThatThrownBy(() -> fillArea.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> fillArea.execute(null, mock(DrawingContext.class)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");
    }

    @Test
    public void fillAreaIfContextIsActive() throws Exception {

        // Given
        context = TestUtils.activeContextWithCanvas(canvas);
        String arguments = "34 31 c";
        argumentParser = validFillAreaArguments(arguments, 34, 31, 'c');
        FillArea fillArea = new FillArea(argumentParser);

        // When
        fillArea.execute(arguments, context);

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(argumentParser).getPositionalArgument(arguments, 0, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 1, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 2, Character.class);
        verify(canvas).fillArea(33, 30, 'c');
    }

    @Test
    public void doNotFillAreaIfContextIsNotActive() throws Exception {
        // Given
        context = TestUtils.inactiveContext();
        String arguments = "34 31 c";
        argumentParser = validFillAreaArguments(arguments, 23, 23, 'b');
        FillArea fillArea = new FillArea(argumentParser);

        // When
        fillArea.execute(arguments, context);

        // Then
        verify(context).isActive();
        verify(context, never()).getCanvas();
        verify(canvas, never()).fillArea(anyInt(), anyInt(), anyChar());
    }

    @Test
    public void throwCanvasNotFoundExceptionIfNoCanvas() throws Exception {
        context = TestUtils.activeContextWithoutCanvas(canvas);
        FillArea fillArea = new FillArea(argumentParser);

        // When
        assertThatThrownBy(() -> fillArea.execute("Arguments are not used.", context))
                .isInstanceOf(CanvasNotFoundException.class)
                .hasMessage("no canvas to draw on");

        // Then
        verify(context).isActive();
        verify(context).getCanvas();
        verify(canvas, never()).fillArea(anyInt(), anyInt(), anyChar());
    }

    @Test
    public void cannotBeConstructedWithNullArgumentParser() throws Exception {

        assertThatThrownBy(() -> new FillArea(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("argumentParser cannot be null");
    }

    private ArgumentParser validFillAreaArguments(String arguments, int x, int y, char c) {
        ArgumentParser argumentParser = mock(ArgumentParser.class);

        when(argumentParser.getPositionalArgument(arguments, 0, Integer.class)).thenReturn(x);
        when(argumentParser.getPositionalArgument(arguments, 1, Integer.class)).thenReturn(y);
        when(argumentParser.getPositionalArgument(arguments, 2, Character.class)).thenReturn(c);

        return argumentParser;
    }

}