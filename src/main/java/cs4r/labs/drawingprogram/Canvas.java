package cs4r.labs.drawingprogram;


public class Canvas {

    private static final char LINE_COLOR = 'x';
    private int width;
    private int height;
    private char[][] canvas;

    public Canvas(int height, int width) {
        this.height = height;
        this.width = width;
        canvas = new char[this.height][this.width];
        initCanvas();
    }

    /**
     * Returns a text representation of this canvas.
     * <p>
     * <b>Note/b>: use {@link #toString()} only for debugging purposes.
     *
     * @return text representation of the canvas as {@link String}.
     */
    public String toText() {

        StringBuilder canvasAsText = new StringBuilder();

        addHeader(canvasAsText);

        addCanvas(canvasAsText);

        addFooter(canvasAsText);

        return canvasAsText.toString();
    }


    public void drawLine(int x1, int y1, int x2, int y2) {

        if (y1 == y2) {
            drawHorizontalLine(y1, x1, x2);
        } else if (x1 == x2) {
            drawVerticalLine(x1, y1, y2);
        }
    }


    private void drawHorizontalLine(int y, int x1, int x2) {

        int from;
        int to;

        if (x1 > x2) {
            from = x2;
            to = x1;
        } else {
            from = x1;
            to = x2;
        }

        boolean xWithinCanvas = !(from < 0 || to >= width);
        boolean yWithinCanvas = !(y >= height || y < 0);

        if (xWithinCanvas && yWithinCanvas) {

            for (int i = from; i <= to; ++i) {
                canvas[y][i] = LINE_COLOR;
            }
        }

    }

    private void drawVerticalLine(int x, int y1, int y2) {

        int from;
        int to;

        if (y1 > y2) {
            from = y2;
            to = y1;
        } else {
            from = y1;
            to = y2;
        }

        boolean xWithinTheCanvas = !(x >= width || x < 0);
        boolean yWithinTheCanvas = !(to >= height || from < 0);

        if (xWithinTheCanvas && yWithinTheCanvas) {
            for (int j = from; j <= to; ++j) {
                canvas[j][x] = LINE_COLOR;
            }

        }
    }

    private void initCanvas() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                canvas[i][j] = ' ';
            }
        }
    }

    private void addHeader(StringBuilder canvasAsText) {
        addHorizontalBorder(canvasAsText);
    }

    private void addCanvas(StringBuilder canvasAsText) {
        for (int i = 0; i < height; ++i) {
            canvasAsText.append('|');
            for (int j = 0; j < width; ++j) {
                canvasAsText.append(canvas[i][j]);
            }
            canvasAsText.append('|');
            canvasAsText.append('\n');
        }
    }

    private void addFooter(StringBuilder canvasAsText) {
        addHorizontalBorder(canvasAsText);
    }

    private void addHorizontalBorder(StringBuilder canvasAsText) {
        for (int i = 0; i < width + 2; ++i) {
            canvasAsText.append('-');
        }
        canvasAsText.append('\n');
    }
}
