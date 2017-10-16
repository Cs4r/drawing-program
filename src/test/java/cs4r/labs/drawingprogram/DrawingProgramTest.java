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
}