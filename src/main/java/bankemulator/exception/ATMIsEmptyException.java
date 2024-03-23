package bankemulator.exception;

public class ATMIsEmptyException extends RuntimeException {
    @Override
    public String getMessage() {
        return "ATM is empty";
    }
}
