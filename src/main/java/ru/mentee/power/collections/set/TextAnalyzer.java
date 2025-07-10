package ru.mentee.power.collections.set;

import java.util.*;

/**
 * Класс для анализа текста с использованием множеств
 */
public class TextAnalyzer {

    /**
     * Находит все уникальные слова в тексте
     * Слова разделяются пробелами и знаками препинания
     *
     * @param text текст для анализа
     * @return множество уникальных слов в нижнем регистре
     * @throws IllegalArgumentException если text равен null
     *
     * Рекомендуемая реализация: HashSet - наиболее эффективная для хранения
     * неупорядоченного набора уникальных элементов
     */
    public static Set<String> findUniqueWords(String text) {
        if (text == null) throw new IllegalArgumentException();
        Set<String> words = new HashSet<>();
        for (String word : text.split("[\\s\\p{Punct}]+")) {
            words.add(word.toLowerCase());
        }
        return words;
    }

    /**
     * Находит все слова, которые встречаются в обоих текстах
     * Это операция ПЕРЕСЕЧЕНИЯ множеств (Intersection)
     *
     * @param text1 первый текст
     * @param text2 второй текст
     * @return множество общих слов в нижнем регистре
     * @throws IllegalArgumentException если text1 или text2 равны null
     *
     * Рекомендуемая реализация: HashSet для создания множеств слов
     * и использование метода retainAll() для пересечения
     */
    public static Set<String> findCommonWords(String text1, String text2) {
        if (text1 == null || text2 == null) throw new IllegalArgumentException();
        Set<String> words1 = new HashSet<>();
        Set<String> words2 = new HashSet<>();

        words1 = findUniqueWords(text1);
        words2 = findUniqueWords(text2);

        words1.retainAll(words2);
        return words1;
    }

    /**
     * Находит все слова, которые встречаются в первом тексте, но отсутствуют во втором
     * Это операция РАЗНОСТИ множеств (Difference)
     *
     * @param text1 первый текст
     * @param text2 второй текст
     * @return множество слов, уникальных для первого текста, в нижнем регистре
     * @throws IllegalArgumentException если text1 или text2 равны null
     *
     * Рекомендуемая реализация: HashSet для создания множеств слов
     * и использование метода removeAll() для вычитания множеств
     */
    public static Set<String> findUniqueWordsInFirstText(String text1, String text2) {
        if (text1 == null || text2 == null) throw new IllegalArgumentException();
        Set<String> words1 = new HashSet<>();
        Set<String> words2 = new HashSet<>();

        words1 = findUniqueWords(text1);
        words2 = findUniqueWords(text2);

        words1.removeAll(words2);
        return words1;
    }

    /**
     * Находит топ-N наиболее часто встречающихся слов в тексте
     *
     * @param text текст для анализа
     * @param n количество слов для возврата
     * @return множество из n наиболее часто встречающихся слов
     * @throws IllegalArgumentException если text равен null или n <= 0
     *
     * Рекомендуемая реализация: использование HashMap для подсчета частоты слов
     * и LinkedHashSet для хранения результата с сохранением порядка вставки
     */
    public static Set<String> findTopNWords(String text, int n) {
        if (text == null || n <= 0) throw new IllegalArgumentException();

        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : text.toLowerCase().split("[\\s\\p{Punct}]+")) {
            if (!word.isEmpty()) {
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }

        TreeSet<Map.Entry<String, Integer>> sortedSet = new TreeSet<>(
                (e1, e2) -> {
                    int cmp = Integer.compare(e2.getValue(), e1.getValue()); // по убыванию
                    return (cmp != 0) ? cmp : e1.getKey().compareTo(e2.getKey()); // по алфавиту, если частота одинаковая
                }
        );

        sortedSet.addAll(freqMap.entrySet());

        Set<String> result = new LinkedHashSet<>();
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedSet) {
            if (count++ >= n) break;
            result.add(entry.getKey());
        }

        return result;

    }

    /**
     * Находит все анаграммы в списке слов
     * Анаграммы - это слова, составленные из одних и тех же букв
     * Например: "пила", "липа", "пали" - анаграммы
     *
     * @param words список слов для проверки
     * @return множество множеств, где каждое внутреннее множество содержит группу анаграмм
     * @throws IllegalArgumentException если words равен null
     *
     * Рекомендуемая реализация: использование TreeSet для внутренних множеств
     * для хранения анаграмм в алфавитном порядке
     */
    public static Set<Set<String>> findAnagrams(List<String> words) {
        // TODO: Реализуйте метод
        return null;
    }

    /**
     * Проверяет, является ли первое множество подмножеством второго
     * Метод демонстрирует операцию проверки ПОДМНОЖЕСТВА
     *
     * @param <T> тип элементов множества
     * @param set1 первое множество
     * @param set2 второе множество
     * @return true, если все элементы set1 содержатся в set2
     * @throws IllegalArgumentException если set1 или set2 равны null
     *
     * Рекомендуемая реализация: использование метода containsAll()
     */
    public static <T> boolean isSubset(Set<T> set1, Set<T> set2) {
        // TODO: Реализуйте метод
        return false;
    }

    /**
     * Удаляет все стоп-слова из текста
     * Демонстрирует ПРАКТИЧЕСКОЕ ПРИМЕНЕНИЕ множеств для фильтрации
     *
     * @param text исходный текст
     * @param stopWords множество стоп-слов
     * @return текст без стоп-слов
     * @throws IllegalArgumentException если text или stopWords равны null
     *
     * Рекомендуемая реализация: использование HashSet для быстрой проверки
     * принадлежности слова к стоп-словам
     */
    public static String removeStopWords(String text, Set<String> stopWords) {
        // TODO: Реализуйте метод
        return null;
    }

    /**
     * Сравнивает производительность работы с разными типами множеств
     * Демонстрирует РАЗНИЦУ между HashSet, LinkedHashSet и TreeSet
     *
     * @param words список слов для тестирования
     * @return Map с результатами замеров времени для разных операций на разных типах Set
     * @throws IllegalArgumentException если words равен null
     */
    public static Map<String, Long> compareSetPerformance(List<String> words) {
        // TODO: Реализуйте метод для сравнения производительности
        // разных типов множеств (HashSet, LinkedHashSet, TreeSet)
        // для различных операций (добавление, поиск, удаление)
        return null;
    }
}