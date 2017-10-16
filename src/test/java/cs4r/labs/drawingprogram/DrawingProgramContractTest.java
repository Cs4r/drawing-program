package cs4r.labs.drawingprogram;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * States the behavior of the program beyond the acceptance criteria.
 */
public class DrawingProgramContractTest {

    @Test
    public void coordinatesOutsideTheCanvas() throws Exception {
        InputStream input = TestUtils.getInputStreamFromFile("contract-test-input.txt", this.getClass());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        new DrawingProgram(input, output).run();

        String realOutput = TestUtils.convertToString(output);
        String expectedOutput = TestUtils.readStringFromFile("contract-test-output.txt", this.getClass());

        assertThat(realOutput).isEqualTo(expectedOutput);

        // Close streams
        output.close();
        input.close();
    }
}
