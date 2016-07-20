package assignment;

/**
 * @author Robin Fritz
 * @version 1.0
 */
public class RuleException extends Exception {

    /**
     * Exception for game rule violations.
     *
     * @param message Specified message, if exception is thrown
     */
    public RuleException(String message) {
        super(message);
    }
}
