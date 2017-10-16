package cs4r.labs.drawingprogram.command.commandimpl;

import cs4r.labs.drawingprogram.TestUtils;
import cs4r.labs.drawingprogram.command.DrawingContext;
import cs4r.labs.drawingprogram.command.exception.CorruptedOutputException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;


/**
 * Unit tests for {@link PrintPrompt}.
 */
public class PrintPromptTest {

    private ByteArrayOutputStream output;

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

        PrintPrompt printPrompt = new PrintPrompt();

        assertThatThrownBy(() -> printPrompt.execute("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
    }

    @Test
    public void printPromptIfContextIsActive() throws Exception {

        // Given
        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = TestUtils.activeContextWithOutput(output);

        // When
        printPrompt.execute(null, context);

        // Then
        verify(context).isActive();
        String outputAsString = TestUtils.getOutputAsString(context);
        assertThat(outputAsString).isEqualTo("enter command:");
    }

    @Test
    public void doNotPrintPromptIfContextIsNotActive() throws Exception {
        // Given
        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = TestUtils.inactiveContextWithOutput(output);

        // When
        printPrompt.execute(null, context);

        // Then
        verify(context).isActive();
        String outputAsString = TestUtils.getOutputAsString(context);
        assertThat(outputAsString).isEmpty();
    }

    @Test
    public void throwCorruptedOutputExceptionIfOutputIsCorrupted() throws Exception {
        PrintPrompt printPrompt = new PrintPrompt();

        DrawingContext context = TestUtils.activeContextWithBrokenOutput(null);

        assertThatThrownBy(() ->
                printPrompt.execute(null, context))
                .isInstanceOf(CorruptedOutputException.class)
                .hasMessage("output is corrupted");

        verify(context).isActive();
    }
}