package ru.mentee.power.loop;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class FizzBuzzTest {

  FizzBuzz fizzBuzzForTest = new FizzBuzz();
  String[] results = fizzBuzzForTest.generateFizzBuzz(100000);

  @Test
  public void testFizzBuzzForFirst15Numbers() {
    // Подготовка
    FizzBuzz fizzBuzz = new FizzBuzz();

    // Действие
    String[] result = fizzBuzz.generateFizzBuzz(15);

    // Проверка
    assertThat(result).isNotNull();
    assertThat(result).hasSize(15);

    // Проверяем конкретные значения
    assertThat(result[0]).isEqualTo("1");    // 1
    assertThat(result[1]).isEqualTo("2");    // 2
    assertThat(result[2]).isEqualTo("Fizz"); // 3
    assertThat(result[4]).isEqualTo("Buzz"); // 5
    assertThat(result[14]).isEqualTo("FizzBuzz"); // 15
  }

  @Test
  public void testFizzBuzzWithZeroInput() {
    // Подготовка
    FizzBuzz fizzBuzz = new FizzBuzz();

    // Действие
    String[] result = fizzBuzz.generateFizzBuzz(0);

    // Проверка
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
  }

  @Test
  public void testAllFizzValuesAreDivisibleBy3() {
    // TODO: Дополнить тест, проверяющий, что все значения "Fizz"

    for (int i = 0; i < results.length; i++) {
      if ((i + 1) % 3 == 0 && (i + 1) % 5 != 0) {
        assertThat(results[i]).isEqualTo("Fizz");
      }
    }
  }

  @Test
  public void testAllBuzzValuesAreDivisibleBy5() {
    // TODO: Дополнить тест, проверяющий, что все значения "Buzz"

    for (int i = 0; i < results.length; i++) {
      if ((i + 1) % 3 != 0 && (i + 1) % 5 == 0) {
        assertThat(results[i]).isEqualTo("Buzz");
      }
    }
  }

  @Test
  public void testAllFizzBuzzValuesAreDivisibleBy3And5() {
    // TODO: Дополнить тест, проверяющий, что все значения "FizzBuzz"

    for (int i = 0; i < results.length; i++) {
      if ((i + 1) % 3 == 0 && (i + 1) % 5 == 0) {
        assertThat(results[i]).isEqualTo("FizzBuzz");
      }
    }
  }
}