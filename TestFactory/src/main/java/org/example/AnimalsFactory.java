package org.example;

public class AnimalsFactory {
    public Animals createAnimal(AnimalsType type) {
        Animals animals = null;

        switch (type) {
            case LION:
                animals =  new Lion();
                break;
            case TIGER:
                animals = new Tiger();
                break;
            case ELEPHANT:
                animals =  new Elephant();
                break;
        }

        return animals;
    }
}
