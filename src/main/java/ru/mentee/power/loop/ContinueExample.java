package ru.mentee.power.loop;

import java.util.List;

public class ContinueExample {
    public static void main(String[] args) {
        List<Integer> data = List.of(5, -2, 10, 0, -8, 3);
        int sumOfPositives = 0;

        System.out.println("Суммируем только положительные числа...");
        for (int value : data) {
            if (value <= 0) {
                System.out.println("Пропускаем: " + value);
                continue;
            }
            System.out.println("Добавляем: " + value);
            sumOfPositives += value;
        }

        System.out.println("Сумма положительных: " + sumOfPositives);
    }
}