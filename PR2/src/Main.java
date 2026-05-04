
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Building> buildings = new ArrayList<>();

        while (true) { 
            System.out.print("\n".repeat(50));
            System.out.print(("Практическая работа 2\nНа данный момент в программу внесено %d построек\n 1. Добавить постройку без данных \n 2. Добавить постройку с внесением параметров \n 3. Редактирование данных \n 4. Вывод информации о постройках \n 5. Сортировка \n 6. Выход\n").formatted(buildings.size()));

            String input = " ";
            while ("123456".indexOf(input.charAt(0)) == -1)
            {
                input = br.readLine() + " ";
            }

            switch (input.charAt(0)) 
            {
                case '1':
                    buildings.add(new Building());
                    break;
                case '2':
                    System.out.print("Введите адрес: ");
                    String address = br.readLine();
                    int year = 0;
                    boolean check = false;
                    while(!check)
                    {
                        System.out.print("Введите год постройки: ");
                        try
                        {
                            year = Integer.parseInt(br.readLine());
                            if (year > 1500 && year <= LocalDate.now().plusYears(5).getYear())
                            {
                                check = true;
                            }
                        }
                        catch (NumberFormatException _) 
                        {
                            System.out.println("Неверный ввод, попробуйте еще раз");
                        }
                    }
                    int numberOfFloors = 0;
                    check = false;
                    while (!check)
                    {
                        System.out.print("Введите количество этажей в здании: ");
                        try
                        {
                            numberOfFloors = Integer.parseInt(br.readLine());
                            if (numberOfFloors >= 1 && numberOfFloors <= 150)
                            {
                                check = true;
                            }
                        }
                        catch (NumberFormatException _)
                        {
                            System.out.println("Неверный ввод, попробуйте еще раз");
                        }
                    }
                    System.out.print("Введите тип постройки: ");
                    String typeOfBuilding = br.readLine();
                    int numberOfRooms = 0;
                    check = false;
                    while (!check)
                    {
                        System.out.print("Введите количество помещений в здании: ");
                        try
                        {
                            numberOfRooms = Integer.parseInt(br.readLine());
                            if (numberOfRooms >= 1 && numberOfRooms <= 10000)
                            {
                                check = true;
                            }
                        }
                        catch (NumberFormatException _)
                        {
                            System.out.println("Неверный ввод, попробуйте еще раз");
                        }
                    }
                    boolean isUnfit = false;
                    check = false;
                    while (!check)
                    {
                        System.out.print("Введите y, если здание признано аварийным, n - если не является аварийным: ");
                        String unfitInput = br.readLine();
                        if (unfitInput.equals("y"))
                        {
                            isUnfit = true;
                            check = true;
                        }
                        else if (unfitInput.equals("n"))
                        {
                            isUnfit = false;
                            check = true;
                        }
                        else
                        {
                            System.out.println("Неверный ввод, попробуйте еще раз");
                        }
                    }
                    float areaOfRooms = 0f;
                    check = false;
                    while (!check)
                    {
                        System.out.print("Введите общую площадь помещений в здании: ");
                        try
                        {
                            areaOfRooms = Float.parseFloat(br.readLine());
                            if (areaOfRooms >= 1f && areaOfRooms <= 1000000f)
                            {
                                check = true;
                            }
                        }
                        catch (NumberFormatException _)
                        {
                            System.out.println("Неверный ввод, попробуйте еще раз");
                        }
                    }
                    buildings.add(new Building(address, year, numberOfFloors, typeOfBuilding, numberOfRooms, isUnfit, areaOfRooms));
                    break;
                case '3':
                    System.out.print("\n".repeat(50));
                    for (Building building : buildings) {
                        System.out.println("%d. %s".formatted(buildings.indexOf(building), building.getAddress()));
                    }
                    check = false;
                    int buildingIndex = -1;
                    while (!check)
                    {
                        System.out.println("Введите номер постройки для редактирования или оставьте поле пустым для выхода... ");
                        try
                        {
                            String buildingInput = br.readLine();
                            if (buildingInput.isBlank())
                            {
                                break;
                            }
                            buildingIndex = Integer.parseInt(buildingInput);
                            if (buildingIndex >= 0 && buildingIndex < buildings.size())
                            {
                                check = true;
                            }
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Неверный ввод... ");
                        }
                    }
                    if (check)
                    {
                        Building building = buildings.get(buildingIndex);
                        Boolean checkStop = true;
                        while (checkStop) {
                            System.out.print("\n".repeat(50));
                            System.out.println("1. Адрес: " + building.getAddress());
                            System.out.println("2. Год постройки: " + building.getYearOfConstructiion());
                            System.out.println("3. Этажей: " + building.getNumberOfFloors());
                            System.out.println("4. Тип постройки: " + building.getBuildingType());
                            System.out.println("5. Помещений: " + building.getNumberOfRooms());
                            System.out.println("6. Аварийность: " + (building.getIsUnfit() ? "+" : "-"));
                            System.out.println("7. Общая площадь помещений: " + building.getAreaOfRooms());
                            System.out.print("Введите номер параметра для редактирования или c для выхода: ");
                            String chosenParameter = ".";
                            while(!chosenParameter.isBlank() && "1234567c".indexOf(chosenParameter.charAt(0)) == -1)
                            {
                                chosenParameter = br.readLine();
                            }
                            switch (chosenParameter)
                            {
                                case "1":
                                    System.out.print("Введите новый адрес (текущий - %s): ".formatted(building.getAddress()));
                                    building.setAddress(br.readLine());
                                    break;
                                case "2":
                                    year = 0;
                                    check = false;
                                    while(!check)
                                    {
                                        System.out.print("Введите год постройки: ");
                                        try
                                        {
                                            year = Integer.parseInt(br.readLine());
                                            if (year > 1500 && year <= LocalDate.now().plusYears(5).getYear())
                                            {
                                                check = true;
                                            }
                                        }
                                        catch (NumberFormatException _) 
                                        {
                                            System.out.println("Неверный ввод, попробуйте еще раз");
                                        }
                                    }
                                    building.setYearOfConstruction(year);
                                    break;
                                case "3":
                                    numberOfFloors = 0;
                                    check = false;
                                    while (!check)
                                    {
                                        System.out.print("Введите количество этажей в здании: ");
                                        try
                                        {
                                            numberOfFloors = Integer.parseInt(br.readLine());
                                            if (numberOfFloors >= 1 && numberOfFloors <= 150)
                                            {
                                                check = true;
                                            }
                                        }
                                        catch (NumberFormatException _)
                                        {
                                            System.out.println("Неверный ввод, попробуйте еще раз");
                                        }
                                    }
                                    building.setNumberOfFloors(numberOfFloors);
                                    break;
                                case "4":
                                    System.out.print("Введите тип постройки: ");
                                    building.setBuildingType(br.readLine());
                                    break;
                                case "5":
                                    numberOfRooms = 0;
                                    check = false;
                                    while (!check)
                                    {
                                        System.out.print("Введите количество помещений в здании: ");
                                        try
                                        {
                                            numberOfRooms = Integer.parseInt(br.readLine());
                                            if (numberOfRooms >= 1 && numberOfRooms <= 10000)
                                            {
                                                check = true;
                                            }
                                        }
                                        catch (NumberFormatException _)
                                        {
                                            System.out.println("Неверный ввод, попробуйте еще раз");
                                        }
                                    }
                                    building.setNumberOfRooms(numberOfRooms);
                                    break;
                                case "6":
                                    building.setIsUnfit(!building.getIsUnfit());
                                    break;
                                case "7":
                                    areaOfRooms = 0f;
                                    check = false;
                                    while (!check)
                                    {
                                        System.out.print("Введите общую площадь помещений в здании: ");
                                        try
                                        {
                                            areaOfRooms = Float.parseFloat(br.readLine());
                                            if (areaOfRooms >= 1f && areaOfRooms <= 1000000f)
                                            {
                                                check = true;
                                            }
                                        }
                                        catch (NumberFormatException _)
                                        {
                                            System.out.println("Неверный ввод, попробуйте еще раз");
                                        }
                                    }
                                    building.setAreaOfRooms(areaOfRooms);
                                    break;
                                case "c":
                                    checkStop = false;

                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
                case '4':
                    System.out.print("\n".repeat(50));
                    System.out.println("  №  |            адрес              | год постройки | этажи |     тип постройки     |помещений|аварийность|площадь помещений|средняя площадь каждого");
                    for (Building building : buildings) {
                        System.out.print(substringOut(buildings.indexOf(building), 5) + "|");
                        System.out.print(substringOut(building.getAddress(), 31) + "|");
                        System.out.print(substringOut(building.getYearOfConstructiion(), 15) + "|");
                        System.out.print(substringOut(building.getNumberOfFloors(), 7) + "|");
                        System.out.print(substringOut(building.getBuildingType(), 23) + "|");
                        System.out.print(substringOut(building.getNumberOfRooms(), 9) + "|");
                        System.out.print(building.getIsUnfit() ? "     +     |" : "     -     |");
                        System.out.print(substringOut(building.getAreaOfRooms(), 17) + "|");
                        System.out.print(substringOut(building.AverageRoomSize(), 22) + "\n");
                    }
                    System.out.println("Нажмите Enter для продолжения... ");
                    br.readLine();
                    break;
                case '5':
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
                            buildings.sort((b1, b2) -> Integer.compare(b1.getYearOfConstructiion(), b2.getYearOfConstructiion()));
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
                    break;
                case '6':
                    return;
            }
        }
    }

    public static String substringOut(String input, int length)
    {
        if (input.length() >= length)
        {
            return input.substring(0, length-1);
        }
        else
        {
            return input + " ".repeat(length-input.length());
        }
    }

    public static String substringOut(Integer iinput, int length)
    {
        String input = iinput.toString();
        if (input.length() >= length)
        {
            return input.substring(0, length-1);
        }
        else
        {
            return input + " ".repeat(length-input.length());
        }
    }

    public static String substringOut(Float finput, int length)
    {
        String input = finput.toString();
        if (input.length() >= length)
        {
            return input.substring(0, length-1);
        }
        else
        {
            return input + " ".repeat(length-input.length());
        }
    }
}
