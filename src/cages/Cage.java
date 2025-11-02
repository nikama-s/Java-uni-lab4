package src.cages;

import src.animals.Animal;
import src.exceptions.CageFullException;
import src.exceptions.AnimalNotFoundException;
import java.util.ArrayList;
import java.util.List;

// Generic base class for cages
public class Cage<T extends Animal> {
    private final int capacity;
    private final List<T> animals;

    public Cage(int capacity) {
        this.capacity = capacity;
        this.animals = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedPlaces() {
        return animals.size();
    }

    public void addAnimal(T animal) throws CageFullException {
        if (animals.size() >= capacity) {
            throw new CageFullException("Cage is full! Cannot add " + animal);
        }
        animals.add(animal);
    }

    public void removeAnimal(T animal) throws AnimalNotFoundException {
        if (!animals.remove(animal)) {
            throw new AnimalNotFoundException("Animal not found in cage: " + animal);
        }
    }

    public List<T> getAnimals() {
        return animals;
    }
}
