package ru.mentee.power.io;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

@DisplayName("Тесты копирования файла (FileCopyDemo)")
class FileCopyDemoTest {

  @TempDir
  Path tempDir;
  Path distinationPath;
  Path sourcePath;
  private static final String SOURCE_FILE_NAME = "source_test.txt";
  private static final String DEST_FILE_NAME = "dest_test.txt";
  String sourceContent = "Строка 1 для копирования.\\nLine 2 with English.\\nИ русские буквы.";

  @BeforeEach
  void setUp() throws IOException {
    sourcePath = tempDir.resolve(SOURCE_FILE_NAME);
    distinationPath = tempDir.resolve(DEST_FILE_NAME);
    Files.writeString(sourcePath, sourceContent, StandardCharsets.UTF_8);
    assertThat(sourcePath).exists();
  }

  private void performCopy(Path source, Path dest) throws IOException {
    try (FileInputStream fis = new FileInputStream(source.toFile());
        FileOutputStream fos = new FileOutputStream(dest.toFile())) {
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, bytesRead);
      }
    }
  }

  @Test
  @DisplayName("Должен успешно копировать непустой файл")
  void shouldCopyNonEmptyFile() throws IOException {

    assertThat(distinationPath).doesNotExist();

    performCopy(sourcePath, distinationPath);

    assertThat(distinationPath).exists();
    String text = Files.readString(distinationPath, StandardCharsets.UTF_8);
    assertThat(text).isEqualTo(sourceContent);

  }

  @Test
  @DisplayName("Должен успешно копировать пустой файл")
  void shouldCopyEmptyFile() throws IOException {

    Path emptySource = tempDir.resolve("empty_source.txt");
    Files.deleteIfExists(emptySource);
    Files.createFile(emptySource);
    assertThat(emptySource).isEmptyFile();
    Path emptyDest = tempDir.resolve("empty_dest.txt");
    assertThat(emptyDest).doesNotExist();

    performCopy(emptySource, emptyDest);

    assertThat(emptyDest).exists().isEmptyFile();

  }

  @Test
  @DisplayName("Должен перезаписать существующий целевой файл")
  void shouldOverwriteExistingDestinationFile() throws IOException {

    String initialDestContent = "Этот текст будет перезаписан.";
    Files.writeString(distinationPath, initialDestContent, StandardCharsets.UTF_8);
    assertThat(distinationPath).hasContent(initialDestContent);

    performCopy(sourcePath, distinationPath);

    assertThat(distinationPath).exists().hasContent(sourceContent);

  }


  @Test
  @DisplayName("Должен выбросить IOException, если исходный файл не существует")
  void shouldThrowIOExceptionIfSourceDoesNotExist() {

    Path nonExistentSource = tempDir.resolve("non_existent.txt");
    assertThat(nonExistentSource).doesNotExist();

    Throwable thrown = catchThrowable(() -> performCopy(nonExistentSource, distinationPath));

    assertThat(thrown).isInstanceOf(IOException.class);
    assertThat(distinationPath).doesNotExist();
  }
}