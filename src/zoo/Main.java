package src.zoo;

import src.animals.*;
import src.cages.*;

public class Main {
    public static void main(String[] args) {
        try {
            Zoo zoo = new Zoo();

            LionCage lionCage = new LionCage(2);
            BirdCage birdCage = new BirdCage(3);
            UngulateCage ungulateCage = new UngulateCage(4);

            lionCage.addAnimal(new Lion("Simba"));
            lionCage.addAnimal(new Lion("Nala"));

            birdCage.addAnimal(new Eagle("Freedom"));
            birdCage.addAnimal(new Eagle("Sky"));

            zoo.addCage(lionCage);
            zoo.addCage(birdCage);
            zoo.addCage(ungulateCage);

            ungulateCage.addAnimal(new Zebra("Marty"));
            ungulateCage.addAnimal(new Giraffe("Melman"));

            System.out.println("Total animals in zoo: " + zoo.getCountOfAnimals());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

