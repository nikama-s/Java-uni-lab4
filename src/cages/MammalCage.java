package src.cages;

import src.animals.Mammal;

// Cage for mammals
public class MammalCage<T extends Mammal> extends Cage<T> {
    public MammalCage(int capacity) {
        super(capacity);
    }
}
