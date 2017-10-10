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

        canvas.drawLine(0, 0, 5, 0);

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

        canvas.drawLine(8, 1, 0, 1);

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

        canvas.drawLine(3, 0, 9, 0);
        canvas.drawLine(3, 1, 6, 1);
        canvas.drawLine(3, 2, 5, 2);
        canvas.drawLine(0, 4, 4, 4);
        canvas.drawLine(0, 5, 15, 5);

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

    @Test
    public void ignoreHorizontalLinesOutSideTheCanvas() throws Exception {

        int height = 5;
        int width = 5;

        Canvas canvas = new Canvas(height, width);

        canvas.drawLine(0, 5, 3, 5);

        canvas.drawLine(0, -1, 3, -1);

        canvas.drawLine(-1, 0, 3, 0);
        canvas.drawLine(1, 0, -3, 0);
        canvas.drawLine(-1, 0, -5, 0);

        canvas.drawLine(5, 0, 1, 0);
        canvas.drawLine(1, 0, 5, 0);
        canvas.drawLine(5, 0, 5, 0);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "-------\n"
                + "|     |\n"
                + "|     |\n"
                + "|     |\n"
                + "|     |\n"
                + "|     |\n"
                + "-------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void drawSingleVerticalLine() throws Exception {

        int height = 10;
        int width = 10;

        Canvas canvas = new Canvas(height, width);

        canvas.drawLine(3, 2, 3, 7);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------\n"
                + "|          |\n"
                + "|          |\n"
                + "|   x      |\n"
                + "|   x      |\n"
                + "|   x      |\n"
                + "|   x      |\n"
                + "|   x      |\n"
                + "|   x      |\n"
                + "|          |\n"
                + "|          |\n"
                + "------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void drawVerticalLineWithInvertedY() throws Exception {

        int height = 6;
        int width = 10;

        Canvas canvas = new Canvas(height, width);

        canvas.drawLine(1, 5, 1, 1);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------\n"
                + "|          |\n"
                + "| x        |\n"
                + "| x        |\n"
                + "| x        |\n"
                + "| x        |\n"
                + "| x        |\n"
                + "------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void ignoreVerticalLinesOutSideTheCanvas() throws Exception {

        int height = 3;
        int width = 3;

        Canvas canvas = new Canvas(height, width);

        canvas.drawLine(3, 0, 3, 1);
        canvas.drawLine(-3, 0, -3, 1);

        canvas.drawLine(1, -1, 1, 2);
        canvas.drawLine(1, 1, 1, -2);

        canvas.drawLine(1, 5, 1, 1);
        canvas.drawLine(1, 1, 1, 5);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "-----\n"
                + "|   |\n"
                + "|   |\n"
                + "|   |\n"
                + "-----\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void drawSeveralVerticalLines() throws Exception {

        int height = 12;
        int width = 23;

        Canvas canvas = new Canvas(height, width);

        canvas.drawLine(3, 2, 3, 7);
        canvas.drawLine(7, 0, 7, 2);
        canvas.drawLine(3, 2, 3, 7);
        canvas.drawLine(5, 3, 5, 5);
        canvas.drawLine(22, 0, 22, 11);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "-------------------------\n"
                + "|       x              x|\n"
                + "|       x              x|\n"
                + "|   x   x              x|\n"
                + "|   x x                x|\n"
                + "|   x x                x|\n"
                + "|   x x                x|\n"
                + "|   x                  x|\n"
                + "|   x                  x|\n"
                + "|                      x|\n"
                + "|                      x|\n"
                + "|                      x|\n"
                + "|                      x|\n"
                + "-------------------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void ignoreDiagonalLines() throws Exception {

        int height = 5;
        int width = 5;

        Canvas canvas = new Canvas(height, width);

        canvas.drawLine(0, 0, 4, 4);
        canvas.drawLine(0, 1, 2, 3);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "-------\n"
                + "|     |\n"
                + "|     |\n"
                + "|     |\n"
                + "|     |\n"
                + "|     |\n"
                + "-------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

}