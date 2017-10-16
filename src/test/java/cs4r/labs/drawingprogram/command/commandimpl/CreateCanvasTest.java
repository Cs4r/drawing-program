package cs4r.labs.drawingprogram.command.commandimpl;

import cs4r.labs.drawingprogram.Canvas;
import cs4r.labs.drawingprogram.TestUtils;
import cs4r.labs.drawingprogram.command.DrawingContext;
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
    @Mock
    private DrawingContext context;

    @Test
    public void requireNonNullContext() throws Exception {

        CreateCanvas createCanvas = new CreateCanvas(argumentParser);

        assertThatThrownBy(() -> createCanvas.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }

    @Test
    public void createANewCanvasIfContextIsActive() throws Exception {

        // Given
        String arguments = "20 40";
        argumentParser = TestUtils.validIntArguments(arguments, 20, 40);
        context = TestUtils.activeContext();
        CreateCanvas createCanvas = new CreateCanvas(argumentParser);

        // When
        createCanvas.execute(arguments, context);

        // Then
        verify(argumentParser).getIntArgument(arguments, 0);
        verify(argumentParser).getIntArgument(arguments, 1);
        ArgumentCaptor<Canvas> canvasCaptor = ArgumentCaptor.forClass(Canvas.class);

        verify(context).setCanvas(canvasCaptor.capture());
        verify(context).isActive();

        Canvas newCanvas = canvasCaptor.getValue();
        assertThat(newCanvas.getWidth()).isEqualTo(20);
        assertThat(newCanvas.getHeight()).isEqualTo(40);
    }

    @Test
    public void doNotCreateANewCanvasIfContextIsNotActive() throws Exception {

        // Given
        CreateCanvas createCanvas = new CreateCanvas(argumentParser);
        context = TestUtils.inactiveContext();

        createCanvas.execute("Arguments are not relevant for this test", context);

        // Then
        verify(context).isActive();
        verify(context, never()).setCanvas(any());
    }

    @Test
    public void cannotBeConstructedWithNullArgumentParser() throws Exception {

        assertThatThrownBy(() -> new CreateCanvas(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("argumentParser cannot be null");
    }
}