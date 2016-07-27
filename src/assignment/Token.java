package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Token {
    private Colour colour;

    /**
     * Constructor of the Token class.
     */
    public Token() {
        this.colour = Colour.EMPTY;
    }

    /**
     * Getter for a token's colour.
     * @return Returns token's colour
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Setter for a token's colour.
     * @param colour Token colour
     */
    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
