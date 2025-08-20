package ru.mentee.power.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Тесты менеджера данных HighScoreManager")
class HighScoreManagerTest {

  @TempDir
  Path tempDir;

  private Path testFilePath;

  @BeforeEach
  void setUp() {
    testFilePath = tempDir.resolve("test_scores.dat");
  }

  @Test
  @DisplayName("Должен сохранять и загружать пустой список")
  void shouldSaveAndLoadEmptyList() throws IOException {
    List<HighScoreEntryClass> expected = new ArrayList<>();
    HighScoreManager.saveScores(expected, testFilePath.toString());
    List<HighScoreEntryClass> loaded = HighScoreManager.loadScores(testFilePath.toString());

    assertThat(loaded).isNotNull().isEmpty();
  }

  @Test
  @DisplayName("Должен сохранять и загружать список с несколькими записями")
  void shouldSaveAndLoadPopulatedList() throws IOException { // Переименовали тест
    List<HighScoreEntryClass> expected = List.of(
        new HighScoreEntryClass("Alice", 1200),
        new HighScoreEntryClass("Bob", 800),
        new HighScoreEntryClass("Charlie", 500)
    );

    HighScoreManager.saveScores(expected, testFilePath.toString());
    List<HighScoreEntryClass> loaded = HighScoreManager.loadScores(testFilePath.toString());

    assertThat(loaded).hasSize(expected.size());
    for (int i = 0; i < expected.size(); i++) {
      assertThat(loaded.get(i).getPlayerName()).isEqualTo(expected.get(i).getPlayerName());
      assertThat(loaded.get(i).getScore()).isEqualTo(expected.get(i).getScore());
    }
  }

  @Test
  @DisplayName("Должен возвращать пустой список при загрузке несуществующего файла")
  void shouldReturnEmptyListForNonExistentFile() {
    List<HighScoreEntryClass> loaded = HighScoreManager.loadScores(testFilePath.toString());
    assertThat(loaded).isNotNull().isEmpty();
  }
}