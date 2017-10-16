package cs4r.labs.drawingprogram.command;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests to state the little contract of {@link Command}
 */
public class CommandTest {

    @Test
    public void cannotBeConstructedWithNullArguments() throws Exception {

        assertThatThrownBy(() -> Command.with("name", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("arguments cannot be null");

        assertThatThrownBy(() -> Command.with(null, "argument1 argument2 argument3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("name cannot be null");
    }
}