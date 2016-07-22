package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Game {
    private static int currentRoll;
    private Field field;
    private Player currentPlayer;
    private boolean backward = false;
    private boolean barrier = false;
    private boolean nojump = false;
    private boolean triply = false;

    /**
     * Constructor of the class Game.
     */
    public Game() {
        field = new Field();
        currentPlayer = new Player();
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

    public String start(String[] optionals) throws InputException {
        String[] positions = new String[4];
        currentPlayer.setColour(Colour.RED);
        field.setStart();

        if (optionals.length > 1) {
            for (int i = 0; i < optionals.length; i++) {
                if (optionals[i].equals(Gamemodes.BACKWARD)) {
                    backward = true;
                }
                if (optionals[i].equals(Gamemodes.BARRIER)) {
                    barrier = true;
                }
                if (optionals[i].equals(Gamemodes.NOJUMP)) {
                    nojump = true;
                }
                if (optionals[i].equals(Gamemodes.TRIPLY)) {
                    triply = true;
                }
                if (optionals[i].matches(",") && !optionals[i].equals(optionals[optionals.length - 1])) {
                    throw new InputException("Error, please comply by the input format! If you wish to use "
                            + "optional rules or positions, please use this input format: <rules> <positions>");
                }
                if (optionals[optionals.length - 1].matches(",")) {
                    positions = optionals[optionals.length - 1].split(";");
                }
            }
        }

        String[] posRed = positions[0].split(",");
        String[] posBlue = positions[1].split(",");
        String[] posGreen = positions[2].split(",");
        String[] posYellow = positions[3].split(",");

        //Überprüfung, ob der kack String valid ist

        for (int i = 0; i < 4; i++) {
            if (posRed[i].equals("SR")) {
                for (int j = 0; j < 4; j++) {
                    if (startRed[j].getColour().equals(Colour.EMPTY)) {

                    }
                }
            }
            for (int k = 0; k < 40; k++) {
                if (Integer.parseInt(posRed[i]) == k) {
                    board[k].setColour(Colour.RED);
                }
            }
            if (posRed[i].equals("AR") || posRed[i].equals("BR") || posRed[i].equals("CR") || posRed[i].equals("DR")) {

            }

            /*
            und so weiter für alle drei anderen Farben.
             */

        }

        if (field[i + currentRoll].getColour().equals(Colour.EMPTY)) {
            field[i + currentRoll].setColour(field[i].getColour());
            field[i].setColour(Colour.EMPTY);
        }


        //Check.checkAmount(param, x);
        //Check.checkOptionals();

        /*
        falls x = 0, setStart();
         */

        return "OK";
    }

    public String roll(String[] param) throws InputException {
        Check.checkAmount(param, 1);
        Check.checkRoll(Integer.parseInt(param[0]));

        currentRoll = Integer.parseInt(param[0]);

        /*
        Check alle möglichen Spielzüge; wenn 0, dann turn(); + return currentPlayer;
        sonst alle möglichen Züge ausgeben
         */
        return "";
    }

    /**
     * Rolls a random number between 1 and 6 and prints out all possible turns.
     *
     * @param param String array with parameters
     * @return Returns the roled number
     * @throws InputException For input format type errors
     */
    public String rollX(String[] param) throws InputException {
        Check.checkAmount(param, 0);
        String[] result = new String[1];

        currentRoll = 1 + (int) (Math.random() * ((6 - 1) + 1));

        result[0] = "" + currentRoll;

        return roll(result);
    }

    public String move(String[] param) throws InputException {
        Check.checkAmount(param, 2);
        Check.checkInteger(Integer.parseInt(param[0]));
        Check.checkInteger(Integer.parseInt(param[1]));

        if (currentRoll != 6) {
            turn();
        }

        //in Check nur Spielfeld einbezogen, Zielfelder wie lösen?

        return currentPlayer.getColour().toString().toLowerCase();
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

        return field.getPrint() + "\n" + currentPlayer.getColour().toString().toLowerCase();
    }
}
