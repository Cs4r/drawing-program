package cs4r.labs.drawingprogram;


import java.io.InputStream;

public interface DrawingContext {

    boolean isActive();

    InputStream getInput();
}
