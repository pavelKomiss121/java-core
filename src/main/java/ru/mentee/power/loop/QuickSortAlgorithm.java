package ru.mentee.power.loop;

import java.util.Arrays;
import java.util.Random;

public class QuickSortAlgorithm {

    /**
     * Сортирует массив с помощью алгоритма быстрой сортировки
     *
     * @param array массив для сортировки
     * @return отсортированная копия массива
     */
    public static int[] quickSort(int[] array) {
        // Создаем копию исходного массива, чтобы не изменять его
        int[] result = Arrays.copyOf(array, array.length);

        // TODO: Вызвать вспомогательный метод quickSortRecursive для сортировки
        quickSortRecursive(result, 0, result.length -1);
        return result;
    }

    /**
     * Рекурсивный метод быстрой сортировки
     *
     * @param array массив для сортировки
     * @param low индекс начала диапазона для сортировки
     * @param high индекс конца диапазона для сортировки
     */
    private static void quickSortRecursive(int[] array, int low, int high) {
        // TODO: Реализовать рекурсивную часть алгоритма Quick Sort
        if (low < high){
            int pivotIndex = partition(array, low, high);

            quickSortRecursive(array, low, pivotIndex-1);
            quickSortRecursive(array, pivotIndex + 1, high);
        }
    }

    /**
     * Метод разделения массива относительно опорного элемента
     *
     * @param array массив для разделения
     * @param low индекс начала диапазона
     * @param high индекс конца диапазона
     * @return индекс опорного элемента после разделения
     */
    private static int partition(int[] array, int low, int high) {
        // TODO: Реализовать метод разделения массива
        int pivot = array[high];

        int i = low-1;

        for(int j=low; j<high; j++){
            if (array[j]<=pivot){
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i+1];
        array[i+1] = array[high];
        array[high] = temp;

        return i+1; // Временный возврат, замените на правильный
    }

    /**
     * Генерирует случайный массив целых чисел заданного размера
     *
     * @param size размер массива
     * @param maxValue максимальное значение элемента
     * @return случайный массив
     */
    public static int[] generateRandomArray(int size, int maxValue) {
        int[] result = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            result[i] = random.nextInt(maxValue + 1);
        }

        return result;
    }

    /**
     * Измеряет время выполнения алгоритма в миллисекундах
     *
     * @param array массив для сортировки
     * @return время выполнения в миллисекундах
     */
    public static long measureSortingTime(int[] array) {
        long startTime = System.currentTimeMillis();
        quickSort(array);
        long endTime = System.currentTimeMillis();

        return endTime - startTime;
    }

    /**
     * Сравнивает производительность Quick Sort с Arrays.sort()
     *
     * @param array массив для тестирования
     */
    public static void compareWithJavaSort(int[] array) {
        // Создаем копии массива
        int[] arrayCopy1 = Arrays.copyOf(array, array.length);
        int[] arrayCopy2 = Arrays.copyOf(array, array.length);

        // Измеряем время для нашей реализации Quick Sort
        long startTime1 = System.currentTimeMillis();
        quickSort(arrayCopy1);
        long endTime1 = System.currentTimeMillis();

        // Измеряем время для Arrays.sort()
        long startTime2 = System.currentTimeMillis();
        Arrays.sort(arrayCopy2);
        long endTime2 = System.currentTimeMillis();

        System.out.println("Сравнение производительности на массиве размером " + array.length);
        System.out.println("---------------------------------------------------");
        System.out.println("Наша реализация Quick Sort: " + (endTime1 - startTime1) + " мс");
        System.out.println("Java Arrays.sort(): " + (endTime2 - startTime2) + " мс");
    }

    public static void main(String[] args) {
        // Пример использования на небольшом массиве
        int[] testArray = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        System.out.println("Исходный массив: " + Arrays.toString(testArray));
        System.out.println("Отсортированный массив: " + Arrays.toString(quickSort(testArray)));

        // Проверка на больших массивах
        int[] largeArray = generateRandomArray(100000, 1000);
        compareWithJavaSort(largeArray);
    }
}