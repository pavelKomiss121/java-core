package ru.mentee.power.collections.base;

import java.util.List;
import java.util.ArrayList;

public class NumberFilter {
    /**
     * Фильтрует список чисел, оставляя только четные.
     *
     * @param numbers Список целых чисел
     * @return Новый список, содержащий только четные числа из исходного списка
     */
    public static List<Integer> filterEvenNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        for (Integer elem : numbers){
            if (elem != null && elem % 2 == 0) list.add(elem);
        }
        return list;
    }
}