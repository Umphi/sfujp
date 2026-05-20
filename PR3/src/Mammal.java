import java.util.Objects;

/**
 * Класс описания млекопитающих.
 * Включает в себя средний пульс и тип шерсти.
 */
class Mammal extends Animal {
  int averageHeartRate;
  String furType;

  /**
   * Конструктор по умолчанию.
   * Создает млекопитающее без данных.
   */
  public Mammal() {
    super();
  }

  /**
   * Конструктор с параметрами.
   *
   * @param binominalName международное название
   * @param nameTakenYear в каком году принято название
   * @param averageHeartRate средний пульс
   * @param furType тип шерсти
   */
  public Mammal(String binominalName, int nameTakenYear, int averageHeartRate, String furType) {
    super(binominalName, nameTakenYear);
    this.averageHeartRate = averageHeartRate;
    this.furType = furType;
  }

  /**
   * Возвращает средний пульс.
   *
   * @return средний пульс
   */
  public int getAverageHeartRate() {
    return averageHeartRate;
  }

  /**
   * Устанавливает средний пульс.
   *
   * @param averageHeartRate средний пульс
   */
  public void setAverageHeartRate(int averageHeartRate) {
    this.averageHeartRate = averageHeartRate;
  }

  /**
   * Возвращает тип шерсти.
   *
   * @return тип шерсти
   */
  public String getFurType() {
    return furType;
  }

  /**
   * Устанавливает тип шерсти.
   *
   * @param furType тип шерсти
   */
  public void setFurType(String furType) {
    this.furType = furType;
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
    Mammal mammal = (Mammal) o;
    return averageHeartRate == mammal.averageHeartRate
      && Objects.equals(furType, mammal.furType);
  }

  /**
   * Возвращает хэш для объекта.
   *
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), averageHeartRate, furType);
  }

  /**
   * Возвращает строковое представление объекта.
   *
   * @return строка с информацией об объекте
   */
  @Override
  public String toString() {
    return "Mammal{"
        + "binominalName='" + binominalName + "\'"
        + ", nameTakenYear=" + nameTakenYear.getYear()
        + ", averageHeartRate=" + averageHeartRate
        + ", furType='" + furType + "\'}";
  }
}
