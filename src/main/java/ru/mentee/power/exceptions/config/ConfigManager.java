package ru.mentee.power.exceptions.config;

import java.util.HashMap;
import java.util.Map;
import ru.mentee.power.exceptions.config.exception.InvalidConfigValueException;
import ru.mentee.power.exceptions.config.exception.MissingConfigKeyException;

/**
 * Класс для работы с конфигурационными параметрами.
 */
public class ConfigManager {

  private final Map<String, String> config;

  /**
   * Создает новый менеджер конфигурации с указанной картой параметров.
   *
   * @param config Карта конфигурационных параметров
   */
  public ConfigManager(Map<String, String> config) {
    this.config = new HashMap<>(config);
  }

  /**
   * Создает новый менеджер конфигурации с пустой картой параметров.
   */
  public ConfigManager() {
    this.config = new HashMap<>();
  }

  /**
   * Получает значение по ключу, выбрасывая исключение, если ключ не найден.
   *
   * @param key Ключ для поиска.
   * @return Значение параметра.
   * @throws MissingConfigKeyException Если ключ отсутствует в конфигурации.
   */
  public String getRequiredValue(String key) throws MissingConfigKeyException {
    if (!config.containsKey(key)) {
      throw new MissingConfigKeyException("Ключ '" + key + "' не найден!");
    }
    return config.get(key);
  }

  /**
   * Получает числовое значение по ключу, выбрасывая исключение, если значение не является числом.
   *
   * @param key Ключ для поиска.
   * @return Числовое значение параметра.
   * @throws MissingConfigKeyException   Если ключ отсутствует в конфигурации.
   * @throws InvalidConfigValueException Если значение не является числом.
   */
  public int getRequiredIntValue(String key)
      throws MissingConfigKeyException, InvalidConfigValueException {
    String value = getRequiredValue(key);
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      throw new InvalidConfigValueException(
          "Значение по ключу '" + key + "' не является числом: " + value);
    }
  }

  /**
   * Получает булево значение по ключу, выбрасывая исключение, если значение не является булевым.
   *
   * @param key Ключ для поиска.
   * @return Булево значение параметра.
   * @throws MissingConfigKeyException   Если ключ отсутствует в конфигурации.
   * @throws InvalidConfigValueException Если значение не является булевым.
   */
  public boolean getRequiredBooleanValue(String key)
      throws MissingConfigKeyException, InvalidConfigValueException {
    String value = getRequiredValue(key);
    if (value.equalsIgnoreCase("true")) {
      return true;
    }
    if (value.equalsIgnoreCase("false")) {
      return false;
    }
    throw new InvalidConfigValueException(
        "Значение по ключу '" + key + "' не является булевым: " + value);
  }

  /**
   * Добавляет параметр в конфигурацию.
   *
   * @param key   Ключ параметра
   * @param value Значение параметра
   */
  public void setValue(String key, String value) {
    config.put(key, value);
  }
}