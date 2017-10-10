package cs4r.labs.drawingprogram;


public class Canvas {

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


    public void drawHorizontalLine(int y, int x1, int x2) {

        for (int i = x1; i <= x2; ++i) {
            canvas[y][i] = 'x';
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
