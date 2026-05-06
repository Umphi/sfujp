/**
 * Исключение, выбрасываемое при некорректных параметрах здания.
 * Обрабатывает ошибки валидации числовых параметров (год, этажи, помещения, площадь).
 */
public class InvalidBuildingDataException extends Exception {
    
    private String parameterName;
    private Object invalidValue;
    
    public InvalidBuildingDataException(String message) {
        super(message);
    }
    
    public InvalidBuildingDataException(String message, String parameterName, Object invalidValue) {
        super(message);
        this.parameterName = parameterName;
        this.invalidValue = invalidValue;
    }
    
    public InvalidBuildingDataException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public String getParameterName() {
        return parameterName;
    }
    
    public Object getInvalidValue() {
        return invalidValue;
    }
}