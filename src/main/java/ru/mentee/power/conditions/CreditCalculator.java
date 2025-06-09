package ru.mentee.power.conditions;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class CreditCalculator {

    // TODO: Объявите константы для базовых процентных ставок по типам кредитов
    // TODO: Объявите константы для диапазонов кредитного рейтинга
    // TODO: Объявите константы для диапазонов суммы кредита и срока кредита
    private static final float MORTGAGE = 0.09f;
    private static final float CONSUMER = 0.15f;
    private static final float CREDIT = 0.12f;

    private static final float EXCELLENT = -0.02f;
    private static final float GOOD = -0.01f;
    private static final float BAD = 0.03f;
    /**
     * Рассчитывает ежемесячный платеж по кредиту
     *
     * @param loanAmount сумма кредита (от 10,000 до 10,000,000 руб.)
     * @param loanTermMonths срок кредита в месяцах (от 1 до 360)
     * @param creditType тип кредита ("Ипотека", "Потребительский", "Автокредит")
     * @param creditScore кредитный рейтинг клиента (от 300 до 850)
     * @return ежемесячный платеж или -1 в случае некорректных входных данных
     */
    public double calculateMonthlyPayment(double loanAmount, int loanTermMonths, String creditType, int creditScore) {
        // TODO: Реализуйте метод согласно требованиям
        double creditRate = 0;
        double creditModifier = 0;
        if (!creditType.equals("Ипотека") && !creditType.equals("Потребительский") && !creditType.equals("Автокредит")) {
            return -1;
        }
        if (loanAmount > 10000000 || loanAmount < 10000) {
            return -1;
        }
        switch (creditType) {
            case ("Ипотека"):
                creditRate  = MORTGAGE;
                break;
            case ("Потребительский"):
                creditRate  = CONSUMER;
                break;
            case ("Автокредит"):
                creditRate  = this.CREDIT;
                break;
        }
        if (creditScore >850){
            return -1;
        } else if (creditScore <= 850 && creditScore >= 750) {
            creditModifier = EXCELLENT;
        } else if (creditScore >= 650) {
            creditModifier = GOOD;
        } else if (creditScore >= 500) {
            creditModifier = 0;
        } else if (creditScore >= 300) {
            creditModifier = BAD;
        } else {
            return -1;
        }

        if (loanTermMonths < 1 || loanTermMonths > 360){
            return -1;
        }
        double monthlyRate = (creditModifier + creditRate)/12;
        double monthlyPayment = loanAmount * (monthlyRate * Math.pow(1 + monthlyRate, loanTermMonths)) / (Math.pow(1 + monthlyRate, loanTermMonths) - 1);
        return  monthlyPayment;

    }

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
        double result = calculator.calculateMonthlyPayment(loanAmount, loanTermMonths, creditType, creditScore);
        System.out.println("Ежемесячный платеж по кредиту: " + result);
        scanner.close();
    }
}