package bankemulator.exception;

public class MoneyInATMIsLessException extends RuntimeException{
    @Override
    public String getMessage() {
        return "There is not enough money in the ATM";
    }
}
