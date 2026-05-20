import java.util.Objects;

/**
 * Класс для описания рептилий.
 * Включает в себя температуру тела и тип чешуи.
 */
class Reptile extends Animal {
  int bodyTemperature;
  String scaleType;

  /**
   * Конструктор по умолчанию.
   * Создает рептилию без данных.
   */
  public Reptile() {
    super();
  }

  /**
   * Конструктор с параметрами.
   *
   * @param binominalName международное название
   * @param nameTakenYear в каком году принято название
   * @param bodyTemperature температура тела
   * @param scaleType тип чешуи
   */
  public Reptile(String binominalName, int nameTakenYear, int bodyTemperature, String scaleType) {
    super(binominalName, nameTakenYear);
    this.bodyTemperature = bodyTemperature;
    this.scaleType = scaleType;
  }

  /**
   * Возвращает температуру тела.
   *
   * @return температура тела
   */
  public int getBodyTemperature() {
    return bodyTemperature;
  }

  /**
   * Устанавливает температуру тела.
   *
   * @param bodyTemperature температура тела
   */
  public void setBodyTemperature(int bodyTemperature) {
    this.bodyTemperature = bodyTemperature;
  }

  /**
   * Возвращает тип чешуи.
   *
   * @return тип чешуи
   */
  public String getScaleType() {
    return scaleType;
  }

  /**
   * Устанавливает тип чешуи.
   *
   * @param scaleType тип чешуи
   */
  public void setScaleType(String scaleType) {
    this.scaleType = scaleType;
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
    Reptile reptile = (Reptile) o;
    return bodyTemperature == reptile.bodyTemperature
      && Objects.equals(scaleType, reptile.scaleType);
  }

  /**
   * Возвращает хэш для объекта.
   *
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), bodyTemperature, scaleType);
  }

  /**
   * Возвращает строковое представление объекта.
   *
   * @return строка с информацией об объекте
   */
  @Override
  public String toString() {
    return "Reptile{"
        + "binominalName='" + binominalName + "\'"
        + ", nameTakenYear=" + nameTakenYear.getYear()
        + ", bodyTemperature=" + bodyTemperature
        + ", scaleType='" + scaleType + "\'}";
  }
}