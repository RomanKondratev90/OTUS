package java_db.tables;

import java_db.animals.Cat;
import java_db.animals.Dog;
import java_db.animals.Duck;
import java_db.db.MySQLConnect;
import java_db.objects.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalTable extends AbsTable {
    private static final String NAME = "animals";

    public AnimalTable() {
        super(NAME);
    }

    public void write(Animal animal) {
        this.dbConnector.execute(String.format(
                "INSERT INTO %s (color, name, weight, type, age) VALUES ('%s', '%s', '%d', '%s', '%d')",
                NAME,
                animal.getColor(),
                animal.getName(),
                animal.getWeight(),
                animal.getType(),
                animal.getAge()
        ));
    }

    public void update(Animal animal) {
        this.dbConnector.execute(String.format(
                "UPDATE %s SET color = '%s', name = '%s', weight = '%d', type = '%s', age = '%d' WHERE id = '%d'",
                NAME,
                animal.getColor(),
                animal.getName(),
                animal.getWeight(),
                animal.getType(),
                animal.getAge(),
                animal.getId()
        ));
    }

    public ArrayList<Animal> read() throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        ResultSet resultSet = this.dbConnector.executeQuery(String.format("SELECT * FROM %s;", NAME));
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String color = resultSet.getString("color");
            String name = resultSet.getString("name");
            int weight = resultSet.getInt("weight");
            String type = resultSet.getString("type");
            int age = resultSet.getInt("age");

            Animal animal;
            switch (type.toLowerCase()) {
                case "cat":
                    animal = new Cat(name, age, weight, color);
                    break;
                case "dog":
                    animal = new Dog(name, age, weight, color);
                    break;
                case "duck":
                    animal = new Duck(name, age, weight, color);
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный тип животного: " + type);
            }
            animal.setId(id);
            animals.add(animal);
        }
        return animals;
    }

    public ArrayList<Animal> filterByType(String type) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        ResultSet resultSet = this.dbConnector.executeQuery(String.format(
                "SELECT * FROM %s WHERE type = '%s';",
                NAME, type
        ));
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String color = resultSet.getString("color");
            String name = resultSet.getString("name");
            int weight = resultSet.getInt("weight");
            String typeFromDB = resultSet.getString("type");
            int age = resultSet.getInt("age");

            Animal animal;
            switch (typeFromDB.toLowerCase()) {
                case "cat":
                    animal = new Cat(name, age, weight, color);
                    break;
                case "dog":
                    animal = new Dog(name, age, weight, color);
                    break;
                case "duck":
                    animal = new Duck(name, age, weight, color);
                    break;
                default:
                    throw new IllegalArgumentException("Неизвестный тип животного: " + typeFromDB);
            }
            animal.setId(id);
            animals.add(animal);
        }
        return animals;
    }
}