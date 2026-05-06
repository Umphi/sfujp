
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {

    public static boolean tryParseInt(String str, int[] result)
    {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            result[0] = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Animal> animals = new ArrayList<>();

        while (true) { 
            System.out.print("\n".repeat(50));
            System.out.println("1. Добавить животное");
            System.out.println("2. Удалить животное");
            System.out.println("3. Вывод списка");
            System.out.println("4. Сравнение");
            System.out.println("5. Выход");
            String input = br.readLine();
            switch (input) {
                case "1":
                    System.out.print("\n".repeat(50));
                    System.out.println("1. Млекопитающие");
                    System.out.println("2. Хищники");
                    System.out.println("3. Рыбы");
                    System.out.println("4. Пресмыкающиеся (рептилии)");
                   System.out.println("5. Выход");
                    int[] chosen = new int[1];
                    String inputClass = "";
                    while(!tryParseInt(inputClass, chosen) || chosen[0] < 1 || chosen[0] > 5) {
                        System.out.print("Выберите класс/отряд: ");
                        inputClass = br.readLine();
                    }
                    switch (chosen[0]) {
                        case 1:
                            String inputParameter = "";
                            System.out.print("\n".repeat(50));
                            System.out.print("Введите научное название: ");
                            String binominalName = br.readLine();
                            
                            int[] chosenYear = new int[1];
                            while(!tryParseInt(inputParameter, chosenYear) || chosenYear[0] < 500 || chosenYear[0] > LocalDate.now().getYear()) {
                                System.out.print("Введите год принятия названия: ");
                                inputParameter = br.readLine();
                            }
                            inputParameter = "";
                            int[] averageHeartRate = new int[1];
                            while(!tryParseInt(inputParameter, averageHeartRate) || averageHeartRate[0] < 1 || averageHeartRate[0] > 1000) {
                                System.out.print("Введите средний пульс: ");
                                inputParameter = br.readLine();
                            }
                            System.out.print("Введите тип шерсти: ");
                            String furType = br.readLine();
                            animals.add(new Mammal(binominalName, chosenYear[0], averageHeartRate[0], furType));
                            break;
                        case 2:
                            inputParameter = "";
                            System.out.print("\n".repeat(50));
                            System.out.print("Введите научное название: ");
                            binominalName = br.readLine();
                            
                            chosenYear = new int[1];
                            while(!tryParseInt(inputParameter, chosenYear) || chosenYear[0] < 500 || chosenYear[0] > LocalDate.now().getYear()) {
                                System.out.print("Введите год принятия названия: ");
                                inputParameter = br.readLine();
                            }
                            inputParameter = "";
                            averageHeartRate = new int[1];
                            while(!tryParseInt(inputParameter, averageHeartRate) || averageHeartRate[0] < 1 || averageHeartRate[0] > 1000) {
                                System.out.print("Введите средний пульс: ");
                                inputParameter = br.readLine();
                            }
                            System.out.print("Введите тип шерсти: ");
                            furType = br.readLine();
                            inputParameter = "";
                            int[] biteForce = new int[1];
                            biteForce[0] = -1;
                            while(!tryParseInt(inputParameter, biteForce) || biteForce[0] < 0 || biteForce[0] > 10000) {
                                System.out.print("Введите силу укуса (в кг): ");
                                inputParameter = br.readLine();
                            }
                            System.out.print("Введите типичную стратегию охоты: ");
                            String huntingStrategy = br.readLine();
                            animals.add(new Carnivora(binominalName, chosenYear[0], averageHeartRate[0], furType, biteForce[0], huntingStrategy));
                            break;
                        case 3:
                            inputParameter = "";
                            System.out.print("\n".repeat(50));
                            System.out.print("Введите научное название: ");
                            binominalName = br.readLine();
                            
                            chosenYear = new int[1];
                            while(!tryParseInt(inputParameter, chosenYear) || chosenYear[0] < 500 || chosenYear[0] > LocalDate.now().getYear()) {
                                System.out.print("Введите год принятия названия: ");
                                inputParameter = br.readLine();
                            }
                            inputParameter = "";
                            int[] finCount = new int[1];
                            finCount[0] = -1;
                            while(!tryParseInt(inputParameter, finCount) || finCount[0] < 0 || finCount[0] > 30) {
                                System.out.print("Введите количество плавников: ");
                                inputParameter = br.readLine();
                            }
                            inputParameter = "";
                            int[] waterType = new int[1];
                            while(!tryParseInt(inputParameter, waterType) || waterType[0] < 1 || waterType[0] > 3) {
                                System.out.print("Выберите тип воды (1 - пресная, 2 - соленая, 3 - любая): ");
                                inputParameter = br.readLine();
                            }
                            String waterTypeStr = "";
                            switch (waterType[0]) {
                                case 1:
                                    waterTypeStr = "Пресная";
                                    break;
                                case 2:
                                    waterTypeStr = "Соленая";
                                    break;
                                case 3:
                                    waterTypeStr = "Любая";
                                    break;
                            }
                            animals.add(new Fish(binominalName, chosenYear[0], finCount[0], waterTypeStr));
                            break;
                        case 4:
                            inputParameter = "";
                            System.out.print("\n".repeat(50));
                            System.out.print("Введите научное название: ");
                            binominalName = br.readLine();
                            
                            chosenYear = new int[1];
                            while(!tryParseInt(inputParameter, chosenYear) || chosenYear[0] < 500 || chosenYear[0] > LocalDate.now().getYear()) {
                                System.out.print("Введите год принятия названия: ");
                                inputParameter = br.readLine();
                            }
                            inputParameter = "";
                            int[] bodyTemperature = new int[1];
                            while(!tryParseInt(inputParameter, bodyTemperature) || bodyTemperature[0] < 1 || bodyTemperature[0] > 100) {
                                System.out.print("Введите температуру тела: ");
                                inputParameter = br.readLine();
                            }
                            System.out.print("Введите тип чешуи: ");
                            String scaleType = br.readLine();
                            animals.add(new Reptile(binominalName, chosenYear[0], bodyTemperature[0], scaleType));
                            break;
                        case 5:
                            break;
                    }

                    break;
                case "2":
                    for (Animal animal : animals) {
                        System.out.print(animals.indexOf(animal) + ". ");
                        System.out.println(animal.getBinominalName());
                    }
                    String removeIndexInput = "";
                    int[] removeIndex = new int[1];
                    removeIndex[0] = -1;
                    while(((!tryParseInt(removeIndexInput, removeIndex) || removeIndex[0] < 0 || removeIndex[0] > animals.size() - 1)) && !removeIndexInput.equals("c")) {
                        System.out.println("Введите номер животного для удаления или c для выхода: ");
                        removeIndexInput = br.readLine();
                    }
                    if (removeIndexInput.equals("c"))
                        break;
                    animals.remove(removeIndex[0]);
                    break;
                case "3":
                    System.out.print("\n".repeat(50));
                    for (Animal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    System.out.println("Для продолжения нажмите Enter... ");
                    br.readLine();
                    break;
                case "4":
                    System.out.print("\n".repeat(50));
                    for (Animal animal : animals) {
                        System.out.print(animals.indexOf(animal) + ". ");
                        System.out.println(animal.getBinominalName());
                    }
                    String checkIndexInput = "";
                    int[] checkIndexFirst = new int[1];
                    checkIndexFirst[0] = -1;
                    while(((!tryParseInt(checkIndexInput, checkIndexFirst) || checkIndexFirst[0] < 0 || checkIndexFirst[0] > animals.size() - 1)) && !checkIndexInput.equals("c")) {
                        System.out.println("Введите номер первого животного для сравнение или c для выхода: ");
                        checkIndexInput = br.readLine();
                    }
                    if (checkIndexInput.equals("c"))
                        break;
                    checkIndexInput = "";
                    int[] checkIndexSecond = new int[1];
                    while(((!tryParseInt(checkIndexInput, checkIndexSecond) || checkIndexSecond[0] < 0 || checkIndexSecond[0] > animals.size() - 1)) && !checkIndexInput.equals("c")) {
                        System.out.println("Введите номер второго животного для сравнение или c для выхода: ");
                        checkIndexInput = br.readLine();
                    }
                    if (checkIndexInput.equals("c"))
                        break;
                    if (animals.get(checkIndexFirst[0]).equals(animals.get(checkIndexSecond[0]))) {
                        System.out.println("Выбранные элементы равны ");
                    } else {
                        System.out.println("Выбранные элементы не равны");
                    }
                    System.out.println("Для продолжения нажмите Enter...");
                    br.readLine();
                    break;
                case "5":
                    return;
            }
        }
    }
}
