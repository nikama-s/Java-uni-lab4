package src.cages;

import src.animals.Lion;

// Cage specifically for lions
public class LionCage extends MammalCage<Lion> {
    public LionCage(int capacity) {
        super(capacity);
    }
}
