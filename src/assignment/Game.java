package assignment;

import java.util.ArrayList;
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

    private String next() {
        turn();
        return current;
    }

    private void possMovesBack(int i, Colour colour, boolean barrier) {
        boolean b = true;

        for (int h = i; h > i - currentRoll; h--) {
            if (h % 40 == field.compareStart(colour).getBoardStart()) {
                b = false;
            }
        }
        if (b) {
            if (!field.getBoard()[i - currentRoll % 40].getColour().equals(colour)
                    && !field.getBoard()[i - currentRoll % 40].getColour().equals(Colour.EMPTY)) {
                if (!barrier) {
                    rolls.add("" + i + "-" + (i - currentRoll % 40));
                } else {
                    for (int j = i - 1; j > i - currentRoll; j--) {
                        if (field.getBoard()[j % 40].isBarrier()) {
                            b = false;
                        }
                    }
                    if (field.getBoard()[i].isBarrier() && !field.getBoard()[((i - currentRoll) % 40)]
                            .getColour().equals(colour) && !field.getBoard()[((i - currentRoll) % 40)]
                            .getColour().equals(Colour.EMPTY)) {
                        if (b) {
                            rolls.add("" + i + "-" + (i - currentRoll % 40));
                        }
                    }
                }
            }
        }
    }

    private void checkPossMovesToDest(int i, int dest, Colour colour, boolean barrier) {
        if (!barrier) {
            possMovesToDest(i, dest, colour);
        } else {
            boolean c = true;
            for (int j = i; j < i + currentRoll - dest; j++) {
                if (field.getBoard()[j % 40].isBarrier()) {
                    c = false;
                }
            }
            if (c) {
                possMovesToDest(i, dest, colour);
            }
        }
    }

    private void possMovesToDest(int i, int dest, Colour colour) {
        boolean d = true;

        if (nojump) {
            for (int j = 0; j < dest; j++) {
                if (!field.compareDest(colour).getDestination()[j].getColour().equals(Colour.EMPTY)) {
                    d = false;
                }
            }
            if (d) {
                rolls.add(i + "-" + field.getAbcd()[dest] + current.toUpperCase().charAt(0));
            }
        } else {
            if (field.compareDest(colour).getDestination()[dest].getColour().equals(Colour.EMPTY)) {
                rolls.add(i + "-" + field.getAbcd()[dest] + current.toUpperCase().charAt(0));
            }
        }
    }

    private void possDestMoves(Colour colour) {
        boolean b = true;

        for (int i = 0; i < 4; i++) {
            if (field.compareDest(colour).getDestination()[i].getColour().equals(colour)) {
                if (i + currentRoll < 4) {
                    if (field.compareDest(colour).getDestination()[i + currentRoll].getColour().equals(Colour.EMPTY)) {
                        if (nojump) {
                            for (int j = i + 1; j <= i + currentRoll; j++) {
                                if (!field.compareDest(colour).getDestination()[j].getColour().equals(Colour.EMPTY)) {
                                    b = false;
                                    break;
                                }
                            }
                            if (b) {
                                rolls.add(field.getAbcd()[i] + current.toUpperCase().charAt(0) + "-"
                                        + field.getAbcd()[i + currentRoll] + current.toUpperCase().charAt(0));
                            }
                        } else {
                            rolls.add(field.getAbcd()[i] + current.toUpperCase().charAt(0) + "-"
                                    + field.getAbcd()[i + currentRoll] + current.toUpperCase().charAt(0));
                        }
                    }
                }
            }
        }
    }

    private void possMoves(Colour colour) {
        for (int i = 0; i < 40; i++) {
            boolean b = true;
            boolean c = true;
            if (field.getBoard()[i].getColour().equals(colour)) {
                if (backward) {
                    possMovesBack(i, colour, barrier);
                }
                for (int h = i + 1; h <= i + currentRoll; h++) {
                    if (h % 40 == field.compareStart(colour).getBoardStart()) {
                        c = false;
                        break;
                    }
                }
                if (c) {
                    if (!field.getBoard()[i + currentRoll % 40].getColour().equals(colour)) {
                        if (!barrier) {
                            rolls.add("" + i + "-" + (i + currentRoll % 40));
                        } else {
                            for (int j = i + 1; j < i + currentRoll + 1; j++) {
                                if (field.getBoard()[j % 40].isBarrier()) {
                                    c = false;
                                }
                            }
                            if (field.getBoard()[i].isBarrier()) {
                                if (c) {
                                    rolls.add("" + i + "-" + (i + currentRoll % 40));
                                }
                            }
                        }
                    } else {
                        if (!barrier) {
                            rolls.add("" + i + "-" + (i + currentRoll % 40));
                        } else {
                            for (int j = i; j < i + currentRoll; j++) {
                                if (field.getBoard()[j % 40].isBarrier()) {
                                    c = false;
                                }
                            }
                            if (c) {
                                if (!field.getBoard()[i + currentRoll % 40].isBarrier()) {
                                    rolls.add("" + i + "-" + (i + currentRoll % 40));
                                }
                            }
                        }
                    }
                } else {
                    int dest = ((i + currentRoll) % 40) - field.compareStart(colour).getBoardStart();
                    if (dest <= 4) {
                        checkPossMovesToDest(i, dest, colour, barrier);
                    }
                }
            }
        }
        possDestMoves(colour);
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

    /**
     * Collects all allowed movements on the board with the number rolled.
     *
     * @param param String array with parameters
     * @return Returns a list of allowed movements on the board
     * @throws InputException For input format type errors
     */
    public String roll(String[] param) throws InputException {
        Check.checkAmount(param, 1);
        Check.checkRoll(param[0]);
        currentRoll = Integer.parseInt(param[0]);
        rolls = new ArrayList<>();
        current = currentPlayer.getColour().toString().toLowerCase();
        Colour colour = currentPlayer.getColour();
        String output = "";

        if (currentRoll != 6) {
            if (field.compareStart(colour).getStart() < 4) {
                possMoves(colour);
                if (rolls.size() == 0) {
                    return next();
                }
            } else {
                if (triply) {
                    turnCounter++;
                    if (turnCounter < 3) {
                        currentRoll = 0;
                        return current;
                    } else {
                        turnCounter = 0;
                        return next();
                    }
                } else {
                    return next();
                }
            }
        } else {
            if (field.compareStart(colour).getStart() > 0) {
                if (!barrier) {
                    if (!field.getBoard()[field.compareStart(colour).getBoardStart()].getColour().equals(colour)) {
                        rolls.add("S" + current.toUpperCase().charAt(0) + "-" + field.compareStart(colour)
                                .getBoardStart());
                    } else if (!field.getBoard()[field.compareStart(colour).getBoardStart() + 6].getColour()
                            .equals(colour)) {
                        rolls.add(field.compareStart(colour).getBoardStart() + "-" + (field.compareStart(colour)
                                .getBoardStart() + 6));
                    } else if (!field.getBoard()[(field.compareStart(colour).getBoardStart() + (2 * 6) % 40)]
                            .getColour().equals(colour)) {
                        rolls.add((field.compareStart(colour).getBoardStart() + 6) + "-" + ((field
                                .compareStart(colour).getBoardStart() + (2 * 6)) % 40));
                    } else if (!field.getBoard()[((field.compareStart(colour).getBoardStart() + (3 * 6)) % 40)]
                            .getColour().equals(colour)) {
                        rolls.add(((field.compareStart(colour).getBoardStart() + (2 * 6)) % 40) + "-"
                                + ((field.compareStart(colour).getBoardStart() + (3 * 6)) % 40));
                    }
                } else {
                    if (!field.getBoard()[field.compareStart(colour).getBoardStart()].getColour().equals(colour)) {
                        rolls.add("" + "S" + current.toUpperCase().charAt(0) + "-" + field.compareStart(colour)
                                .getBoardStart());
                    } else {
                        int k = field.compareStart(colour).getBoardStart();

                        for (int j = k; j <= (k + currentRoll); j++) {
                            if (field.getBoard()[k].isBarrier()) {
                                return next();
                            }
                        }
                        rolls.add("" + field.compareStart(colour).getBoardStart() + "-"
                                + (field.compareStart(colour).getBoardStart() + 6));
                    }
                }
            } else {
                possMoves(colour);
                if (rolls.size() == 0) {
                    return next();
                }
            }
        }
        for (int j = 0; j < rolls.size(); j++) {
            output += "" + rolls.get(j) + "\n";
        }

        output = output.trim();
        return output + "\n" + current;
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

    /**
     * Moves a token on the board
     *
     * @param param String array with parameters
     * @return Returns the field where the token was moved to, if successful
     * @throws InputException For input format type errors
     * @throws RuleException  For game rule violations
     */
    public String move(String[] param) throws InputException, RuleException {
        if (currentRoll == 0) {
            throw new RuleException("Error, you first have to roll a number!");
        }

        Check.checkAmount(param, 2);
        turnCounter = 0;
        Colour colour = currentPlayer.getColour();
        boolean b;

        for (int j = 0; j < 2; j++) {
            b = !param[j].contains("" + current.toUpperCase().charAt(0));
            if (b) {
                Check.checkInteger(param[j]);
                Check.isField(Integer.parseInt(param[j]));
            }
        }
        String[] possibilities;

        for (int m = 0; m < rolls.size(); m++) {
            possibilities = rolls.get(m).split("\\W");

            if (param[0].equals(possibilities[0])) {
                if (param[1].equals(possibilities[1])) {

                    if (param[0].contains("S")) {
                        field.moveOut(field.compareStart(colour), field.compareStart(colour).getBoardStart(),
                                Integer.parseInt(param[1]), colour);
                        turn();
                        return param[1] + "\n" + current;

                    } else if ("ABCD".contains("" + param[0].charAt(0))) {
                        field.moveDest("" + param[0].charAt(0), currentRoll, colour);
                        if (currentRoll != 6) {
                            turn();
                            return param[1] + "\n" + current;
                        } else {
                            return param[1] + "\n" + current;
                        }

                    } else {
                        field.move(field.getBoard(), Integer.parseInt(param[0]), param[1], colour, barrier);
                        if (field.isWinner()) {
                            rolls = new ArrayList<>();
                            Main.setActive(false);
                            return param[1] + "\n" + current + " winner";
                        } else {
                            if (currentRoll != 6) {
                                turn();
                                rolls = new ArrayList<>();
                                return param[1] + "\n" + current;
                            } else {
                                rolls = new ArrayList<>();
                                return param[1] + "\n" + current;
                            }
                        }
                    }
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