package bankemulator.exception;

public class LimitExceedingException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Exceeding the limit";
    }
}
