package cs4r.labs.drawingprogram;


import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;

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

    Optional<Canvas> getOptionalCanvas();

    /**
     * Returns the canvas held by this context.
     *
     * @return Canvas held by this context
     * @throw {@link cs4r.labs.drawingprogram.exception.CanvasNotFoundException}
     * if there is no canvas set in this context.
     */
    default Canvas getCanvas() {
        return getOptionalCanvas().orElseThrow(CanvasNotFoundException::new);
    }

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
