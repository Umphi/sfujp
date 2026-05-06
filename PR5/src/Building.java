import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс, представляющий здание (постройку).
 * Содержит информацию об адресе, годе постройки, этажности, типе,
 * количестве помещений, аварийности и общей площади.
 */
public class Building {
    private String address;
    private LocalDate yearOfConstruction;
    private int numberOfFloors;
    private String buildingType;
    private int numberOfRooms;
    private boolean isUnfit;
    private float areaOfRooms;
    
    private static final Logger logger = Logger.getLogger(Building.class.getName());

    /**
     * Конструктор по умолчанию. Создает здание с параметрами по умолчанию.
     * @throws InvalidAddressException если адрес по умолчанию некорректен
     * @throws InvalidBuildingDataException если параметры по умолчанию некорректны
     */
    public Building() throws InvalidAddressException, InvalidBuildingDataException {
        this("Неизвестный адрес", LocalDate.now().getYear(), 1, "Постройка", 1, false, 500.0f);
        logger.info("Создана постройка с параметрами по умолчанию");
    }
    
    /**
     * Конструктор с параметрами.
     * @param address адрес здания
     * @param year год постройки
     * @param numberOfFloors количество этажей
     * @param buildingType тип постройки
     * @param numberOfRooms количество помещений
     * @param isUnfit статус аварийности
     * @param areaOfRooms общая площадь помещений
     * @throws InvalidAddressException если адрес некорректен
     * @throws InvalidBuildingDataException если числовые параметры некорректны
     */
    public Building(String address, int year, int numberOfFloors, String buildingType, 
                    int numberOfRooms, boolean isUnfit, float areaOfRooms) 
                    throws InvalidAddressException, InvalidBuildingDataException {
        setAddress(address);
        setYearOfConstruction(year);
        setNumberOfFloors(numberOfFloors);
        setBuildingType(buildingType);
        setNumberOfRooms(numberOfRooms);
        setIsUnfit(isUnfit);
        setAreaOfRooms(areaOfRooms);
        logger.info("Создана постройка с адресом: " + address);
    }

    /**
     * Возвращает адрес здания.
     * @return адрес
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Устанавливает адрес здания.
     * @param address новый адрес
     * @throws InvalidAddressException если адрес null, пустой или состоит только из пробелов
     */
    public void setAddress(String address) throws InvalidAddressException {
        if (address == null || address.trim().isEmpty()) {
            InvalidAddressException e = new InvalidAddressException("Адрес не может быть пустым или null");
            logger.warning("Попытка установить пустой адрес");
            throw e;
        }
        if (address.length() > 200) {
            InvalidAddressException e = new InvalidAddressException("Адрес не может превышать 200 символов");
            logger.warning("Попытка установить слишком длинный адрес: " + address.length() + " символов");
            throw e;
        }
        this.address = address;
    }

    /**
     * Возвращает год постройки.
     * @return год постройки
     */
    public int getYearOfConstruction() {
        return this.yearOfConstruction.getYear();
    }

    /**
     * Устанавливает год постройки.
     * @param year новый год
     * @throws InvalidBuildingDataException если год вне допустимого диапазона (1500 - текущий+5)
     */
    public void setYearOfConstruction(int year) throws InvalidBuildingDataException {
        int currentYear = LocalDate.now().getYear();
        int maxYear = currentYear + 5;
        
        if (year < 1500 || year > maxYear) {
            InvalidBuildingDataException e = new InvalidBuildingDataException(
                "Год должен быть от 1500 до " + maxYear, "year", year);
            logger.warning("Попытка установить некорректный год: " + year);
            throw e;
        }
        this.yearOfConstruction = LocalDate.of(year, 1, 1);
    }

    /**
     * Возвращает количество этажей.
     * @return количество этажей
     */
    public int getNumberOfFloors() {
        return this.numberOfFloors;
    }

