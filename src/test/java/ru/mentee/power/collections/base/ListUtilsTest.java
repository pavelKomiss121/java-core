package ru.mentee.power.collections.base;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ListUtilsTest {

    @Test
    void shouldMergeTwoListsAndRemoveDuplicates() {
        // Arrange
        List<String> list1 = Arrays.asList("Apple", "Banana", "Cherry");
        List<String> list2 = Arrays.asList("Banana", "Cherry", "Date");

        // Act
        List<String> result = ListUtils.mergeLists(list1, list2);

        // Assert
        assertThat(result)
                .hasSize(4)
                .containsExactlyInAnyOrder("Apple", "Banana", "Cherry", "Date");
    }

    @Test
    void shouldReturnFirstListElementsWhenSecondListIsEmpty() {
        List<String> list1 = Arrays.asList("One", "Two");
        List<String> list2 = new ArrayList<>();

        List<String> result = ListUtils.mergeLists(list1, list2);

        assertThat(result)
                .hasSize(2)
                .containsExactlyInAnyOrder("One", "Two");
    }

    @Test
    void shouldReturnEmptyListWhenBothListsAreEmpty() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        List<String> result = ListUtils.mergeLists(list1, list2);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnSecondListWhenFirstListIsNull() {
        List<String> list1 = null;
        List<String> list2 = Arrays.asList("X", "Y");

        List<String> result = ListUtils.mergeLists(list1, list2);

        assertThat(result)
                .hasSize(2)
                .containsExactlyInAnyOrder("X", "Y");
    }

    @Test
    void shouldReturnEmptyListWhenBothListsAreNull() {
        List<String> result = ListUtils.mergeLists(null, null);

        assertThat(result).isEmpty();
    }

    @Test
    void shouldIgnoreNullElementsWhenMergingLists() {
        List<String> list1 = Arrays.asList("A", null, "B");
        List<String> list2 = Arrays.asList(null, "B", "C");

        List<String> result = ListUtils.mergeLists(list1, list2);

        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder("A", "B", "C");
    }

    @Test
    void shouldHandleCustomScenarioForMergeLists() {
        List<String> list1 = Arrays.asList("dog", "cat", "bird");
        List<String> list2 = Arrays.asList("cat", "fish", null, "dog");

        List<String> result = ListUtils.mergeLists(list1, list2);

        assertThat(result)
                .hasSize(4)
                .containsExactlyInAnyOrder("dog", "cat", "bird", "fish");
    }
}