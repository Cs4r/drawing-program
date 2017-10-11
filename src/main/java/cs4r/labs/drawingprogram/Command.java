package cs4r.labs.drawingprogram;


/**
 * Represents a console command
 */
public class Command {

    public static final Command PRINT_PROMPT_COMMAND = with("#PP#", "");

    private final String arguments;
    private final String name;

    public static Command with(String name, String arguments) {
        return new Command(arguments, name);
    }

    private Command(String arguments, String name) {
        this.arguments = arguments;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getArguments() {
        return arguments;
    }
}
