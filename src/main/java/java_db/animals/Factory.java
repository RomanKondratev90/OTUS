package java_db.animals;

import java_db.objects.Animal;

public class Factory {
    public Animal createAnimal(String type, String name, int age, int weight, String color) {
        switch (type.toLowerCase()) {
            case "cat":
                return new Cat(name, age, weight, color);
            case "dog":
                return new Dog(name, age, weight, color);
            case "duck":
                return new Duck(name, age, weight, color);
            default:
                throw new IllegalArgumentException("Неизвестный тип животного.");
        }
    }
}