package cs4r.labs.drawingprogram;

/**
 * A class to state the supported commands.
 */
public final class SupportedCommands {
    public static final Command PRINT_CANVAS_COMMAND = Command.with("#PC#", "");
    public static final Command PRINT_PROMPT_COMMAND = Command.with("#PP#", "");

    public static final String CREATE_CANVAS_COMMAND = "C";
    public static final String DRAW_LINE_COMMAND = "L";
    public static final String DRAW_RECTANGLE_COMMAND = "R";
    public static final String FILL_AREA_COMMAND = "B";
    public static final String QUIT_COMMAND = "Q";
}
