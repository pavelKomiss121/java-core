package ru.mentee.power.loop;

public class ShapeDrawer {

  /**
   * Главный метод для демонстрации работы программы
   */
  public static void main(String[] args) {
    ShapeDrawer drawer = new ShapeDrawer();

    System.out.println("Квадрат 5x5:");
    drawer.printShape(drawer.drawSquare(5));

    System.out.println("\nПустой квадрат 5x5:");
    drawer.printShape(drawer.drawEmptySquare(5));

    System.out.println("\nТреугольник высотой 5:");
    drawer.printShape(drawer.drawTriangle(5));

    System.out.println("\nРомб размером 5:");
    drawer.printShape(drawer.drawRhombus(5));
  }

  /**
   * Метод рисует заполненный квадрат заданного размера
   *
   * @param size размер стороны квадрата
   * @return строка с изображением квадрата
   */
  public String drawSquare(int size) {
    // TODO: Реализовать метод
    if (size <= 0) {
      return "";
    }
    StringBuilder resultBuilder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        resultBuilder.append("*");
      }
      resultBuilder.append("\n");
    }
    String resultString = resultBuilder.toString().trim();
    return resultString;
  }

  /**
   * Метод рисует пустой квадрат (только границы) заданного размера
   *
   * @param size размер стороны квадрата
   * @return строка с изображением пустого квадрата
   */
  public String drawEmptySquare(int size) {
    // TODO: Реализовать метод
    if (size <= 0) {
      return "";
    }
    StringBuilder resultBuilder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      if (i == 0 || i == size - 1) {
        for (int j = 0; j < size; j++) {
          resultBuilder.append("*");
        }
      } else {
        for (int j = 0; j < size; j++) {
          if (j == 0 || j == size - 1) {
            resultBuilder.append("*");
          } else {
            resultBuilder.append(" ");
          }
        }
      }
      resultBuilder.append("\n");
    }
    String resultString = resultBuilder.toString().trim();
    return resultString;
  }

  /**
   * Метод рисует прямоугольный треугольник заданной высоты
   *
   * @param height высота треугольника
   * @return строка с изображением треугольника
   */
  public String drawTriangle(int height) {
    // TODO: Реализовать метод
    if (height <= 0) {
      return "";
    }
    StringBuilder resultBuilder = new StringBuilder();
    int index = 1;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < index; j++) {
        resultBuilder.append("*");
      }
      index += 1;
      resultBuilder.append("\n");
    }
    String resultString = resultBuilder.toString().trim();
    return resultString;
  }

  /**
   * Метод рисует ромб заданного размера
   *
   * @param size размер ромба (должен быть нечетным числом)
   * @return строка с изображением ромба
   */
  public String drawRhombus(int size) {
    // TODO: Реализовать метод
    if (size <= 0) {
      return "";
    } else if (size % 2 == 0) {
      size += 1;
    }
    StringBuilder resultBuilder = new StringBuilder();
    int middle = size / 2;
    int stars = 0;
    int space = 0;
    for (int i = 0; i <= middle; i++) {
      stars = i * 2 + 1;
      space = middle - i;
      for (int j = 0; j < space; j++) {
        resultBuilder.append(" ");
      }
      for (int j = 0; j < stars; j++) {
        resultBuilder.append("*");
      }
      resultBuilder.append("\n");
    }
    for (int i = middle - 1; i >= 0; i--) {
      stars = i * 2 + 1;
      space = middle - i;
      for (int j = 0; j < space; j++) {
        resultBuilder.append(" ");
      }
      for (int j = 0; j < stars; j++) {
        resultBuilder.append("*");
      }
      resultBuilder.append("\n");
    }
    String resultString = resultBuilder.substring(0, resultBuilder.length() - 1);
    return resultString;
  }

  /**
   * Метод выводит фигуру в консоль
   *
   * @param shape строка с изображением фигуры
   */
  public void printShape(String shape) {
    System.out.println(shape);
  }
}