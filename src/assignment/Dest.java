package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Dest {
    private Colour colour;
    private Token[] destination;

    /**
     * Constructor of the class Dest.
     *
     * @param colour Colour of a player
     */
    public Dest(Colour colour) {
        this.colour = colour;
        destination = new Token[4];
        fillDestination();
    }

    private void fillDestination() {
        for (int i = 0; i < 4; i++) {
            destination[i] = new Token();
        }
    }

    /**
     * Getter for the colour of a destination area.
     *
     * @return Returns colour
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Getter for a destination area.
     *
     * @return Returns destination
     */
    public Token[] getDestination() {
        return destination;
    }

    /**
     * Setter for the colour of an entry in a destination area.
     *
     * @param destination Destination area
     * @param i           Index i in a destination area
     * @param colour      Colour of the player
     */
    public void setDestinationColour(Token[] destination, int i, Colour colour) {
        destination[i].setColour(colour);
    }
}
