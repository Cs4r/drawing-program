package cs4r.labs.drawingprogram;


import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * An acceptance test for the drawing program.
 * <p>
 * It tests that the program supports the 5 commands described in the functional specification.
 */
public class DrawingProgramAcceptanceTest {


    @Test
    public void allSupportedCommands() throws Exception {

        InputStream input = TestUtils.getInputStreamFromFile("acceptance-test-input.txt", this.getClass());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        new DrawingProgram(input, output).run();

        String currentOutput = TestUtils.convertToString(output);
        String expectedOutput = TestUtils.readStringFromFile("acceptance-test-output.txt", this.getClass());

        assertThat(currentOutput).isEqualTo(expectedOutput);

        // Close streams
        output.close();
        input.close();
    }
}
