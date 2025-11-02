package src.cages;

import src.animals.Ungulate;

// Cage for hoofed mammals (zebra, giraffe)
public class UngulateCage extends MammalCage<Ungulate> {
    public UngulateCage(int capacity) {
        super(capacity);
    }
}

