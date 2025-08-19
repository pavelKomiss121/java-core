package ru.mentee.power.iterators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Класс, представляющий двумерную матрицу с различными способами итерации
 */
public class Matrix<T> implements Iterable<T> {

  private final T[][] data;
  private final int rows;
  private final int columns;

  /**
   * Создает матрицу на основе двумерного массива
   *
   * @param data двумерный массив данных
   * @throws IllegalArgumentException если data равен null или это не прямоугольная матрица
   */
  @SuppressWarnings("unchecked")
  public Matrix(T[][] data) {
    if (data == null) {
      throw new IllegalArgumentException("Массив данных не может быть null");
    }

    this.rows = data.length;
    if (rows == 0) {
      this.columns = 0;
    } else {
      this.columns = data[0].length;
      // Проверка на прямоугольность матрицы
      for (T[] row : data) {
        if (row.length != columns) {
          throw new IllegalArgumentException("Все строки матрицы должны иметь одинаковую длину");
        }
      }
    }

    // Создаем копию массива для инкапсуляции
    this.data = (T[][]) new Object[rows][];
    for (int i = 0; i < rows; i++) {
      this.data[i] = (T[]) new Object[columns];
      System.arraycopy(data[i], 0, this.data[i], 0, columns);
    }
  }

  /**
   * @return количество строк в матрице
   */
  public int getRows() {
    return rows;
  }

  /**
   * @return количество столбцов в матрице
   */
  public int getColumns() {
    return columns;
  }

  /**
   * Получить элемент по индексам
   *
   * @param row    индекс строки
   * @param column индекс столбца
   * @return элемент матрицы
   * @throws IndexOutOfBoundsException если индексы за пределами матрицы
   */
  public T get(int row, int column) {
    if (row < 0 || row >= rows || column < 0 || column >= columns) {
      throw new IndexOutOfBoundsException("Индексы за пределами матрицы");
    }
    return data[row][column];
  }

  /**
   * Установить значение элемента
   *
   * @param row    индекс строки
   * @param column индекс столбца
   * @param value  новое значение
   * @throws IndexOutOfBoundsException если индексы за пределами матрицы
   */
  public void set(int row, int column, T value) {
    if (row < 0 || row >= rows || column < 0 || column >= columns) {
      throw new IndexOutOfBoundsException("Индексы за пределами матрицы");
    }
    data[row][column] = value;
  }

  /**
   * Возвращает итератор по умолчанию (построчный)
   */
  @Override
  public Iterator<T> iterator() {
    return new RowMajorIterator();
  }

  /**
   * Итератор, обходящий матрицу построчно (слева направо, сверху вниз)
   */
  public Iterator<T> rowMajorIterator() {
    return new RowMajorIterator();
  }

  /**
   * Итератор, обходящий матрицу по столбцам (сверху вниз, слева направо)
   */
  public Iterator<T> columnMajorIterator() {
    return new ColumnMajorIterator();
  }

  /**
   * Итератор, обходящий матрицу по спирали от центра к краям
   */
  public Iterator<T> spiralIterator() {
    return new spiralIterator();
  }

  /**
   * Итератор, обходящий матрицу зигзагом (змейкой)
   */
  public Iterator<T> zigzagIterator() {
    return new zigzagIterator();
  }

  /**
   * Итератор, обходящий матрицу по столбцам (сверху вниз, слева направо)
   */
  private class ColumnMajorIterator implements Iterator<T> {

    private int currentColumn = 0;
    private int currentRow = 0;

    @Override
    public boolean hasNext() {
      return currentColumn < columns && currentRow < rows;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T value = data[currentRow][currentColumn];
      currentRow += 1;
      if (currentRow == rows) {
        currentRow = 0;
        currentColumn += 1;
      }
      return value;
    }
  }

  /**
   * Итератор, обходящий матрицу зигзагом (змейкой)
   */
  private class zigzagIterator implements Iterator<T> {

    private int row = 0;
    private int col = 0;
    private boolean leftToRight = true;

    @Override
    public boolean hasNext() {
      return row < rows;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      T value = data[row][col];

      if (leftToRight) {
        col++;
        if (col == columns) {
          col = columns - 1;
          row++;
          leftToRight = false;
        }
      } else {
        col--;
        if (col < 0) {
          col = 0;
          row++;
          leftToRight = true;
        }
      }
      return value;
    }
  }

  /**
   * Итератор, обходящий матрицу по спирали от центра к краям
   */
  private class spiralIterator implements Iterator<T> {

    private final List<T> spiralOrder = new ArrayList<>();
    private int index = 0;

    public spiralIterator() {
      boolean[][] visited = new boolean[rows][columns];
      int centerRow = rows / 2;
      int centerCol = columns / 2;

      int r = centerRow;
      int c = centerCol;
      spiralOrder.add(data[r][c]);
      visited[r][c] = true;

      int[] dr = {0, 1, 0, -1};
      int[] dc = {1, 0, -1, 0};
      int step = 1;

      while (spiralOrder.size() < rows * columns) {
        for (int dir = 0; dir < 4; dir++) {
          for (int i = 0; i < step; i++) {
            r += dr[dir];
            c += dc[dir];
            if (r >= 0 && r < rows && c >= 0 && c < columns && !visited[r][c]) {
              spiralOrder.add(data[r][c]);
              visited[r][c] = true;
            }
          }
          if (dir == 1 || dir == 3) {
            step++;
          }
        }
      }
    }

    @Override
    public boolean hasNext() {
      return index < spiralOrder.size();
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return spiralOrder.get(index++);
    }
  }

  /**
   * Построчный итератор (основной)
   */
  private class RowMajorIterator implements Iterator<T> {

    private int currentRow = 0;
    private int currentColumn = 0;

    @Override
    public boolean hasNext() {
      return currentRow < rows && currentColumn < columns;
    }

    @Override
    public T next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      T element = data[currentRow][currentColumn];

      // Переход к следующему элементу
      currentColumn++;
      if (currentColumn >= columns) {
        currentRow++;
        currentColumn = 0;
      }

      return element;
    }
  }
}