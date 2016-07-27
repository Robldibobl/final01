package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Field {
    private int currentRoll; // *********************
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
    private Token token;
    private String[] abcd;

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
        token = new Token();
        board = new Token[40];
        fillBoard();
        abcd = new String[4];
        fillAbcd();
    }

    private void fillStart() {
        startList.add(startRed);
        startList.add(startBlue);
        startList.add(startGreen);
        startList.add(startYellow);
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

    private void add(Token[] board, int i, Colour colour) throws RuleException {

        /*
        vorziehen -> leeres Feld, eigene Figur, andere Figur

        eigene: Zug nicht möglich

        leer: reinschreiben, vorheriges Feld löschen

        andere: überprüfen, welche Farbe Figur hat, Start eins hochzählen, löschen/überschreiben PLUS

        vorheriges Feld löschen
         */

        if (!board[i].getColour().equals(colour)) {
            if (board[i].getColour().equals(Colour.EMPTY)) {
                board[i].setColour(colour);
            } else {
                if (compareStart(board[i].getColour()).getStart() < 4) {
                    compareStart(board[i].getColour()).setStart(compareStart(board[i].getColour()).getStart() + 1);
                    board[i].setColour(colour);
                }
            }
        } else {
            throw new RuleException("Error, you are not allowed to take your own token!");
        }


    }

    private void remove(Token[] board, int i) {
        board[i].setColour(Colour.EMPTY);
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

    public String possibleMoves(int roll, Colour colour) {
        String output = new String();

        if (roll != 6 && startRed.getStart() == 4) {
            return output;
        } else {
            if (roll == 6 && startRed.getStart() > 0) {
                output += "" + "SR" + "-" + 0 + "\n";
            }


            for (int i = 0; i < 40; i++) {
                if (board[i].getColour().equals(colour)) {
                    if (!board[i + roll % 40].getColour().equals(colour)) {
                        int temp = i + roll % 40;
                        output += "" + i + "-" + temp + "\n";
                    }
                }
            }
        }
        /*
        Überprüfung der ausführbaren Züge
         */

        int i = 0;

        if (board[i + currentRoll].getColour().equals(Colour.EMPTY)) {
            board[i + currentRoll].setColour(board[i].getColour());
            board[i].setColour(Colour.EMPTY);
        }

        return output;
    }

    public String moveToken(Token[] board, int from, int to, Colour colour) throws RuleException {
        String output = new String();

        add(board, to, colour);
        if (from >= 0 && from < 40) {
            remove(board, from);
        } else {
            if (true) {

            } else {

            }
        }
        output = "" + to;
        return output;
    }

    public void setPositions(String[] positions) throws InputException {
        String[] pos = new String[4];
        char[] temp;

        startRed.setStart(0);
        startBlue.setStart(0);
        startGreen.setStart(0);
        startYellow.setStart(0);

        //try {
        for (int i = 0; i < 4; i++) {
            pos = positions[i].split(",");

            for (int j = 0; j < 4; j++) {

            }


            for (int j = 0; j < 4; j++) {

                if (pos[j].equals("SR")) {
                    startRed.setStart(startRed.getStart() + 1);
                } else if (pos[j].equals("AR")) {
                    red.setDestinationColour(red.getDestination(), 0, Colour.RED);
                } else if (pos[j].equals("BR")) {
                    red.setDestinationColour(red.getDestination(), 1, Colour.RED);
                } else if (pos[j].equals("CR")) {
                    red.setDestinationColour(red.getDestination(), 2, Colour.RED);
                } else if (pos[j].equals("DR")) {
                    red.setDestinationColour(red.getDestination(), 3, Colour.RED);
                } else {
                    if (Check.isInteger(Integer.parseInt(pos[j]))) {
                        if (Integer.parseInt(pos[j]) >= 0 || Integer.parseInt(pos[j]) < 40) {
                            board[Integer.parseInt(pos[j])].setColour(Colour.RED);
                        }
                    }
                }

            }
        }
        /*
        temp = Character.toChars(65 + k);
        if (pos[j].equals(temp + "R")) { // Exception hier
            red[k].setColour(Colour.RED);
        }
        */

        //} catch (NumberFormatException e) {
        //    throw new InputException("Error, wrong input format abc!");
        //}
    }


    /**
     * Collects all player tokens' positions and returns them.
     *
     * @return Returns all tokens' positions in the game
     */
    public String getPrint() {
        String[] output = new String[4];
        String result = new String();
        char[] temp;

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
                output[0] += "" + i + ",";
            }
            if (board[i].getColour().equals(Colour.BLUE)) {
                output[1] += "" + i + ",";
            }
            if (board[i].getColour().equals(Colour.GREEN)) {
                output[2] += "" + i + ",";
            }
            if (board[i].getColour().equals(Colour.YELLOW)) {
                output[3] += "" + i + ",";
            }
        }

        for (int i = 0; i < 4; i++) {
            if (red.getDestinationIndex(red.getDestination(), i).getColour().equals(Colour.RED)) {

                output[0] += "" + abcd[i] + "R" + ",";
            }
            if (blue.getDestinationIndex(blue.getDestination(), i).getColour().equals(Colour.BLUE)) {

                output[1] += "" + abcd[i] + "B" + ",";
            }
            if (green.getDestinationIndex(green.getDestination(), i).getColour().equals(Colour.GREEN)) {

                output[2] += "" + abcd[i] + "G" + ",";
            }
            if (yellow.getDestinationIndex(yellow.getDestination(), i).getColour().equals(Colour.YELLOW)) {

                output[3] += "" + abcd[i] + "Y" + ",";
            }
        }

        System.out.println(output[0]);
        System.out.println(output[1]);
        System.out.println(output[2]);
        System.out.println(output[3]);
        output[0] = output[0].substring(0, output[0].lastIndexOf(","));
        output[1] = output[1].substring(0, output[1].lastIndexOf(",")); // StringIndexOutOfBounds ..?????
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

    public List<Start> getStartList() {
        return startList;
    }

    /**
     * Getter for the red starting area.
     *
     * @return Returns red starting area
     */
    public Start getStartRed() {
        return startRed;
    }

    /**
     * Getter for the blue starting area.
     *
     * @return Returns blue starting area
     */
    public Start getStartBlue() {
        return startBlue;
    }

    /**
     * Getter for the green starting area.
     *
     * @return Returns green starting area
     */
    public Start getStartGreen() {
        return startGreen;
    }

    /**
     * Getter for the yellow starting area.
     *
     * @return Returns yellow starting area
     */
    public Start getStartYellow() {
        return startYellow;
    }
}