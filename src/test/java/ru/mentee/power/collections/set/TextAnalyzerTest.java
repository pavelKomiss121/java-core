package ru.mentee.power.collections.set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TextAnalyzerTest {

    @Test
    @DisplayName("Метод findUniqueWords должен находить все уникальные слова в тексте")
    void shouldFindUniqueWordsInText() {
        String text = "Привет, мир! Привет всем в этом мире!";
        Set<String> expected = new HashSet<>(Arrays.asList("привет", "мир", "всем", "в", "этом", "мире"));

        Set<String> result = TextAnalyzer.findUniqueWords(text);

        assertThat(result)
                .isNotNull()
                .hasSize(6)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("Метод findUniqueWords должен выбросить исключение при null аргументе")
    void shouldThrowExceptionForNullTextInFindUniqueWords() {
        assertThatThrownBy(() -> TextAnalyzer.findUniqueWords(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод findUniqueWords должен вернуть пустое множество для пустого текста")
    void shouldReturnEmptySetForEmptyTextInFindUniqueWords() {
        String text = "";
        Set<String> result = TextAnalyzer.findUniqueWords(text);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Метод findCommonWords должен находить общие слова в двух текстах (операция пересечения)")
    void shouldFindCommonWordsInTexts() {
        String text1 = "я люблю котов и собак";
        String text2 = "она любит собак и кошек";
        Set<String> expected = Set.of("и", "собак");

        Set<String> result = TextAnalyzer.findCommonWords(text1, text2);
        assertThat(result).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("Метод findCommonWords должен выбросить исключение при null аргументах")
    void shouldThrowExceptionForNullArgumentsInFindCommonWords() {
        assertThatThrownBy(() -> TextAnalyzer.findCommonWords(null, "текст"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> TextAnalyzer.findCommonWords("текст", null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод findUniqueWordsInFirstText должен находить слова, уникальные для первого текста (операция разности)")
    void shouldFindUniqueWordsInFirstText() {
        String text1 = "apple banana orange";
        String text2 = "banana cherry kiwi";
        Set<String> expected = new HashSet<>(Arrays.asList("apple", "orange"));

        Set<String> result = TextAnalyzer.findUniqueWordsInFirstText(text1, text2);

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Метод findUniqueWordsInFirstText должен выбросить исключение при null аргументах")
    void shouldThrowExceptionForNullArgumentsInFindUniqueWordsInFirstText() {
        assertThatThrownBy(() -> TextAnalyzer.findUniqueWordsInFirstText(null, "text"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> TextAnalyzer.findUniqueWordsInFirstText("text", null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод findTopNWords должен находить наиболее часто встречающиеся слова")
    void shouldFindTopNWords() {
        String text = "кот кот собака кот собака мышь";
        Set<String> expected = Set.of("кот", "собака", "мышь");
        Set<String> result = TextAnalyzer.findTopNWords(text, 3);

        assertThat(result)
                .hasSize(3)
                .containsExactly("кот", "собака", "мышь"); // важно: порядок как в LinkedHashSet
    }

    @Test
    @DisplayName("Метод findTopNWords должен выбросить исключение при некорректных аргументах")
    void shouldThrowExceptionForInvalidArgumentsInFindTopNWords() {
        assertThatThrownBy(() -> TextAnalyzer.findTopNWords(null, 3))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> TextAnalyzer.findTopNWords("какой-то текст", -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод findAnagrams должен находить группы анаграмм")
    void shouldFindAnagrams() {
        List<String> words = List.of("пила", "липа", "пали", "лапа", "папа", "лист");
        Set<Set<String>> result = TextAnalyzer.findAnagrams(words);

        assertThat(result).containsExactlyInAnyOrder(
                Set.of("липа", "пали", "пила")
        );
    }

    @Test
    @DisplayName("Метод findAnagrams должен выбросить исключение при null аргументе")
    void shouldThrowExceptionForNullArgumentInFindAnagrams() {
        assertThatThrownBy(() -> TextAnalyzer.findAnagrams(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод isSubset должен определять, является ли одно множество подмножеством другого")
    void shouldCheckIfSetIsSubset() {
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));

        boolean result1 = TextAnalyzer.isSubset(set1, set2);
        boolean result2 = TextAnalyzer.isSubset(set2, set1);

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    @DisplayName("Метод isSubset должен выбросить исключение при null аргументах")
    void shouldThrowExceptionForNullArgumentsInIsSubset() {
        Set<Integer> validSet = Set.of(1, 2);

        assertThatThrownBy(() -> TextAnalyzer.isSubset(null, validSet))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> TextAnalyzer.isSubset(validSet, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод removeStopWords должен удалять стоп-слова из текста")
    void shouldRemoveStopWordsFromText() {
        String text = "я люблю программирование и учёбу";
        Set<String> stopWords = Set.of("я", "и");
        String result = TextAnalyzer.removeStopWords(text, stopWords);

        assertThat(result).isEqualTo("люблю программирование учёбу");
    }

    @Test
    @DisplayName("Метод removeStopWords должен выбросить исключение при null аргументах")
    void shouldThrowExceptionForNullArgumentsInRemoveStopWords() {
        assertThatThrownBy(() -> TextAnalyzer.removeStopWords(null, Set.of("и")))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> TextAnalyzer.removeStopWords("текст", null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Метод compareSetPerformance должен сравнивать производительность разных типов множеств")
    void shouldCompareSetPerformance() {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            words.add("word" + i);
        }

        Map<String, Long> result = TextAnalyzer.compareSetPerformance(words);

        assertThat(result).isNotNull().hasSize(9);
    }
}