package ru.mentee.power.methods.recursion;

import java.util.Arrays;

public class RecursionDemo {
    public static void main(String[] args) {
        System.out.println("=== Факториал ===");
        System.out.println("5! = " + RecursionExercises.factorial(5));

        System.out.println("\n=== Числа Фибоначчи ===");
        System.out.println("Наивная реализация:");
        for (int i = 0; i <= 10; i++) {
            System.out.print(RecursionExercises.fibonacci(i) + " ");
        }
        System.out.println("\nОптимизированная реализация:");
        for (int i = 0; i <= 10; i++) {
            System.out.print(RecursionExercises.fibonacciOptimized(i) + " ");
        }

        System.out.println("\n\n=== Проверка палиндрома ===");
        String[] testStrings = {
                "А роза упала на лапу Азора",
                "Hello",
                "12321",
                "123456",
                "RaceCar"
        };
        for (String s : testStrings) {
            boolean result = RecursionExercises.isPalindrome(s);
            System.out.println("\"" + s + "\" -> " + (result ? "палиндром" : "не палиндром"));
        }

        System.out.println("\n=== Сумма цифр ===");
        int number = 12345;
        int sum = RecursionExercises.sumOfDigits(number);
        System.out.println("Сумма цифр числа " + number + " = " + sum);

        System.out.println("\n=== Возведение в степень ===");
        System.out.println("2^10 = " + RecursionExercises.power(2, 10));
        System.out.println("2^-3 = " + RecursionExercises.power(2, -3));

        System.out.println("\n=== Наибольший общий делитель ===");
        int a = 48;
        int b = 36;
        int gcd = RecursionExercises.gcd(a, b);
        System.out.println("НОД(" + a + ", " + b + ") = " + gcd);

        System.out.println("\n=== Обращение массива ===");
        int[] array = {1, 2, 3, 4, 5};
        System.out.println("Исходный массив: " + Arrays.toString(array));
        RecursionExercises.reverseArray(array, 0, array.length - 1);
        System.out.println("Обращённый массив: " + Arrays.toString(array));
    }
}