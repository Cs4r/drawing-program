package cs4r.labs.drawingprogram;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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


    /**
     * Draws a line from the point defined by (x1, y2) to the point defined by (x2, y2).
     * <p>
     * Only horizontal and vertical lines are supported. Diagonal lines will not be drawn.
     * <p>
     * Lines whose defining points are outside the canvas will not be drawn either.
     *
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @param x2 x coordinate of the second point
     * @param y2 y coordinate of the second point
     */
    public void drawLine(int x1, int y1, int x2, int y2) {

        if (y1 == y2) {
            drawHorizontalLine(y1, x1, x2);
        } else if (x1 == x2) {
            drawVerticalLine(x1, y1, y2);
        }
    }


    /**
     * Draws a rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2).
     * <p>
     * Rectangles with corners outside the canvas will not be drawn.
     *
     * @param x1 x coordinate of the first point
     * @param y1 y coordinate of the first point
     * @param x2 x coordinate of the second point
     * @param y2 y coordinate of the second point
     */
    public void drawRectangle(int x1, int y1, int x2, int y2) {

        boolean validLeftCorner = (x1 <= x2 && y1 <= y2) && (x1 >= 0 && x1 < width) && (y1 >= 0 && y1 < height);
        boolean validRightCorner = (x2 >= 0 && x2 < width) && (y2 >= 0 && y2 < height);

        if (validLeftCorner && validRightCorner) {
            drawHorizontalLine(y1, x1, x2);
            drawVerticalLine(x1, y1, y2);
            drawVerticalLine(x2, y1, y2);
            drawHorizontalLine(y2, x1, x2);
        }
    }

    public void fillArea(int x, int y, char colour) {


        LinkedList<Cell> toColour = new LinkedList<>();

        toColour.add(Cell.from(x, y));

        while (!toColour.isEmpty()) {

            Cell current = toColour.removeFirst();

            canvas[current.getY()][current.getX()] = colour;

            List<Cell> uncoloredNeighbors = uncoloredNeighbors(current, colour);

            for (Cell uncolored : uncoloredNeighbors) {
                if (!toColour.contains(uncolored)) {
                    toColour.addLast(uncolored);
                }
            }
        }

    }

    private List<Cell> uncoloredNeighbors(Cell current, char colour) {

        List<Cell> uncoloredNeighbors = new ArrayList<>();

        int x = current.getX();
        int y = current.getY();

        for (int xOffset : new int[]{-1, 0, 1}) {
            for (int yOffset : new int[]{-1, 0, 1}) {
                if (xOffset != 0 || yOffset != 0) {
                    if (isWithinCanvas(x + xOffset, y + yOffset) && !isColoured(x + xOffset, y + yOffset, colour)) {
                        uncoloredNeighbors.add(Cell.from(x + xOffset, y + yOffset));
                    }
                }
            }
        }

        return uncoloredNeighbors;

    }

    private boolean isWithinCanvas(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    private boolean isColoured(int x, int y, char colour) {
        return canvas[y][x] == colour || canvas[y][x] != ' ';
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

    private static class Cell {

        private final int x;
        private final int y;

        public static Cell from(int x, int y) {
            return new Cell(x, y);
        }

        private Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (!(o instanceof Cell)) {
                return false;
            }

            Cell cell = (Cell) o;

            if (getX() != cell.getX()) {
                return false;
            }

            return getY() == cell.getY();
        }

        @Override
        public int hashCode() {
            int result = getX();
            result = 31 * result + getY();
            return result;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
