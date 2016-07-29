package assignment;

import java.util.IntSummaryStatistics;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class Check {

    /**
     * Checks if input is an integer.
     *
     * @param input Input
     * @throws InputException For input format type errors
     */
    public static void checkInteger(int input) throws InputException {
        if (input < 0 || input > 39) {
            throw new InputException("Error, please choose a board position!"); //XXXXXXXXXXXX
        }
    }

    /**
     * Checks if input is a valid field.
     *
     * @param input Input
     * @return Returns true or false
     * @throws InputException For input format type errors
     */
    public static boolean isInteger(int input) throws InputException {
        if (input < 0 || input > 39) {
            throw new InputException("Error, please choose a number between 0 and 39!");
        } else {
            return true;
        }
    }

    /**
     * Checks if rolled number is from 1 to 6.
     *
     * @param input String array with parameters
     * @throws InputException For input format type errors
     */
    public static void checkRoll(String input) throws InputException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InputException("Error, rolled number has to be between 1 and 6!");
        }

        int roll = Integer.parseInt(input);

        if (roll < 1 || roll > 6) {
            throw new InputException("Error, rolled number has to be between 1 and 6!");
        }
    }

    /**
     * Checks if input is a valid roll.
     *
     * @param input Input
     * @return Returns true or false
     * @throws InputException For input format type errors
     */
    public static boolean isRoll(int input) throws InputException {
        if (input < 1 || input > 6) {
            throw new InputException("Error, please choose a number between 1 and 6!");
        } else {
            return true;
        }
    }

    /**
     * Checks length of parameter array.
     *
     * @param param Input
     * @param n     Number of required parameters
     * @throws InputException For input format type errors
     */
    public static void checkAmount(String[] param, int n) throws InputException {
        if (param.length != n) {
            throw new InputException("Error, wrong input format!");
        }
    }

    public static boolean checkTurn(int start) {
        if (start == 4) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if a string contains only letters.
     *
     * @param input Input
     * @return Returns true, if string is valid
     * @throws InputException For input format type errors
     */
    public static boolean checkString(String input) throws InputException {
        String temp;
        temp = input.replaceAll("\\d+.*", "");

        Pattern p = Pattern.compile("[A-Z0-9]");
        Matcher m = p.matcher("" + input.charAt(input.length() - 1));
        boolean b = m.find();

        if (input.equals("")) {
            throw new InputException("Error, strings can not be empty!");
        }

        if (temp.equals(input)) {
            if (!input.contains("S")) {
                temp = temp.replaceAll("\\W", "");

                if (!temp.equals(input)) {
                    throw new InputException("Error, game mode names only contain letters!");
                } else {
                    return true;
                }
            } else {
                return true;
            }
        } else if (!b) {
            throw new InputException("Error, wrong input format!");
        }

        return true;
    }

    /**
     * Checks the order and validity of the positions string.
     *
     * @param pos    String array with positions for each colour
     * @param colour Colour for respective positions
     * @return Returns true or throws exceptions
     * @throws InputException For input format type errors
     */
    public static boolean checkOrder(String[] pos, char colour) throws InputException {
        int[] test = new int[4];

        for (int i = 0; i < 4; i++) {
            String v = pos[i];
            try {
                isInteger(Integer.parseInt(v));
                if (Integer.parseInt(v) < 0 || Integer.parseInt(v) > 39) {
                    throw new InputException("Error, wrong input format!");
                }
                test[i] = Integer.parseInt(v);
            } catch (NumberFormatException e) {
                if (v.length() != 2 || !("ABCDS").contains(("" + v.charAt(0))) || v.charAt(1) != colour) {
                    throw new InputException("Error, wrong input format!");
                }

                test[i] = v.charAt(0);
                if (v.charAt(0) == 'S') {
                    test[i] = -1;
                }
            }
        }

        for (int i = 1; i < 4; i++) {
            if (test[i] < test[i - 1]) {
                throw new InputException("Error, wrong input format!");
            }
            if (test[i] == test[i - 1] && test[i] != -1) {
                throw new InputException("Error, wrong input format!");
            }
        }
        return true;
    }
}