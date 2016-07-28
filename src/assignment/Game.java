package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Game {
    private int currentRoll;
    private int turnCounter = 0;
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

    /**
     * Starts a game and sets booleans for different game modes. Also sets positions of tokens, if input.
     *
     * @param optionals String array with parameters
     * @return Returns OK, if no exception thrown
     * @throws InputException For input format type errors
     */
    public String start(String[] optionals) throws InputException {
        String[] positions = new String[4];

        for (int i = 0; i < 4; i++) {
            positions[i] = "";
        }

        currentPlayer.setColour(Colour.RED);
        field.setStart();

        if (optionals.length > 1) {
            for (String optional : optionals) {
                if (optional.equals("BACKWARD")) {
                    backward = true;
                } else if (optional.equals("BARRIER")) {
                    barrier = true;
                } else if (optional.equals("NOJUMP")) {
                    nojump = true;
                } else if (optional.equals("TRIPLY")) {
                    triply = true;
                } else {
                    throw new InputException("Error, wrong input format!");
                }
            }
            for (int i = 0; i < optionals.length; i++) {
                if (optionals[i].contains(",")) {
                    if (!optionals[i].equals(optionals[optionals.length - 1])) {
                        throw new InputException("Error, please comply by the input format! If you wish to use "
                                + "optional rules or positions, please use this input format: <rules> <positions>");
                    } else {
                        positions = optionals[optionals.length - 1].split(";");

                        field.setPositions(positions);
                        break;
                    }
                }
            }
        }
        return "OK";
    }

    /*
    aus Startfeld aufs Spielfeld,

    nach vorn,

    (nach hinten; NUR bei schlagen [backwards = true]),

    (nach vorne/hinten(backwards) nicht überspringen [nojump = true])

    (nicht an Spielfigur vorbei ziehen [barrier = true])

    ins Zielfeld der entsprechenden Farbe
     */

    public String roll(String[] param) throws InputException {
        Check.checkAmount(param, 1);

        try {
            Integer.parseInt(param[0]);
        } catch (NumberFormatException e) {
            throw new InputException("Error, rolled number has to be between 1 and 6!");
        }

        Check.checkRoll(Integer.parseInt(param[0]));
        currentRoll = Integer.parseInt(param[0]);
        String temp;
        Player player = new Player();


        if (triply == true) {
            if (field.compareStart(currentPlayer.getColour()).getStart() == 4) {
                if (currentRoll != 6) {
                    turnCounter++;

                    if (turnCounter != 3) {
                        return currentPlayer.getColour().toString().toLowerCase();
                    } else {
                        turn();
                        turnCounter = 0;
                        currentRoll = 0;
                        return currentPlayer.getColour().toString().toLowerCase();
                    }
                }
            }
        }


        if (currentRoll == 6) {
            player = currentPlayer;
        }


        /*
        Check alle möglichen Spielzüge; wenn 0, dann turn(); + return currentPlayer;
        sonst alle möglichen Züge ausgeben
         */

        temp = field.possibleMoves(currentRoll, currentPlayer.getColour()); // mit obigem triply zusammenfügen

        if (temp.length() == 0) {
            turn();

            // TRIPLY HERE

            currentRoll = 0;
            return currentPlayer.getColour().toString().toLowerCase();
        }

        currentPlayer = player;
        currentRoll = 0;
        return temp + currentPlayer.getColour().toString().toLowerCase();
    }

    /**
     * Rolls a random number between 1 and 6 and prints out all possible turns.
     *
     * @param param String array with parameters
     * @return Returns the rolled number
     * @throws InputException For input format type errors
     */
    public String rollX(String[] param) throws InputException {
        Check.checkAmount(param, 0);
        String[] result = new String[1];

        currentRoll = 1 + (int) (Math.random() * ((6 - 1) + 1));

        result[0] = "" + currentRoll;

        return roll(result);
    }

    public String move(String[] param) throws InputException, RuleException {
        if (currentRoll == 0) {
            throw new RuleException("Error, you first have to roll a number!");
        }

        Check.checkAmount(param, 2);
        Check.checkInteger(Integer.parseInt(param[0]));
        Check.checkInteger(Integer.parseInt(param[1]));
        String output;


        //in Check nur Spielfeld einbezogen, Zielfelder wie lösen?

        output = field.moveToken(field.getBoard(), Integer.parseInt(param[0]), Integer.parseInt(param[1]), currentPlayer
                .getColour());

        if (currentRoll != 6) {
            turn();
        }

        output += "" + "\n" + currentPlayer.getColour().toString().toLowerCase();

        return output;
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
