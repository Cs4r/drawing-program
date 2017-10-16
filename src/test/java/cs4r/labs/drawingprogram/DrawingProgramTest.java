package cs4r.labs.drawingprogram;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.io.OutputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Unit test for {@link DrawingProgram}.
 */
public class DrawingProgramTest {

    private InputStream input;
    private OutputStream output;
    private DrawingProgram drawingProgram;

    @Before
    public void setUp() throws Exception {
        input = Mockito.mock(InputStream.class);
        output = Mockito.mock(OutputStream.class);

        drawingProgram = new DrawingProgram(input, output);
    }

    @Test
    public void hasContext() throws Exception {
        DrawingContext context = drawingProgram.getContext();
        assertThat(context).isNotNull();

        assertThat(context.getInput()).isEqualTo(input);
        assertThat(context.getOutput()).isEqualTo(output);
    }

    @Test
    public void implementsAllCommandRequired() throws Exception {
        CommandsProcessor commandImplementationRegistry = drawingProgram.getCommandsProcessor();

        assertThat(commandImplementationRegistry.canHandle(Command.PRINT_PROMPT_COMMAND)).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.PRINT_CANVAS_COMMAND)).isTrue();

        assertThat(commandImplementationRegistry.canHandle(Command.with("C", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("L", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("R", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("B", ""))).isTrue();
        assertThat(commandImplementationRegistry.canHandle(Command.with("Q", ""))).isTrue();
    }

    @Test
    public void hasCommandsReader() throws Exception {
        CommandsReader commandsReader = drawingProgram.getCommandsReader();
        assertThat(commandsReader).isNotNull();
    }

    @Test
    public void hasDrawingCommandsInterpreter() throws Exception {
        DrawingCommandsInterpreter drawingCommandsInterpreter = drawingProgram.getDrawingCommandsInterpreter();
        assertThat(drawingCommandsInterpreter).isNotNull();
    }
}