package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Token {
    private Colour colour;
    private boolean barrier;

    /**
     * Constructor of the Token class.
     */
    public Token() {
        this.colour = Colour.EMPTY;
        barrier = false;
    }

    /**
     * Getter for a token's colour.
     *
     * @return Returns token's colour
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Setter for a token's colour.
     *
     * @param colour Token colour
     */
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    /**
     * Getter for the boolean barrier.
     *
     * @return Returns barrier
     */
    public boolean isBarrier() {
        return barrier;
    }

    /**
     * Setter for the boolean barrier.
     *
     * @param barrier Boolean that describes whether a barrier is intact
     */
    public void setBarrier(boolean barrier) {
        this.barrier = barrier;
    }
}
