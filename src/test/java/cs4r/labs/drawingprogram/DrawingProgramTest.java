package cs4r.labs.drawingprogram;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Unit test for {@link DrawingProgram}
 */
public class DrawingProgramTest {


    @Test
    public void hasContext() throws Exception {

        InputStream input = Mockito.mock(InputStream.class);
        OutputStream output = Mockito.mock(OutputStream.class);

        DrawingProgram drawingProgram = new DrawingProgram(input, output);

        DrawingContext context = drawingProgram.getContext();
        assertThat(context).isNotNull();

        assertThat(context.getInput()).isEqualTo(input);
        assertThat(context.getOutput()).isEqualTo(output);
    }

    @Test
    public void implementsAllCommandRequired() throws Exception {

        InputStream input = Mockito.mock(InputStream.class);
        OutputStream output = Mockito.mock(OutputStream.class);

        DrawingProgram drawingProgram = new DrawingProgram(input, output);

        CommandsProcessor commandImplementationRegistry = drawingProgram.getCommandsProcessor();

        assertThat(commandImplementationRegistry.canHandle(Command.PRINT_PROMPT_COMMAND)).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.PRINT_CANVAS_COMMAND)).isTrue();

        assertThat(commandImplementationRegistry.canHandle(Command.with("C", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("L", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("R", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("B", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("Q", ""))).isTrue();
    }
}