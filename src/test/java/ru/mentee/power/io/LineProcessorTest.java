package ru.mentee.power.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Тесты обработчика строк (LineProcessor)")
class LineProcessorTest {

  @TempDir
  Path tempDir;

  private Path inputFile;
  private Path outputFile;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  void setUp() {
    inputFile = tempDir.resolve("input_test.txt");
    outputFile = tempDir.resolve("output_test.txt");
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  // Вспомогательный метод для запуска основной логики (для тестирования)
  private void runLineProcessor(String inputFileName, String outputFileName) {
    // В тестах мы можем передать пути к временным файлам
    // или же модифицировать main так, чтобы он принимал пути как параметры.
    // Здесь для простоты предположим, что LineProcessor.main использует
    // фиксированные имена, но мы создадим файлы с этими именами во временной папке.
    Path tempInput = tempDir.resolve(inputFileName);
    Path tempOutput = tempDir.resolve(outputFileName);

    // Копируем наш тестовый входной файл во временную папку с нужным именем
    try {
      if (Files.exists(inputFile)) { // Если тестовый входной файл был создан
        Files.copy(inputFile, tempInput);
      }
    } catch (IOException e) {
      throw new RuntimeException("Ошибка копирования тестового файла", e);
    }

    // Запускаем main, который будет работать с файлами в tempDir
    LineProcessor.main(new String[]{inputFile.toString(), outputFile.toString()});

    // Копируем результат из временной папки в наш outputFile для проверки
    try {
      if (Files.exists(tempOutput)) {
        Files.copy(tempOutput, outputFile);
      }
    } catch (IOException e) {
      throw new RuntimeException("Ошибка копирования тестового результата", e);
    }
  }


  @Test
  @DisplayName("Должен корректно обработать файл с несколькими строками разного регистра")
  void shouldProcessFileWithMixedCaseLines() throws IOException {

    List<String> inputLines = List.of("hello", "WORLD", "JaVa");
    Files.write(inputFile, inputLines, StandardCharsets.UTF_8);

    runLineProcessor("input_test.txt", "output_test.txt");

    assertThat(outputFile).exists();
    List<String> outputLines = Files.readAllLines(outputFile, StandardCharsets.UTF_8);
    assertThat(outputLines).containsExactly("HELLO", "WORLD", "JAVA");
    assertThat(errContent.toString()).isEmpty();
  }

  @Test
  @DisplayName("Должен корректно обработать пустой входной файл")
  void shouldProcessEmptyInputFile() throws IOException {
    Files.createFile(inputFile);

    runLineProcessor("input_test.txt", "output_test.txt");

    assertThat(outputFile).exists()
        .isEmptyFile();
    assertThat(errContent.toString()).isEmpty();
  }

  @Test
  @DisplayName("Должен перезаписать существующий выходной файл")
  void shouldOverwriteExistingOutputFile() throws IOException {
    List<String> inputLines = List.of("one", "two");
    Files.write(inputFile, inputLines, StandardCharsets.UTF_8);

    Files.writeString(outputFile, "OLD DATA", StandardCharsets.UTF_8);

    runLineProcessor("input_test.txt", "output_test.txt");

    List<String> outputLines = Files.readAllLines(outputFile, StandardCharsets.UTF_8);
    assertThat(outputLines).containsExactly("ONE", "TWO");
    assertThat(errContent.toString()).isEmpty();
  }

  @Test
  @DisplayName("Должен создать входной файл по умолчанию, если он не существует")
  void shouldCreateDefaultInputFileIfNotExists() throws IOException {
    assertThat(inputFile).doesNotExist();

    runLineProcessor("input_test.txt", "output_test.txt");

    assertThat(outputFile).exists();
    List<String> outputLines = Files.readAllLines(outputFile, StandardCharsets.UTF_8);
    assertThat(outputLines).isNotEmpty();
    assertThat(outContent.toString()).contains("Создан файл по умолчанию");
    assertThat(errContent.toString()).isEmpty();
  }
}