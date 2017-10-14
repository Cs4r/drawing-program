package cs4r.labs.drawingprogram;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface DrawingContext {

    boolean isActive();

    InputStream getInput();

    OutputStream getOutput();

    Optional<Canvas> getCanvas();
}
