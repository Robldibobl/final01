package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Field {
    private List<Start> startList;
    private List<Dest> destList;
    private Start startRed;
    private Start startBlue;
    private Start startGreen;
    private Start startYellow;
    private Token[] board;
    private Dest red;
    private Dest blue;
    private Dest green;
    private Dest yellow;
    private String[] abcd;
    private String[] colours;
    private boolean winner;

    /**
     * Constructor of the class Field.
     */
    public Field() {
        startRed = new Start(Colour.RED);
        startBlue = new Start(Colour.BLUE);
        startGreen = new Start(Colour.GREEN);
        startYellow = new Start(Colour.YELLOW);
        startList = new ArrayList<>();
        fillStart();
        red = new Dest(Colour.RED);
        blue = new Dest(Colour.BLUE);
        green = new Dest(Colour.GREEN);
        yellow = new Dest(Colour.YELLOW);
        destList = new ArrayList<>();
        fillDest();
        board = new Token[40];
        fillBoard();
        abcd = new String[4];
        fillAbcd();
        colours = new String[4];
        fillColours();
    }

    private void fillStart() {
        startList.add(startRed);
        startList.add(startBlue);
        startList.add(startGreen);
        startList.add(startYellow);

        for (int i = 0; i < 4; i++) {
            startList.get(i).setBoardStart(i * 10);
        }
    }

    private void fillDest() {
        destList.add(red);
        destList.add(blue);
        destList.add(green);
        destList.add(yellow);
    }

    private void fillBoard() {
        for (int i = 0; i < 40; i++) {
            board[i] = new Token();
        }
    }

    private void fillAbcd() {
        abcd[0] = "A";
        abcd[1] = "B";
        abcd[2] = "C";
        abcd[3] = "D";
    }

    private void fillColours() {
        colours[0] = "R";
        colours[1] = "B";
        colours[2] = "G";
        colours[3] = "Y";
    }

    /**
     * Compares the given colour with all starting areas and returns the matching one.
     *
     * @param colour Colour of a starting area
     * @return Returns the matching starting area
     */
    public Start compareStart(Colour colour) {
        for (int i = 0; i < 4; i++) {
            if (startList.get(i).getColour().equals(colour)) {
                return startList.get(i);
            }
        }
        return null;
    }

    /**
     * Compares the given colour with all destination areas and returns the matching one.
     *
     * @param colour Colour of a destination area
     * @return Returns the matching destination area
     */
    public Dest compareDest(Colour colour) {
        for (int i = 0; i < 4; i++) {
            if (destList.get(i).getColour().equals(colour)) {
                return destList.get(i);
            }
        }
        return null;
    }

    /**
     * Method to move a token from a starting area onto the board.
     *
     * @param start  Starting area of a player
     * @param i      Staring point
     * @param j      Starting point input
     * @param colour Colour of the player
     * @throws RuleException For game rule violations
     */
    public void moveOut(Start start, int i, int j, Colour colour) throws RuleException {
        start.setStart(start.getStart() - 1);

        if (i != j) {
            throw new RuleException("Error, you are not allowed to move your token there!");
        }

        setToken(board, start.getBoardStart(), colour);
    }

    private void setToken(Token[] board, int i, Colour colour) throws RuleException {
        if (!board[i].getColour().equals(colour)) {
            placeToken(board, i, colour);
        }
    }

    /**
     * Method to move tokens on the board and check if a player has won.
     *
     * @param board   Board
     * @param i       Position of the token before being moved
     * @param param   String with second input
     * @param colour  Player colour
     * @param barrier Boolean barrier
     * @throws RuleException  For game rule violations
     * @throws InputException For input format type errors
     */
    public void move(Token[] board, int i, String param, Colour colour, boolean barrier)
            throws RuleException, InputException {
        setToken(board, i, param, colour, barrier);

        for (int k = 0; k < 4; k++) {
            if (compareDest(colour).getDestination()[k].getColour().equals(Colour.EMPTY)) {
                winner = false;
                return;
            }
        }
        winner = true;
    }

    private void setToken(Token[] board, int i, String param, Colour colour, boolean barrier)
            throws RuleException, InputException {

        if (Check.isInteger(param)) {
            int j = Integer.parseInt(param);
            if (!board[j].getColour().equals(Colour.EMPTY)) {
                if (!barrier) {
                    compareStart(board[j].getColour()).setStart(compareStart(board[j].getColour()).getStart() + 1);
                    board[j].setColour(colour);
                    board[i].setColour(Colour.EMPTY);
                } else {
                    if (board[j].getColour().equals(colour)) {
                        board[j].setBarrier(true);
                        board[i].setColour(Colour.EMPTY);
                    } else {
                        compareStart(board[j].getColour()).setStart(compareStart(board[j].getColour()).getStart() + 1);
                        board[j].setColour(colour);
                        board[i].setColour(Colour.EMPTY);
                    }
                }
            } else {
                board[j].setColour(colour);
                board[i].setColour(Colour.EMPTY);
            }
        } else {
            for (int k = 0; k < 4; k++) {
                if (("" + param.charAt(0)).equals(abcd[k])) {
                    compareDest(colour).getDestination()[k].setColour(colour);
                    board[i].setColour(Colour.EMPTY);
                }
            }
        }
    }

    private void placeToken(Token[] board, int i, Colour colour) throws RuleException {
        if (board[i].getColour().equals(Colour.EMPTY)) {
            board[i].setColour(colour);
        } else {
            if (compareStart(board[i].getColour()).getStart() < 4) {
                compareStart(board[i].getColour()).setStart(compareStart(board[i].getColour()).getStart() + 1);
                board[i].setColour(colour);
            } else {
                throw new RuleException("Error, something went wrong!");
            }
        }
    }

    /**
     * Moves tokens inside of destination areas.
     *
     * @param s      Starting position inside the destination area
     * @param roll   Rolled number
     * @param colour Current player's colour
     * @throws RuleException For game rule violations
     */
    public void moveDest(String s, int roll, Colour colour) throws RuleException {

        for (int k = 0; k < 4; k++) {
            if (abcd[k].equals(s)) {
                compareDest(colour).getDestination()[k + roll].setColour(colour);
                compareDest(colour).getDestination()[k].setColour(Colour.EMPTY);
                return;

            }
        }
    }

    /**
     * Sets the standard positions at game start.
     */
    public void setStart() {
        startRed.setStart(4);
        startBlue.setStart(4);
        startGreen.setStart(4);
        startYellow.setStart(4);

        for (int i = 0; i < 40; i++) {
            board[i].setColour(Colour.EMPTY);
        }
        for (int i = 0; i < 4; i++) {
            red.setDestinationColour(red.getDestination(), i, Colour.EMPTY);
            blue.setDestinationColour(blue.getDestination(), i, Colour.EMPTY);
            green.setDestinationColour(green.getDestination(), i, Colour.EMPTY);
            yellow.setDestinationColour(yellow.getDestination(), i, Colour.EMPTY);
        }
    }

    /**
     * Sets positions that are input with a start command.
     *
     * @param positions String array with parameters
     * @throws InputException For input format type errors
     */
    public void setPositions(String[] positions) throws InputException {
        String[] pos;

        startRed.setStart(0);
        startBlue.setStart(0);
        startGreen.setStart(0);
        startYellow.setStart(0);

        try {
            for (int i = 0; i < 4; i++) {
                pos = positions[i].split(",");
                char colour = colours[i].charAt(0);
                Check.checkOrder(pos, colour);

                for (int j = 0; j < 4; j++) {

                    if (pos[j].equals("S" + colours[i])) {
                        startList.get(i).setStart(startList.get(i).getStart() + 1);

                    } else if (pos[j].contains(colours[i])) {

                        for (int k = 0; k < 4; k++) {
                            if (pos[j].equals(abcd[k] + colours[i])) {
                                if (destList.get(i).getDestination()[k].getColour().equals(Colour.EMPTY)) {
                                    destList.get(i).setDestinationColour(destList.get(i).getDestination(), k, destList
                                            .get(i).getColour());
                                } else {
                                    throw new InputException("Error, invalid input! Please do not place multiple"
                                            + " tokens in the same place on the field!");
                                }
                            }
                        }
                    } else {

                        for (int l = 0; l < 4; l++) {
                            if (!pos[j].contains(abcd[l]) && !pos[j].contains(colours[l])) {
                                Check.checkInteger(pos[j]);

                                if (board[Integer.parseInt(pos[j])].getColour().equals(Colour.EMPTY)) {
                                    board[Integer.parseInt(pos[j])].setColour(startList.get(i).getColour());
                                    break;
                                } else {
                                    throw new InputException("Error, invalid input! Please do not place multiple"
                                            + " tokens in the same place on the field!");
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            throw new InputException("Error, wrong input format!");
        }
    }

    /**
     * Collects all player tokens' positions and returns them.
     *
     * @return Returns all tokens' positions in the game
     */
    public String getPrint() {
        String[] output = new String[4];
        String result = "";

        for (int i = 0; i < 4; i++) {
            output[i] = "";
        }

        for (int i = 0; i < 4; i++) {
            if (i < startRed.getStart()) {
                output[0] += "" + "SR" + ",";
            }
            if (i < startBlue.getStart()) {
                output[1] += "" + "SB" + ",";
            }
            if (i < startGreen.getStart()) {
                output[2] += "" + "SG" + ",";
            }
            if (i < startYellow.getStart()) {
                output[3] += "" + "SY" + ",";
            }
        }

        for (int i = 0; i < 40; i++) {
            if (board[i].getColour().equals(Colour.RED)) {
                if (board[i].isBarrier()) {
                    output[0] += i + ",";
                }
                output[0] += i + ",";
            }
            if (board[i].getColour().equals(Colour.BLUE)) {
                if (board[i].isBarrier()) {
                    output[1] += i + ",";
                }
                output[1] += i + ",";
            }
            if (board[i].getColour().equals(Colour.GREEN)) {
                if (board[i].isBarrier()) {
                    output[2] += i + ",";
                }
                output[2] += i + ",";
            }
            if (board[i].getColour().equals(Colour.YELLOW)) {
                if (board[i].isBarrier()) {
                    output[3] += i + ",";
                }
                output[3] += i + ",";
            }
        }

        for (int i = 0; i < 4; i++) {
            if (red.getDestination()[i].getColour().equals(Colour.RED)) {

                output[0] += "" + abcd[i] + "R" + ",";
            }
            if (blue.getDestination()[i].getColour().equals(Colour.BLUE)) {

                output[1] += "" + abcd[i] + "B" + ",";
            }
            if (green.getDestination()[i].getColour().equals(Colour.GREEN)) {

                output[2] += "" + abcd[i] + "G" + ",";
            }
            if (yellow.getDestination()[i].getColour().equals(Colour.YELLOW)) {

                output[3] += "" + abcd[i] + "Y" + ",";
            }
        }

        output[0] = output[0].substring(0, output[0].lastIndexOf(","));
        output[1] = output[1].substring(0, output[1].lastIndexOf(","));
        output[2] = output[2].substring(0, output[2].lastIndexOf(","));
        output[3] = output[3].substring(0, output[3].lastIndexOf(","));

        for (int i = 0; i < 4; i++) {
            result += "" + output[i] + "\n";
        }
        result = result.trim();
        return result;
    }

    /**
     * Getter for board.
     *
     * @return Returns board
     */
    public Token[] getBoard() {
        return board;
    }

    /**
     * Getter for abcd.
     *
     * @return Returns abcd
     */
    public String[] getAbcd() {
        return abcd;
    }

    /**
     * Getter for boolean winner.
     *
     * @return Returns winner
     */
    public boolean isWinner() {
        return winner;
    }
}