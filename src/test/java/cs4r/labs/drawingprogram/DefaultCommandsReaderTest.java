package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.InvalidCommandException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

    @Before
    public void setUp() throws Exception {
        reader = new DefaultCommandsReader();
    }

    @After
    public void tearDown() throws Exception {
        input.close();
    }

    @Test
    public void readOneWellFormedCommandWith2Arguments() throws Exception {

        contextWithInput("C 20 4");

        // When
        Command command = reader.nextCommand(context);

        // Then
        assertThat(command.getName()).isEqualTo("C");
        assertThat(command.getArguments()).isEqualTo("20 4");
    }

    @Test
    public void readOneWellFormedCommandWith4Arguments() throws Exception {

        contextWithInput("L 1 2 4 4");

        // When
        Command command = reader.nextCommand(context);

        // Then
        assertThat(command.getName()).isEqualTo("L");
        assertThat(command.getArguments()).isEqualTo("1 2 4 4");
    }

    @Test
    public void readOneWellFormedCommandWithNoArguments() throws Exception {

        contextWithInput("Q");

        // When
        Command command = reader.nextCommand(context);

        // Then
        assertThat(command.getName()).isEqualTo("Q");
        assertThat(command.getArguments()).isEqualTo("");
    }

    @Test
    public void readOneBadFormedCommand() throws Exception {

        contextWithInput("");
        assertThatThrowsInvalidCommandException(() -> reader.nextCommand(context));

        contextWithInput("A --");
        assertThatThrowsInvalidCommandException(() -> reader.nextCommand(context));

        contextWithInput("-- 1 2");
        assertThatThrowsInvalidCommandException(() -> reader.nextCommand(context));

        contextWithInput("$$ 1 2");
        assertThatThrowsInvalidCommandException(() -> reader.nextCommand(context));

        contextWithInput("$$ 1 2");
        assertThatThrowsInvalidCommandException(() -> reader.nextCommand(context));
    }

    private void assertThatThrowsInvalidCommandException(ThrowableAssert.ThrowingCallable callable) {
        assertThatThrownBy(callable)
                .isInstanceOf(InvalidCommandException.class)
                .hasMessage("Invalid command");
    }

    private InputStream contextWithInput(String inputAsString) {

        context = mock(DrawingContext.class);

        input = getInputStream(inputAsString);

        when(context.getInput()).thenReturn(input);
        return input;
    }

    private InputStream getInputStream(String inputAsString) {
        return new ByteArrayInputStream(inputAsString.getBytes());
    }
}