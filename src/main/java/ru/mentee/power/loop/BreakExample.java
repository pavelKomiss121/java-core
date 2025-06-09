package ru.mentee.power.loop;

public class BreakExample {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 0, -3, 8, -1};
        int firstNegative = 0; // Переменная для хранения результата

        System.out.println("Ищем первое отрицательное число...");
        for (int number : numbers) {
            System.out.println("Проверяем: " + number);
            if (number < 0) {
                firstNegative = number;
                System.out.println("Нашли! Это " + number);
                break; // Выходим из цикла, как только нашли
            }
        }

        if (firstNegative == 0) { // Проверяем, было ли найдено
            System.out.println("Отрицательных чисел не найдено.");
        } else {
            System.out.println("Первое найденное отрицательное: " + firstNegative);
        }
    }
}