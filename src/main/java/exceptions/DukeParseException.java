package exceptions;

public class DukeParseException extends DukeException {
    // raised when data format is incorrect
    public DukeParseException(String errorMessage) {
        super(errorMessage);
    }
}




