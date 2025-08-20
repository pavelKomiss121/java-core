package ru.mentee.power.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCopyDemo {

  public static void main(String[] args) {
    String sourceFileName = "source.txt";
    String destinationFileName = "copy_of_source.txt";
    Path sourcePath = Paths.get(sourceFileName);
    Path destinationPath = Paths.get(destinationFileName);

    if (!Files.exists(sourcePath)) {
      try {
        Files.writeString(
            sourcePath,
            "Это содержимое файла по умолчанию.\nСтрока 2.",
            StandardCharsets.UTF_8
        );
        System.out.println("Создан файл по умолчанию: " + sourceFileName);
        System.out.println("Расположение: " + sourcePath);
      } catch (IOException e) {
        System.err.println("Не удалось создать исходный файл: " + e.getMessage());
        return;
      }
    }

    int bufferSize = 4096; // Размер буфера (например, 4KB)
    byte[] buffer = new byte[bufferSize];

    try (FileInputStream fis = new FileInputStream(sourcePath.toFile());
        FileOutputStream fos = new FileOutputStream(destinationPath.toFile())) {

      int bytesRead;
      while ((bytesRead = fis.read(buffer)) != -1) {
        fos.write(buffer, 0, bytesRead);
      }
      System.out.println(
          "Файл успешно скопирован из " + sourceFileName + " в " + destinationFileName);

    } catch (IOException e) {
      System.err.println("Ошибка при копировании файла: " + e.getMessage());
    }
  }
}