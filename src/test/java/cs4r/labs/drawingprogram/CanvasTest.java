package cs4r.labs.drawingprogram;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * Unit tests for {@link Canvas}
 */
public class CanvasTest {

    @Test
    public void createEmptyCanvas() throws Exception {

        int height = 4;
        int width = 10;

        Canvas canvas = new Canvas(height, width);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------\n"
                + "|          |\n"
                + "|          |\n"
                + "|          |\n"
                + "|          |\n"
                + "------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void drawSingleHorizontalLine() throws Exception {

        int height = 5;
        int width = 10;

        Canvas canvas = new Canvas(height, width);

        canvas.drawHorizontalLine(0, 0, 5);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------\n"
                + "|xxxxxx    |\n"
                + "|          |\n"
                + "|          |\n"
                + "|          |\n"
                + "|          |\n"
                + "------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }
}