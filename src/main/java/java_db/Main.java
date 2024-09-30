package java_db;

import java_db.animals.Factory;
import java_db.command.Command;
import java_db.objects.Animal;
import java_db.db.MySQLConnect;
import java_db.tables.AnimalTable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        AnimalTable animalTable = new AnimalTable();
        Scanner scanner = new Scanner(System.in);
        Factory factory = new Factory();
        MySQLConnect dbConnector = new MySQLConnect();

        try {
            while (true) {
                System.out.println("Введите команду (add/list/edit/filter/exit): ");
                String input = scanner.nextLine().trim().toUpperCase();

                Command command;
                try {
                    command = Command.valueOf(input);
                } catch (IllegalArgumentException e) {
                    System.out.println("Неизвестная команда. Попробуйте снова.");
                    continue;
                }

                switch (command) {
                    case ADD:
                        Animal animal;
                        while (true) {
                            System.out.println("Какое животное (cat/dog/duck)?");
                            String type = scanner.nextLine().trim().toLowerCase();

                            if (type.equals("cat") || type.equals("dog") || type.equals("duck")) {
                                animal = factory.createAnimal(type, getName(scanner), getAge(scanner), getWeight(scanner), getColor(scanner));
                                animalTable.write(animal);
                                System.out.println("Животное добавлено в базу данных.");
                                break;
                            } else {
                                System.out.println("Ввести можно только cat/dog/duck. Попробуйте снова.");
                            }
                        }
                        break;

                    case LIST:
                        ArrayList<Animal> animals = animalTable.read(null);
                        if (animals.isEmpty()) {
                            System.out.println("Список животных пуст.");
                        } else {
                            for (Animal a : animals) {
                                System.out.println(a);
                            }
                        }
                        break;

                    case EDIT:
                        System.out.println("Введите ID животного для редактирования: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        animal = factory.createAnimal(
                                getType(scanner),
                                getName(scanner),
                                getAge(scanner),
                                getWeight(scanner),
                                getColor(scanner)
                        );
                        animal.setId(id);
                        animalTable.update(animal);
                        System.out.println("Животное обновлено.");
                        break;

                    case FILTER:
                        System.out.println("Введите тип животного для фильтрации (cat/dog/duck): ");
                        String filterType = scanner.nextLine().trim().toLowerCase();
                        ArrayList<Animal> filteredAnimals = animalTable.read(filterType);
                        for (Animal filteredAnimal : filteredAnimals) {
                            System.out.println(filteredAnimal);
                        }
                        break;

                    case EXIT:
                        System.out.println("Выход из программы.");
                        dbConnector.close();
                        scanner.close();
                        return;

                    default:
                        System.out.println("Неизвестная команда.");
                }
            }
        } finally {
            dbConnector.close();
            scanner.close();
        }
    }

    private static String getName(Scanner scanner) {
        System.out.println("Введите имя: ");
        return scanner.nextLine().trim();
    }

    private static int getAge(Scanner scanner) {
        while (true) {
            System.out.println("Введите возраст: ");
            try {
                int age = Integer.parseInt(scanner.nextLine().trim());
                if (age < 0 || age > 150) {
                    System.out.println("Возраст должен быть в диапазоне от 0 до 150. Попробуйте снова.");
                } else {
                    return age;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите число.");
            }
        }
    }

    private static int getWeight(Scanner scanner) {
        while (true) {
            System.out.println("Введите вес: ");
            try {
                int weight = Integer.parseInt(scanner.nextLine().trim());
                if (weight < 0 || weight > 1000) {
                    System.out.println("Вес должен быть в диапазоне от 0 до 1000 кг. Попробуйте снова.");
                } else {
                    return weight;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Введите число.");
            }
        }
    }

    private static String getColor(Scanner scanner) {
        System.out.println("Введите цвет: ");
        return scanner.nextLine().trim();
    }

    private static String getType(Scanner scanner) {
        System.out.println("Введите тип животного (cat/dog/duck): ");
        return scanner.nextLine().trim().toLowerCase();
    }
}