package assignment;

import jdk.internal.util.xml.impl.Input;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Game {
    private int currentRoll;
    private int turnCounter = 0;
    private Field field;
    private Player currentPlayer;
    private String current;
    private String rolls;
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
        current = currentPlayer.getColour().toString().toLowerCase();
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
            for (int i = 1; i < optionals.length; i++) {
                if (optionals[i].equals(Gamemodes.BACKWARD.name())) {
                    backward = true;
                } else if (optionals[i].equals(Gamemodes.BARRIER.name())) {
                    barrier = true;
                } else if (optionals[i].equals(Gamemodes.NOJUMP.name())) {
                    nojump = true;
                } else if (optionals[i].equals(Gamemodes.TRIPLY.name())) {
                    triply = true;
                } else if (optionals[i].contains(",")) {
                    if (!optionals[i].equals(optionals[optionals.length - 1])) {
                        throw new InputException("Error, please comply by the input format! If you wish to use "
                                + "optional rules or positions, please use this input format: <rules> <positions>");
                    } else {
                        positions = optionals[optionals.length - 1].split(";");

                        field.setPositions(positions);
                        break;
                    }
                } else {
                    throw new InputException("Error, wrong input format!");
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
        Check.checkRoll(param[0]);
        Player player = new Player();

        currentRoll = Integer.parseInt(param[0]);
        current = currentPlayer.getColour().toString().toLowerCase();
        String output = new String();

        if (currentRoll != 6) {
            if (field.compareStart(currentPlayer.getColour()).getStart() == 4) { // falls Start voll
                if (triply == true) { // und triply aktiv, dann bis zu 3 Mal würfeln; dann nächster dran
                    turnCounter++;

                    if (turnCounter < 3) {
                        return current;
                    } else {
                        turn();
                        turnCounter = 0;
                        currentRoll = 0;
                        return current;
                    }
                } else {
                    turn();
                    currentRoll = 0;
                    return current;
                }
            } else { // board + Ziel durchgehen, mögliche Züge überprüfen, in output speichern -> returnen
                rolls = field.possMoves(currentPlayer.getColour(), barrier);
                return rolls + "\n" + current;
            }
        } else {
            if (field.compareStart(currentPlayer.getColour()).getStart() > 0) { // falls 6 und noch Tokens in start,
                // dann diese rausholen, falls Platz; falls kein Platz, dann versperrenden Token bewegen, falls Barrier
                // aktiv, dann kein Problem

            } else { // board + Ziel durchgehen, mögliche Züge überprüfen, in output speichern -> returnen
                for (int i = 0; i < 40; i++) {
                    if (!field.getBoard()[i].getColour().equals(currentPlayer.getColour())) {

                    } else { // falls Barrier aktiv, dann auch möglich
                        if (barrier == true) {

                        } else {
                            break;
                        }
                    }
                }
                for (int j = 0; j < 4; j++) {
                    if (!nojump) {
                        if (!field.getDestList().get(j).getColour().equals(currentPlayer.getColour())) {
                            // kann dorthin moven, falls Nojump nicht aktiv
                        }
                    } else { // alle Dest Felder müssen überprüft werden auf dem Weg

                    }
                }
            }
        }

        //output += "" + asd + "-" + asdfhj;


        if (currentRoll == 6) {
            player = currentPlayer;
        }


        /*
        Check alle möglichen Spielzüge; wenn 0, dann turn(); + return currentPlayer;
        sonst alle möglichen Züge ausgeben
         */

        //temp = field.possibleMoves(currentRoll, currentPlayer.getColour()); // mit obigem triply zusammenfügen

        if (0 == 0) {
            turn();

            // TRIPLY HERE

            currentRoll = 0;
            return currentPlayer.getColour().toString().toLowerCase();
        }

        currentPlayer = player;
        currentRoll = 0;
        return currentPlayer.getColour().toString().toLowerCase();
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
        Check.checkInteger(param[0]);
        Check.checkInteger(param[1]);
        String[] possibilities;
        String output;

        possibilities = rolls.split("\\s");

        for (int i = 0; i < possibilities.length - 1; i++) {
            if (param[0].matches(possibilities[i])) {
                if (param[1].matches(possibilities[i])) {
                    if (param[0].matches("S")) { // fertig
                        field.moveOut(field.compareStart(currentPlayer.getColour()),
                                field.compareStart(currentPlayer.getColour()).getBoardStart());
                    } else if (param[0].matches(field.getAbcd()[i])) {
                        field.getDestList().get(i).getDestination()[i].setColour(Colour.EMPTY);
                        field.getDestList().get(i + Integer.parseInt(param[1])).getDestination()[i]
                                .setColour(currentPlayer.getColour());
                    } else {
                        field.getBoard()[Integer.parseInt(param[0])].setColour(Colour.EMPTY);
                        field.getBoard()[Integer.parseInt(param[1])].setColour(currentPlayer.getColour());
                    }
                } else {
                    throw new InputException("Error, wrong input format!");
                }
            }
        }

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