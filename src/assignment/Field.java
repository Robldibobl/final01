package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Field {
    private static int currentRoll;
    private Token[] startRed;
    private Token[] startBlue;
    private Token[] startGreen;
    private Token[] startYellow;
    private Token[] board;
    private Token[] destRed;
    private Token[] destBlue;
    private Token[] destGreen;
    private Token[] destYellow;
    private Token token;
    private Player player;

    /**
     * Constructor of the class Field.
     */
    public Field() {
        token = new Token();
        startRed = new Token[4];
        startBlue = new Token[4];
        startGreen = new Token[4];
        startYellow = new Token[4];
        board = new Token[4];
        destRed = new Token[4];
        destBlue = new Token[4];
        destGreen = new Token[4];
        destYellow = new Token[4];
        fillBoard();
    }

    private void fillBoard() {
        for (int i = 0; i < 4; i++) {
            System.out.println(startRed[i].getColour());
            startRed[i].setColour(Colour.EMPTY);
            startBlue[i].setColour(Colour.EMPTY);
            startGreen[i].setColour(Colour.EMPTY);
            startYellow[i].setColour(Colour.EMPTY);
        }
    }

    /**
     * Sets the basic positions at game start.
     */
    public void setStart() {
        for (int i = 0; i < 4; i++) {
            startRed[i].setColour(Colour.RED);
            startBlue[i].setColour(Colour.BLUE);
            startGreen[i].setColour(Colour.GREEN);
            startYellow[i].setColour(Colour.YELLOW);
            destRed[i].setColour(Colour.EMPTY);
            destBlue[i].setColour(Colour.EMPTY);
            destGreen[i].setColour(Colour.EMPTY);
            destYellow[i].setColour(Colour.EMPTY);
        }
        for (int i = 0; i < 40; i++) {
            board[i].setColour(Colour.EMPTY);
        }
    }

    public void moveToken() {
        /*
        Überprüfung der ausführbaren Züge
         */

        int i = 0;

        if (board[i + currentRoll].getColour().equals(Colour.EMPTY)) {
            board[i + currentRoll].setColour(board[i].getColour());
            board[i].setColour(Colour.EMPTY);
        }
    }

    /**
     * Collects all player tokens' positions and returns them.
     * @return Returns all tokens' positions in the game
     */
    public String[] getPrint() {
        String[] output = new String[4];
        char[] temp1;
        char[] temp2;
        char[] temp3;
        char[] temp4;

        for (int i = 0; i < 4; i++) {
            if (startRed[i].equals(Colour.RED)) {
                output[0] += "" + "SR" + ",";
            }
            if (startBlue.equals(Colour.BLUE)) {
                output[1] += "" + "SB" + ",";
            }
            if (startGreen[i].equals(Colour.GREEN)) {
                output[0] += "" + "SG" + ",";
            }
            if (startYellow.equals(Colour.YELLOW)) {
                output[1] += "" + "SY" + ",";
            }
        }

        for (int i = 0; i < 40; i++) {
            if (board[i].equals(Colour.RED)) {
                output[0] += "" + i + ",";
            }
            if (board[i].equals(Colour.BLUE)) {
                output[0] += "" + i + ",";
            }
            if (board[i].equals(Colour.GREEN)) {
                output[0] += "" + i + ",";
            }
            if (board[i].equals(Colour.YELLOW)) {
                output[0] += "" + i + ",";
            }
        }

        for (int i = 0; i < 4; i++) {
            if (destRed[i].equals(Colour.RED)) {
                temp1 = Character.toChars(65 + i);

                output[0] += "" + temp1 + "R" + ",";
            }
            if (destBlue[i].equals(Colour.BLUE)) {
                temp2 = Character.toChars(65 + i);

                output[1] += "" + temp2 + "B" + ",";
            }
            if (destGreen[i].equals(Colour.GREEN)) {
                temp3 = Character.toChars(65 + i);

                output[2] += "" + temp3 + "G" + ",";
            }
            if (destYellow[i].equals(Colour.YELLOW)) {
                temp4 = Character.toChars(65 + i);

                output[3] += "" + temp4 + "Y" + ",";
            }
        }

        output[0] = output[0].substring(0, output[0].lastIndexOf(","));
        output[1] = output[1].substring(0, output[1].lastIndexOf(","));
        output[2] = output[2].substring(0, output[2].lastIndexOf(","));
        output[3] = output[3].substring(0, output[3].lastIndexOf(","));

        return output;
    }
}