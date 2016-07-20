package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Game {
    private Field field;
    private Player currentPlayer;

    public Game() {
        field = new Field();
    }

    /*
    optionale Spielregeln
     */

    private void turn() {
        if (currentPlayer.getColour().equals(Colour.RED)) {
            currentPlayer.setColour(Colour.BLUE);
        } else if (currentPlayer.getColour().equals(Colour.BLUE)) {
            currentPlayer.setColour(Colour.GREEN);
        } else if (currentPlayer.getColour().equals(Colour.GREEN)) {
            currentPlayer.setColour(Colour.YELLOW);
        } else if (currentPlayer.getColour().equals(Colour.YELLOW)) {
            currentPlayer.setColour(Colour.RED);
        }
    }

    public void start(String[] param) {
        //Check.checkAmount(param, x);
        //Check.checkOptionals();

        /*
        falls x = 0, setStart();
         */

    }

    public void roll(String[] param) throws InputException {
        Check.checkAmount(param, 1);
        Check.checkRoll(Integer.parseInt(param[0]));
    }

    public void rollX(String[] param) throws InputException {
        Check.checkAmount(param, 0);

        //return roll(random);
    }

    public void move(String[] param) throws InputException {
        Check.checkAmount(param, 2);
        Check.checkInteger(Integer.parseInt(param[0]));
        Check.checkInteger(Integer.parseInt(param[1]));

        //in Check nur Spielfeld einbezogen, Zielfelder wie lösen?

    }

    /**
     * Prints out the current state of the game and the colour of next turn's player.
     *
     * @param param String array with parameters
     * @return Returns the current state of the game
     * @throws InputException For input format type errors
     */
    public String print(String[] param) throws InputException {
        Check.checkAmount(param, 0);
        String output = new String();

        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                output += "" + field.getRed();
            }
            if (i == 1) {
                output += "" + field.getBlue();
            }
            if (i == 2) {
                output += "" + field.getGreen();
            }
            if (i == 3) {
                output += "" + field.getYellow();
            }
        }

        output = output + "\n" + currentPlayer.getColour().toString().toLowerCase();
        return output;
    }
}
