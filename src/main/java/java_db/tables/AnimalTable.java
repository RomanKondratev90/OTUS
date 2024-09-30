package java_db.tables;

import java_db.animals.Cat;
import java_db.animals.Dog;
import java_db.animals.Duck;
import java_db.db.IDBConnect;
import java_db.db.MySQLConnect;
import java_db.objects.Animal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalTable extends AbsTable {
    private static final String NAME = "animals";

    public AnimalTable(IDBConnect dbConnector) {
        super(dbConnector, NAME);
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

    public ArrayList<Animal> read(String filter) throws SQLException {
        ArrayList<Animal> animals = new ArrayList<>();
        String query = filter == null || filter.isEmpty()
                ? String.format("SELECT * FROM %s;", NAME)
                : String.format("SELECT * FROM %s WHERE type = '%s';", NAME, filter);

        ResultSet resultSet = null;
        try {
            resultSet = this.dbConnector.executeQuery(query);
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
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return animals;
    }
}