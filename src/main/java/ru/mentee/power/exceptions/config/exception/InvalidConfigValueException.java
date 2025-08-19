package ru.mentee.power.exceptions.config.exception;

/**
 * Исключение, выбрасываемое при неверном формате значения в конфигурации.
 */
public class InvalidConfigValueException extends ConfigException {

  private final String key;
  private final String invalidValue;

  public InvalidConfigValueException(String message) {
    super(message);
    this.key = null;
    this.invalidValue = null;
  }

  public InvalidConfigValueException(String message, String key, String invalidValue) {
    super(message);
    this.key = key;
    this.invalidValue = invalidValue;
  }

  public InvalidConfigValueException(String message, String key, String invalidValue,
      Throwable cause) {
    super(message, cause);
    this.key = key;
    this.invalidValue = invalidValue;
  }

  public String getKey() {
    return key;
  }

  public String getInvalidValue() {
    return invalidValue;
  }
}