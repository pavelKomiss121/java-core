package ru.mentee.power.loop;

import java.util.HashMap;
import java.util.Map;

public class FibonacciCalculator {

    // Кэш для хранения уже вычисленных чисел Фибоначчи
    private final Map<Integer, Long> cache = new HashMap<>();

    /**
     * Вычисляет число Фибоначчи с заданным индексом, используя рекурсивный подход
     * Внимание: этот метод имеет экспоненциальную сложность и не рекомендуется для больших чисел!
     *
     * @param n индекс числа Фибоначчи (n >= 0)
     * @return число Фибоначчи с индексом n
     */
    public long fibonacciRecursive(int n) {
        if (n<0){
            return -1;
        }
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    /**
     * Вычисляет число Фибоначчи с заданным индексом, используя итеративный подход
     *
     * @param n индекс числа Фибоначчи (n >= 0)
     * @return число Фибоначчи с индексом n
     */
    public long fibonacciIterative(int n) {
        if (n<0){
            return -1;
        }

        if (n <= 0) return 0;
        if (n == 1) return 1;

        long prev = 0;
        long current = 1;

        for (int i = 2; i <= n; i++) {
            long next = prev + current;
            prev = current;
            current = next;
        }

        return current;
    }

    /**
     * Вычисляет число Фибоначчи с заданным индексом, используя кэширование
     *
     * @param n индекс числа Фибоначчи (n >= 0)
     * @return число Фибоначчи с индексом n
     */
    public long fibonacciWithCache(int n) {
        if (n<0){
            return -1;
        }

        if (n <= 0) return 0;
        if (n == 1) return 1;

        // Проверяем, есть ли значение в кэше
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        // Вычисляем значение и сохраняем его в кэше
        long result = fibonacciWithCache(n - 1) + fibonacciWithCache(n - 2);
        cache.put(n, result);

        return result;
    }

    /**
     * Генерирует последовательность первых n чисел Фибоначчи
     *
     * @param n количество чисел Фибоначчи для генерации
     * @return массив с числами Фибоначчи
     */
    public long[] generateFibonacciSequence(int n) {
        if (n<0){
            long[] results = new long[0];
            return results;
        }

        // TODO: Реализовать метод
        long[] results = new long[n];
        for (int i=0; i<n; i++){
            results[i] = fibonacciWithCache(i);
        }
        return results;
    }

    /**
     * Проверяет, является ли число числом Фибоначчи
     *
     * @param number проверяемое число
     * @return true, если число является числом Фибоначчи, иначе false
     */
    public boolean isFibonacciNumber(long number) {
        // TODO: Реализовать метод
        long actualNumber;
        int index=0;
        for (actualNumber=0; actualNumber<=number;){
            actualNumber = fibonacciWithCache(index);
            if (actualNumber == number){
                return true;
            }
            index+=1;
        }
        return false;
    }

    public static void main(String[] args) {
        FibonacciCalculator calculator = new FibonacciCalculator();

        System.out.println("Последовательность Фибоначчи (первые 10 чисел):");
        long[] sequence = calculator.generateFibonacciSequence(10);
        for (int i = 0; i < sequence.length; i++) {
            System.out.println("F(" + i + ") = " + sequence[i]);
        }

        System.out.println("\nПроверка различных методов вычисления F(10):");
        System.out.println("Рекурсивный метод: " + calculator.fibonacciRecursive(10));
        System.out.println("Итеративный метод: " + calculator.fibonacciIterative(10));
        System.out.println("Метод с кэшированием: " + calculator.fibonacciWithCache(10));

        System.out.println("\nПроверка, является ли число числом Фибоначчи:");
        long[] testNumbers = {8, 10, 13, 15, 21};
        for (long num : testNumbers) {
            System.out.println(num + ": " + calculator.isFibonacciNumber(num));
        }
    }
}