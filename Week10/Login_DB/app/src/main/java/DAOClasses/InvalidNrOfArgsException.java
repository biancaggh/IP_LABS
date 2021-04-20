package DAOClasses;

public class InvalidNrOfArgsException extends Exception {
    public InvalidNrOfArgsException(String msg) {
        super("Invalid number of arguments! " + msg);
    }
}
