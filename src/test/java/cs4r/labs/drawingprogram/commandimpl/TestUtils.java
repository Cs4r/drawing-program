package cs4r.labs.drawingprogram.commandimpl;

import cs4r.labs.drawingprogram.DrawingContext;

import java.io.ByteArrayOutputStream;


/**
 * Common methods used in tests.
 */
public class TestUtils {

    public static String getOutputAsString(DrawingContext context) {
        ByteArrayOutputStream output = (ByteArrayOutputStream) context.getOutput();
        return new String(output.toByteArray());
    }

    public static String convertToString(ByteArrayOutputStream output) {
        return new String(output.toByteArray());
    }
}