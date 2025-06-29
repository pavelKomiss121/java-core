package ru.mentee.power.exceptions.config.exception;

/**
 * Исключение, выбрасываемое при отсутствии обязательного ключа конфигурации.
 */
public class MissingConfigKeyException extends ConfigException {
    String key;
    public MissingConfigKeyException(String message) {
        super(message);
        key = null;
    }
    public MissingConfigKeyException(String message, Throwable cause) {
        super(message, cause);
        key = null;
    }

    public MissingConfigKeyException(String message, String key) {
        super(message);
        this.key = key;
    }

    public MissingConfigKeyException(String message, Throwable cause, String key) {
        super(message, cause);
        this.key = key;
    }

    public String getKey() {
        return key;
    }


}