package assignment;

import java.util.List;

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
    private List<String> rolls;
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
        currentRoll = 0;
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
        boolean b = true;

        if (currentRoll != 6) {
            if (field.compareStart(currentPlayer.getColour()).getStart() < 4) {
                for (int i = 0; i < 40; i++) {
                    if (field.getBoard()[i].getColour().equals(currentPlayer.getColour())) {
                        if (backward) {
                            if (i - currentRoll > field.compareStart(currentPlayer.getColour()).getBoardStart()) {
                                if (!field.getBoard()[i - currentRoll].getColour().equals(currentPlayer.getColour())
                                        && !field.getBoard()[i - currentRoll].getColour().equals(Colour.EMPTY)) {
                                    if (!barrier) {
                                        rolls.add("" + i + "-" + (i - currentRoll));
                                    } else {
                                        for (int j = i - currentRoll; j < i; j++) {
                                            if (field.getBoard()[j].isBarrier()) {
                                                b = false;
                                            }
                                        }
                                        if (b) {
                                            rolls.add("" + i + "-" + (i - currentRoll));
                                        }
                                    }
                                }
                            }
                        } else if (i + currentRoll < field.compareStart(currentPlayer.getColour()).getBoardStart()) {
                            if (!field.getBoard()[i + currentRoll].getColour().equals(currentPlayer.getColour())) {
                                if (!barrier) {
                                    rolls.add("" + i + "-" + (i + currentRoll));
                                } else {
                                    for (int j = i; j < i + currentRoll; j++) {
                                        if (field.getBoard()[j].isBarrier()) {
                                            b = false;
                                        }
                                    }
                                    if (b) {
                                        rolls.add("" + i + "-" + (i + currentRoll));
                                    }
                                }
                            }
                        } else {
                            if (!barrier) {
                                int dest = i + currentRoll % 40 - field.compareStart(currentPlayer.getColour())
                                        .getBoardStart();

                                if (nojump) {

                                }
                            } else {

                            }
                        }
                    }
                }
                for (int j = 0; j < 4; j++) {
                    if (field.compareDest(currentPlayer.getColour()).g) {

                    }
                }
            } else { // = 4
                if (triply) {
                    turnCounter++;
                    if (turnCounter < 3) {
                        return current;
                    } else {
                        turn();
                        return current;
                    }
                } else {
                    turn();
                    return current;
                }
            }
        } else { // = 6
            if (field.compareStart(currentPlayer.getColour()).getStart() > 0) {

            } else { // = 0

            }
        }
        return rolls + "\n" + current;
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
        boolean b;

        for (int j = 0; j < 2; j++) {
            if (param[j].matches("" + current.toUpperCase().charAt(0))) {
                b = false;
            } else {
                b = true;
            }
            if (b) {
                Check.checkInteger(param[j]);
                Check.isField(Integer.parseInt(param[j]));
            }
        }
        String[] possibilities;

        for (int m = 0; m < rolls.size(); m++) {
            possibilities = rolls.get(m).split("\\s");

            if (param[0].equals(possibilities[0])) {
                if (param[1].equals(possibilities[1])) {

                    if (param[0].matches("S")) {
                        field.moveOut(field.compareStart(currentPlayer.getColour()),
                                field.compareStart(currentPlayer.getColour()).getBoardStart(),
                                Integer.parseInt(param[1]), currentPlayer.getColour());
                        turn();
                        return param[1] + "\n" + current;

                    } else if ("ABCD".contains("" + param[0].charAt(0))) {
                        field.moveDest("" + param[0].charAt(0), currentRoll, currentPlayer.getColour(), nojump);
                        if (currentRoll != 6) {
                            turn();
                            return param[1] + "\n" + current;
                        } else {
                            return param[1] + "\n" + current;
                        }

                    } else {
                        field.move(field.getBoard(), Integer.parseInt(param[0]), currentRoll, param[1],
                                currentPlayer.getColour(), barrier, nojump);
                        if (field.isWinner()) {
                            return param[1] + "\n" + current + " winner";
                        } else {
                            if (currentRoll != 6) {
                                turn();
                                return param[1] + "\n" + current;
                            } else {
                                return param[1] + "\n" + current;
                            }
                        }
                    }
                } else {
                    throw new InputException("Error, wrong input format!");
                }
            }
        }

        throw new InputException("Error, input does not match with possible movements on the board!");
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