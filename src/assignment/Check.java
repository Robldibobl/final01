package assignment;

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

    public static boolean isInteger(int input) {
        if (input < 0 || input > 40) {
            return false;
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
     * @throws InputException For input format type errors
     */
    public static void checkString(String input) throws InputException {
        String temp = input;
        temp = temp.replaceAll("[*0-9]", "");

        if (input.matches("")) {
            throw new InputException("Error, vertex names can not be empty!");
        }

        if (input.contains(",")) {
            throw new InputException("Error, vertex names can only contain letters!");
        }

        if (!temp.matches(input)) {
            throw new InputException("Error, vertex names can only contain letters!");
        }
    }
}