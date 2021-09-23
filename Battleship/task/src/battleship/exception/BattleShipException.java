package battleship.exception;

public class BattleShipException extends RuntimeException{
    public BattleShipException() {
    }

    public BattleShipException(String message) {
        super(message);
    }

    public BattleShipException(String message, Throwable cause) {
        super(message, cause);
    }

    public BattleShipException(Throwable cause) {
        super(cause);
    }

    public BattleShipException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
