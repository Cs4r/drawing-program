package cs4r.labs.drawingprogram.command;


import cs4r.labs.drawingprogram.util.Checks;

import java.util.Objects;

/**
 * Represents a console command.
 */
public class Command {

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
        Checks.failIfNullArgument(name, "name");
        Checks.failIfNullArgument(arguments, "arguments");
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Command)) {
            return false;
        }

        Command command = (Command) o;

        return Objects.equals(getArguments(), command.getArguments()) &&
                Objects.equals(getName(), command.getName());
    }

    @Override
    public String toString() {
        return "Command{" +
                "arguments='" + arguments + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getArguments(), getName());
    }

}
