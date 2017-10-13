package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.InvalidCommandException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static cs4r.labs.drawingprogram.DefaultCommandsReader.COMMAND_REGEX;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link DefaultCommandsReader}
 */
public class DefaultCommandsReaderTest {


    private DrawingContext context;
    private InputStream input;
    private DefaultCommandsReader reader;

    @After
    public void tearDown() throws Exception {
        if (input != null) {
            input.close();
        }
    }

    @Test
    public void readOneWellFormedCommandWith2Arguments() throws Exception {

        contextWithInput("C 20 4");

        reader = new DefaultCommandsReader(context);

        // When
        Command command = reader.nextCommand();

        // Then
        assertThat(command.getName()).isEqualTo("C");
        assertThat(command.getArguments()).isEqualTo("20 4");
    }

    @Test
    public void readOneWellFormedCommandWith4Arguments() throws Exception {

        contextWithInput("L 1 2 4 4");

        reader = new DefaultCommandsReader(context);

        // When
        Command command = reader.nextCommand();

        // Then
        assertThat(command.getName()).isEqualTo("L");
        assertThat(command.getArguments()).isEqualTo("1 2 4 4");
    }

    @Test
    public void readOneWellFormedCommandWithNoArguments() throws Exception {

        contextWithInput("Q");

        reader = new DefaultCommandsReader(context);

        // When
        Command command = reader.nextCommand();

        // Then
        assertThat(command.getName()).isEqualTo("Q");
        assertThat(command.getArguments()).isEqualTo("");
    }

    @Test
    public void readOneBadFormedCommand() throws Exception {

        contextWithInput("");

        reader = new DefaultCommandsReader(context);

        assertThatThrowsInvalidCommandException(() -> reader.nextCommand());
    }

    @Test
    public void testCommandRegex() throws Exception {
        // Valid commands
        assertThat(COMMAND_REGEX.matcher("C w h").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher(" C w h").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("C w h ").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher(" C w h ").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("L x1 y1 x2 y2").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("R x1 y1 x2 y2").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("B x y c").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("Q").matches()).isTrue();

        // Invalid commands
        assertThat(COMMAND_REGEX.matcher("L- x1 *").matches()).isFalse();
        assertThat(COMMAND_REGEX.matcher("R¡ x1 y1 x2 y2").matches()).isFalse();
        assertThat(COMMAND_REGEX.matcher("B x y c,").matches()).isFalse();
        assertThat(COMMAND_REGEX.matcher("").matches()).isFalse();
        assertThat(COMMAND_REGEX.matcher("A --").matches()).isFalse();
        assertThat(COMMAND_REGEX.matcher("-- 1 2").matches()).isFalse();
        assertThat(COMMAND_REGEX.matcher("$$ 1 2").matches()).isFalse();

    }

    private void assertThatThrowsInvalidCommandException(ThrowableAssert.ThrowingCallable callable) {
        assertThatThrownBy(callable)
                .isInstanceOf(InvalidCommandException.class)
                .hasMessage("Invalid command");
    }

    private DrawingContext contextWithInput(String inputAsString) {

        context = mock(DrawingContext.class);

        input = getInputStream(inputAsString);

        when(context.getInput()).thenReturn(input);
        return context;
    }

    private InputStream getInputStream(String inputAsString) {
        return new ByteArrayInputStream(inputAsString.getBytes());
    }
}