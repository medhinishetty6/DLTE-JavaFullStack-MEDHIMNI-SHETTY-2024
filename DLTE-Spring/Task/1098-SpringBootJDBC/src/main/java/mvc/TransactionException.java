package mvc;

public class TransactionException extends RuntimeException {
    public TransactionException() {
        super("Transaction Not available");
    }
}
