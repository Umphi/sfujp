import java.util.Objects;

/**
 * Класс описания хищников.
 * Включает в себя силу укуса и стратегию охоты.
 */
class Carnivora extends Mammal {
  int biteForce;
  String huntingStrategy;

  /**
   * Конструктор по умолчанию.
   * Создает хищника без данных.
   */
  public Carnivora() {
    super();
  }

  /**
   * Конструктор с параметрами.
   *
   * @param binominalName международное название
   * @param nameTakenYear в каком году принято название
   * @param averageHeartRate средний пульс
   * @param furType тип шерсти
   * @param biteForce сила укуса
   * @param huntingStrategy стратегия охоты
   */
  public Carnivora(String binominalName, int nameTakenYear, int averageHeartRate, String furType,
                  int biteForce, String huntingStrategy) {
    super(binominalName, nameTakenYear, averageHeartRate, furType);
    this.biteForce = biteForce;
    this.huntingStrategy = huntingStrategy;
  }

  /**
   * Возвращает силу укуса.
   *
   * @return сила укуса
   */
  public int getBiteForce() {
    return biteForce;
  }

  /**
   * Устанавливает силу укуса.
   *
   * @param biteForce сила укуса
   */
  public void setBiteForce(int biteForce) {
    this.biteForce = biteForce;
  }

  /**
   * Возвращает стратегию охоты.
   *
   * @return стратегия охоты
   */
  public String getHuntingStrategy() {
    return huntingStrategy;
  }

  /**
   *  Устанавливает стратегию охоты.
   *
   * @param huntingStrategy стратегия охоты
   */
  public void setHuntingStrategy(String huntingStrategy) {
    this.huntingStrategy = huntingStrategy;
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
    Carnivora carnivora = (Carnivora) o;
    return biteForce == carnivora.biteForce
      && Objects.equals(huntingStrategy, carnivora.huntingStrategy);
  }

  /**
   * Возвращает хэш для объекта.
   *
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), biteForce, huntingStrategy);
  }

  /**
   * Возвращает строковое представление объекта.
   *
   * @return строка с информацией об объекте
   */
  @Override
  public String toString() {
    return "Carnivora{"
          + "binominalName='" + binominalName + "\'"
          + ", nameTakenYear=" + nameTakenYear.getYear()
          + ", averageHeartRate=" + averageHeartRate
          + ", furType='" + furType + "\'"
          + ", biteForce=" + biteForce
          + ", huntingStrategy='" + huntingStrategy + "\'}";
  }
}