package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.DrawingContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link CreateCanvas}.
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateCanvasTest {

    @Mock
    private ArgumentParser argumentParser;

    @Test
    public void requireNonNullContext() throws Exception {

        CreateCanvas createCanvas = new CreateCanvas(null);

        assertThatThrownBy(() -> createCanvas.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }

    @Test
    public void createsANewCanvasIfContextIsActive() throws Exception {

        // Given
        CreateCanvas createCanvas = new CreateCanvas(argumentParser);
        DrawingContext context = mock(DrawingContext.class);
        when(context.isActive()).thenReturn(true);
        String arguments = "20 40";
        validArguments(arguments, 20, 40);

        // When
        createCanvas.execute(arguments, context);

        // Then
        verify(argumentParser).getPositionalArgument(arguments, 0, Integer.class);
        verify(argumentParser).getPositionalArgument(arguments, 1, Integer.class);
        ArgumentCaptor<Canvas> canvasCaptor = ArgumentCaptor.forClass(Canvas.class);

        verify(context).setCanvas(canvasCaptor.capture());

        Canvas newCanvas = canvasCaptor.getValue();
        assertThat(newCanvas.getWidth()).isEqualTo(20);
        assertThat(newCanvas.getHeight()).isEqualTo(40);
    }

    private void validArguments(String arguments, int w, int h) {
        when(argumentParser.getPositionalArgument(arguments, 0, Integer.class)).thenReturn(w);
        when(argumentParser.getPositionalArgument(arguments, 1, Integer.class)).thenReturn(h);
    }
}