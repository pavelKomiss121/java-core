package ru.mentee.power.collections.list;

import java.util.*;

/**
 * Класс для анализа и обработки связных списков (LinkedList)
 */
public class LinkedListAnalyzer {

    /**
     * Объединяет два списка, удаляя дубликаты
     *
     * @param list1 первый список
     * @param list2 второй список
     * @return новый список, содержащий все уникальные элементы из обоих списков
     * @throws IllegalArgumentException если list1 или list2 равны null
     */
    public static <T> List<T> mergeLists(List<T> list1, List<T> list2) {
        if (list1 == null || list2 == null) throw new IllegalArgumentException("Список не может быть null");

        list1.addAll(list2);
        list1 = removeDuplicates(list1);
        return list1;

        // Оптимизированная реализация должна:
        // 1. Использовать итераторы для обхода списков
        // 2. Возвращать LinkedList для эффективных вставок
        // 3. Не использовать get(index)
    }

    /**
     * Переворачивает список (изменяет порядок элементов на обратный)
     *
     * @param list список для обращения
     * @return тот же список с обратным порядком элементов
     * @throws IllegalArgumentException если list равен null
     */
    public static <T> List<T> reverse(List<T> list) {
        // TODO: Реализуйте метод, используя методы addFirst/addLast
        if (list == null) throw new IllegalArgumentException("Список не может быть null");

        List<T> result = new LinkedList<>();
        for (T elem : list) result.addFirst(elem);
        return result;
    }

    /**
     * Удаляет из списка все элементы, которые встречаются более одного раза,
     * оставляя только их первое вхождение
     *
     * @param list список для обработки
     * @return список с удаленными дубликатами
     * @throws IllegalArgumentException если list равен null
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        // TODO: Реализуйте метод, используя итератор для эффективного
        if (list == null) throw new IllegalArgumentException("Список не может быть null");

        List<T> result = new LinkedList<>();
        Iterator<T> iterator = list.iterator();

        while (iterator.hasNext()) {
            T elem = iterator.next();
            if (result.contains(elem)) {
                iterator.remove();
            } else {
                result.add(elem);
            }
        }
        return list;
    }

    /**
     * Реализует циклический сдвиг элементов списка на указанное количество позиций
     *
     * @param list список для сдвига
     * @param positions количество позиций для сдвига (положительное - вправо, отрицательное - влево)
     * @return тот же список с циклически сдвинутыми элементами
     * @throws IllegalArgumentException если list равен null
     */
    public static <T> List<T> rotate(List<T> list, int positions) {
        // TODO: Реализуйте метод, используя методы addFirst/removeLast или
        if (list == null) throw new IllegalArgumentException("Список не может быть null");
        if (positions > 0) {
            for (int i = 0; i < positions; i++) {
                T elem = list.getLast();
                list.removeLast();
                list.addFirst(elem);
            }
        } else {
            positions = Math.abs(positions);
            for (int i = 0; i < positions; i++) {
                T elem = list.getFirst();
                list.removeFirst();
                list.addLast(elem);
            }
        }
        return list;
    }
}