package java_db.animals;

import java_db.objects.Animal;

public class Duck extends Animal implements Flying {
    public Duck(String name, int age, int weight, String color) {
        super(color, name, weight, "duck", age);
    }

    @Override
    public void say() {
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        System.out.println("Я лечу");
    }
}
