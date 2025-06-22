package ru.mentee.power.collections.base;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class CollectionAnalyzer {
    /**
     * Находит все строки в коллекции, длина которых больше заданной.
     *
     * @param strings Коллекция строк
     * @param minLength Минимальная длина
     * @return Список строк, длина которых больше minLength
     */
    public static List<String> findLongStrings(Collection<String> strings, int minLength) {
        if (strings == null || strings.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        for (String elem : strings){
            if (elem != null && elem.length() > minLength) list.add(elem);
        }
        return list;
    }

    /**
     * Подсчитывает количество элементов в коллекции, удовлетворяющих условию:
     * сумма цифр числа больше заданного значения.
     *
     * @param numbers Коллекция целых чисел
     * @param threshold Пороговое значение для суммы цифр
     * @return Количество чисел, сумма цифр которых больше threshold
     */
    public static int countNumbersWithDigitSumGreaterThan(Collection<Integer> numbers, int threshold) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        int count=0;
        for (Integer elem : numbers){
            int sum = calculateDigitSum(elem);
            if (sum > threshold) count +=1;
        }
        return count;
    }

    /**
     * Вспомогательный метод для подсчета суммы цифр числа.
     *
     * @param number Целое число
     * @return Сумма цифр числа
     */
    static int calculateDigitSum(int number) {
        int sum =0;
        number = Math.abs(number);
        while (number >= 10){
            sum += number % 10;
            number /=10;
        }
        sum += number;
        return sum;
    }
}