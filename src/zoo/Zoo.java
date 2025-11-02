package src.zoo;

import src.cages.Cage;
import src.animals.Animal;
import java.util.ArrayList;
import java.util.List;

// Zoo class that contains all cages
public class Zoo {
    private final List<Cage<? extends Animal>> cages;

    public Zoo() {
        cages = new ArrayList<>();
    }

    public void addCage(Cage<? extends Animal> cage) {
        cages.add(cage);
    }

    // Count total number of animals in the zoo
    public int getCountOfAnimals() {
        int total = 0;
        for (Cage<? extends Animal> cage : cages) {
            total += cage.getOccupiedPlaces();
        }
        return total;
    }

    public List<Cage<? extends Animal>> getCages() {
        return cages;
    }
}

