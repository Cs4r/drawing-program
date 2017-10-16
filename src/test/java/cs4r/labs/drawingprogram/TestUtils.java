package cs4r.labs.drawingprogram;

import cs4r.labs.drawingprogram.command.DrawingContext;
import cs4r.labs.drawingprogram.command.commandimpl.ArgumentParser;
import cs4r.labs.drawingprogram.command.exception.CanvasNotFoundException;
import org.mockito.Mockito;

import java.io.*;

import static java.util.stream.Collectors.joining;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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


    public static DrawingContext activeContextWithCanvas(Canvas canvas) {
        DrawingContext context = Mockito.mock(DrawingContext.class);

        when(context.isActive()).thenReturn(true);
        when(context.getCanvas()).thenReturn(canvas);

        return context;
    }

    public static ArgumentParser validIntArguments(String arguments, int... args) {
        ArgumentParser argumentParser = mock(ArgumentParser.class);

        int pos = 0;
        for (int arg : args) {
            when(argumentParser.getIntArgument(arguments, pos)).thenReturn(arg);
            ++pos;
        }

        return argumentParser;
    }

    public static DrawingContext inactiveContext() {
        DrawingContext context = Mockito.mock(DrawingContext.class);
        when(context.isActive()).thenReturn(false);
        return context;
    }


    public static DrawingContext activeContextWithoutCanvas(Canvas canvas) {
        DrawingContext context = activeContext();
        when(context.getCanvas()).thenThrow(new CanvasNotFoundException());
        return context;
    }

    public static DrawingContext activeContext() {
        DrawingContext context = Mockito.mock(DrawingContext.class);
        when(context.isActive()).thenReturn(true);
        return context;
    }


    public static DrawingContext activeContextWithOutput(OutputStream outputStream) {
        DrawingContext drawingContext = activeContext();
        when(drawingContext.getOutput()).thenReturn(outputStream);

        return drawingContext;
    }

    public static DrawingContext inactiveContextWithOutput(OutputStream output) {
        DrawingContext drawingContext = inactiveContext();
        when(drawingContext.getOutput()).thenReturn(output);
        return drawingContext;
    }

    public static DrawingContext activeContextWithBrokenOutput(Canvas canvas) throws IOException {
        DrawingContext context = TestUtils.activeContext();
        OutputStream brokenOutput = mock(OutputStream.class);
        doThrow(IOException.class).when(brokenOutput).write(any());
        return activeContextWith(canvas, brokenOutput);
    }

    public static DrawingContext activeContextWith(Canvas canvas, OutputStream output) {
        DrawingContext context = activeContextWithOutput(output);
        if (canvas == null) {
            when(context.getCanvas()).thenThrow(new CanvasNotFoundException());
        } else {
            when(context.getCanvas()).thenReturn(canvas);
        }
        return context;
    }


    public static InputStream getInputStreamFromFile(String filePath, Class<?> clazz) throws Exception {
        return clazz.getResourceAsStream(filePath);
    }

    public static String readStringFromFile(String filePath, Class<?> clazz) throws Exception {

        InputStream resourceAsStream = getInputStreamFromFile(filePath, clazz);

        String fileAsString = new BufferedReader(new InputStreamReader(resourceAsStream))
                .lines()
                .collect(joining("\n"));

        resourceAsStream.close();

        return fileAsString;
    }

}