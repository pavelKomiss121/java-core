package ru.mentee.power.loop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayStatistics {

    // Массив с данными для анализа
    private final int[] data;

    /**
     * Конструктор класса
     *
     * @param data массив целых чисел для анализа
     */
    public ArrayStatistics(int[] data) {
        // Создаем копию массива, чтобы избежать изменений извне
        this.data = Arrays.copyOf(data, data.length);
    }

    /**
     * Возвращает минимальное значение в массиве
     *
     * @return минимальное значение или Integer.MAX_VALUE, если массив пуст
     */
    public int findMin() {
        // TODO: Реализовать метод

        int minElem = Integer.MAX_VALUE;
        for (int elem : data){
            if (elem < minElem) minElem = elem;
        }
        return minElem;
    }

    /**
     * Возвращает максимальное значение в массиве
     *
     * @return максимальное значение или Integer.MIN_VALUE, если массив пуст
     */
    public int findMax() {
        // TODO: Реализовать метод
        int maxElem = Integer.MIN_VALUE;
        for (int elem : data){
            if (elem > maxElem) maxElem = elem;
        }
        return maxElem;
    }

    /**
     * Вычисляет сумму всех элементов массива
     *
     * @return сумма элементов
     */
    public int calculateSum() {
        // TODO: Реализовать метод
        int sum=0;
        for (int elem : data) sum+=elem;
        return sum;
    }

    /**
     * Вычисляет среднее арифметическое элементов массива
     *
     * @return среднее арифметическое или 0, если массив пуст
     */
    public double calculateAverage() {
        if (data.length == 0) return 0.0;
        return (double)calculateSum()/data.length;
    }

    /**
     * Вычисляет медиану массива (среднее значение после сортировки)
     *
     * @return медиана или 0, если массив пуст
     */
    public double calculateMedian() {
        // TODO: Реализовать метод
        if (data.length == 0) return 0.0;
        int[] sorted = Arrays.copyOf(data, data.length);
        Arrays.sort(sorted);

        int mid = sorted.length / 2;
        if (sorted.length % 2 == 0) {
            return (sorted[mid - 1] + sorted[mid]) / 2.0;
        } else {
            return sorted[mid];
        }
    }

    /**
     * Находит моду массива (наиболее часто встречающееся значение)
     * Если таких значений несколько, возвращает наименьшее из них
     *
     * @return мода или 0, если массив пуст
     */
    public int findMode() {
        if (data.length == 0) return 0;

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : data) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int mode = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();

            if (freq > maxFreq || (freq == maxFreq && num < mode)) {
                maxFreq = freq;
                mode = num;
            }
        }
        return mode;
    }

    /**
     * Вычисляет стандартное отклонение элементов массива
     *
     * @return стандартное отклонение или 0, если массив пуст или содержит менее 2 элементов
     */
    public double calculateStandardDeviation() {
        if (data.length < 2) return 0.0;

        double mean = calculateAverage();
        double sumSqDiff = 0.0;

        for (int num : data) {
            sumSqDiff += Math.pow(num - mean, 2);
        }

        return Math.sqrt(sumSqDiff / data.length );
    }

    /**
     * Подсчитывает количество элементов, больших заданного значения
     *
     * @param value значение для сравнения
     * @return количество элементов, больших value
     */
    public int countGreaterThan(int value) {
        int count = 0;
        for (int num : data) {
            if (num > value) count++;
        }
        return count;
    }

    /**
     * Подсчитывает количество элементов, меньших заданного значения
     *
     * @param value значение для сравнения
     * @return количество элементов, меньших value
     */
    public int countLessThan(int value) {
        int count = 0;
        for (int num : data) {
            if (num < value) count++;
        }
        return count;
    }

    /**
     * Проверяет, содержит ли массив заданное значение
     *
     * @param value искомое значение
     * @return true, если значение найдено, иначе false
     */
    public boolean contains(int value) {
        for (int num : data) {
            if (num == value) return true;
        }
        return false;
    }

    /**
     * Выводит статистический отчет по массиву
     */
    public void printStatisticsReport() {
        System.out.println("===== Статистический отчет =====");
        System.out.println("Размер массива: " + data.length);
        System.out.println("Минимальное значение: " + findMin());
        System.out.println("Максимальное значение: " + findMax());
        System.out.println("Сумма элементов: " + calculateSum());
        System.out.println("Среднее арифметическое: " + calculateAverage());
        System.out.println("Медиана: " + calculateMedian());
        System.out.println("Мода: " + findMode());
        System.out.println("Стандартное отклонение: " + calculateStandardDeviation());
        System.out.println("================================");
    }

    public static void main(String[] args) {
        // Пример использования
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        System.out.println("Исходный массив: " + Arrays.toString(testData));
        stats.printStatisticsReport();

        // Примеры использования отдельных методов
        System.out.println("Элементов больше 5: " + stats.countGreaterThan(5));
        System.out.println("Элементов меньше 5: " + stats.countLessThan(5));
        System.out.println("Массив содержит 7: " + stats.contains(7));
        System.out.println("Массив содержит 10: " + stats.contains(10));
    }
}