package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Start {
    private Colour colour;
    private int start;
    private int boardStart;

    /**
     * Constructor of the class Start.
     *
     * @param colour Colour of a starting area
     */
    public Start(Colour colour) {
        this.colour = colour;
    }

    /**
     * Getter for a starting area's colour.
     *
     * @return Returns the starting area's colour
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Getter for the number of tokens in a starting area.
     *
     * @return Returns an integer
     */
    public int getStart() {
        return start;
    }

    /**
     * Setter for the number of tokens in a starting area.
     *
     * @param start Number of tokens in a starting area
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Getter for the starting position on the board.
     *
     * @return Returns the starting position
     */
    public int getBoardStart() {
        return boardStart;
    }

    /**
     * Setter for the starting position on the board.
     *
     * @param boardStart Starting position on the board
     */
    public void setBoardStart(int boardStart) {
        this.boardStart = boardStart;
    }
}