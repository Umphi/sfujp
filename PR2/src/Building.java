import java.time.LocalDate;

/**
 * Класс, описывающий постройку.
 */
public class Building {
  private String address;
  private LocalDate yearOfConstruction;
  private int numberOfFloors;
  private String buildingType;
  private int numberOfRooms;
  private boolean isUnfit;
  private float areaOfRooms;

  /**
   * Конструктор по умолчанию для постройки.
   */
  public Building() {
    this.address = "";
    this.yearOfConstruction = LocalDate.of(LocalDate.now().getYear(), 1, 1);
    this.numberOfFloors = 1;
    this.buildingType = "Постройка";
    this.numberOfRooms = 1;
    this.isUnfit = false;
    this.areaOfRooms = 500.0f;
  }

  /**
   * Конструктор с параметрами для постройки.
   * 
   * @param address адрес здания
   * @param year год постройки
   * @param numberOfFloors количество этажей
   * @param buildingType тип постройки
   * @param numberOfRooms количество помещений
   * @param isUnfit статус аварийности
   * @param areaOfRooms общая площадь помещений
   */
  public Building(String address,
                  int year,
                  int numberOfFloors,
                  String buildingType,
                  int numberOfRooms,
                  boolean isUnfit,
                  float areaOfRooms
  ) {
    this.address = address;
    this.yearOfConstruction = LocalDate.of(year,  1, 1);
    this.numberOfFloors = numberOfFloors;
    this.buildingType = buildingType;
    this.numberOfRooms = numberOfRooms;
    this.isUnfit = isUnfit;
    this.areaOfRooms = areaOfRooms;
  }

  /**
   * Возвращает адрес здания.
   * 
   * @return адрес
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * Устанавливает адрес здания.
   * 
   * @param address новый адрес
   */
  public void setAddress(String address) {
    if (address.isBlank()) {
      return;
    }

    this.address = address;
  }

  /**
   * Возвращает год постройки.
   * 
   * @return год постройки
   */
  public int getYearOfConstruction() {
    return this.yearOfConstruction.getYear();
  }

  /**
   * Устанавливает год постройки.
   * 
   * @param year новый год
   */
  public void setYearOfConstruction(int year) {
    if (year <= 1500 || year > LocalDate.now().plusYears(5).getYear()) {
      return;
    }

    this.yearOfConstruction = LocalDate.of(year, 1, 1);
  }

  /**
   * Возвращает количество этажей.
   * 
   * @return количество этажей
   */
  public int getNumberOfFloors() {
    return this.numberOfFloors;
  }

  /**
   * Устанавливает количество этажей.
   * 
   * @param numberOfFloors новое количество этажей
   */
  public void setNumberOfFloors(int numberOfFloors) {
    if (numberOfFloors < 1 || numberOfFloors > 150) {
      return;
    }

    this.areaOfRooms = this.areaOfRooms / this.numberOfFloors * numberOfFloors;
    this.numberOfFloors = numberOfFloors;
  }

  /**
   * Возвращает тип постройки.
   * 
   * @return тип постройки
   */
  public String getBuildingType() {
    return this.buildingType;
  }

  /**
   * Устанавливает тип постройки.
   * 
   * @param buildingType новый тип
   */
  public void setBuildingType(String buildingType) {
    if (buildingType.isBlank()) {
      return;
    }

    this.buildingType = buildingType;
  }

  /**
   * Возвращает количество помещений.
   * 
   * @return количество помещений
   */
  public int getNumberOfRooms() {
    return this.numberOfRooms;
  }

  /**
   * Устанавливает количество помещений.
   * 
   * @param numberOfRooms новое количество помещений
   */
  public void setNumberOfRooms(int numberOfRooms) {
    if (numberOfRooms < 1 || numberOfRooms > 10000) {
      return;
    } 

    this.numberOfRooms = numberOfRooms;
  }

  /**
   * Возвращает статус аварийности.
   * 
   * @return true если здание аварийное, false в противном случае
   */
  public boolean isUnfit() {
    return this.isUnfit;
  }

  /**
   * Устанавливает статус аварийности.
   * 
   * @param isUnfit новый статус аварийности
   */
  public void setIsUnfit(boolean isUnfit) {
    this.isUnfit = isUnfit;
  }

  /**
   * Возвращает общую площадь помещений.
   * 
   * @return площадь в квадратных единицах
   */
  public float getAreaOfRooms() {
    return this.areaOfRooms;
  }

  /**
   * Устанавливает общую площадь помещений.
   * 
   * @param areaOfRooms новая площадь
   */
  public void setAreaOfRooms(float areaOfRooms) {
    if (areaOfRooms < 1f || areaOfRooms > 1000000f) {
      return;
    }

    this.areaOfRooms = areaOfRooms;
  }

  /**
   * Вычисляет среднюю площадь одного помещения.
   * 
   * @return средняя площадь помещения
   */
  public float averageRoomSize() {
    if (this.numberOfRooms == 0) {
      return 0;
    }

    return this.areaOfRooms / this.numberOfRooms;
  }
}
