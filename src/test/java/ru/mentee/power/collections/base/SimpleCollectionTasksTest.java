package ru.mentee.power.collections.base;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleCollectionTasksTest {

    @Test
    void shouldReturnCorrectCountForStringsStartingWithGivenLetter() {
        List<String> names = Arrays.asList("Alice", "Bob", "Anna", "Alex");
        char letter = 'A';

        int result = SimpleCollectionTasks.countStringsStartingWith(names, letter);

        assertThat(result).isEqualTo(3);
    }

    @Test
    void shouldReturnZeroForEmptyList() {
        List<String> names = Collections.emptyList();

        int result = SimpleCollectionTasks.countStringsStartingWith(names, 'A');

        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldReturnZeroForNullList() {
        List<String> names = null;

        int result = SimpleCollectionTasks.countStringsStartingWith(names, 'A');

        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldIgnoreNullAndEmptyStringsInList() {
        List<String> names = Arrays.asList(null, "", "Alice", " ", "Anna");

        int result = SimpleCollectionTasks.countStringsStartingWith(names, 'A');

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldBeCaseInsensitiveWhenComparingLetters() {
        List<String> names = Arrays.asList("alice", "ALAN", "Amanda", "bob");

        int result = SimpleCollectionTasks.countStringsStartingWith(names, 'a');

        assertThat(result).isEqualTo(3);
    }

    @Test
    void shouldHandleCustomScenario() {
        List<String> names = Arrays.asList("Apple", "Banana", "Apricot", "blueberry", "avocado", null, "", " ", "Art");

        int result = SimpleCollectionTasks.countStringsStartingWith(names, 'A');

        assertThat(result).isEqualTo(4); // Apple, Apricot, avocado, Art
    }
}