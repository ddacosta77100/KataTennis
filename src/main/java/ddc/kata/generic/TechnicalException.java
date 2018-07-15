package ddc.kata.generic;

/**
 * @author ddacosta 15/07/2018
 * @project KataTennis
 */
public class TechnicalException extends RuntimeException {

    public TechnicalException() {
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalException(Throwable cause) {
        super(cause);
    }

    public TechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
