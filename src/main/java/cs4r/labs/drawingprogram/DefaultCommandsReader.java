package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.exception.InvalidCommandException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of {@link CommandsReader}
 */
public class DefaultCommandsReader implements CommandsReader {

    // Protected for testing purposes
    protected static Pattern COMMAND_REGEX = Pattern.compile("\\s*(\\p{Alnum}+)\\s*(((\\p{Alnum}+)\\s*)*)\\s*");
    private final Scanner scanner;

    public DefaultCommandsReader(DrawingContext context) {
        scanner = new Scanner(context.getInput());
    }

    @Override
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
}