    /**
     * Устанавливает количество этажей.
     * @param numberOfFloors новое количество этажей
     * @throws InvalidBuildingDataException если значение вне диапазона 1-150
     */
    public void setNumberOfFloors(int numberOfFloors) throws InvalidBuildingDataException {
        if (numberOfFloors < 1 || numberOfFloors > 150) {
            InvalidBuildingDataException e = new InvalidBuildingDataException(
                "Количество этажей должно быть от 1 до 150", "numberOfFloors", numberOfFloors);
            logger.warning("Попытка установить некорректное количество этажей: " + numberOfFloors);
            throw e;
        }
        try {
            if (this.numberOfFloors != 0) {
                this.areaOfRooms = this.areaOfRooms / this.numberOfFloors * numberOfFloors;
            }
            this.numberOfFloors = numberOfFloors;
        } catch (ArithmeticException e) {
            logger.log(Level.WARNING, "Ошибка при пересчете площади", e);
            throw new InvalidBuildingDataException("Ошибка при пересчете площади", e);
        }
    }

    /**
     * Возвращает тип постройки.
     * @return тип постройки
     */
    public String getBuildingType() {
        return this.buildingType;
    }

    /**
     * Устанавливает тип постройки.
     * @param type новый тип
     * @throws InvalidAddressException если тип null, пустой или слишком длинный
     */
    public void setBuildingType(String type) throws InvalidAddressException {
        if (type == null || type.trim().isEmpty()) {
            InvalidAddressException e = new InvalidAddressException("Тип постройки не может быть пустым");
            logger.warning("Попытка установить пустой тип постройки");
            throw e;
        }
        if (type.length() > 100) {
            InvalidAddressException e = new InvalidAddressException("Тип постройки не может превышать 100 символов");
            logger.warning("Попытка установить слишком длинный тип: " + type.length() + " символов");
            throw e;
        }
        this.buildingType = type;
    }

    /**
     * Возвращает количество помещений.
     * @return количество помещений
     */
    public int getNumberOfRooms() {
        return this.numberOfRooms;
    }

    /**
     * Устанавливает количество помещений.
     * @param numberOfRooms новое количество помещений
     * @throws InvalidBuildingDataException если значение вне диапазона 1-10000
     */
    public void setNumberOfRooms(int numberOfRooms) throws InvalidBuildingDataException {
        if (numberOfRooms < 1 || numberOfRooms > 10000) {
            InvalidBuildingDataException e = new InvalidBuildingDataException(
                "Количество помещений должно быть от 1 до 10000", "numberOfRooms", numberOfRooms);
            logger.warning("Попытка установить некорректное количество помещений: " + numberOfRooms);
            throw e;
        }
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Возвращает статус аварийности.
     * @return true если здание аварийное, false в противном случае
     */
    public boolean getIsUnfit() {
        return this.isUnfit;
    }

    /**
     * Устанавливает статус аварийности.
     * @param isUnfit новый статус аварийности
     */
    public void setIsUnfit(boolean isUnfit) {
        this.isUnfit = isUnfit;
        logger.fine("Статус аварийности изменен на: " + isUnfit);
    }

    /**
     * Возвращает общую площадь помещений.
     * @return площадь в квадратных единицах
     */
    public float getAreaOfRooms() {
        return this.areaOfRooms;
    }

    /**
     * Устанавливает общую площадь помещений.
     * @param areaOfRooms новая площадь
     * @throws InvalidBuildingDataException если значение вне диапазона 1-1000000
     */
    public void setAreaOfRooms(float areaOfRooms) throws InvalidBuildingDataException {
        if (areaOfRooms < 1f || areaOfRooms > 1000000f) {
            InvalidBuildingDataException e = new InvalidBuildingDataException(
                "Площадь должна быть от 1 до 1000000", "areaOfRooms", areaOfRooms);
            logger.warning("Попытка установить некорректную площадь: " + areaOfRooms);
            throw e;
        }
        this.areaOfRooms = areaOfRooms;
    }

    /**
     * Вычисляет среднюю площадь одного помещения.
     * @return средняя площадь помещения
     */
    public float AverageRoomSize() {
        assert numberOfRooms > 0 : "Количество помещений должно быть положительным";
        return this.areaOfRooms / this.numberOfRooms;
    }
    
    /**
     * Возвращает строковое представление здания.
     * @return строка с информацией о здании
     */
    @Override
    public String toString() {
        return String.format("Building{address='%s', year=%d, floors=%d, type='%s', rooms=%d, unfit=%s, area=%.2f}",
            address, getYearOfConstruction(), numberOfFloors, buildingType, numberOfRooms, isUnfit, areaOfRooms);
    }
}