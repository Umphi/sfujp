import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.*;

/**
 * Главный класс приложения для управления информацией о зданиях.
 * Предоставляет интерфейс командной строки.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    
    static {
        configureLogger();
    }
    
    /**
     * Настраивает систему логирования.
     * Создает файловый обработчик для записи логов в файл app.log.
     */
    private static void configureLogger() {
        try {
            FileHandler fh = new FileHandler("app.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.ALL);
        } catch (Exception e) {
            System.err.println("Не удалось настроить логирование: " + e.getMessage());
        }
    }
    
    /**
     * Главный метод приложения.
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            ArrayList<Building> buildings = new ArrayList<>();
            
            while (true) { 
                System.out.print("\n".repeat(50));
                System.out.print(("Практическая работа 2\nНа данный момент в программу внесено %d построек\n" +
                    " 1. Добавить постройку без данных \n 2. Добавить постройку с внесением параметров \n" +
                    " 3. Редактирование данных \n 4. Вывод информации о постройках \n" +
                    " 5. Сортировка \n 6. Выход\n").formatted(buildings.size()));

                String input = " ";
                while ("123456".indexOf(input.charAt(0)) == -1) {
                    input = br.readLine() + " ";
                }

                switch (input.charAt(0)) {
                    case '1':
                        try {
                            buildings.add(new Building());
                            System.out.println("Постройка успешно добавлена!");
                        } catch (InvalidAddressException | InvalidBuildingDataException e) {
                            System.out.println("Ошибка при создании: " + e.getMessage());
                            logger.warning("Не удалось создать постройку по умолчанию: " + e.getMessage());
                        }
                        break;
                    case '2':
                        try {
                            System.out.print("Введите адрес: ");
                            String address = br.readLine();
                            
                            int year = readIntWithValidation(br, "Введите год постройки: ");
                            int numberOfFloors = readIntWithValidation(br, "Введите количество этажей в здании: ");
                            
                            System.out.print("Введите тип постройки: ");
                            String typeOfBuilding = br.readLine();
                            
                            int numberOfRooms = readIntWithValidation(br, "Введите количество помещений в здании: ");
                            
                            boolean isUnfit = readBooleanWithValidation(br, 
                                "Введите y, если здание признано аварийным, n - если не является аварийным: ");
                            
                            float areaOfRooms = readFloatWithValidation(br, "Введите общую площадь помещений в здании: ");
                            
                            try {
                                buildings.add(new Building(address, year, numberOfFloors, typeOfBuilding, 
                                                          numberOfRooms, isUnfit, areaOfRooms));
                                System.out.println("Постройка успешно добавлена!");
                            } catch (InvalidAddressException | InvalidBuildingDataException e) {
                                System.out.println("Ошибка создания постройки: " + e.getMessage());
                                if (e.getCause() != null) {
                                    System.out.println("Причина: " + e.getCause().getMessage());
                                }
                                logger.warning("Не удалось создать постройку: " + e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println("Непредвиденная ошибка: " + e.getMessage());
                            logger.severe("Критическая ошибка при создании: " + e.getMessage());
                        }
                        break;
                    case '3':
                        editBuilding(br, buildings);
                        break;
                    case '4':
                        displayBuildings(br, buildings);
                        break;
                    case '5':
                        sortBuildings(br, buildings);
                        break;
                    case '6':
                        return;
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка инициализации приложения: " + e.getMessage());
            logger.severe("Критическая ошибка: " + e.getMessage());
        }
    }
    
    /**
     * Читает целое число с валидацией.
     * @param br BufferedReader для чтения ввода
     * @param prompt сообщение-подсказка
     * @return валидное целое число
     * @throws Exception при ошибках ввода-вывода
     */
    private static int readIntWithValidation(BufferedReader br, String prompt) throws Exception {
        int result = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                result = Integer.parseInt(br.readLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте еще раз");
                logger.log(Level.FINE, "Ошибка парсинга числа", e);
            }
        }
        return result;
    }
    
    /**
     * Читает число с плавающей точкой с валидацией.
     * @param br BufferedReader для чтения ввода
     * @param prompt сообщение-подсказка
     * @return валидное число с плавающей точкой
     * @throws Exception при ошибках ввода-вывода
     */
    private static float readFloatWithValidation(BufferedReader br, String prompt) throws Exception {
        float result = 0f;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            try {
                result = Float.parseFloat(br.readLine());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод, попробуйте еще раз");
                logger.log(Level.FINE, "Ошибка парсинга числа с плавающей точкой", e);
            }
        }
        return result;
    }
    
    /**
     * Читает булево значение с валидацией.
     * @param br BufferedReader для чтения ввода
     * @param prompt сообщение-подсказка
     * @return true если введено 'y', false если 'n'
     * @throws Exception при ошибках ввода-вывода
     */
    private static boolean readBooleanWithValidation(BufferedReader br, String prompt) throws Exception {
        boolean result = false;
        boolean valid = false;
        while (!valid) {
            System.out.print(prompt);
            String input = br.readLine();
            if (input.equals("y")) {
                result = true;
                valid = true;
            } else if (input.equals("n")) {
                result = false;
                valid = true;
            } else {
                System.out.println("Неверный ввод, попробуйте еще раз");
            }
        }
        return result;
    }
    
    /**
     * Отображает список зданий в табличном формате.
     * @param br BufferedReader для ожидания ввода пользователя
     * @param buildings список зданий
     * @throws Exception при ошибках ввода-вывода
     */
    private static void displayBuildings(BufferedReader br, ArrayList<Building> buildings) throws Exception {
        System.out.print("\n".repeat(50));
        if (buildings.isEmpty()) {
            System.out.println("Список построек пуст.");
        } else {
            System.out.println("  №  |            адрес              | год постройки | этажи |     тип постройки     |помещений|аварийность|площадь помещений|средняя площадь каждого");
            for (Building building : buildings) {
                System.out.print(substringOut(String.valueOf(buildings.indexOf(building)), 5) + "|");
                System.out.print(substringOut(building.getAddress(), 31) + "|");
                System.out.print(substringOut(String.valueOf(building.getYearOfConstruction()), 15) + "|");
                System.out.print(substringOut(String.valueOf(building.getNumberOfFloors()), 7) + "|");
                System.out.print(substringOut(building.getBuildingType(), 23) + "|");
                System.out.print(substringOut(String.valueOf(building.getNumberOfRooms()), 9) + "|");
                System.out.print(building.getIsUnfit() ? "     +     |" : "     -     |");
                System.out.print(substringOut(String.valueOf(building.getAreaOfRooms()), 17) + "|");
                System.out.print(substringOut(String.format("%.2f", building.AverageRoomSize()), 22) + "\n");
            }
        }
        System.out.println("Нажмите Enter для продолжения... ");
        br.readLine();
    }
    
    /**
     * Редактирует выбранное здание.
     * @param br BufferedReader для чтения ввода
     * @param buildings список зданий
     * @throws Exception при ошибках ввода-вывода
     */
    private static void editBuilding(BufferedReader br, ArrayList<Building> buildings) throws Exception {
        if (buildings.isEmpty()) {
            System.out.println("Нет построек для редактирования.");
            System.out.println("Нажмите Enter для продолжения... ");
            br.readLine();
            return;
        }
        
        System.out.print("\n".repeat(50));
        for (int i = 0; i < buildings.size(); i++) {
            System.out.println(i + ". " + buildings.get(i).getAddress());
        }
        
        int buildingIndex = -1;
        boolean valid = false;
        while (!valid) {
            System.out.println("Введите номер постройки для редактирования или оставьте поле пустым для выхода... ");
            String buildingInput = br.readLine();
            if (buildingInput.isBlank()) {
                return;
            }
            try {
                buildingIndex = Integer.parseInt(buildingInput);
                if (buildingIndex >= 0 && buildingIndex < buildings.size()) {
                    valid = true;
                } else {
                    System.out.println("Номер вне диапазона... ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный ввод... ");
                logger.log(Level.FINE, "Ошибка выбора здания для редактирования", e);
            }
        }
        
        Building building = buildings.get(buildingIndex);
        boolean editing = true;
        while (editing) {
            System.out.print("\n".repeat(50));
            System.out.println("1. Адрес: " + building.getAddress());
            System.out.println("2. Год постройки: " + building.getYearOfConstruction());
            System.out.println("3. Этажей: " + building.getNumberOfFloors());
            System.out.println("4. Тип постройки: " + building.getBuildingType());
            System.out.println("5. Помещений: " + building.getNumberOfRooms());
            System.out.println("6. Аварийность: " + (building.getIsUnfit() ? "Да" : "Нет"));
            System.out.println("7. Общая площадь помещений: " + building.getAreaOfRooms());
            System.out.print("Введите номер параметра для редактирования или c для выхода: ");
            
            String choice = br.readLine();
            
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Введите новый адрес (текущий - %s): ".formatted(building.getAddress()));
                        building.setAddress(br.readLine());
                        break;
                    case "2":
                        int year = readIntWithValidation(br, "Введите год постройки: ");
                        building.setYearOfConstruction(year);
                        break;
                    case "3":
                        int floors = readIntWithValidation(br, "Введите количество этажей: ");
                        building.setNumberOfFloors(floors);
                        break;
                    case "4":
                        System.out.print("Введите тип постройки: ");
                        building.setBuildingType(br.readLine());
                        break;
                    case "5":
                        int rooms = readIntWithValidation(br, "Введите количество помещений: ");
                        building.setNumberOfRooms(rooms);
                        break;
                    case "6":
                        building.setIsUnfit(!building.getIsUnfit());
                        break;
                    case "7":
                        float area = readFloatWithValidation(br, "Введите общую площадь: ");
                        building.setAreaOfRooms(area);
                        break;
                    case "c":
                        editing = false;
                        break;
                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (InvalidAddressException | InvalidBuildingDataException e) {
                System.out.println("Ошибка редактирования: " + e.getMessage());
                logger.warning("Ошибка при редактировании: " + e.getMessage());
                System.out.println("Нажмите Enter для продолжения...");
                br.readLine();
            }
        }
    }
    
    /**
     * Сортирует список зданий по выбранному критерию.
     * @param br BufferedReader для чтения ввода
     * @param buildings список зданий
     * @throws Exception при ошибках ввода-вывода
     */
    private static void sortBuildings(BufferedReader br, ArrayList<Building> buildings) throws Exception {
        if (buildings.isEmpty()) {
            System.out.println("Нет построек для сортировки.");
            System.out.println("Нажмите Enter для продолжения... ");
            br.readLine();
            return;
        }
        
        System.out.print("\n".repeat(50));
        System.out.println("Выберите параметр по которому будет происходить сортировка:");
        System.out.println("1. По адресу");
        System.out.println("2. По году постройки");
        System.out.println("3. По количеству этажей");
        System.out.println("4. По типу постройки");
        System.out.println("5. По количеству помещений");
        System.out.println("6. По аварийности");
        System.out.println("7. По общей площади помещений");
        System.out.println("8. По средней площади помещения");
        String sortChoice = br.readLine();
        
        switch(sortChoice) {
            case "1":
                buildings.sort((b1, b2) -> b1.getAddress().compareTo(b2.getAddress()));
                break;
            case "2":
                buildings.sort((b1, b2) -> Integer.compare(b1.getYearOfConstruction(), b2.getYearOfConstruction()));
                break;
            case "3":
                buildings.sort((b1, b2) -> Integer.compare(b1.getNumberOfFloors(), b2.getNumberOfFloors()));
                break;
            case "4":
                buildings.sort((b1, b2) -> b1.getBuildingType().compareTo(b2.getBuildingType()));
                break;
            case "5":
                buildings.sort((b1, b2) -> Integer.compare(b1.getNumberOfRooms(), b2.getNumberOfRooms()));
                break;
            case "6":
                buildings.sort((b1, b2) -> Boolean.compare(b1.getIsUnfit(), b2.getIsUnfit()));
                break;
            case "7":
                buildings.sort((b1, b2) -> Float.compare(b1.getAreaOfRooms(), b2.getAreaOfRooms()));
                break;
            case "8":
                buildings.sort((b1, b2) -> Float.compare(b1.AverageRoomSize(), b2.AverageRoomSize()));
                break;
            default:
                System.out.println("Неверный выбор!");
        }
        System.out.println("Сортировка выполнена!");
    }
    
    /**
     * Форматирует строку для вывода в таблицу фиксированной ширины.
     * @param input входная строка
     * @param length требуемая длина
     * @return отформатированная строка
     */
    public static String substringOut(String input, int length) {
        if (input == null) {
            input = "";
        }
        if (input.length() >= length) {
            return input.substring(0, length - 1);
        } else {
            return input + " ".repeat(length - input.length());
        }
    }
}