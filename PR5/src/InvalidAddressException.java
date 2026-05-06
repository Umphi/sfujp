/**
 * Исключение, выбрасываемое при некорректном адресе здания.
 * Адрес считается некорректным, если он null, пустой или содержит недопустимые символы.
 */
public class InvalidAddressException extends Exception {
    
    public InvalidAddressException(String message) {
        super(message);
    }
    
    public InvalidAddressException(String message, Throwable cause) {
        super(message, cause);
    }
}