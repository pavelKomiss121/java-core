package ru.mentee.power.conditions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UnitConverter {

  // TODO: Задайте значение константы для кода ошибки
  private static final double ERROR_CODE = -1.0;

  // TODO: Заполните списки поддерживаемых единиц измерения для каждой категории
  private static final List<String> LENGTH_UNITS = Arrays.asList("Метр", "Сантиметр", "Дюйм",
      "Фут");
  private static final List<String> WEIGHT_UNITS = Arrays.asList("Килограмм", "Грамм", "Фунт",
      "Унция");
  private static final List<String> TEMP_UNITS = Arrays.asList("Цельсий", "Фаренгейт", "Кельвин");

  public static void main(String[] args) {
    UnitConverter converter = new UnitConverter();
    Scanner scanner = new Scanner(System.in);

    // TODO: Запросите у пользователя значение, исходную и целевую единицы
    // TODO: Вызовите метод convert и выведите результат
    // Пример:
    System.out.println("Введите значение:");
    double val = scanner.nextDouble();
    System.out.println("Введите исходную единицу:");
    String from = scanner.next();
    System.out.println("Введите целевую единицу:");
    String to = scanner.next();
    double result = converter.convert(val, from, to);
    if (result == ERROR_CODE) {
      System.out.println("Ошибка конвертации!");
    } else {
      System.out.println("Результат: " + result);
    }
    scanner.close();
  }

  /**
   * Конвертирует значение из одной единицы измерения в другую
   *
   * @param value    значение для конвертации
   * @param fromUnit исходная единица измерения
   * @param toUnit   целевая единица измерения
   * @return конвертированное значение или ERROR_CODE в случае ошибки
   */
  public double convert(double value, String fromUnit, String toUnit) {
    // TODO: Реализуйте метод согласно требованиям
    // 1. Проверьте, поддерживаются ли обе единицы
    if (!LENGTH_UNITS.contains(fromUnit) && !WEIGHT_UNITS.contains(fromUnit)
        && !TEMP_UNITS.contains(fromUnit)) {
      return ERROR_CODE;
    }
    if (!LENGTH_UNITS.contains(toUnit) && !WEIGHT_UNITS.contains(toUnit) && !TEMP_UNITS.contains(
        toUnit)) {
      return ERROR_CODE;
    }

    // 2. Проверьте, относятся ли единицы к одной категории

    if (!areSameCategory(fromUnit, toUnit)) {
      return ERROR_CODE;
    }
    // 3. Вызовите соответствующий метод конвертации (convertLength, convertWeight, convertTemperature)
    if (LENGTH_UNITS.contains(fromUnit)) {
      return convertLength(value, fromUnit, toUnit);
    } else if (WEIGHT_UNITS.contains(fromUnit)) {
      return convertWeight(value, fromUnit, toUnit);
    } else {
      return convertTemperature(value, fromUnit, toUnit);
    }
  }

  /**
   * Проверяет, относятся ли единицы измерения к одной категории
   *
   * @param unit1 первая единица измерения
   * @param unit2 вторая единица измерения
   * @return true, если единицы относятся к одной категории, иначе false
   */
  private boolean areSameCategory(String unit1, String unit2) {
    // TODO: Реализуйте метод проверки категорий
    return getCategory(unit1) != null && getCategory(unit1).equals(getCategory(unit2));
  }

  /**
   * Определяет к какой категории относится единица измерения
   *
   * @param unit единица измерения
   * @return название категории ("Длина", "Вес", "Температура") или null, если единица не
   * поддерживается
   */
  private String getCategory(String unit) {
    // TODO: Реализуйте метод определения категории
    if (LENGTH_UNITS.contains(unit)) {
      return "Длина";
    }
    if (WEIGHT_UNITS.contains(unit)) {
      return "Вес";
    }
    if (TEMP_UNITS.contains(unit)) {
      return "Температура";
    }
    return null;
  }

  /**
   * Конвертирует длину Сначала конвертируем в базовую единицу (Метр), затем в целевую
   *
   * @param value    значение для конвертации
   * @param fromUnit исходная единица измерения
   * @param toUnit   целевая единица измерения
   * @return конвертированное значение
   */
  private double convertLength(double value, String fromUnit, String toUnit) {
    // TODO: Реализуйте метод конвертации длины
    double meters;
    switch (fromUnit) {
      case "Метр":
        meters = value;
        break;
      case "Сантиметр":
        meters = value / 100;
        break;
      case "Дюйм":
        meters = value / 39.37;
        break;
      case "Фут":
        meters = value / 3.28;
        break;
      default:
        return ERROR_CODE;
    }
    switch (toUnit) {
      case "Метр":
        return meters;
      case "Сантиметр":
        return meters * 100;
      case "Дюйм":
        return meters * 39.37;
      case "Фут":
        return meters * 3.28;
      default:
        return ERROR_CODE;
    }
  }

  /**
   * Конвертирует вес Сначала конвертируем в базовую единицу (Килограмм), затем в целевую
   *
   * @param value    значение для конвертации
   * @param fromUnit исходная единица измерения
   * @param toUnit   целевая единица измерения
   * @return конвертированное значение
   */
  private double convertWeight(double value, String fromUnit, String toUnit) {
    // TODO: Реализуйте метод конвертации веса
    double kilograms;
    switch (fromUnit) {
      case "Килограмм":
        kilograms = value;
        break;
      case "Грамм":
        kilograms = value / 1000;
        break;
      case "Фунт":
        kilograms = value / 2.20462;
        break;
      case "Унция":
        kilograms = value / 35.274;
        break;
      default:
        return ERROR_CODE;
    }
    switch (toUnit) {
      case "Килограмм":
        return kilograms;
      case "Грамм":
        return kilograms * 1000;
      case "Фунт":
        return kilograms * 2.20462;
      case "Унция":
        return kilograms * 35.274;
      default:
        return ERROR_CODE;
    }
  }

  /**
   * Конвертирует температуру Используем прямые формулы конвертации
   *
   * @param value    значение для конвертации
   * @param fromUnit исходная единица измерения
   * @param toUnit   целевая единица измерения
   * @return конвертированное значение
   */
  private double convertTemperature(double value, String fromUnit, String toUnit) {
    // TODO: Реализуйте метод конвертации температуры
    double celsius;
    switch (fromUnit) {
      case "Цельсий":
        celsius = value;
        break;
      case "Фаренгейт":
        celsius = (value - 32) * 5 / 9;
        break;
      case "Кельвин":
        celsius = value - 273.15;
        break;
      default:
        return ERROR_CODE;
    }
    switch (toUnit) {
      case "Цельсий":
        return celsius;
      case "Фаренгейт":
        return (celsius * 9 / 5) + 32;
      case "Кельвин":
        return celsius + 273.15;
      default:
        return ERROR_CODE;
    }
  }
}