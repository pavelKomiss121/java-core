package ru.mentee.power.collections.map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConfigParserTest {

  @Test
  @DisplayName("Метод setValue должен добавлять и возвращать предыдущее значение")
  void shouldSetValueAndReturnPreviousValue() {
    ConfigParser parser = new ConfigParser();

    assertThat(parser.setValue("host", "localhost")).isNull();
    assertThat(parser.setValue("port", "8080")).isNull();

    assertThat(parser.setValue("host", "127.0.0.1")).isEqualTo("localhost");
    assertThat(parser.getValue("host")).isEqualTo("127.0.0.1");
  }

  @Test
  @DisplayName("Метод setValue должен выбрасывать исключение при null аргументах")
  void shouldThrowExceptionForNullArgumentsInSetValue() {
    ConfigParser parser = new ConfigParser();

    assertThatThrownBy(() -> parser.setValue(null, "value"))
        .isInstanceOf(IllegalArgumentException.class);

    assertThatThrownBy(() -> parser.setValue("key", null))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Метод getValue должен возвращать значение по ключу")
  void shouldGetValueByKey() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");

    assertThat(parser.getValue("host")).isEqualTo("localhost");
  }

  @Test
  @DisplayName("Метод getValue должен возвращать defaultValue, если ключ не найден")
  void shouldReturnDefaultValueIfKeyNotFound() {
    ConfigParser parser = new ConfigParser();
    assertThat(parser.getValue("nonexistent", "default")).isEqualTo("default");
  }

  @Test
  @DisplayName("Метод getValue с defaultValue должен выбрасывать исключение при null ключе")
  void shouldThrowExceptionForNullKeyInGetValueWithDefault() {
    ConfigParser parser = new ConfigParser();

    assertThatThrownBy(() -> parser.getValue(null, "default"))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Метод removeKey должен удалять ключ и возвращать true, если ключ существовал")
  void shouldRemoveKeyAndReturnTrueIfKeyExists() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");
    parser.setValue("port", "8080");

    assertTrue(parser.removeKey("host"));
    assertFalse(parser.containsKey("host"));
    assertEquals(1, parser.size());
  }

  @Test
  @DisplayName("Метод removeKey должен возвращать false, если ключ не существовал")
  void shouldReturnFalseIfKeyDidNotExist() {
    ConfigParser parser = new ConfigParser();
    assertFalse(parser.removeKey("nonexistent"));
  }

  @Test
  @DisplayName("Метод containsKey должен корректно проверять наличие ключа")
  void shouldCheckIfKeyExists() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");

    assertTrue(parser.containsKey("host"));
    assertFalse(parser.containsKey("nonexistent"));
  }

  @Test
  @DisplayName("Метод getKeys должен возвращать все ключи")
  void shouldReturnAllKeys() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");
    parser.setValue("port", "8080");

    Set<String> keys = parser.getKeys();
    assertThat(keys).containsExactlyInAnyOrder("host", "port");
  }

  @Test
  @DisplayName("Метод getAll должен возвращать копию всех пар ключ-значение")
  void shouldReturnCopyOfAllEntries() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");

    Map<String, String> config = parser.getAll();
    config.put("port", "8080");

    assertThat(parser.getAll()).hasSize(1);
    assertThat(config).hasSize(2);
  }

  @Test
  @DisplayName("Метод getIntValue должен корректно парсить целые числа")
  void shouldParseIntegerValues() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("port", "8080");

    assertThat(parser.getIntValue("port")).isEqualTo(8080);
  }

  @Test
  @DisplayName("Метод getIntValue должен выбрасывать исключение, если значение не является числом")
  void shouldThrowExceptionIfValueIsNotANumber() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("port", "hi");

    assertThatThrownBy(() -> parser.getIntValue("port"))
        .isInstanceOf(NumberFormatException.class);
  }

  @Test
  @DisplayName("Метод getBooleanValue должен корректно распознавать логические значения")
  void shouldParseBooleanValues() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("debug", "true");
    parser.setValue("verbose", "false");

    assertTrue(parser.getBooleanValue("debug"));
    assertFalse(parser.getBooleanValue("verbose"));
  }

  @Test
  @DisplayName("Метод getListValue должен разбивать строку на список по запятым")
  void shouldSplitStringByCommas() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("colors", "red,green,blue");

    List<String> expected = Arrays.asList("red", "green", "blue");
    List<String> result = parser.getListValue("colors");

    assertThat(result).containsExactlyElementsOf(expected);
  }

  @Test
  @DisplayName("Метод getListValue должен возвращать пустой список для несуществующего ключа")
  void shouldReturnEmptyListForNonExistentKey() {
    ConfigParser parser = new ConfigParser();
    assertThat(parser.getListValue("nonexistent")).isEmpty();
  }

  @Test
  @DisplayName("Метод parseConfig должен корректно парсить конфигурационную строку")
  void shouldParseConfigString() {
    String config = "# Это комментарий\nhost=localhost\nport=8080\n\ndebug=true";

    ConfigParser parser = new ConfigParser();
    parser.parseConfig(config);

    assertThat(parser.size()).isEqualTo(3);
    assertThat(parser.getValue("host")).isEqualTo("localhost");
    assertThat(parser.getValue("port")).isEqualTo("8080");
    assertThat(parser.getValue("debug")).isEqualTo("true");
  }

  @Test
  @DisplayName("Метод parseConfig должен выбрасывать исключение при null аргументе")
  void shouldThrowExceptionWhenConfigStringIsNull() {
    ConfigParser parser = new ConfigParser();

    assertThatThrownBy(() -> parser.parseConfig(null))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Метод toConfigString должен корректно преобразовывать конфигурацию в строку")
  void shouldConvertConfigToString() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");
    parser.setValue("port", "8080");

    String configString = parser.toConfigString();
    assertThat(configString).contains("host=localhost", "port=8080");
  }

  @Test
  @DisplayName("Метод clear должен удалять все пары ключ-значение")
  void shouldClearAllEntries() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");
    parser.setValue("port", "8080");

    parser.clear();
    assertThat(parser.size()).isZero();
  }

  @Test
  @DisplayName("Метод size должен возвращать правильное количество пар")
  void shouldReturnCorrectSize() {
    ConfigParser parser = new ConfigParser();
    parser.setValue("host", "localhost");
    parser.setValue("port", "8080");

    assertThat(parser.size()).isEqualTo(2);

    parser.removeKey("host");
    assertThat(parser.size()).isEqualTo(1);
  }
}