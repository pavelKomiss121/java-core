package ru.mentee.power.collections.list;

import java.util.*;

/**
 * Класс для анализа и обработки списков на основе ArrayList
 */
public class ArrayListAnalyzer {

    /**
     * Фильтрует список строк, оставляя только те, которые начинаются с указанного префикса
     *
     * @param strings список строк для фильтрации
     * @param prefix префикс для фильтрации
     * @return новый список, содержащий только строки с указанным префиксом
     * @throws IllegalArgumentException если strings или prefix равны null
     */
    public static List<String> filterByPrefix(List<String> strings, String prefix) {
        if (strings==null || prefix == null) throw new IllegalArgumentException();

        List<String> result = new ArrayList<>(strings.size()/2);
        for (int i=0; i< strings.size(); i++){
            String elem = strings.get(i);
            if (elem.startsWith(prefix)) result.add(elem);
        }
        return result;
    }

    /**
     * Находит максимальный элемент в списке
     *
     * @param numbers список чисел
     * @return максимальное число из списка
     * @throws IllegalArgumentException если список пуст или равен null
     */
    public static Integer findMax(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) throw new IllegalArgumentException();
        int max = Integer.MIN_VALUE;
        for (int number : numbers){
            if (number > max) max = number;
        }
        return max;
    }

    /**
     * Разбивает список на части указанного размера
     *
     * @param list исходный список
     * @param partSize размер каждой части
     * @return список списков, где каждый внутренний список имеет размер не более partSize
     * @throws IllegalArgumentException если list равен null или partSize <= 0
     */
    public static <T> List<List<T>> partition(List<T> list, int partSize) {
        if (list == null || partSize <= 0) throw new IllegalArgumentException();

        List<List<T>> result = new ArrayList<>();
        for (int i =0; i < list.size(); i += partSize){
            int end = Math.min(i + partSize, list.size());
            result.add(new ArrayList<>(list.subList(i, end)));
        }
        return result;
    }

    /**
     * Проверяет, является ли список палиндромом
     * (читается одинаково в обоих направлениях)
     *
     * @param list список для проверки
     * @return true, если список является палиндромом, иначе false
     * @throws IllegalArgumentException если list равен null
     */
    public static <T> boolean isPalindrome(List<T> list) {
        if (list == null) throw new IllegalArgumentException();
        int left = 0;
        int right = list.size() -1;
        while (left <right){
            if (!Objects.equals(list.get(left), list.get(right))) return false;
            left ++;
            right --;
        }
        return true;
    }
}