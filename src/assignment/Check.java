package assignment;

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
     * @param input Input from terminal
     * @throws InputException For input format type errors
     */
    public static void checkInteger(int input) throws InputException {
        if (input < 0 || input > 40) {
            throw new InputException("Error, please choose a board position!"); //XXXXXXXXXXXX
        }
    }

    public static boolean isInteger(int input) throws InputException {
        if (input < 0 || input > 6) {
            throw new InputException("Error, please choose a number from 1 to 6!");
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

    /**
     * Checks if rolled number is from 1 to 6.
     *
     * @param input String array with parameters
     * @throws InputException For input format type errors
     */
    public static void checkRoll(int input) throws InputException {
        if (input < 1 || input > 6) {
            throw new InputException("Error, rolled number has to be from 1 to 6!");
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
            temp = temp.replaceAll("\\W", "");

            if (!temp.equals(input)) {
                throw new InputException("Error, game mode names only contain letters!");
            } else {
                return true;
            }
        } else if (!b) {
            throw new InputException("Error, wrong input format!");
        }

        return true;
    }
}