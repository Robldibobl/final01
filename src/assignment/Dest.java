package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Dest {
    private Colour colour;
    private Token[] destination;

    private void fillDestination() {
        for (int i = 0; i < 4; i++) {
            destination[i] = new Token();
        }
    }

    public Dest(Colour colour) {
        this.colour = colour;
        destination = new Token[4];
        fillDestination();
    }

    public Colour getColour() {
        return colour;
    }

    public Token[] getDestination() {
        return destination;
    }

    public Token getDestinationIndex(Token[] destination, int i) {
        return destination[i];
    }

    public void setDestinationColour(Token[] destination, int i, Colour colour) {
        destination[i].setColour(colour);
    }
}
