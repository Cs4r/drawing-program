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

    @Test
    public void drawSingleRectangle() throws Exception {

        int height = 6;
        int width = 10;

        Canvas canvas = new Canvas(height, width);

        canvas.drawRectangle(1, 1, 5, 5);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------\n"
                + "|          |\n"
                + "| xxxxx    |\n"
                + "| x   x    |\n"
                + "| x   x    |\n"
                + "| x   x    |\n"
                + "| xxxxx    |\n"
                + "------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void ignoreInvalidRectangles() throws Exception {

        int height = 4;
        int width = 16;

        Canvas canvas = new Canvas(height, width);

        // Invalid corners
        canvas.drawRectangle(2, 2, 0, 0);
        canvas.drawRectangle(3, 4, 1, 2);

        // Points outside the canvas
        canvas.drawRectangle(-1, 0, 2, 2);
        canvas.drawRectangle(0, -1, 2, 2);

        canvas.drawRectangle(0, 0, -2, 2);
        canvas.drawRectangle(0, 0, 2, -2);

        canvas.drawRectangle(0, 20, 10, 10);
        canvas.drawRectangle(20, 0, 10, 10);

        canvas.drawRectangle(0, 0, 20, 2);
        canvas.drawRectangle(0, 0, 2, 20);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------------\n"
                + "|                |\n"
                + "|                |\n"
                + "|                |\n"
                + "|                |\n"
                + "------------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void drawSeveralRectangles() throws Exception {

        int height = 16;
        int width = 16;

        Canvas canvas = new Canvas(height, width);

        canvas.drawRectangle(0, 0, 2, 2);
        canvas.drawRectangle(3, 6, 9, 10);
        canvas.drawRectangle(4, 13, 15, 15);

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------------\n"
                + "|xxx             |\n"
                + "|x x             |\n"
                + "|xxx             |\n"
                + "|                |\n"
                + "|                |\n"
                + "|                |\n"
                + "|   xxxxxxx      |\n"
                + "|   x     x      |\n"
                + "|   x     x      |\n"
                + "|   x     x      |\n"
                + "|   xxxxxxx      |\n"
                + "|                |\n"
                + "|                |\n"
                + "|    xxxxxxxxxxxx|\n"
                + "|    x          x|\n"
                + "|    xxxxxxxxxxxx|\n"
                + "------------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void fillSimpleArea() throws Exception {

        int height = 10;
        int width = 25;

        Canvas canvas = new Canvas(height, width);

        canvas.fillArea(5, 5, 'a');

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "---------------------------\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "|aaaaaaaaaaaaaaaaaaaaaaaaa|\n"
                + "---------------------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void fillComplexArea() throws Exception {

        int height = 4;
        int width = 20;

        Canvas canvas = new Canvas(height, width);

        canvas.drawLine(0, 1, 5, 1);
        canvas.drawLine(5, 2, 5, 3);
        canvas.drawRectangle(15, 0, 19, 2);

        canvas.fillArea(9, 2, 'o');

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "----------------------\n"
                + "|oooooooooooooooxxxxx|\n"
                + "|xxxxxxooooooooox   x|\n"
                + "|     xoooooooooxxxxx|\n"
                + "|     xoooooooooooooo|\n"
                + "----------------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void ignoreAreaFillsOutsideTheCanvas() throws Exception {

        int height = 4;
        int width = 4;

        Canvas canvas = new Canvas(height, width);

        canvas.fillArea(-1, 0, 'a');
        canvas.fillArea(0, -1, 'b');
        canvas.fillArea(0, 4, 'c');
        canvas.fillArea(4, 0, 'd');

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------\n"
                + "|    |\n"
                + "|    |\n"
                + "|    |\n"
                + "|    |\n"
                + "------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

    @Test
    public void fillSeveralAreas() throws Exception {

        int height = 16;
        int width = 16;

        Canvas canvas = new Canvas(height, width);

        canvas.drawRectangle(0, 0, 2, 2);
        canvas.drawRectangle(3, 6, 9, 10);
        canvas.drawRectangle(4, 13, 15, 15);

        canvas.fillArea(1, 1, 'o');
        canvas.fillArea(15, 0, '*');
        canvas.fillArea(14, 14, '-');
        canvas.fillArea(5, 7, '.');

        String currentCanvas = canvas.toText();

        String expectedCanvas = ""
                + "------------------\n"
                + "|xxx*************|\n"
                + "|xox*************|\n"
                + "|xxx*************|\n"
                + "|****************|\n"
                + "|****************|\n"
                + "|****************|\n"
                + "|***xxxxxxx******|\n"
                + "|***x.....x******|\n"
                + "|***x.....x******|\n"
                + "|***x.....x******|\n"
                + "|***xxxxxxx******|\n"
                + "|****************|\n"
                + "|****************|\n"
                + "|****xxxxxxxxxxxx|\n"
                + "|****x----------x|\n"
                + "|****xxxxxxxxxxxx|\n"
                + "------------------\n";

        assertThat(currentCanvas).isEqualTo(expectedCanvas);
    }

}