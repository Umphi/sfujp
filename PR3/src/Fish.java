import java.util.Objects;

/**
 * Класс для описания рыб, наследующий Animal
 * Включает в себя помимо базовых полей, количество плавников и тип воды.
 */
class Fish extends Animal {
  int finCount;
  String waterType;

  /**
   * Конструктор по умолчанию.
   * Создает рыбу без данных.
   */
  public Fish() {
    super();
  }

  /**
   * Конструктор с параметрами.
   *
   * @param binominalName международное название
   * @param nameTakenYear в каком году принято название
   * @param finCount количество плавников
   * @param waterType тип воды
   */
  public Fish(String binominalName, int nameTakenYear, int finCount, String waterType) {
    super(binominalName, nameTakenYear);
    this.finCount = finCount;
    this.waterType = waterType;
  }

  /**
   * Возвращает количество плавников.
   *
   * @return количество плавников
   */
  public int getFinCount() {
    return finCount;
  }

  /**
   * Устанавливает количество плавников.
   *
   * @param finCount количество плавников
   */
  public void setFinCount(int finCount) {
    this.finCount = finCount;
  }

  /**
   * Возвращает тип воды.
   *
   * @return тип воды
   */
  public String getWaterType() {
    return waterType;
  }

  /**
   * Устанавливает тип воды.
   *
   * @param waterType тип воды
   */
  public void setWaterType(String waterType) {
    this.waterType = waterType;
  }

  /**
   * Производит сравнение между объектами.
   *
   * @param o объект с которым производится сравнение
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Fish fish = (Fish) o;
    return finCount == fish.finCount
        && Objects.equals(waterType, fish.waterType);
  }

  /**
   * Возвращает хэш для объекта.
   *
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), finCount, waterType);
  }

  /**
   * Возвращает строковое представление объекта.
   *
   * @return строка с информацией об объекте
   */
  @Override
  public String toString() {
    return "Fish{"
            + "binominalName='" + binominalName + "\'"
            + ", nameTakenYear=" + nameTakenYear.getYear()
            + ", finCount=" + finCount
            + ", waterType='" + waterType + "\'}";
  }
}