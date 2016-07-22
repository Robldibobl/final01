package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Player {
    private Token token;
    private Colour colour;

    /**
     * Constructor of the class Player.
     */
    public Player() {
    }

    public Player(Token token) {
        this.token = token;
    }

    /**
     * Getter for player colours
     * @return Returns the colour of a player
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Setter for player colours
     * @param colour Colour of a player
     */
    public void setColour(Colour colour) {
        this.colour = colour;
    }
}
