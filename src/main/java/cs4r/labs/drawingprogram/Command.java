package cs4r.labs.drawingprogram;


import java.util.Objects;

/**
 * Represents a console command
 */
public class Command {

    public static final Command PRINT_PROMPT_COMMAND = with("#PP#", "");
    public static final Command PRINT_CANVAS_COMMAND = with("#PC#", "");

    private final String arguments;
    private final String name;

    /**
     * Creates a console command.
     *
     * @param name      the name of the command. Must be non-null.
     * @param arguments the arguments of the command. Must be non-null.
     * @return a {@link Command} holding the supplied details.
     */
    public static Command with(String name, String arguments) {
        failIfNameIsNull(name);
        failIfArgumentsIsNull(arguments);
        return new Command(arguments, name);
    }

    private Command(String arguments, String name) {
        this.arguments = arguments;
        this.name = name;
    }

    /**
     * Returns the name of this command.
     *
     * @return a non null string representing the name of this command.
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the arguments of this command.
     *
     * @return a non null string representing the arguments of this command.
     */
    public String getArguments() {
        return arguments;
    }


    private static void failIfNameIsNull(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException("name cannot be null");
        }
    }

    private static void failIfArgumentsIsNull(String arguments) {
        if (Objects.isNull(arguments)) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
    }
}
