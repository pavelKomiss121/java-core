package ru.mentee.power.io;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Тесты корректности копирования (PerformanceComparison)")
class PerformanceComparisonCorrectnessTest {

  @TempDir
  Path tempDir;

  private Path sourceFile;
  private Path destBufferedFile;
  private Path destUnbufferedFile;

  @BeforeEach
  void setUp(@TempDir Path tempDir) throws IOException {
    sourceFile = tempDir.resolve("source.bin");
    destBufferedFile = tempDir.resolve("dest_buffered.bin");
    destUnbufferedFile = tempDir.resolve("dest_unbuffered.bin");

    // Создадим небольшой тестовый файл
    byte[] testData = new byte[1024 * 5]; // 5 KB
    for (int i = 0; i < testData.length; i++) {
      testData[i] = (byte) i;
    }
    Files.write(sourceFile, testData);
  }

  @Test
  @DisplayName("copyBuffered должен корректно копировать файл")
  void copyBufferedShouldCopyFileCorrectly() throws IOException {
    PerformanceComparison.copyBuffered(sourceFile.toString(), destBufferedFile.toString());
    assertThat(destBufferedFile).exists();
    assertThat(Files.mismatch(sourceFile, destBufferedFile)).isEqualTo(-1L);
  }

  @Test
  @DisplayName("copyUnbuffered должен корректно копировать файл")
  void copyUnbufferedShouldCopyFileCorrectly() throws IOException {
    PerformanceComparison.copyUnbuffered(sourceFile.toString(), destUnbufferedFile.toString());
    assertThat(destUnbufferedFile).exists();
    assertThat(Files.mismatch(sourceFile, destUnbufferedFile)).isEqualTo(-1L);
  }

  @Test
  @DisplayName("createLargeBinaryFile должен создавать файл нужного размера")
  void createLargeBinaryFileShouldCreateFileOfCorrectSize() throws IOException {
    Path largeFile = tempDir.resolve("test_large.bin");
    long expectedSize = 1024 * 10; // 10 KB для теста
    PerformanceComparison.createLargeBinaryFile(largeFile.toString(), expectedSize);
    assertThat(largeFile).exists();
    assertThat(Files.size(largeFile)).isEqualTo(expectedSize);
  }
}
