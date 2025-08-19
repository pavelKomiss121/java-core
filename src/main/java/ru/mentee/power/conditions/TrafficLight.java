package ru.mentee.power.conditions;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class TrafficLight {

  /**
   * Возвращает рекомендацию для пешехода в зависимости от сигнала светофора.
   *
   * @param signal строковое представление сигнала ("Красный", "Желтый", "Зеленый")
   * @return Рекомендация для пешехода
   */
  public static String getRecommendation(String signal) {
    if (signal == null) {
      return "Некорректный сигнал!";
    }
    switch (signal.toLowerCase()) { // Приводим к нижнему регистру для надежности
      case "красный":
        return "Стой на месте!";
      case "желтый":
        return "Приготовься, но подожди!";
      case "зеленый":
        return "Можно переходить дорогу!";
      default:
        return "Некорректный сигнал!";
    }

  }

  public static void main(String[] args) {
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (
        UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    Scanner scanner = new Scanner(System.in);

    System.out.println("Какой сейчас сигнал светофора (Красный, Желтый, Зеленый)?");
    System.out.print("Введите название сигнала: ");

    String signal = scanner.nextLine(); // Считываем строку
    String recommendation = getRecommendation(signal); // Вызываем наш метод
    System.out.println(recommendation);

    scanner.close();
  }
}