package java_db.animals;

import java_db.objects.Animal;

public class Dog extends Animal {
    public Dog(String name, int age, int weight, String color) {
        super(color, name, weight, "dog", age);
    }

    @Override
    public void say() {
        System.out.println("Гав");
    }
}