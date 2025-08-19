package ru.mentee.power.collections.base;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class CollectionAnalyzerTest {

  @Test
  void shouldReturnStringsLongerThanMinLength() {
    Collection<String> strings = Arrays.asList("a", "abc", "abcde", "xy");
    int minLength = 2;

    List<String> result = CollectionAnalyzer.findLongStrings(strings, minLength);

    assertThat(result)
        .hasSize(2)
        .containsExactly("abc", "abcde");
  }

  @Test
  void shouldReturnEmptyListWhenCollectionIsNull() {
    List<String> result = CollectionAnalyzer.findLongStrings(null, 3);

    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnEmptyListWhenCollectionIsEmpty() {
    Collection<String> strings = Collections.emptyList();

    List<String> result = CollectionAnalyzer.findLongStrings(strings, 5);

    assertThat(result).isEmpty();
  }

  @Test
  void shouldIgnoreNullAndEmptyStringsWhenFindingLongStrings() {
    Collection<String> strings = Arrays.asList("hello", "", null, "hi", "world");

    List<String> result = CollectionAnalyzer.findLongStrings(strings, 3);

    assertThat(result)
        .hasSize(2)
        .containsExactly("hello", "world");
  }

  @Test
  void shouldCalculateCorrectDigitSumForPositiveNumber() {
    int result = CollectionAnalyzer.calculateDigitSum(123);
    assertThat(result).isEqualTo(6); // 1 + 2 + 3 = 6
  }

  @Test
  void shouldCalculateCorrectDigitSumForNegativeNumber() {
    int result = CollectionAnalyzer.calculateDigitSum(-456);
    assertThat(result).isEqualTo(15); // 4 + 5 + 6 = 15
  }

  @Test
  void shouldReturnZeroAsDigitSumForZero() {
    int result = CollectionAnalyzer.calculateDigitSum(0);
    assertThat(result).isEqualTo(0);
  }

  @Test
  void shouldCountNumbersWithDigitSumGreaterThanThreshold() {
    Collection<Integer> numbers = Arrays.asList(123, 45, 9, 1000); // sums: 6, 9, 9, 1
    int threshold = 5;

    int result = CollectionAnalyzer.countNumbersWithDigitSumGreaterThan(numbers, threshold);

    assertThat(result).isEqualTo(3); // 123 (6), 45 (9), 9 (9) > 5
  }

  @Test
  void shouldReturnZeroWhenCountingWithNullCollection() {
    int result = CollectionAnalyzer.countNumbersWithDigitSumGreaterThan(null, 10);
    assertThat(result).isZero();
  }

  @Test
  void shouldReturnZeroWhenCountingWithEmptyCollection() {
    int result = CollectionAnalyzer.countNumbersWithDigitSumGreaterThan(Collections.emptyList(), 3);
    assertThat(result).isZero();
  }

  @Test
  void shouldHandleCustomScenarioForDigitSumCount() {
    Collection<Integer> numbers = Arrays.asList(11, 29, 30, -999, 0); // sums: 2, 11, 3, 27, 0
    int threshold = 10;

    int result = CollectionAnalyzer.countNumbersWithDigitSumGreaterThan(numbers, threshold);

    assertThat(result).isEqualTo(2); // 29 and -999
  }
}
