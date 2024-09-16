package java_db.animals;

import java_db.objects.Animal;

public class Cat extends Animal {
    public Cat(String name, int age, int weight, String color) {
        super(color, name, weight, "cat", age);
    }

    @Override
    public void say() {
        System.out.println("Мяу");
    }
}