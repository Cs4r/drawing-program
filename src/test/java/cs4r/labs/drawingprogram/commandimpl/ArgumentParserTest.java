package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.exception.InvalidArgumentException;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link ArgumentParser}.
 */
public class ArgumentParserTest {

    @Test
    public void getIntArgumentReturnsArgumentValueWhenValidType() throws Exception {
        ArgumentParser argumentParser = new ArgumentParser();
        Integer firstArgument, secondArgument, thirdArgument, fourthArgument;

        // First input
        String firstInput = "20 4";
        firstArgument = argumentParser.getIntArgument(firstInput, 0);
        assertThat(firstArgument).isEqualTo(20);
        secondArgument = argumentParser.getIntArgument(firstInput, 1);
        assertThat(secondArgument).isEqualTo(4);


        // Second input
        String secondInput = "6 3 6 4";
        firstArgument = argumentParser.getIntArgument(secondInput, 0);
        assertThat(firstArgument).isEqualTo(6);

        secondArgument = argumentParser.getIntArgument(secondInput, 1);
        assertThat(secondArgument).isEqualTo(3);

        thirdArgument = argumentParser.getIntArgument(secondInput, 2);
        assertThat(thirdArgument).isEqualTo(6);

        fourthArgument = argumentParser.getIntArgument(secondInput, 3);
        assertThat(fourthArgument).isEqualTo(4);


        // Third input
        String thirdInput = "           16 1 20 3         ";
        firstArgument = argumentParser.getIntArgument(thirdInput, 0);
        assertThat(firstArgument).isEqualTo(16);

        secondArgument = argumentParser.getIntArgument(thirdInput, 1);
        assertThat(secondArgument).isEqualTo(1);

        thirdArgument = argumentParser.getIntArgument(thirdInput, 2);
        assertThat(thirdArgument).isEqualTo(20);

        fourthArgument = argumentParser.getIntArgument(thirdInput, 3);
        assertThat(fourthArgument).isEqualTo(3);
    }

    @Test
    public void getIntArgumentThrowsInvalidArgumentExceptionWhenInvalidType() throws Exception {

        ArgumentParser argumentParser = new ArgumentParser();

        assertThatThrownBy(() -> argumentParser.getIntArgument("20 c", 1))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("argument at position 1 has an unexpected type");

        assertThatThrownBy(() -> argumentParser.getIntArgument("c 23", 0))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("argument at position 0 has an unexpected type");

        assertThatThrownBy(() -> argumentParser.getIntArgument("20 1 hola", 2))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("argument at position 2 has an unexpected type");
    }

    @Test
    public void getIntArgumentThrowsInvalidArgumentExceptionWhenArgumentNotFound() throws Exception {

        ArgumentParser argumentParser = new ArgumentParser();

        assertThatThrownBy(() -> argumentParser.getIntArgument("12 24", 3))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("no argument at position 3");

        assertThatThrownBy(() -> argumentParser.getIntArgument("26 300 0 0", -1))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("no argument at position -1");
    }

    @Test
    public void getIntArgumentRequiresNonNullRawArguments() throws Exception {

        assertThatThrownBy(() -> new ArgumentParser().getIntArgument(null, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("rawArguments cannot be null");
    }

    @Test
    public void getCharArgumentReturnsArgumentValueWhenValidType() throws Exception {

        ArgumentParser argumentParser = new ArgumentParser();

        Character thirdArgument = argumentParser.getCharArgument("20 40 c", 2);
        assertThat(thirdArgument).isEqualTo('c');


        Character secondArgument = argumentParser.getCharArgument("20 b", 1);
        assertThat(secondArgument).isEqualTo('b');

        Character firstArgument = argumentParser.getCharArgument("a", 0);
        assertThat(firstArgument).isEqualTo('a');
    }

    @Test
    public void getCharArgumentThrowsInvalidArgumentExceptionWhenInvalidType() {

        ArgumentParser argumentParser = new ArgumentParser();

        assertThatThrownBy(() -> argumentParser.getCharArgument("argument", 0))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("argument at position 0 has an unexpected type");

        assertThatThrownBy(() -> argumentParser.getCharArgument("23 b c2", 2))
                .isInstanceOf(InvalidArgumentException.class)
                .hasMessage("argument at position 2 has an unexpected type");
    }
}
