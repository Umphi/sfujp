
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Главный класс приложения.
 * Демонстранция работы с классом Building
 */
public class Main {
  /**
   * Главный метод приложения.
   * 
   * @param args аргументы командной строки (не используются)
   */
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Building> buildings = new ArrayList<>();

    while (true) {
      System.out.print("\n".repeat(50));
      System.out.println("Практическая работа 2");
      System.out.println("В программу внесено %d построек".formatted(buildings.size()));
      System.out.print(
          """
            1. Добавить постройку без данных
            2. Добавить постройку с внесением параметров
            3. Редактирование данных
            4. Вывод информации о постройках
            5. Сортировка
            6. Выход
          """
      );

      String input = " ";
      while ("123456".indexOf(input.charAt(0)) == -1) {
        input = br.readLine() + " ";
      }

      switch (input.charAt(0)) {
        case '1':
          buildings.add(new Building());
          break;
        case '2':
          System.out.print("Введите адрес: ");
          final String address = br.readLine();
          int year = 0;
          boolean check = false;
          while (!check) {
            System.out.print("Введите год постройки: ");
            try {
              year = Integer.parseInt(br.readLine());
              if (year > 1500 && year <= LocalDate.now().plusYears(5).getYear()) {
                check = true;
              }
            } catch (NumberFormatException e) {
              System.out.println("Неверный ввод, попробуйте еще раз");
            }
          }
          int numberOfFloors = 0;
          check = false;
          while (!check) {
            System.out.print("Введите количество этажей в здании: ");
            try {
              numberOfFloors = Integer.parseInt(br.readLine());
              if (numberOfFloors >= 1 && numberOfFloors <= 150) {
                check = true;
              }
            } catch (NumberFormatException e) {
              System.out.println("Неверный ввод, попробуйте еще раз");
            }
          }
          System.out.print("Введите тип постройки: ");
          final String typeOfBuilding = br.readLine();
          int numberOfRooms = 0;
          check = false;
          while (!check) {
            System.out.print("Введите количество помещений в здании: ");
            try {
              numberOfRooms = Integer.parseInt(br.readLine());
              if (numberOfRooms >= 1 && numberOfRooms <= 10000) {
                check = true;
              }
            } catch (NumberFormatException e) {
              System.out.println("Неверный ввод, попробуйте еще раз");
            }
          }
          boolean isUnfit = false;
          check = false;
          while (!check) {
            System.out.print("Введите y, если здание признано аварийным, n - если не является: ");
            String unfitInput = br.readLine();
            switch (unfitInput) {
              case "y":
                isUnfit = true;
                check = true;
                break;
              case "b":
                isUnfit = false;
                check = true;
                break;
              default:
                System.out.println("Неверный ввод, попробуйте еще раз");
            }
          }
          float areaOfRooms = 0f;
          check = false;
          while (!check) {
            System.out.print("Введите общую площадь помещений в здании: ");
            try {
              areaOfRooms = Float.parseFloat(br.readLine());
              if (areaOfRooms >= 1f && areaOfRooms <= 1000000f) {
                check = true;
              }
            } catch (NumberFormatException e) {
              System.out.println("Неверный ввод, попробуйте еще раз");
            }
          }
          buildings.add(new Building(
              address, year, numberOfFloors, typeOfBuilding, numberOfRooms, isUnfit, areaOfRooms
          ));
          break;
        case '3':
          System.out.print("\n".repeat(50));
          for (int i = 0; i < buildings.size(); i++) {
            System.out.println("%d. %s".formatted(i, buildings.get(i).getAddress()));
          }
          check = false;
          int buildingIndex = -1;
          while (!check) {
            System.out.println("Введите номер постройки или оставьте поле пустым для выхода... ");
            try {
              String buildingInput = br.readLine();
              if (buildingInput.isBlank()) {
                break;
              }
              buildingIndex = Integer.parseInt(buildingInput);
              if (buildingIndex >= 0 && buildingIndex < buildings.size()) {
                check = true;
              }
            } catch (NumberFormatException e) {
              System.out.println("Неверный ввод... ");
            }
          }
          if (check) {
            Building building = buildings.get(buildingIndex);
            boolean checkStop = true;
            while (checkStop) {
              System.out.print("\n".repeat(50));
              System.out.println("1. Адрес: " + building.getAddress());
              System.out.println("2. Год постройки: " + building.getYearOfConstruction());
              System.out.println("3. Этажей: " + building.getNumberOfFloors());
              System.out.println("4. Тип постройки: " + building.getBuildingType());
              System.out.println("5. Помещений: " + building.getNumberOfRooms());
              System.out.println("6. Аварийность: " + (building.isUnfit() ? "+" : "-"));
              System.out.println("7. Общая площадь помещений: " + building.getAreaOfRooms());
              System.out.print("Введите номер параметра для редактирования или c для выхода: ");
              String chosenParam = ".";
              while (!chosenParam.isBlank() && "1234567c".indexOf(chosenParam.charAt(0)) == -1) {
                chosenParam = br.readLine();
              }
              switch (chosenParam) {
                case "1":
                  System.out.print("Новый адрес (текущий - %s): ".formatted(building.getAddress()));
                  building.setAddress(br.readLine());
                  break;
                case "2":
                  year = 0;
                  check = false;
                  while (!check) {
                    System.out.print("Введите год постройки: ");
                    try {
                      year = Integer.parseInt(br.readLine());
                      if (year > 1500 && year <= LocalDate.now().plusYears(5).getYear()) {
                        check = true;
                      }
                    } catch (NumberFormatException e) {
                      System.out.println("Неверный ввод, попробуйте еще раз");
                    }
                  }
                  building.setYearOfConstruction(year);
                  break;
                case "3":
                  numberOfFloors = 0;
                  check = false;
                  while (!check) {
                    System.out.print("Введите количество этажей в здании: ");
                    try {
                      numberOfFloors = Integer.parseInt(br.readLine());
                      if (numberOfFloors >= 1 && numberOfFloors <= 150) {
                        check = true;
                      }
                    } catch (NumberFormatException e) {
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
                  while (!check) {
                    System.out.print("Введите количество помещений в здании: ");
                    try {
                      numberOfRooms = Integer.parseInt(br.readLine());
                      if (numberOfRooms >= 1 && numberOfRooms <= 10000) {
                        check = true;
                      }
                    } catch (NumberFormatException e) {
                      System.out.println("Неверный ввод, попробуйте еще раз");
                    }
                  }
                  building.setNumberOfRooms(numberOfRooms);
                  break;
                case "6":
                  building.setIsUnfit(!building.isUnfit());
                  break;
                case "7":
                  areaOfRooms = 0f;
                  check = false;
                  while (!check) {
                    System.out.print("Введите общую площадь помещений в здании: ");
                    try {
                      areaOfRooms = Float.parseFloat(br.readLine());
                      if (areaOfRooms >= 1f && areaOfRooms <= 1000000f) {
                        check = true;
                      }
                    } catch (NumberFormatException e) {
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
          System.out.println("  №  |            адрес              | год постройки | этажи |"
                              + "     тип постройки     |помещений|аварийность|площадь помещений|"
                              + "средняя площадь каждого");
          for (int i = 0; i < buildings.size(); i++) {
            System.out.print(padOrTrim(i, 5) + "|");
            System.out.print(padOrTrim(buildings.get(i).getAddress(), 31) + "|");
            System.out.print(padOrTrim(buildings.get(i).getYearOfConstruction(), 15) + "|");
            System.out.print(padOrTrim(buildings.get(i).getNumberOfFloors(), 7) + "|");
            System.out.print(padOrTrim(buildings.get(i).getBuildingType(), 23) + "|");
            System.out.print(padOrTrim(buildings.get(i).getNumberOfRooms(), 9) + "|");
            System.out.print(buildings.get(i).isUnfit() ? "     +     |" : "     -     |");
            System.out.print(padOrTrim(buildings.get(i).getAreaOfRooms(), 17) + "|");
            System.out.print(padOrTrim(buildings.get(i).averageRoomSize(), 22) + "\n");
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
          
          switch (sortChoice) {
            case "1":
              buildings.sort((b1, b2) -> b1.getAddress().compareTo(b2.getAddress()));
              break;
            case "2":
              buildings.sort((b1, b2) ->
                  Integer.compare(b1.getYearOfConstruction(), b2.getYearOfConstruction())
              );
              break;
            case "3":
              buildings.sort((b1, b2) ->
                  Integer.compare(b1.getNumberOfFloors(), b2.getNumberOfFloors())
              );
              break;
            case "4":
              buildings.sort((b1, b2) -> b1.getBuildingType().compareTo(b2.getBuildingType()));
              break;
            case "5":
              buildings.sort((b1, b2) ->
                  Integer.compare(b1.getNumberOfRooms(), b2.getNumberOfRooms())
              );
              break;
            case "6":
              buildings.sort((b1, b2) -> Boolean.compare(b1.isUnfit(), b2.isUnfit()));
              break;
            case "7":
              buildings.sort((b1, b2) -> Float.compare(b1.getAreaOfRooms(), b2.getAreaOfRooms()));
              break;
            case "8":
              buildings.sort((b1, b2) -> Float.compare(b1.averageRoomSize(), b2.averageRoomSize()));
              break;
            default:
              System.out.println("Неверный выбор!");
          }
          break;
        case '6':
          return;
        default:
          break;
      }
    }
  }

  /**
   * Выводит строку строго определенной длины.
   * 
   * @param input текст для вывода
   * @param length длина строки
   */
  public static String padOrTrim(String input, int length) {
    if (input.length() >= length) {
      return input.substring(0, length - 1);
    }
    return input + " ".repeat(length - input.length());
  }

  /**
   * Выводит строку строго определенной длины из int.
   * 
   * @param iinput число для вывода
   * @param length длина строки
   */
  public static String padOrTrim(Integer iinput, int length) {
    String input = iinput.toString();
    if (input.length() >= length) {
      return input.substring(0, length - 1);
    }
    return input + " ".repeat(length - input.length());
  }

  /**
   * Выводит строку строго определенной длины из float.
   * 
   * @param finput число для вывода
   * @param length длина строки
   */
  public static String padOrTrim(Float finput, int length) {
    String input = finput.toString();
    if (input.length() >= length) {
      return input.substring(0, length - 1);
    }
    return input + " ".repeat(length - input.length());
  }
}
