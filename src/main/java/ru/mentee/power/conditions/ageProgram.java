package ru.mentee.power.conditions;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ageProgram {

  public static void main(String[] args) {
    try {
      System.setOut(new PrintStream(System.out, true, "UTF-8"));
    } catch (
        UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    int age;
    boolean correntInput = false;
    String license = "";
    Scanner scanner = new Scanner(System.in);
    System.out.print("Введите свой возраст: ");
    age = scanner.nextInt();
    scanner.nextLine();
    while (!correntInput) {
      System.out.print("У вас есть права? Введите да/нет: ");
      license = scanner.nextLine();
      correntInput = license.equals("да") || license.equals("нет");
    }
    if ((license.equals("да")) && (age >= 18)) {
      System.out.println("\nМожно арендовать автомобиль!");
    } else if (license.equals("нет") && (age >= 18)) {
      System.out.println("\nАрендовать пока что нельзя, у Вас нет прав");
    } else if ((age < 18) && (license.equals("да"))) {
      System.out.println("\nАрендовать пока что нельзя, Вам меньше 18 лет((");
    } else {
      System.out.println("\nАрендовать пока что нельзя, Вам меньше 18 лет и у вас нет прав!!!");
    }

  }
}
