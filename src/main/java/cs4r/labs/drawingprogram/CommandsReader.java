package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.InvalidCommandException;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads commands from a given {@link DrawingContext}
 */
public class CommandsReader {

    // Protected for testing purposes
    protected static Pattern COMMAND_REGEX = Pattern.compile("\\s*(\\p{Alnum}+)\\s*(((\\p{Alnum}+)\\s*)*)\\s*");
    private final Scanner scanner;

    /**
     * Constructs a {@link CommandsReader}
     *
     * @param context a {@link DrawingContext}
     */
    public CommandsReader(DrawingContext context) {
        failIfNullContext(context);
        scanner = new Scanner(context.getInput());
    }

    /**
     * Reads the next command in the input
     *
     * @return a {@link Command}
     */
    public Command nextCommand() {

        try {
            String line = scanner.nextLine();
            Matcher matcher = COMMAND_REGEX.matcher(line);

            if (matcher.matches()) {
                String name = matcher.group(1);
                String arguments = matcher.group(2);
                return Command.with(name, arguments);
            }

        } catch (RuntimeException exception) {
            // If any exception is thrown, we will rethrow an InvalidCommandException
        }

        throw new InvalidCommandException("Invalid command");
    }


    private void failIfNullContext(DrawingContext context) {
        if (Objects.isNull(context)) {
            throw new IllegalArgumentException("context cannot be null");
        }
    }
}
