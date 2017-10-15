package cs4r.labs.drawingprogram;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface DrawingContext {

    /**
     * @return true if the context is active, false otherwise.
     */
    boolean isActive();

    InputStream getInput();

    OutputStream getOutput();

    Optional<Canvas> getCanvas();

    /**
     * Deactivates the context.
     * <p>
     * Once the context is deactivated {@link DrawingContext#isActive()} will always return false.
     */
    void deactivate();

    /**
     * Sets the canvas held by this context.
     */
    void setCanvas(Canvas canvas);
}
