package cs4r.labs.drawingprogram;


import java.io.InputStream;
import java.io.OutputStream;

public interface DrawingContext {

    boolean isActive();

    InputStream getInput();

    OutputStream getOutput();
}
