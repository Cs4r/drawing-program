package cs4r.labs.drawingprogram;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Default implementation of {@link CommandsReader}
 */
public class DefaultCommandsReader implements CommandsReader {

    private static Pattern COMMAND_REGEX = Pattern.compile("\\s*(\\p{Alnum}+)\\s+(((\\p{Alnum}+)\\s*)+)\\s*");

    @Override
    public Command nextCommand(DrawingContext drawingContext) {

        Scanner scanner = new Scanner(drawingContext.getInput());

        String line = scanner.nextLine();

        Matcher matcher = COMMAND_REGEX.matcher(line);

        matcher.matches();

        String name = matcher.group(1);

        String arguments = matcher.group(2);

        return Command.with(name, arguments);
    }
}
