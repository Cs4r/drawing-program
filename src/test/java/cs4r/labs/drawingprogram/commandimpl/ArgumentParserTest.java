package cs4r.labs.drawingprogram.commandimpl;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

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

}
