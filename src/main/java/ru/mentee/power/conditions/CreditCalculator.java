package ru.mentee.power.conditions;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CreditCalculator {

  // TODO: Объявите константы для базовых процентных ставок по типам кредитов
  // TODO: Объявите константы для диапазонов кредитного рейтинга
  // TODO: Объявите константы для диапазонов суммы кредита и срока кредита
  private final float mortgage = 0.09f;
  private final float consumer = 0.15f;
  private final float credit = 0.12f;

  private final float excellent = -0.02f;
  private final float good = -0.01f;
  private final float bad = 0.03f;

  public static void main(String[] args) {
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (
        UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    CreditCalculator calculator = new CreditCalculator();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите сумму кредита: ");
    double loanAmount = scanner.nextDouble();

    System.out.print("Введите срок кредита: ");
    int loanTermMonths = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Введите тип кредита: ");
    String creditType = scanner.nextLine();

    System.out.print("Введите кредитный рейтинг: ");
    int creditScore = scanner.nextInt();

    // TODO: Вызовите метод calculateMonthlyPayment и выведите результат
    double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType,
        creditScore);
    System.out.println("Ежемесячный платеж по кредиту: " + result);
    scanner.close();
  }

  /**
   * Рассчитывает ежемесячный платеж по кредиту
   *
   * @param loanAmount     сумма кредита (от 10,000 до 10,000,000 руб.)
   * @param loanTermMonths срок кредита в месяцах (от 1 до 360)
   * @param creditType     тип кредита ("Ипотека", "Потребительский", "Автокредит")
   * @param creditScore    кредитный рейтинг клиента (от 300 до 850)
   * @return ежемесячный платеж или -1 в случае некорректных входных данных
   */
  public double calculateMonthlyPayment(double loanAmount, int loanTermMonths, String creditType,
      int creditScore) {
    // TODO: Реализуйте метод согласно требованиям
    double credit = 0;
    double creditRating = 0;
    if (!creditType.equals("Ипотека") && !creditType.equals("Потребительский")
        && !creditType.equals("Автокредит")) {
      return -1;
    }
    if (loanAmount > 10000000 || loanAmount < 10000) {
      return -1;
    }
    switch (creditType) {
      case ("Ипотека"):
        credit = mortgage;
        break;
      case ("Потребительский"):
        credit = consumer;
        break;
      case ("Автокредит"):
        credit = this.credit;
        break;
    }
    if (creditScore > 850) {
      return -1;
    } else if (creditScore <= 850 && creditScore >= 750) {
      creditRating = excellent;
    } else if (creditScore >= 650) {
      creditRating = good;
    } else if (creditScore >= 500) {
      creditRating = 0;
    } else if (creditScore >= 300) {
      creditRating = bad;
    } else {
      return -1;
    }

    if (loanTermMonths < 1 || loanTermMonths > 360) {
      return -1;
    }
    double monthlyRate = (creditRating + credit) / 12;
    double monthlyPayment =
        loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths)) / (
            Math.pow(1 + monthlyRate, loanTermMonths) - 1);
    return monthlyPayment;

  }
}