package final01;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class InputException extends Exception {

    /**
     * Exception for input format type errors.
     *
     * @param message Specified message, if exception is thrown
     */
    public InputException(String message) {
        super(message);
    }
}
