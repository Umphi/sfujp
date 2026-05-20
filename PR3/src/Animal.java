import java.time.LocalDate;
import java.util.Objects;

/**
 * Базовый класс описания животных.
 * Включает в себя международное название и год в который название принято.
 */
public class Animal {
  String binominalName;
  LocalDate nameTakenYear;

  /**
   * Конструктор по умолчанию.
   * Создает животное без данных.
   */
  public Animal() {
  }

  /**
   * Конструктор с параметрами.
   *
   * @param binominalName международное название
   * @param nameTakenYear в каком году принято название
   */
  public Animal(String binominalName, int nameTakenYear) {
    this.binominalName = binominalName;
    this.nameTakenYear = LocalDate.of(nameTakenYear, 1, 1);
  }

  /**
   * Возвращает международное название.
   *
   * @return международное название
   */
  public String getBinominalName() {
    return binominalName;
  }

  /**
   * Устанавливает международное название.
   *
   * @param binominalName международное название
   */
  public void setBinominalName(String binominalName) {
    this.binominalName = binominalName;
  }

  /**
   * Возвращает год принятия названия.
   *
   * @return год принятия названия
   */
  public int getNameTakenYear() {
    return nameTakenYear.getYear();
  }

  /**
   * Устанавливает год принятия названия.
   *
   * @param nameTakenYear год принятия названия
   */
  public void setNameTakenYear(int nameTakenYear) {
    this.nameTakenYear = LocalDate.of(nameTakenYear, 1, 1);
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
    Animal animal = (Animal) o;
    return Objects.equals(binominalName, animal.binominalName)
        && Objects.equals(nameTakenYear, animal.nameTakenYear);
  }

  /**
   * Возвращает хэш для объекта.
   *
   * @return хэш-код объекта
   */
  @Override
  public int hashCode() {
    return Objects.hash(binominalName, nameTakenYear);
  }

  /**
   * Возвращает строковое представление объекта.
   *
   * @return строка с информацией об объекте
   */
  @Override
  public String toString() {
    return "Animal{"
        + "binominalName='" + binominalName + "\'"
        + ", nameTakenYear=" + nameTakenYear.getYear() + "}";
  }
}


