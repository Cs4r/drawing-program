package cs4r.labs.drawingprogram;

import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link DefaultCommandsReader}
 */
public class DefaultCommandsReaderTest {


    private DrawingContext context;
    private InputStream input;

    @After
    public void tearDown() throws Exception {
        input.close();
    }

    @Test
    public void readOneWellFormedCommandWith2Arguments() throws Exception {

        DefaultCommandsReader reader = new DefaultCommandsReader();

        contextWithInput("C 20 4");

        // When
        Command command = reader.nextCommand(context);

        // Then
        assertThat(command.getName()).isEqualTo("C");
        assertThat(command.getArguments()).isEqualTo("20 4");
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