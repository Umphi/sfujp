import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Демонстрационное приложение для работы с обобщённым двунаправленным списком.
 * Поддерживает работу как со ссылочными (String), так и с примитивными (int) типами.
 */
public class Main {
    
    private static DoubleLinkedList<String> stringList;
    private static DoubleLinkedList<Integer> integerList;
    private static boolean usingString = true;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {
        stringList = new DoubleLinkedList<>();
        integerList = new DoubleLinkedList<>();
        
        System.out.println("Демонстрация обобщённого двунаправленного списка");
        
        while (true) {
            printMainMenu();
            int choice = readIntInput("Выберите действие: ", 0, 4);
            
            switch (choice) {
                case 1:
                    selectDataType();
                    break;
                case 2:
                    performListOperations();
                    break;
                case 3:
                    if (usingString) {
                        showListInfo(stringList);
                    } else {
                        showListInfo(integerList);
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
    
    /**
     * Выводит главное меню.
     */
    private static void printMainMenu() {
        System.out.println("\n1. Выбрать тип данных (текущий: " + (usingString ? "String" : "Integer") + ")");
        System.out.println("2. Операции со списком");
        System.out.println("3. Показать текущее состояние списка");
        System.out.println("4. Выход");
    }
    
    /**
     * Меню выбора типа данных.
     */
    private static void selectDataType() throws IOException {
        System.out.println("1. String (ссылочный тип)");
        System.out.println("2. int (примитивный, через Integer)");
        
        int choice = readIntInput("Выберите тип: ", 1, 2);
        
        usingString = (choice == 1);
        System.out.println("\nТип данных изменён на: " + (usingString ? "String" : "Integer"));
    }
    
    /**
     * Выполняет операции со списком.
     */
    private static void performListOperations() throws IOException {
        boolean back = false;
        while (!back) {
            printOperationsMenu();
            int choice = readIntInput("Выберите операцию: ", 0, 12);
            
            try {
                if (usingString) {
                    back = executeOperationOnList(stringList, choice, true);
                } else {
                    back = executeOperationOnList(integerList, choice, false);
                }
            } catch (Exception e) {
                System.err.println("\nОшибка: " + e.getMessage());
            }
            
            if (!back && choice != 0) {
                if (usingString) {
                    System.out.println(stringList.toString());
                } else {
                    System.out.println(integerList.toString());
                }
            }
        }
    }
    
    /**
     * Выводит меню операций над списком.
     */
    private static void printOperationsMenu() {
        System.out.println("Операции над списком: ");
        System.out.println("1. Проверить, пуст ли список");
        System.out.println("2. Установить указатель в начало");
        System.out.println("3. Установить указатель в конец");
        System.out.println("4. Добавить элемент ПОСЛЕ указателя");
        System.out.println("5. Добавить элемент ДО указателя");
        System.out.println("6. Удалить элемент ПОСЛЕ указателя");
        System.out.println("7. Удалить элемент ДО указателя");
        System.out.println("8. Переместить указатель ВПРАВО");
        System.out.println("9. Переместить указатель ВЛЕВО");
        System.out.println("10. Обменять значения ДО и ПОСЛЕ указателя");
        System.out.println("11. Показать текущий элемент");
        System.out.println("12. Очистить список");
        System.out.println("0. Вернуться в главное меню");
    }
    
    /**
     * Выполняет операцию над конкретным списком.
     * 
     * @param list список для операций
     * @param choice номер операции
     * @param isString true если работаем со строками
     * @return true если нужно вернуться в главное меню
     */
    private static <T> boolean executeOperationOnList(DoubleLinkedList<T> list, int choice, boolean isString) throws IOException {
        System.out.println();
        
        switch (choice) {
            case 0:
                return true;
            case 1:
                if (list.isEmpty()) {
                    System.out.println("Список ПУСТ");
                } else {
                    System.out.println("Список НЕ ПУСТ (содержит " + list.size() + ")");
                }
                break;
            case 2:
                try {
                    list.moveToFirst();
                    System.out.println("Указатель установлен в НАЧАЛО списка");
                    System.out.println("Текущий элемент: " + list.getCurrent());
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                try {
                    list.moveToLast();
                    System.out.println("Указатель установлен в КОНЕЦ списка");
                    System.out.println("Текущий элемент: " + list.getCurrent());
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                T afterElement = readElement(isString, "введите элемент для добавления ПОСЛЕ указателя");
                list.addAfterCurrent(afterElement);
                System.out.println("Элемент \"" + afterElement + "\" добавлен ПОСЛЕ указателя");
                break;
            case 5:
                T beforeElement = readElement(isString, "введите элемент для добавления ДО указателя");
                list.addBeforeCurrent(beforeElement);
                System.out.println("Элемент \"" + beforeElement + "\" добавлен ДО указателя");
                break;
            case 6:
                try {
                    T removedAfter = list.removeAfterCurrent();
                    System.out.println("Удалён элемент ПОСЛЕ указателя: \"" + removedAfter + "\"");
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 7:
                try {
                    T removedBefore = list.removeBeforeCurrent();
                    System.out.println("Удалён элемент ДО указателя: \"" + removedBefore + "\"");
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 8:
                if (list.moveRight()) {
                    System.out.println("Указатель перемещён ВПРАВО");
                    System.out.println("Новый текущий элемент: " + list.getCurrent());
                } else {
                    System.out.println("Невозможно переместить указатель вправо (достигнут конец списка)");
                }
                break;
            case 9:
                if (list.moveLeft()) {
                    System.out.println("Указатель перемещён ВЛЕВО");
                    System.out.println("Новый текущий элемент: " + list.getCurrent());
                } else {
                    System.out.println("Невозможно переместить указатель влево (достигнуто начало списка)");
                }
                break; 
            case 10:
                if (list.swapBeforeAndAfter()) {
                    System.out.println("Значения элементов ДО и ПОСЛЕ указателя успешно поменяны местами");
                } else {
                    System.out.println("Невозможно выполнить обмен (нет элементов ДО или ПОСЛЕ указателя)");
                }
                break;
            case 11:
                try {
                    System.out.println("Информация об указателе:");
                    System.out.println("Текущий элемент: " + list.getCurrent());
                    
                    T next = list.getNext();
                    System.out.println("Следующий элемент: " + (next != null ? next : "нет (конец списка)"));
                    
                    T prev = list.getPrev();
                    System.out.println("Следующий элемент: " + (prev != null ? prev : "нет (начало списка)"));
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 12:
                list.clear();
                System.out.println("Список очищен");
                break;
            default:
                System.out.println("Неизвестная операция");
        }
        return false;
    }
    
    /**
     * Считывает элемент от пользователя.
     * 
     * @param isString true если нужна строка, false если число
     * @param prompt подсказка для ввода
     * @return введённый элемент
     */
    @SuppressWarnings("unchecked")
    private static <T> T readElement(boolean isString, String prompt) throws IOException {
        System.out.print("  → " + prompt + ": ");
        
        if (isString) {
            return (T) reader.readLine();
        } else {
            while (true) {
                try {
                    int value = Integer.parseInt(reader.readLine());
                    return (T) Integer.valueOf(value);
                } catch (NumberFormatException e) {
                    System.out.print("Некорректное число. Введите целое число: ");
                }
            }
        }
    }
    
    /**
     * Считывает целое число с проверкой диапазона.
     */
    private static int readIntInput(String prompt, int min, int max) throws IOException {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(reader.readLine());
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.print("Введите число от " + min + " до " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Некорректный ввод. Введите число: ");
            }
        }
    }
    
    /**
     * Показывает расширенную информацию о списке.
     */
    private static <T> void showListInfo(DoubleLinkedList<T> list) {
        System.out.println("Информация о списке: ");
        System.out.println("Содержимое: " + list.toString());
        System.out.println("Размер: " + list.size());
        System.out.println("Пуст: " + (list.isEmpty() ? "да" : "нет"));
        
        try {
            System.out.println("Текущий элемент: " + list.getCurrent());
            T next = list.getNext();
            if (next != null) {
                System.out.println("Следующий: " + next);
            }
            T prev = list.getPrev();
            if (prev != null) {
                System.out.println("Предыдущий: " + prev);
            }
        } catch (IllegalStateException e) {
            System.out.println("Текущий элемент не установлен");
        }
    }
}