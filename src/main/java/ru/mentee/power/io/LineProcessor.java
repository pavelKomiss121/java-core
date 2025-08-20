package ru.mentee.power.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class LineProcessor {

  public static void main(String[] args) {
    String inputFileName = args.length > 0 ? args[0] : "input_test.txt";
    String outputFileName = args.length > 1 ? args[1] : "output_test.txt";

    Path inputPath = Paths.get(inputFileName);

    // TODO: Проверить и создать входной файл, если он не существует
    try {
      if (Files.notExists(inputPath)) {
        List<String> defaultLines = List.of("Первая строка.", "Second Line with MixEd Case",
            "третья");
        Files.write(inputPath, defaultLines, StandardCharsets.UTF_8);
        System.out.println("Создан файл по умолчанию: " + inputFileName);
      }
    } catch (IOException e) {
      System.err.println("Ошибка при создании файла по умолчанию: " + e.getMessage());
      return;
    }

    // Используем try-with-resources для открытия:
    // FileReader -> BufferedReader для чтения
    // FileWriter -> BufferedWriter для записи
    // Указываем кодировку UTF-8 для надежности
    try (Reader reader = new FileReader(inputFileName, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        Writer writer = new FileWriter(outputFileName, StandardCharsets.UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
      String line;
      System.out.println("Обработка файла " + inputFileName + "...");

      // TODO: Написать цикл while, который читает строки с помощью bufferedReader.readLine()
      while ((line = bufferedReader.readLine()) != null) {

        String processedLine = line.toUpperCase();
        bufferedWriter.write(processedLine);
        bufferedWriter.newLine();
      }

      System.out.println("Файл успешно обработан. Результат в " + outputFileName);

    } catch (IOException e) {
      System.err.println("Ошибка при обработке файла: " + e.getMessage());
      // e.printStackTrace();
    }
  }
}