package ru.mentee.power.conditions;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class TaxCalculator {

  /**
   * Рассчитывает налог по прогрессивной шкале.
   *
   * @param income Годовой доход.
   * @return Сумма налога. Если доход отрицательный, возвращает -1.
   */
  public static double calculateTax(double income) {
    if (income < 0) {
      return -1;
    }

    double taxRate;
    if (income <= 100_000) {
      taxRate = 0.10;
    } else if (income <= 500_000) {
      taxRate = 0.15;
    } else if (income <= 1_000_000) {
      taxRate = 0.20;
    } else {
      taxRate = 0.25;
    }
    return income * taxRate;
  }

  public static void main(String[] args) {

    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (
        UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    Scanner scanner = new Scanner(System.in);

    System.out.print("Введите ваш годовой доход в рублях: ");
    double income = scanner.nextDouble();

    if (income < 0) {
      System.out.println("Доход не может быть отрицательным.");
    } else {
      double taxAmount = calculateTax(income);
      double netIncome = income - taxAmount;
      double effectiveTaxRate = (income == 0) ? 0 : (taxAmount / income) * 100;

      System.out.printf("Ваш налог: %.2f руб.%n", taxAmount);
      System.out.printf("Чистый доход после уплаты налога: %.2f руб.%n", netIncome);
      System.out.printf("Эффективная налоговая ставка: %.1f%%%n", effectiveTaxRate);
    }
    scanner.close();
  }
}