package cs4r.labs.drawingprogram;


import org.junit.Ignore;
import org.junit.Test;

import java.io.*;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * An acceptance test for the drawing program.
 * <p>
 * It tests that the program supports the 5 commands described in the functional specification.
 */
@Ignore("This test will be ignored until I finish the implementation of the program")
public class DrawingProgramAcceptanceTest {


    @Test
    public void allSupportedCommands() throws Exception {

        InputStream input = getInputStreamFromFile("acceptance-test-input.txt");
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        new DrawingProgram(input, output).run();

        String currentOutput = convertToString(output);
        String expectedOutput = readStringFromFile("acceptance-test-output.txt");

        assertThat(currentOutput).isEqualTo(expectedOutput);

        // Close streams
        output.close();
        input.close();
    }

    private String convertToString(ByteArrayOutputStream output) {
        return new String(output.toByteArray());
    }

    private InputStream getInputStreamFromFile(String filePath) throws Exception {
        return this.getClass().getResourceAsStream(filePath);
    }

    private String readStringFromFile(String filePath) throws Exception {

        InputStream resourceAsStream = getInputStreamFromFile(filePath);

        String fileAsString = new BufferedReader(new InputStreamReader(resourceAsStream))
                .lines()
                .collect(joining("\n"));

        resourceAsStream.close();

        return fileAsString;
    }

}
