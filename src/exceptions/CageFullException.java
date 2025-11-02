package src.exceptions;

// Exception thrown when a cage is already full
public class CageFullException extends Exception {
    public CageFullException(String message) {
        super(message);
    }
}
