package ru.mentee.power.loop;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ArrayStatisticsTest {

    @Test
    void testFindMinMax() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.findMin()).isEqualTo(1);
        assertThat(stats.findMax()).isEqualTo(9);
    }

    @Test
    void testCalculateSumAndAverage() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.calculateSum()).isEqualTo(51);
        assertThat(stats.calculateAverage()).isEqualTo(5.1);
    }

    @Test
    void testCalculateMedian() {
        // Подготовка
        int[] testData1 = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6}; // Четное количество элементов
        int[] testData2 = {5, 7, 2, 9, 3, 5, 1, 8, 5}; // Нечетное количество элементов

        ArrayStatistics stats1 = new ArrayStatistics(testData1);
        ArrayStatistics stats2 = new ArrayStatistics(testData2);

        // Проверка
        assertThat(stats1.calculateMedian()).isEqualTo(5.0);
        assertThat(stats2.calculateMedian()).isEqualTo(5.0);
    }

    @Test
    void testFindMode() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.findMode()).isEqualTo(5); // 5 встречается 3 раза
    }

    @Test
    void testCalculateStandardDeviation() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка (с округлением до 2 знаков после запятой)
        assertThat(Math.round(stats.calculateStandardDeviation() * 100) / 100.0).isEqualTo(2.43);
    }

    @Test
    void testCountGreaterAndLessThan() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.countGreaterThan(5)).isEqualTo(4); // 7, 9, 8, 6
        assertThat(stats.countLessThan(5)).isEqualTo(3); // 2, 3, 1
    }

    @Test
    void testContains() {
        // Подготовка
        int[] testData = {5, 7, 2, 9, 3, 5, 1, 8, 5, 6};
        ArrayStatistics stats = new ArrayStatistics(testData);

        // Проверка
        assertThat(stats.contains(7)).isTrue();
        assertThat(stats.contains(10)).isFalse();
    }

    @Test
    void testEmptyArray() {
        // TODO: Реализовать тест для проверки работы методов с пустым массивом
        int[] testData = {};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.findMin()).isEqualTo(Integer.MAX_VALUE);
        assertThat(stats.findMax()).isEqualTo(Integer.MIN_VALUE);
        assertThat(stats.calculateSum()).isEqualTo(0);
        assertThat(stats.calculateAverage()).isEqualTo(0.0);
        assertThat(stats.calculateMedian()).isEqualTo(0.0);
        assertThat(stats.findMode()).isEqualTo(0);
        assertThat(stats.calculateStandardDeviation()).isEqualTo(0.0);
        assertThat(stats.countGreaterThan(0)).isEqualTo(0);
        assertThat(stats.countLessThan(0)).isEqualTo(0);
        assertThat(stats.contains(1)).isFalse();
    }

    @Test
    void testSingleElementArray() {
        int[] testData = {7};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.findMin()).isEqualTo(7);
        assertThat(stats.findMax()).isEqualTo(7);
        assertThat(stats.calculateSum()).isEqualTo(7);
        assertThat(stats.calculateAverage()).isEqualTo(7.0);
        assertThat(stats.calculateMedian()).isEqualTo(7.0);
        assertThat(stats.findMode()).isEqualTo(7);
        assertThat(stats.calculateStandardDeviation()).isEqualTo(0.0);
        assertThat(stats.countGreaterThan(5)).isEqualTo(1);
        assertThat(stats.countLessThan(5)).isEqualTo(0);
        assertThat(stats.contains(7)).isTrue();
    }

    @Test
    void testArrayWithDuplicates() {
        // TODO: Реализовать тест для проверки работы методов с массивом, содержащим только дубликаты
        int[] testData = {3, 3, 3, 3, 3};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.findMin()).isEqualTo(3);
        assertThat(stats.findMax()).isEqualTo(3);
        assertThat(stats.calculateSum()).isEqualTo(15);
        assertThat(stats.calculateAverage()).isEqualTo(3.0);
        assertThat(stats.calculateMedian()).isEqualTo(3.0);
        assertThat(stats.findMode()).isEqualTo(3);
        assertThat(stats.calculateStandardDeviation()).isEqualTo(0.0);
        assertThat(stats.countGreaterThan(2)).isEqualTo(5);
        assertThat(stats.countLessThan(4)).isEqualTo(5);
        assertThat(stats.contains(3)).isTrue();
    }

    @Test
    void testArrayWithNegativeValues() {
        // TODO: Реализовать тест для проверки работы методов с массивом, содержащим отрицательные значения
        int[] testData = {-5, -10, -3, -1, -7};
        ArrayStatistics stats = new ArrayStatistics(testData);

        assertThat(stats.findMin()).isEqualTo(-10);
        assertThat(stats.findMax()).isEqualTo(-1);
        assertThat(stats.calculateSum()).isEqualTo(-26);
        assertThat(stats.calculateAverage()).isEqualTo(-5.2);
        assertThat(stats.calculateMedian()).isEqualTo(-5.0);
        assertThat(stats.findMode()).isEqualTo(-10);
        assertThat(stats.calculateStandardDeviation()).isGreaterThan(0.0);
        assertThat(stats.countGreaterThan(-6)).isEqualTo(3);
        assertThat(stats.countLessThan(-6)).isEqualTo(2);
        assertThat(stats.contains(-3)).isTrue();
        assertThat(stats.contains(0)).isFalse();
    }
}