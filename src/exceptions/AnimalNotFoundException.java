package src.exceptions;

// Exception thrown when trying to remove a non-existent animal
public class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

