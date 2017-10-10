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


    @Test
    public void drawHorizontalLineWithInvertedX() throws Exception {
        int height = 5;
        int width = 10;

        Canvas canvas = new Canvas(height, width);

        canvas.drawHorizontalLine(1, 8, 0);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------\n"
                + "|          |\n"
                + "|xxxxxxxxx |\n"
                + "|          |\n"
                + "|          |\n"
                + "|          |\n"
                + "------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void drawSeveralHorizontalLines() throws Exception {

        int height = 7;
        int width = 16;

        Canvas canvas = new Canvas(height, width);

        canvas.drawHorizontalLine(0, 3, 9);
        canvas.drawHorizontalLine(1, 3, 6);
        canvas.drawHorizontalLine(2, 3, 5);
        canvas.drawHorizontalLine(4, 0, 4);
        canvas.drawHorizontalLine(5, 0, 15);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------------\n"
                + "|   xxxxxxx      |\n"
                + "|   xxxx         |\n"
                + "|   xxx          |\n"
                + "|                |\n"
                + "|xxxxx           |\n"
                + "|xxxxxxxxxxxxxxxx|\n"
                + "|                |\n"
                + "------------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

}