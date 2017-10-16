package cs4r.labs.drawingprogram;


import cs4r.labs.drawingprogram.exception.CanvasNotFoundException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

/**
 * Holds valuable information for the executing of {@link DrawingProgram}.
 */
public class DrawingContext {

    private boolean isActive = true;
    private InputStream input;
    private OutputStream output;
    private Canvas canvas;

    /**
     * Constructs a {@link DrawingContext}
     *
     * @param input  the input that the context is going to use.
     * @param output the output that the context is going to use.
     */
    public DrawingContext(InputStream input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    /**
     * @return true if the context is active, false otherwise.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * @return InputStream that represents the input used by this context.
     */
    public InputStream getInput() {
        return input;
    }

    /**
     * @return OutputStream that represents the output used by this context.
     */
    public OutputStream getOutput() {
        return output;
    }

    /**
     * Returns an optional that may contain the canvas held by this context.
     *
     * @return java.util.Optional containing the canvas held by this context (if there is any),
     * {@link Optional#empty()} otherwise
     */
    public Optional<Canvas> getOptionalCanvas() {
        return Optional.ofNullable(canvas);
    }

    /**
     * Returns the canvas held by this context.
     *
     * @return Canvas held by this context
     * @throw {@link cs4r.labs.drawingprogram.exception.CanvasNotFoundException}
     * if there is no canvas set in this context.
     */
    public Canvas getCanvas() {
        return getOptionalCanvas().orElseThrow(CanvasNotFoundException::new);
    }

    /**
     * Deactivates the context.
     * <p>
     * Once the context is deactivated {@link DrawingContext#isActive()} will always return false.
     */
    public void deactivate() {
        isActive = false;
    }

    /**
     * Sets the canvas held by this context.
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
