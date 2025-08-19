package ru.mentee.power.iterators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MatrixTest {

  private Matrix<Integer> matrix;

  @BeforeEach
  void setUp() {
    // Создаем тестовую матрицу 3x3
    Integer[][] data = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    matrix = new Matrix<>(data);
  }

  @Test
  @DisplayName("Конструктор должен выбрасывать исключение при невалидных данных")
  void constructorShouldThrowExceptionForInvalidData() {
    // Проверка на null
    assertThatThrownBy(() -> new Matrix<>(null))
        .isInstanceOf(IllegalArgumentException.class);

    // Проверка на неравномерные строки
    Integer[][] irregularData = {
        {1, 2, 3},
        {4, 5},    // Короткая строка
        {7, 8, 9}
    };

    assertThatThrownBy(() -> new Matrix<>(irregularData))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("Методы get и set должны корректно работать")
  void getAndSetShouldWorkCorrectly() {
    // Получение элемента
    assertEquals(Integer.valueOf(5), matrix.get(1, 1));

    // Установка элемента
    matrix.set(1, 1, 99);
    assertEquals(Integer.valueOf(99), matrix.get(1, 1));

    // Проверка на выход за границы
    assertThatThrownBy(() -> matrix.get(3, 0))
        .isInstanceOf(IndexOutOfBoundsException.class);

    assertThatThrownBy(() -> matrix.set(0, 3, 42))
        .isInstanceOf(IndexOutOfBoundsException.class);
  }

  @Test
  @DisplayName("Итератор по умолчанию (построчный) должен корректно обходить матрицу")
  void defaultIteratorShouldTraverseRowMajor() {
    List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    List<Integer> actual = new ArrayList<>();

    for (Integer value : matrix) {
      actual.add(value);
    }

    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  @DisplayName("Итератор по столбцам должен корректно обходить матрицу")
  void columnMajorIteratorShouldTraverseColumnMajor() {
    List<Integer> expected = Arrays.asList(1, 4, 7, 2, 5, 8, 3, 6, 9);
    List<Integer> actual = new ArrayList<>();

    Iterator<Integer> iterator = matrix.columnMajorIterator();
    while (iterator.hasNext()) {
      actual.add(iterator.next());
    }

    assertThat(actual).containsExactlyElementsOf(expected);
  }

  @Test
  @DisplayName("Спиральный итератор должен корректно обходить матрицу по спирали")
  void spiralIteratorShouldTraverseSpirally() {
    List<Integer> expected = Arrays.asList(5, 6, 9, 8, 7, 4, 1, 2, 3);
    List<Integer> actual = new ArrayList<>();
    Iterator<Integer> iterator = matrix.spiralIterator();
    while (iterator.hasNext()) {
      actual.add(iterator.next());
    }
    assertThat(actual).containsExactlyElementsOf(expected);
    // Пример ожидаемого порядка: 5, 6, 9, 8, 7, 4, 1, 2, 3
  }

  @Test
  @DisplayName("Зигзаг-итератор должен корректно обходить матрицу змейкой")
  void zigzagIteratorShouldTraverseInZigzag() {
    List<Integer> expected = Arrays.asList(1, 2, 3, 6, 5, 4, 7, 8, 9);
    List<Integer> actual = new ArrayList<>();
    Iterator<Integer> iterator = matrix.zigzagIterator();
    while (iterator.hasNext()) {
      actual.add(iterator.next());
    }
    assertThat(actual).containsExactlyElementsOf(expected);
    // Пример ожидаемого порядка: 1, 2, 3, 6, 5, 4, 7, 8, 9
  }
}