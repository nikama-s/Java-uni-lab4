package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import src.animals.*;
import src.cages.*;
import src.exceptions.*;
import src.zoo.*;

public class ZooTest {
    private Zoo zoo;
    private LionCage lionCage;
    private BirdCage birdCage;
    private UngulateCage ungulateCage;

    @BeforeEach
    void setUp() {
        zoo = new Zoo();
        lionCage = new LionCage(2);
        birdCage = new BirdCage(3);
        ungulateCage = new UngulateCage(4);
    }

    @Test
    void testLionCageCreationAndCapacity() throws CageFullException {
        // Test 1: Lion Cage
        lionCage.addAnimal(new Lion("Leo"));
        lionCage.addAnimal(new Lion("Simba"));
        
        assertEquals(2, lionCage.getCapacity(), "Lion cage capacity should be 2");
        assertEquals(2, lionCage.getOccupiedPlaces(), "Lion cage should have 2 animals");
        zoo.addCage(lionCage);
    }

    @Test
    void testBirdCageCreationAndCapacity() throws CageFullException {
        // Test 2: Bird Cage
        birdCage.addAnimal(new Eagle("Storm"));
        birdCage.addAnimal(new Eagle("Wind"));
        
        assertEquals(3, birdCage.getCapacity(), "Bird cage capacity should be 3");
        assertEquals(2, birdCage.getOccupiedPlaces(), "Bird cage should have 2 animals");
        zoo.addCage(birdCage);
    }

    @Test
    void testUngulateCageCreationAndCapacity() throws CageFullException {
        // Test 3: Ungulate Cage
        ungulateCage.addAnimal(new Zebra("Marty"));
        ungulateCage.addAnimal(new Giraffe("Melman"));
        
        assertEquals(4, ungulateCage.getCapacity(), "Ungulate cage capacity should be 4");
        assertEquals(2, ungulateCage.getOccupiedPlaces(), "Ungulate cage should have 2 animals");
        zoo.addCage(ungulateCage);
    }

    @Test
    void testZooAnimalCount() throws CageFullException {
        // Test 4: Zoo Animal Count
        lionCage.addAnimal(new Lion("Leo"));
        lionCage.addAnimal(new Lion("Simba"));
        birdCage.addAnimal(new Eagle("Storm"));
        birdCage.addAnimal(new Eagle("Wind"));
        ungulateCage.addAnimal(new Zebra("Marty"));
        ungulateCage.addAnimal(new Giraffe("Melman"));
        
        zoo.addCage(lionCage);
        zoo.addCage(birdCage);
        zoo.addCage(ungulateCage);
        
        int totalAnimals = zoo.getCountOfAnimals();
        assertEquals(6, totalAnimals, "Total animals should be 6 (2 lions + 2 eagles + 1 zebra + 1 giraffe)");
    }

    @Test
    void testCageFullException() throws CageFullException {
        // Test 5: CageFullException
        LionCage smallCage = new LionCage(1);
        smallCage.addAnimal(new Lion("Leo"));
        
        // Attempting to add animal to full cage should throw CageFullException
        assertThrows(CageFullException.class, () -> {
            smallCage.addAnimal(new Lion("Extra"));
        }, "Should throw CageFullException when cage is full");
    }

    @Test
    void testAnimalNotFoundException() throws CageFullException {
        // Test 6: AnimalNotFoundException
        birdCage.addAnimal(new Eagle("Storm"));
        Eagle nonExistent = new Eagle("NonExistent");
        
        // Attempting to remove non-existent animal should throw AnimalNotFoundException
        assertThrows(AnimalNotFoundException.class, () -> {
            birdCage.removeAnimal(nonExistent);
        }, "Should throw AnimalNotFoundException when animal is not found");
    }

    @Test
    void testRemoveAnimalSuccessfully() throws CageFullException, AnimalNotFoundException {
        // Test 7: Remove Animal Successfully
        birdCage.addAnimal(new Eagle("Storm"));
        birdCage.addAnimal(new Eagle("Wind"));
        
        Bird storm = birdCage.getAnimals().get(0);
        birdCage.removeAnimal(storm);
        
        assertEquals(1, birdCage.getOccupiedPlaces(), "Bird cage should have 1 animal after removal");
    }

    @Test
    void testZooCountAfterRemoval() throws CageFullException, AnimalNotFoundException {
        // Test 8: Zoo Count After Removal
        lionCage.addAnimal(new Lion("Leo"));
        lionCage.addAnimal(new Lion("Simba"));
        birdCage.addAnimal(new Eagle("Storm"));
        birdCage.addAnimal(new Eagle("Wind"));
        ungulateCage.addAnimal(new Zebra("Marty"));
        ungulateCage.addAnimal(new Giraffe("Melman"));
        
        zoo.addCage(lionCage);
        zoo.addCage(birdCage);
        zoo.addCage(ungulateCage);
        
        // Remove one animal
        Bird storm = birdCage.getAnimals().get(0);
        birdCage.removeAnimal(storm);
        
        int newTotal = zoo.getCountOfAnimals();
        assertEquals(5, newTotal, "Total animals should be 5 after removing 1 eagle");
    }

    @Test
    void testTypeSafetyPreventsWrongAnimalTypes() {
        // Test 9: Type Safety - Generics prevent wrong animal types at compile time
        // This test demonstrates that the compiler prevents wrong types
        
        LionCage lionCage2 = new LionCage(2);
        BirdCage birdCage2 = new BirdCage(2);
        UngulateCage ungulateCage2 = new UngulateCage(2);
        
        // These compile correctly - only correct types can be added
        assertDoesNotThrow(() -> {
            try {
                lionCage2.addAnimal(new Lion("Test"));
            } catch (CageFullException e) {
                // Ignore capacity exception, just testing type safety
            }
        }, "Should accept Lion in LionCage");
        
        assertDoesNotThrow(() -> {
            try {
                birdCage2.addAnimal(new Eagle("Test"));
            } catch (CageFullException e) {
                // Ignore capacity exception, just testing type safety
            }
        }, "Should accept Eagle in BirdCage");
        
        assertDoesNotThrow(() -> {
            try {
                ungulateCage2.addAnimal(new Zebra("Test"));
            } catch (CageFullException e) {
                // Ignore capacity exception, just testing type safety
            }
        }, "Should accept Zebra in UngulateCage");
        
        // Note: The following would cause compile errors (demonstrating type safety):
        // lionCage2.addAnimal(new Eagle("Wrong"));  // COMPILE ERROR
        // birdCage2.addAnimal(new Lion("Wrong"));    // COMPILE ERROR
        // ungulateCage2.addAnimal(new Eagle("Wrong")); // COMPILE ERROR
    }
}
