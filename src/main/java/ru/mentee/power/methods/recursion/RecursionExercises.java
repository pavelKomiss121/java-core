package ru.mentee.power.methods.recursion;

/**
 * Класс с рекурсивными методами для решения различных задач
 */
public class RecursionExercises {

  /**
   * Вычисляет факториал числа n
   *
   * @param n Положительное целое число
   * @return Факториал числа n
   * @throws -1 если n < 0
   */
  public static int factorial(int n) {
    if (n < 0) {
      return -1;
    }
    if (n == 0 || n == 1) {
      return 1;
    }
    return n * factorial(n - 1);
  }

  /**
   * Вычисляет n-ое число в последовательности Фибоначчи
   *
   * @param n Позиция в последовательности Фибоначчи (0-based)
   * @return Число Фибоначчи на позиции n
   * @throws -1 если n < 0
   */
  public static long fibonacci(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }
    if (n <= 1) {
      return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
  }

  /**
   * Оптимизированный метод для вычисления n-ого числа Фибоначчи
   *
   * @param n Позиция в последовательности Фибоначчи (0-based)
   * @return Число Фибоначчи на позиции n
   * @throws -1 если n < 0
   */
  public static int fibonacciOptimized(int n) {
    if (n < 0) {
      return -1;
    }

    int[] memo = new int[n + 1];
    return fibonacciMemo(n, memo);
  }

  private static int fibonacciMemo(int n, int[] memo) {
    if (n <= 1) {
      return n;
    }
    if (memo[n] != 0) {
      return memo[n];
    }

    memo[n] = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
    return memo[n];
  }

  /**
   * Проверяет, является ли строка палиндромом
   *
   * @param str Исходная строка
   * @return true, если строка является палиндромом, иначе false
   */
  public static boolean isPalindrome(String str) {
    if (str == null) {
      return false;
    }
    str = str.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
    return isPalindrome(str, 0, str.length() - 1);
  }

  private static boolean isPalindrome(String str, int left, int right) {
    if (left >= right) {
      return true;
    }
    if (str.charAt(left) != str.charAt(right)) {
      return false;
    }
    return isPalindrome(str, left + 1, right - 1);
  }

  /**
   * Вычисляет сумму цифр числа
   *
   * @param n Целое число
   * @return Сумма цифр числа
   */
  public static int sumOfDigits(int n) {
    n = Math.abs(n);
    if (n == 0) {
      return 0;
    }
    return n % 10 + sumOfDigits(n / 10);
  }

  /**
   * Возводит число в степень
   *
   * @param base     Основание
   * @param exponent Показатель степени
   * @return Результат возведения в степень
   */
  public static double power(double base, int exponent) {
    if (exponent == 0) {
      return 1;
    }
    if (exponent < 0) {
      return 1 / power(base, -exponent);
    }
    return base * power(base, exponent - 1);
  }

  /**
   * Находит наибольший общий делитель двух чисел
   *
   * @param a Первое число
   * @param b Второе число
   * @return Наибольший общий делитель
   */
  public static int gcd(int a, int b) {
    if (b == 0) {
      return Math.abs(a);
    }
    if (a == 0) {
      return Math.abs(b);
    }
    return gcd(b, a % b);
  }

  /**
   * Обращает порядок элементов в массиве
   *
   * @param array Исходный массив
   * @param start Начальный индекс для обработки
   * @param end   Конечный индекс для обработки
   */
  public static void reverseArray(int[] array, int start, int end) {
    if (start >= end) {
      return;
    }
    int temp = array[start];
    array[start] = array[end];
    array[end] = temp;
    reverseArray(array, start + 1, end - 1);
  }
}