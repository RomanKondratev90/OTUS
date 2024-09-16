package java_db.objects;

public abstract class Animal {
    private int id;
    private String type, name, color;
    private int weight, age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Animal(String color, String name, int weight, String type, int age) {
        this.type = type;
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.age = age;
    }

    public Animal(int id, String color, String name, int weight, String type, int age) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.age = age;
    }

    public abstract void say();

    @Override
    public String toString() {
        return String.format("Животное: %s, Имя: %s, Возраст: %d, Вес: %d, Цвет: %s", type, name, age, weight, color);
    }
}
