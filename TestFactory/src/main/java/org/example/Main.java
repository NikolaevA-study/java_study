package org.example;

public class Main {
    public static void main(String[] args) {
        final AnimalsFactory animalsFactory = new AnimalsFactory();
        Animals animals1 = animalsFactory.createAnimal(AnimalsType.LION);
        Animals animals2 = animalsFactory.createAnimal(AnimalsType.ELEPHANT);
        Animals animals3 = animalsFactory.createAnimal(AnimalsType.TIGER);
        animals1.getType();
        animals2.getType();
        animals3.getType();
    }
}