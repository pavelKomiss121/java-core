package ru.mentee.power.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тесты для утилиты работы со строками")
class StringUtilsTest {

  private StringUtils stringUtils; // Тестируемый объект

  // Метод @BeforeEach выполняется перед каждым тестом
  @BeforeEach
  void setUp() {
    stringUtils = new StringUtils(); // Создаем объект перед тестом
    // TODO: Возможно, класс StringUtils еще не создан. Создай его!
  }

  @Test
  @DisplayName("Переворот обычной строки")
  void shouldReverseNormalString() {
    // Arrange
    String original = "hello";
    String expected = "olleh";

    // Act
    // TODO: Вызови метод reverse(). Его еще нужно создать в StringUtils!
    String actual = stringUtils.reverse(original);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Переворот пустой строки")
  void shouldReturnEmptyStringWhenInputIsEmpty() {
    // Arrange
    String original = "";
    String expected = "";

    // Act
    // TODO: Вызови метод reverse() с пустой строкой
    String actual = stringUtils.reverse(original);

    // Assert
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Возврат null при null на входе")
  void shouldReturnNullWhenInputIsNull() {
    // Arrange
    String original = null;

    // Act
    // TODO: Вызови метод reverse() с null
    String actual = stringUtils.reverse(original);

    // Assert
    assertThat(actual).isNull();
  }

  @Test
  @DisplayName("Переворот строки с одним символом")
  void shouldReturnSameStringWhenSingleCharacter() {
    // Arrange
    String original = "a";
    String expected = "a";

    // Act
    // TODO: Вызови метод reverse() с одним символом
    String actual = stringUtils.reverse(original);

    // Assert
    // TODO: Напиши проверку с использованием AssertJ, что actual равен expected
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Переворот строки-палиндрома")
  void shouldReturnSameStringForPalindrome() {
    // Arrange
    String original = "madam";
    String expected = "madam";
    // Act
    // TODO: Вызови метод reverse() с палиндромом
    String actual = stringUtils.reverse(original);
    // Assert
    // TODO: Напиши проверку
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  @DisplayName("Переворот словосочетания")
  void shouldReturnSameStringForPalindromeWithSpaces() {
    String original = "21 years old";
    String expected = "dlo sraey 12";
    String actual = stringUtils.reverse(original);
    assertThat(actual).isEqualTo(expected);
  }

  // TODO: Добавь еще один тестовый случай (например, строка с пробелами или цифрами)
}