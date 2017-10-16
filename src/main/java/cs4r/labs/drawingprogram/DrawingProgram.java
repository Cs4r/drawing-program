package cs4r.labs.drawingprogram;

import java.io.InputStream;
import java.io.OutputStream;

/*
 * The drawing program. Main entry point of this piece of software.
 */
public class DrawingProgram {


    private DrawingContext context;

    public DrawingProgram(InputStream input, OutputStream output) {
        context = new DrawingContext(input, output);
    }

    public void run() {

    }

    public DrawingContext getContext() {
        return context;
    }
}
