import java.time.LocalDate;
import java.util.Objects;

public class Animal {
    String binominalName;
    LocalDate nameTakenYear;

    public Animal() {
    }

    public Animal(String binominalName, LocalDate nameTakenYear) {
        this.binominalName = binominalName;
        this.nameTakenYear = nameTakenYear;
    }

    public String getBinominalName() {
        return binominalName;
    }

    public void setBinominalName(String binominalName) {
        this.binominalName = binominalName;
    }

    public int getNameTakenYear() {
        return nameTakenYear.getYear();
    }

    public void setNameTakenYear(int nameTakenYear) {
        this.nameTakenYear = LocalDate.of(nameTakenYear, 1, 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(binominalName, animal.binominalName) &&
                Objects.equals(nameTakenYear, animal.nameTakenYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(binominalName, nameTakenYear);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "binominalName='" + binominalName + '\'' +
                ", nameTakenYear=" + nameTakenYear +
                '}';
    }
}

class Fish extends Animal {
    int finCount;
    String waterType;

    public Fish() {
        super();
    }

    public Fish(String binominalName, int nameTakenYear, int finCount, String waterType) {
        super(binominalName, LocalDate.of(nameTakenYear, 1, 1));
        this.finCount = finCount;
        this.waterType = waterType;
    }

    public int getFinCount() {
        return finCount;
    }

    public void setFinCount(int finCount) {
        this.finCount = finCount;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fish fish = (Fish) o;
        return finCount == fish.finCount &&
                Objects.equals(waterType, fish.waterType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), finCount, waterType);
    }

    @Override
    public String toString() {
        return "Fish{" +
                "binominalName='" + binominalName + '\'' +
                ", nameTakenYear=" + nameTakenYear +
                ", finCount=" + finCount +
                ", waterType='" + waterType + '\'' +
                '}';
    }
}

class Reptile extends Animal {
    int bodyTemperature;
    String scaleType;

    public Reptile() {
        super();
    }

    public Reptile(String binominalName, int nameTakenYear, int bodyTemperature, String scaleType) {
        super(binominalName, LocalDate.of(nameTakenYear, 1, 1));
        this.bodyTemperature = bodyTemperature;
        this.scaleType = scaleType;
    }

    public int getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(int bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public String getScaleType() {
        return scaleType;
    }

    public void setScaleType(String scaleType) {
        this.scaleType = scaleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Reptile reptile = (Reptile) o;
        return bodyTemperature == reptile.bodyTemperature &&
                Objects.equals(scaleType, reptile.scaleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bodyTemperature, scaleType);
    }

    @Override
    public String toString() {
        return "Reptile{" +
                "binominalName='" + binominalName + '\'' +
                ", nameTakenYear=" + nameTakenYear +
                ", bodyTemperature=" + bodyTemperature +
                ", scaleType='" + scaleType + '\'' +
                '}';
    }
}

class Mammal extends Animal {
    int averageHeartRate;
    String furType;

    public Mammal() {
        super();
    }

    public Mammal(String binominalName, int nameTakenYear, int averageHeartRate, String furType) {
        super(binominalName, LocalDate.of(nameTakenYear, 1, 1));
        this.averageHeartRate = averageHeartRate;
        this.furType = furType;
    }

    public int getAverageHeartRate() {
        return averageHeartRate;
    }

    public void setAverageHeartRate(int averageHeartRate) {
        this.averageHeartRate = averageHeartRate;
    }

    public String getFurType() {
        return furType;
    }

    public void setFurType(String furType) {
        this.furType = furType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Mammal mammal = (Mammal) o;
        return averageHeartRate == mammal.averageHeartRate &&
                Objects.equals(furType, mammal.furType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), averageHeartRate, furType);
    }

    @Override
    public String toString() {
        return "Mammal{" +
                "binominalName='" + binominalName + '\'' +
                ", nameTakenYear=" + nameTakenYear +
                ", averageHeartRate=" + averageHeartRate +
                ", furType='" + furType + '\'' +
                '}';
    }
}

class Carnivora extends Mammal {
    int biteForce;
    String huntingStrategy;

    public Carnivora() {
        super();
    }

    public Carnivora(String binominalName, int nameTakenYear, int averageHeartRate, String furType,
                     int biteForce, String huntingStrategy) {
        super(binominalName, nameTakenYear, averageHeartRate, furType);
        this.biteForce = biteForce;
        this.huntingStrategy = huntingStrategy;
    }

    public int getBiteForce() {
        return biteForce;
    }

    public void setBiteForce(int biteForce) {
        this.biteForce = biteForce;
    }

    public String getHuntingStrategy() {
        return huntingStrategy;
    }

    public void setHuntingStrategy(String huntingStrategy) {
        this.huntingStrategy = huntingStrategy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Carnivora carnivora = (Carnivora) o;
        return biteForce == carnivora.biteForce &&
                Objects.equals(huntingStrategy, carnivora.huntingStrategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), biteForce, huntingStrategy);
    }

    @Override
    public String toString() {
        return "Carnivora{" +
                "binominalName='" + binominalName + '\'' +
                ", nameTakenYear=" + nameTakenYear +
                ", averageHeartRate=" + averageHeartRate +
                ", furType='" + furType + '\'' +
                ", biteForce=" + biteForce +
                ", huntingStrategy='" + huntingStrategy + '\'' +
                '}';
    }
}