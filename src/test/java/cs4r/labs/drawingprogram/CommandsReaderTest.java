package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.InvalidCommandException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static cs4r.labs.drawingprogram.CommandsReader.COMMAND_REGEX;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link CommandsReader}
 */
public class CommandsReaderTest {

    private DrawingContext context;
    private InputStream input;
    private CommandsReader reader;

    @After
    public void tearDown() throws Exception {
        if (input != null) {
            input.close();
        }
    }

    @Test
    public void readOneWellFormedCommandWith2Arguments() throws Exception {

        contextWithInput("C 20 4");

        reader = new CommandsReader(context);

        // When
        Command command = reader.nextCommand();

        // Then
        assertThat(command.getName()).isEqualTo("C");
        assertThat(command.getArguments()).isEqualTo("20 4");
    }

    @Test
    public void readOneWellFormedCommandWith4Arguments() throws Exception {

        contextWithInput("L 1 2 4 4");

        reader = new CommandsReader(context);

        // When
        Command command = reader.nextCommand();

        // Then
        assertThat(command.getName()).isEqualTo("L");
        assertThat(command.getArguments()).isEqualTo("1 2 4 4");
    }

    @Test
    public void readOneWellFormedCommandWithNoArguments() throws Exception {

        contextWithInput("Q");

        reader = new CommandsReader(context);

        // When
        Command command = reader.nextCommand();

        // Then
        assertThat(command.getName()).isEqualTo("Q");
        assertThat(command.getArguments()).isEqualTo("");
    }

    @Test
    public void readOneBadFormedCommand() throws Exception {

        contextWithInput("");

        reader = new CommandsReader(context);

        assertThatThrowsInvalidCommandException(() -> reader.nextCommand());
    }

    @Test
    public void readSeveralCommandsAllValid() throws Exception {

        contextWithInput("C w h\n"
                + "L x1 y1 x2 y2\n"
                + "R x1 y1 x2 y2\n"
                + "B x y c\n"
                + "Q"
        );

        reader = new CommandsReader(context);

        assertThat(reader.nextCommand()).isEqualTo(Command.with("C", "w h"));
        assertThat(reader.nextCommand()).isEqualTo(Command.with("L", "x1 y1 x2 y2"));
        assertThat(reader.nextCommand()).isEqualTo(Command.with("R", "x1 y1 x2 y2"));
        assertThat(reader.nextCommand()).isEqualTo(Command.with("B", "x y c"));
        assertThat(reader.nextCommand()).isEqualTo(Command.with("Q", ""));
    }

    @Test
    public void readSeveralCommandsSomeInvalid() throws Exception {

        contextWithInput("\n"
                + "L x1 y1 x2 y2\n"
                + "R x1 y1 x2 y2\n"
                + "\n"
                + "B x y c\n"
                + "Q------"
        );

        reader = new CommandsReader(context);

        assertThatThrownBy(() -> reader.nextCommand()).isInstanceOf(InvalidCommandException.class);

        assertThat(reader.nextCommand()).isEqualTo(Command.with("L", "x1 y1 x2 y2"));

        assertThat(reader.nextCommand()).isEqualTo(Command.with("R", "x1 y1 x2 y2"));

        assertThatThrownBy(() -> reader.nextCommand()).isInstanceOf(InvalidCommandException.class);

        assertThat(reader.nextCommand()).isEqualTo(Command.with("B", "x y c"));

        assertThatThrownBy(() -> reader.nextCommand()).isInstanceOf(InvalidCommandException.class);
    }

    @Test
    public void testCommandRegex() throws Exception {
        // Valid commands
        assertThat(COMMAND_REGEX.matcher("C w h").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("C -1 -1").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher(" C w h").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("C w h ").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher(" C w h ").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("L x1 y1 x2 y2").matches()).isTrue();
        assertThat(COMMAND_REGEX.matcher("L -1 -2 -4 -5000").matches()).isTrue();
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

    @Test
    public void cannotBeConstructedWithANullContext() throws Exception {

        assertThatThrownBy(() -> new CommandsReader(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("context cannot be null");
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