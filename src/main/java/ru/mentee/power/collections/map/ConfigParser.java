package ru.mentee.power.collections.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Класс для работы с конфигурационными данными в формате "ключ=значение"
 */
public class ConfigParser {

  private Map<String, String> configMap;

  /**
   * Создает пустой объект ConfigParser
   */
  public ConfigParser() {
    configMap = new HashMap<>();
  }

  /**
   * Парсит строку конфигурации в формате "ключ=значение" Каждая строка должна быть на отдельной
   * строке. Строки, начинающиеся с # считаются комментариями и игнорируются Пустые строки
   * игнорируются
   *
   * @param configString строка конфигурации
   * @throws IllegalArgumentException если строка конфигурации null
   */
  public void parseConfig(String configString) {
    if (configString == null) {
      throw new IllegalArgumentException();
    }

    String[] lines = configString.split("\n");

    for (String line : lines) {
      line = line.trim();
      if (line.startsWith("#") || line.isEmpty()) {
        continue;
      }

      String[] partsLine = line.split("=", 2);
      configMap.put(partsLine[0].trim(), partsLine[1].trim());
    }
  }

  /**
   * Преобразует текущую конфигурацию в строку
   *
   * @return строка конфигурации в формате "ключ=значение" с разделителями новой строки
   */
  public String toConfigString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, String> entry : configMap.entrySet()) {
      sb.append(entry.getKey());
      sb.append("=");
      sb.append(entry.getValue());
      sb.append("\n");
    }
    if (!sb.isEmpty()) {
      sb.setLength(sb.length() - 1);
    }
    return sb.toString();
  }

  /**
   * Получает значение по ключу
   *
   * @param key ключ
   * @return значение или null, если ключ не найден
   * @throws IllegalArgumentException если ключ null
   */
  public String getValue(String key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    return configMap.getOrDefault(key, null);
  }

  /**
   * Получает значение по ключу или значение по умолчанию, если ключ не найден
   *
   * @param key          ключ
   * @param defaultValue значение по умолчанию
   * @return значение или defaultValue, если ключ не найден
   * @throws IllegalArgumentException если ключ null
   */
  public String getValue(String key, String defaultValue) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    return configMap.getOrDefault(key, defaultValue);
  }

  /**
   * Устанавливает значение для ключа
   *
   * @param key   ключ
   * @param value значение
   * @return предыдущее значение или null, если ключ не существовал
   * @throws IllegalArgumentException если ключ или значение null
   */
  public String setValue(String key, String value) {
    if (key == null || value == null) {
      throw new IllegalArgumentException();
    }
    return configMap.put(key, value);
  }

  /**
   * Удаляет ключ и его значение
   *
   * @param key ключ
   * @return true, если ключ существовал и был удален
   */
  public boolean removeKey(String key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }
    if (configMap.containsKey(key)) {
      configMap.remove(key);
      return true;
    }
    return false;
  }

  /**
   * Проверяет наличие ключа
   *
   * @param key ключ
   * @return true, если ключ существует
   */
  public boolean containsKey(String key) {
    return configMap.containsKey(key);
  }

  /**
   * Возвращает все ключи
   *
   * @return множество ключей
   */
  public Set<String> getKeys() {
    return configMap.keySet();
  }

  /**
   * Возвращает все пары ключ-значение
   *
   * @return карта с парами ключ-значение
   */
  public Map<String, String> getAll() {
    return new HashMap<>(configMap);
  }

  /**
   * Получает целочисленное значение по ключу
   *
   * @param key ключ
   * @return целое число
   * @throws NoSuchElementException если ключ не найден
   * @throws NumberFormatException  если значение не является числом
   */
  public int getIntValue(String key) {
    if (!configMap.containsKey(key)) {
      throw new NoSuchElementException();
    }
    String value = configMap.get(key).trim();

    return Integer.parseInt(value);
  }

  /**
   * Получает логическое значение по ключу Строки "true", "yes", "1" (игнорируя регистр) считаются
   * true Все остальные значения считаются false
   *
   * @param key ключ
   * @return логическое значение
   * @throws NoSuchElementException если ключ не найден
   */
  public boolean getBooleanValue(String key) {
    if (!configMap.containsKey(key)) {
      throw new NoSuchElementException();
    }
    String value = configMap.get(key);
    String valueTrim = value.trim().toLowerCase();

    return "1".equals(valueTrim) || "yes".equals(valueTrim) || "true".equals(valueTrim);
  }

  /**
   * Получает список значений, разделенных запятыми
   *
   * @param key ключ
   * @return список значений или пустой список, если ключ не найден
   */
  public List<String> getListValue(String key) {
    if (key == null) {
      throw new IllegalArgumentException();
    }

    String value = configMap.get(key);
    if (value == null || value.trim().isEmpty()) {
      return new ArrayList<>();
    }

    String[] parts = value.split(",");
    List<String> result = new ArrayList<>(parts.length);

    for (String part : parts) {
      String partTrim = part.trim();
      if (!partTrim.isEmpty()) {
        result.add(partTrim);
      }
    }
    return result;
  }

  /**
   * Очищает все настройки
   */
  public void clear() {
    configMap.clear();
  }

  /**
   * Возвращает количество пар ключ-значение
   *
   * @return количество пар
   */
  public int size() {
    return configMap.size();
  }
}